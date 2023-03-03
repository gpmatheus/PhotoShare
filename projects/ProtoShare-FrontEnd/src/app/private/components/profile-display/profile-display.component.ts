import { ProfileService } from './../../services/profile/profile.service';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Profile } from './../../../shared/models/profile';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-profile-display',
  templateUrl: './profile-display.component.html',
  styleUrls: ['./profile-display.component.css']
})
export class ProfileDisplayComponent implements OnInit {

  profile!: Observable<Profile>;

  constructor(private activatedRoute: ActivatedRoute, private profileService: ProfileService) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(paramMap => {
      let profileId = paramMap.get('profileId');
      this.profile = this.profileService.getProfileById(profileId!);
      this.profile.subscribe(pro => {
        console.log(pro);
      })
    })
  }

  addPost(): void {

  }

}
