import { Router } from '@angular/router';
import { Post } from './../../models/post';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})
export class PostCardComponent implements OnInit {

  @Input() post!: Post;
  @Input() showProfileImg: boolean = true;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  checkPost(): void {
    this.router.navigate(['/', { outlets: { post: ['post', this.post.id]}}])
  }

  checkProfile(): void {
    this.router.navigate(['profile', this.post.profile.id])
  }

}
