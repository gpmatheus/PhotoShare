import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  postForm = new FormGroup({
    description: new FormControl('', [Validators.required]),
    image: new FormControl(new Blob(), [Validators.required]),
    profileId: new FormControl()
  })
  postImage: string | null = null;

  constructor() { }

  ngOnInit(): void {
  }


  handlePhoto() {
    let reader = new FileReader();
    reader.onload = e => {
      this.postImage = reader.result as string
    };
    reader.readAsDataURL(this.postForm.value.image!);
  }

}
