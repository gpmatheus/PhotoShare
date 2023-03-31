import { AddPostComponent } from './components/add-post/add-post.component';
import { ProfileComponent } from './profile.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: ':profileId',
    component: ProfileComponent,
    children: [
      {
        path: 'add-post',
        component: AddPostComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
