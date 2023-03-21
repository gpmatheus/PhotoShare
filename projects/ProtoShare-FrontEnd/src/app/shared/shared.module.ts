import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuIconComponent } from './components/menu-icon/menu-icon.component';
import { ImgJwtPipe } from './pipes/imgJwt/img-jwt.pipe';
import { PostCardComponent } from './components/post-card/post-card.component';
import { ModalComponent } from './components/modal/modal.component';
import { ImageInputComponent } from './components/image-input/image-input.component';
import { ProfileImageComponent } from './components/profile-image/profile-image.component';
import { CloseButtonComponent } from './components/close-button/close-button.component';
import { LogoComponent } from './components/logo/logo.component';
import { RouterModule } from '@angular/router';
import { ConfigIconComponent } from './components/config-icon/config-icon.component';


@NgModule({
  declarations: [
    MenuIconComponent,
    ImgJwtPipe,
    PostCardComponent,
    ModalComponent,
    ImageInputComponent,
    ProfileImageComponent,
    CloseButtonComponent,
    LogoComponent,
    ConfigIconComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    MenuIconComponent,
    ImgJwtPipe,
    PostCardComponent,
    ModalComponent,
    ImageInputComponent,
    ProfileImageComponent,
    CloseButtonComponent,
    LogoComponent,
    ConfigIconComponent
  ]
})
export class SharedModule { }
