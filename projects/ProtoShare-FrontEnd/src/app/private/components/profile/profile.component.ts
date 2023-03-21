import { UserService } from './../../../core/services/user/user.service';
import { Post } from './../../../shared/models/post';
import { ProfileService } from './../../services/profile/profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from './../../../shared/models/profile';
import { Observable, tap } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profileId!: string;
  profile$!: Observable<Profile>;
  isOwner!: boolean;

  constructor(
    private activatedRoute: ActivatedRoute,
    private profileService: ProfileService,
    private userService: UserService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      this.profileId = paramMap.get('profileId')!;
      this.loadProfile();
      this.profile$.subscribe((profile: Profile) => {
        this.isOwner = this.userService.getUser!.id === profile.ownerId!;
      })
    })
  }

  loadProfile(): void {
    this.profile$ = this.profileService.getProfileById(this.profileId).pipe(
      tap((profile: Profile) => {
        profile.posts?.forEach((post: Post) => {
          post.profile = profile
        })
      })
    );
  }

  addPost(): void {
    this.router.navigate(['add-post'], { relativeTo: this.activatedRoute });
  }

}
