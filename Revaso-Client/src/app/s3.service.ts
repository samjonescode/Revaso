import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class S3Service {
  downloadImage: any;
  // uploadImage: any;
  // filename: string;
  constructor(private http: HttpClient) { }

  putImage(imageName:string, payload){
    return this.http.put('http://localhost:9007/SpringMVCPractice/s3/' +imageName,{}).pipe(res => res as any)
  }
  getImage(downloadImage: any){
    return this.http.get('http://localhost:9007/SpringMVCPractice/s3/' + downloadImage).pipe(res => res as any)


  }
}
