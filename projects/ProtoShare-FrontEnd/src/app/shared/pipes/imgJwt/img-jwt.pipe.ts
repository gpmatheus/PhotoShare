import { UserService } from './../../../core/services/user/user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imgJwt'
})
export class ImgJwtPipe implements PipeTransform {

  constructor(private http: HttpClient, private userService: UserService) {}

  async transform(src: string): Promise<string> {
    const headers: HttpHeaders = new HttpHeaders({'Authorization': `Bearer ${this.userService.getUser!.token}`});
    const imageBlob = await this.http.get(src, {headers, responseType: 'blob'}).toPromise();
    const reader = new FileReader();
    return new Promise((resolve, reject) => {
      reader.onloadend = () => resolve(reader.result as string)
      reader.readAsDataURL(imageBlob!);
    })
  }

}
