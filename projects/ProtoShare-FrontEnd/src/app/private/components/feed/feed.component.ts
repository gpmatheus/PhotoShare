import { Router } from '@angular/router';
import { Page } from './../../../shared/models/utils/page';
import { PostService } from './../../services/post/post.service';
import { BehaviorSubject, map, reduce, tap } from 'rxjs';
import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { Post } from 'src/app/shared/models/post';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit, OnDestroy {

  posts$ = new BehaviorSubject<Post[]>([]);
  index$ = new BehaviorSubject<number>(0);
  maxIndex!: number;

  constructor(private postService: PostService, private router: Router) { }

  ngOnInit(): void {
    this.index$.subscribe(index => {
      this.loadPosts(index);
    })
  }

  ngOnDestroy(): void {
    this.posts$.unsubscribe();
    this.index$.unsubscribe();
  }

  @HostListener("window:scroll", ["$event"])
  onWindowScroll() {
    let pos = (document.documentElement.scrollTop || document.body.scrollTop) + window.innerHeight;
    let max = document.documentElement.scrollHeight;
    if (pos == max && this.index$.value < this.maxIndex) {
      console.log('searching for more posts...');
      this.index$.next(this.index$.value + 1);
    }
}

  private loadPosts(index: number): void {
    this.postService.feedWithPosts(15, index).pipe(
      tap((value: Page<Post>) => {
        this.maxIndex = value.totalPages - 1;
      }),
      map((page: Page<Post>): Post[] => page.content),
      reduce((acc: Post[], value: Post[]) => acc.concat(value))
    ).subscribe((value: Post[]) => {
      this.posts$.next(this.posts$.value.concat(value));
    })
  }

}
