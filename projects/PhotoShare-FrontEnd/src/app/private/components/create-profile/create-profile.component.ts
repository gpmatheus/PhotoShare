import { Profile } from './../../../shared/models/profile';
import { ProfileService } from './../../services/profile/profile.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.css']
})
export class CreateProfileComponent implements OnInit {

  profileForm = new FormGroup({
    name: new FormControl('', Validators.required),
    about: new FormControl('', Validators.required),
    profileImage: new FormControl(new Blob(), Validators.required)
  });

  profileImage: string | null = null;
  open = true;

  constructor(private router: Router, private route: ActivatedRoute, private profileService: ProfileService) { }

  ngOnInit(): void {
  }

  createProfile(): void {
    if (this.profileForm.valid) {
      this.open = false;
      const profile = {
        name: this.profileForm.value.name!,
        about: this.profileForm.value.about!,
        profileImage: this.profileForm.value.profileImage!,
      }
      this.profileService.createProfile(profile).subscribe((pro: Profile) => {
        this.router.navigate([{ outlets: { create: null, primary: ['profile', pro.id] } }]);
      })
    }
  }

  cancelCreation(): void {
    this.open = false;
    this.router.navigate(['/', { outlets: {create: null } }], {
      replaceUrl: true,
      relativeTo: this.route.parent
    })
  }

}
