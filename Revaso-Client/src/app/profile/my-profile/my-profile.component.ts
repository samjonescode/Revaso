import { Component, OnInit } from '@angular/core';
import { PostService } from '../profile-page/post/post.service';
import { Post } from '../profile-page/post/post.model';
import { Profile } from 'selenium-webdriver/firefox';
import { ProfilesService } from '../profiles.service';
import { AuthService } from '../auth.service';
import { S3Service } from 'src/app/s3.service';
import { UploadFileService } from './upload-file.service';
import { HttpResponse } from '@angular/common/http';
@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  myPosts: Post[];
  myProfile: any;
  image: any;
    //from grokonozen
    selectedFiles: FileList;
    currentFileUpload: File;

  constructor(private postServ: PostService, private profileServ: ProfilesService,
    private auth: AuthService, private s3Serv: S3Service
    , private uploadService: UploadFileService) { }

  ngOnInit() {
    this.s3Serv.getImage(this.auth.user.userName).subscribe((data)=>{
      // this.image
      console.log(data)
    }, (error)=>{
      this.image=error.error.text;
      console.log(error);
    })
  
    this.myProfile=this.auth.user
    console.log(this.myProfile.userId)
    this.postServ.fetchPostsByUserId(this.myProfile.userId).subscribe((response)=>{
      this.myPosts = response;

    })
  }

   //from grokonozen
  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  upload() {

    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    });

    this.selectedFiles = undefined;
  }




}
