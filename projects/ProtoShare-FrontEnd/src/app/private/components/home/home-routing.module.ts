import { HomeComponent } from './home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      {
        path: 'create-post',
        loadChildren: () => import('../create-post/create-post.module').then(m => m.CreatePostModule)
      },
      {
        path: 'search',
        loadChildren: () => import('../search-result/search-result.module').then(m => m.SearchResultModule)
      },
      {
        path: 'profiles/:profileId',
        loadChildren: () => import('../profile-display/profile-display.module').then(m => m.ProfileDisplayModule)
      },
      {
        // path: 'feed',
        path: '',
        loadChildren: () => import('../feed/feed.module').then(m => m.FeedModule)
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
