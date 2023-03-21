import { environment } from './../../../../environments/environment';
import { Observable, tap } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Page } from 'src/app/shared/models/utils/page';
import { Post } from 'src/app/shared/models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) {
  }

  feedWithPosts(pageSize: number, pageIndex: number): Observable<Page<Post>> {
    let params = new HttpParams()
      .append('pageSize', pageSize)
      .append('pageIndex', pageIndex)
    return this.http.get<Page<Post>>(`${environment.apiUrl}/posts`, {params}).pipe(
      tap(console.log)
    )
  }

  createPost(post: {description: string, image: Blob, profileId: string}): Observable<Post> {
    const postFormData = new FormData();
    postFormData.append("description", post.description);
    postFormData.append("image", post.image);
    postFormData.append("profileId", post.profileId);
    return this.http.post<Post>(`${environment.apiUrl}/posts`, postFormData)
  }

  getPostById(postId: string): Observable<Post> {
    return this.http.get<Post>(`${environment.apiUrl}/posts/${postId}`).pipe(
      tap(console.log)
    )
  }

  commentPost(comment: string, postId: string): Observable<Post> {
    return this.http.put<Post>(`${environment.apiUrl}/posts/${postId}/comment`, comment).pipe(
      tap(console.log)
    )
  }

  editDescription(newDescription: string, postId: string): Observable<Post> {
    return this.http.put<Post>(`${environment.apiUrl}/posts/${postId}/changeDescription`, newDescription).pipe(
      tap(console.log)
    )
  }
}
