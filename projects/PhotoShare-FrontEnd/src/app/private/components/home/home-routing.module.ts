import { HomeComponent } from './home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'results',
        loadChildren: () => import('../results/results.module').then(m => m.ResultsModule)
      },
      {
        path: 'profile',
        loadChildren: () => import('../profile/profile.module').then(m => m.ProfileModule)
      },
      {
        path: 'create-profile',
        outlet: 'create',
        loadChildren: () => import('../create-profile/create-profile.module').then(m => m.CreateProfileModule)
      },
      {
        path: 'post',
        outlet: 'post',
        loadChildren: () => import('../post/post.module').then(m => m.PostModule)
      },
      {
        path: '',
        loadChildren: () => import('../feed/feed.module').then(m => m.FeedModule)
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
