import { Post } from './../../../../../shared/models/post';
import { PostService } from './../../../../services/post/post.service';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {

  postForm = new FormGroup({
    description: new FormControl('', Validators.required),
    image: new FormControl(new Blob(), Validators.required),
  })
  postImage: string | null = null;
  profileId!: string;
  open = true;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private postService: PostService) { }

  ngOnInit(): void {
    this.activatedRoute.parent!.paramMap.subscribe(paramMap => {
      this.profileId = paramMap.get('profileId')!;
    })
  }

  createPost(): void {
    if (this.postForm.valid) {
      const postObj = {
        description: this.postForm.value.description!,
        image: this.postForm.value.image!,
        profileId: this.profileId
      }
      this.postService.createPost(postObj).subscribe((post: Post) => {
        this.open = false;
        this.router.navigate(['../'], {relativeTo: this.activatedRoute})
      })
    }
  }

  cancelCreation(): void {
    this.open = false;
    this.router.navigate(['../'], { relativeTo: this.activatedRoute })
  }

}
