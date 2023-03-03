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
}
