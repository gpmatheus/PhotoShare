import { User } from './../../../shared/models/user';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly localStoragePropertyName = 'currentUser';
  private user: User | null = null;

  constructor() {
    if (localStorage[this.localStoragePropertyName]) {
      this.user = JSON.parse(localStorage[this.localStoragePropertyName])
    }
  }

  public get getUser(): User | null {
    return this.user;
  }

  removeUser(): void {
    localStorage.removeItem(this.localStoragePropertyName)
    this.user = null;
  }

  setUserLogged(user: User): void {
    localStorage.setItem(this.localStoragePropertyName, JSON.stringify(user))
    this.user = user;
  }
}
