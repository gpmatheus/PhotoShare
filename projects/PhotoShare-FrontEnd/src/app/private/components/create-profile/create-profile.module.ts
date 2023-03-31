import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateProfileRoutingModule } from './create-profile-routing.module';
import { CreateProfileComponent } from './create-profile.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CreateProfileComponent
  ],
  imports: [
    CommonModule,
    CreateProfileRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class CreateProfileModule { }
