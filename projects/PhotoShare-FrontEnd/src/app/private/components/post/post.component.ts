import { Description } from './../../../shared/models/description';
import { UserService } from './../../../core/services/user/user.service';
import { Comment } from './../../../shared/models/comment';
import { PostService } from './../../services/post/post.service';
import { Observable, BehaviorSubject, map, tap } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from 'src/app/shared/models/post';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  postId!: string;
  post$!: BehaviorSubject<Post>;
  open = true;
  isMine!: boolean;
  editing: boolean = false;

  constructor(private route: ActivatedRoute,
    private postService: PostService,
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(paramMap => {
      this.postId = paramMap.get('postId')!;
      this.postService.getPostById(this.postId).subscribe((post: Post) => {
        this.isMine = post.profile.ownerId == this.userService.getUser!.id
        this.post$ = new BehaviorSubject(post);
      });
    })
  }

  closeModal(): void {
    this.open = false;
    this.router.navigate(['/', { relativeTo: this.route.parent, outlets: { post: null } }])
  }

  comment(commentValue: string): void {
    this.postService.commentPost(commentValue, this.postId).pipe(
      map((post: Post): Comment[] => post.comments!)
    ).subscribe((comments: Comment[]) => {
      this.post$.value.comments = comments;
    })
  }

  editDescription(newDescription: string): void {
    this.postService.editDescription(newDescription, this.postId).pipe(
      map((post: Post): Description[] => post.descriptions!)
    ).subscribe((descriptions: Description[]) => {
      this.editing = false;
      this.post$.value.descriptions = descriptions;
    })
  }

}
