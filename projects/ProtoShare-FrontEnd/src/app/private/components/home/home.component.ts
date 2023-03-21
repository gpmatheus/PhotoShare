import { ProfileService } from './../../services/profile/profile.service';
import { UserService } from './../../../core/services/user/user.service';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/shared/models/profile';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userProfiles$!: Observable<Profile[]>;
  closedSidebar: boolean = true;

  constructor(private router: Router, private userService: UserService, private profileService: ProfileService) { }

  ngOnInit(): void {
    this.userProfiles$ = this.profileService.getUserProfiles(this.userService.getUser!.id);
  }

  toggleSidebar(): void {
    this.closedSidebar = !this.closedSidebar;
  }

  search(text: string): void {
    if (text && text.trim() !== '') {
      console.log(text);
      this.router.navigate(["results"], {
        queryParams: { search: text }
      })
    }
  }
}
