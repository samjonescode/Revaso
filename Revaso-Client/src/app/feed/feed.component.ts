import { Component, OnInit, OnDestroy } from '@angular/core';
import { Post } from '../profile/profile-page/post/post.model';
import { PostService } from '../profile/profile-page/post/post.service';
import { Subscription } from 'rxjs';
import { ProfilesService } from '../profile/profiles.service';
import { Profile } from '../profile/profile.model';
import { AuthService } from '../profile/auth.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  // post: Post = new Post(1,1,"First Post!","First posts body", 4, [1]);
  posts: Post[];
  testDataAll: any;
  postSubsc: Subscription;
  loggedInUser: any;
  constructor(private postService: PostService, private profileServ: ProfilesService, 
    private auth: AuthService) { }

  ngOnInit() {
    //subscribe to fetchAllPosts in order to fetch all posts for the feed
    this.postSubsc = this.postService.fetchAllPosts().subscribe((data)=>{
      this.posts = data;
    });

  

  
  }
}
