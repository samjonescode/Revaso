import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Profile } from '../profile.model';
import { ProfilesService } from '../profiles.service';
import { PostService } from './post/post.service';
import { SelectService } from 'src/app/select.service';
import { FetchProfileService } from 'src/app/fetch-profile.service';
import { AuthService } from '../auth.service';
import { S3Service } from 'src/app/s3.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {
  posts: any;
 profile: any;
  profiles: any;
  username: string;
  testProfile: Profile;
  image;
   
  constructor(private route: ActivatedRoute, 
              private fetchServ: FetchProfileService,
              private postServ: PostService,
              private selectServ: SelectService,
              private s3Serv: S3Service
    ) {

   }

  ngOnInit() {
    //uses the select service to fetch the profile.
    this.profile = this.selectServ.foundUser;
    this.fetchServ.fetchProfileById(this.selectServ.foundUser).subscribe((user)=>{
      this.profile=user;

    this.s3Serv.getImage(this.profile.userName).subscribe((data)=>{
      console.log(data)
    },(error)=>{
      this.image = error.error.text;
    })

      
    })
    this.postServ.fetchPostsByUserId(this.selectServ.foundUser).subscribe((posts)=>{
      this.posts=posts;
    })

    // uses the route params to fetch the profile.
    // this.route.params
    // .subscribe(
    //   (params: Params) =>{
    //     this.fetchServ.fetchProfileById(params.id).subscribe((data)=>{
    //       this.profile=data;
    //       this.postServ.fetchPostsByUserId(this.profile.userId).subscribe((posts)=>{
    //         this.posts = posts;
    //       })
    //     }) 

    //   }
    // )
   
   
    
  }
 
}
