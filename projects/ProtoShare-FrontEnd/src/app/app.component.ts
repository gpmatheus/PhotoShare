import { User } from './shared/models/user';
import { AuthService } from './core/services/auth/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ProtoShare-FrontEnd';

  constructor(authService: AuthService) {
    authService.login('defaultUser', 'password123').subscribe((user: User) => console.log(user));
  }
}
