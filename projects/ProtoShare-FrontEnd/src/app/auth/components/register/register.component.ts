import { Router } from '@angular/router';
import { AuthService } from './../../../core/services/auth/auth.service';
import { User } from './../../../shared/models/user';
import { BehaviorSubject, Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    username: new FormControl('', [Validators.required, Validators.minLength(3)]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)])
  })
  equalPassword: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  submit(): void {
    if (this.registerForm.valid && this.equalPassword) {
      this.authService.register(
        this.registerForm.value.username!,
        this.registerForm.value.password!,
        this.registerForm.value.email!
      ).subscribe((user: User) => {
        this.router.navigate(['/'])
      });
    }
  }

  passwordChange(value: string): void {
    this.equalPassword = this.registerForm.value.password == value;
  }

}
