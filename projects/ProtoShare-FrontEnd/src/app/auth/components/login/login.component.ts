import { User } from './../../../shared/models/user';
import { Router } from '@angular/router';
import { tap, Subscription, catchError, ObservableInput } from 'rxjs';
import { AuthService } from './../../../core/services/auth/auth.service';
import { NgForm } from '@angular/forms';
import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm): void {
    this.authService.login(form.value.username, form.value.password).pipe(
    ).subscribe((value: User) => this.router.navigate(['/']))
  }

}
