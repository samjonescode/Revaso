import { Component, OnInit } from '@angular/core';
import { AuthService } from './profile/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'P2-initial';
  loggedInUser: any;
  constructor(private auth: AuthService){
  }

  ngOnInit(){
    this.auth.loggedInUser.subscribe((loggedInUser)=>{
      this.loggedInUser=loggedInUser;
    })
  }
 
}
