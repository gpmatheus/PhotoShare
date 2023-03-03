import { environment } from './../../../../environments/environment';
import { Profile } from './../../../shared/models/profile';
import { Page } from './../../../shared/models/utils/page';
import { Observable, tap } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  searchProfiles(search: string, pageSize: number, pageIndex: number): Observable<Page<Profile>> {
    let params = new HttpParams()
      .append('search', search)
      .append('pageSize', pageSize)
      .append('pageIndex', pageIndex);
    return this.http.get<Page<Profile>>(`${environment.apiUrl}/profiles/search`, {params}).pipe(
      tap(console.log)
    )
  }

  getProfileById(profileId: string): Observable<Profile> {
    return this.http.get<Profile>(`${environment.apiUrl}/profiles/${profileId}`).pipe(
      tap(console.log)
    )
  }
}
