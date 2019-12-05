import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Post } from '../post.model';
import { PostService } from '../post.service';
import { FormGroup, NgForm, FormControl, Validators } from '@angular/forms';
import MediumEditor from 'medium-editor';
import { AuthService } from 'src/app/profile/auth.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {
  @ViewChild('editable',{static:true}) editable: ElementRef;
  // @ViewChild('newPostForm', {static: true}) newPostForm: NgForm;
  post: Post;
  newPostForm: FormGroup;
  MediumEditor: any;
  myProfile: any;
  filename: string;
  constructor(private postServ: PostService, private auth: AuthService) { }
  
  ngOnInit() {
    this.myProfile=this.auth.user;
    this.newPostForm = new FormGroup({
      'userId': new FormControl(this.myProfile.userId),
      'postTitle': new FormControl(null, [Validators.required, Validators.minLength(5)]),
      'postBody': new FormControl(null, Validators.required)
    });
    }

  onSubmit(){
    this.postServ.insertPost(this.newPostForm.value).subscribe((data)=>{
      console.log("Inserted post" + JSON.stringify(data))
    }, (error)=>{
      console.log("Ruh roh " + JSON.stringify(error));
    })
    this.newPostForm.reset();
  }

  
  async uploadFile(event: any) {
    const file = event.target.files[0];
    this.filename = file.name;
    const urlResponse = await fetch('http://localhost:9005/SpringMVCPractice/s3/' + file.name, { method: 'PUT' });
    const signedUrl = await urlResponse.text();
    const s3Response = await fetch(signedUrl, { method: 'PUT', body: file });
  }
}
