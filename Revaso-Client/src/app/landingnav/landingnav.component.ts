import { Component, OnInit } from '@angular/core';
import { AuthService } from '../profile/auth.service';

@Component({
  selector: 'app-landingnav',
  templateUrl: './landingnav.component.html',
  styleUrls: ['./landingnav.component.css']
})
export class LandingnavComponent implements OnInit {
  loggedIn: boolean;
  constructor(private auth: AuthService) { }

  ngOnInit() {
    this.auth.loggingIn.subscribe((loggedIn: boolean)=>{
      this.loggedIn = loggedIn;
    })
  }

}
