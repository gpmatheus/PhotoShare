import { ProfileService } from '../../services/profile/profile.service';
import { Profile } from './../../../shared/models/profile';
import { Page } from './../../../shared/models/utils/page';
import { Observable, Subscription } from 'rxjs';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit, OnDestroy {

  profilesPage$!: Observable<Page<Profile>>;
  readonly pageSize: number = 9;
  search!: string;
  routeSubscription!: Subscription;

  constructor(private profileService: ProfileService,
    private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.routeSubscription = this.activatedRoute.queryParams.subscribe((params: Params) => {
      if (params['search']) {
        this.search = params['search'];
      }
      this.profilesPage$ = this.profileService.searchProfiles(
        this.search, this.pageSize, params['pageIndex'] ? +params['pageIndex'] : 0);
    });
  }

  ngOnDestroy(): void {
    if (this.routeSubscription) {
      this.routeSubscription.unsubscribe();
    }
  }

  changePageIndex(index: number): void {
    this.router.navigate(['../results'], {
      queryParams: {
        search: this.search,
        pageIndex: index
      },
      relativeTo: this.activatedRoute
    });
  }

  checkProfile(profileId: string): void {
    this.router.navigate(['profile', profileId])
  }

}
