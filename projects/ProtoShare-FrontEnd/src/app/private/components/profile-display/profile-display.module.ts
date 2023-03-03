import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileDisplayRoutingModule } from './profile-display-routing.module';
import { ProfileDisplayComponent } from './profile-display.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    ProfileDisplayComponent
  ],
  imports: [
    CommonModule,
    ProfileDisplayRoutingModule,
    SharedModule
  ]
})
export class ProfileDisplayModule { }
