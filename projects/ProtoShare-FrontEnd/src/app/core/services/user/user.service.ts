import { Injectable } from '@angular/core';
import { User } from 'src/app/shared/models/user';

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
