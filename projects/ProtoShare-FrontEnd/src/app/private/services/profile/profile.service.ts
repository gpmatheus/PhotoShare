import { User } from './../../../shared/models/user';
import { environment } from './../../../../environments/environment';
import { Page } from './../../../shared/models/utils/page';
import { Profile } from './../../../shared/models/profile';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, map } from 'rxjs';

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

  createProfile(profile: {name: string, about: string, profileImage: Blob}): Observable<Profile> {
    const profileFormData = new FormData();
    profileFormData.append('name', profile.name);
    profileFormData.append('about', profile.about);
    profileFormData.append('profileImage', profile.profileImage);
    return this.http.post<Profile>(`${environment.apiUrl}/profiles`, profileFormData).pipe(
      tap(console.log)
    )
  }

  getUserProfiles(userId: string): Observable<Profile[]> {
    return this.http.get<User>(`${environment.apiUrl}/users/${userId}`).pipe(
      tap(console.log),
      map((user: User) => user.profiles!)
    );
  }
}
