import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LogoComponent } from './components/logo/logo.component';
import { ToolBarComponent } from './components/tool-bar/tool-bar.component';
import { RouterModule } from '@angular/router';
import { PostcardComponent } from './components/postcard/postcard.component';
import { ImgJwtPipe } from './pipes/imgJwt/img-jwt.pipe';
import { ModalComponent } from './components/modal/modal.component';
import { ImageInputComponent } from './components/image-input/image-input.component';


@NgModule({
  declarations: [
    LogoComponent,
    ToolBarComponent,
    PostcardComponent,
    ImgJwtPipe,
    ModalComponent,
    ImageInputComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ],
  exports: [
    FormsModule,
    LogoComponent,
    ToolBarComponent,
    PostcardComponent,
    ImgJwtPipe,
    ImageInputComponent
  ]
})
export class SharedModule { }
