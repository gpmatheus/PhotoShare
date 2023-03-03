import { Router } from '@angular/router';
import { UserService } from './../user/user.service';
import { User } from './../../../shared/models/user';
import { environment } from './../../../../environments/environment';
import { Observable, map, BehaviorSubject, tap, catchError, ObservableInput, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authenticated: BehaviorSubject<boolean> = new BehaviorSubject(false);
  private authenticatedObservable: Observable<boolean> | null;

  constructor(private http: HttpClient, private userService: UserService, router: Router) {
    this.authenticatedObservable = this.getAuthenticationObservable();
    this.authenticatedObservable?.subscribe((auth: boolean) => {
      this.authenticated.next(auth);
      router.navigate(['/']);
    });
  }

  public get isAuthenticated(): Observable<boolean> {
    return this.authenticated.asObservable();
  }

  private getAuthenticationObservable(): Observable<boolean> | null {
    if (this.userService.getUser) {
      let user = this.userService.getUser;
      return this.checkAuthentication(user!.username, user!.token)
    }
    return null;
  }

  checkAuthentication(username: string, token: string): Observable<boolean> {
    return this.http.get<{isValid:boolean}>
    (`${environment.apiUrl}/auth/isValid/${username}/${token}`).pipe(
      map((response: {isValid: boolean}) => response.isValid),
      tap(console.log)
    )
  }

  register(username: string, password: string, email: string): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/auth/register`, {username, password, email}, {
      withCredentials: true
    }).pipe(
      tap((user: User) => {
        this.userService.setUserLogged(user);
        this.authenticated.next(true);
        console.log(user);
      }),
      catchError((error): ObservableInput<any> => {
        this.authenticated.next(false);
        console.log(error);
        return of(error);
      })
    )
  }

  login(username: string, password: string): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/auth`, {username, password}, {
      withCredentials: true,
    }).pipe(
      map((loggedUser) => {
        this.userService.setUserLogged(loggedUser);
        this.authenticated.next(true);
        return loggedUser;
      }),
      tap(console.log),
      catchError((error): ObservableInput<any> => {
        this.authenticated.next(false);
        return of(error);
      })
    );
  }

  logout(): void {
    this.userService.removeUser();
    this.authenticated.next(false);
  }
}
