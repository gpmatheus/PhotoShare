import { AuthService } from './../../../core/services/auth/auth.service';
import { AuthComponent } from './../../../auth/auth.component';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  shown: boolean = false;
  openedSideBar: boolean = false;

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm): void {
    this.router.navigate(["search"], {
      queryParams: { search: form.value.search }
    })
  }


  logout(): void {
    this.authService.logout();
  }

}
