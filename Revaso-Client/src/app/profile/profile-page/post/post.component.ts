import { Component, OnInit, Input } from '@angular/core';
import { PostService } from './post.service';
import { AuthService } from '../../auth.service';
import { FetchProfileService } from 'src/app/fetch-profile.service';
import { Router } from '@angular/router';
import { SelectService } from 'src/app/select.service';
import { S3Service } from 'src/app/s3.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() post: any;
  isYourPost: boolean =false;
  postAuthor: string;
  comments: any[];
  image;
  hideComments: boolean =true;

  constructor(
    private fetchServ: FetchProfileService,
    private postServ: PostService, private authServ: AuthService,
    private selectServ: SelectService, private S3Serv: S3Service,
    private router: Router) { }

  ngOnInit() {
    if(this.post.userId == this.authServ.user.userId){
      this.isYourPost=true;
    }

    this.fetchServ.fetchProfileById(this.post.userId).subscribe((author)=>{
      this.postAuthor=author.userName;
    })

    this.S3Serv.getImage(this.post.postTitle).subscribe((data)=>{
      console.log(data)
    },(error)=>{
      this.image = error.error.text;
      console.log(this.image);
    })
  }

  toggleComments(){
    this.hideComments = !this.hideComments;
  }
  navigateById(){
    this.selectServ.foundUser = this.post.userId;
    this.router.navigate(['/profilepage'])
  }

  likePost(){
    console.log("like post method is empty! And there's no controller yet for it")
    if(this.post.userId == this.authServ.user.userId){
      console.log("this is your own post");
      return;
    }
    this.postServ.likePost(this.post.postId).subscribe((data)=>{
      console.log("Post liked:")
      console.log(data)
      this.post=data;
    }, (error)=>{
      console.log("ruhoh");
      console.log(error);

    })

  }
}
