import { Component, OnInit } from '@angular/core';
import { ProfilesService } from '../profile/profiles.service';
import { FetchProfileService } from '../fetch-profile.service';
// import * as Gmail from 'gmail-send';


@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {
  username: any = "";
  constructor(private fetchServ: FetchProfileService, private proServ: ProfilesService) { }

  // send = Gmail({
  //   user: 'revaturesocial@gmail.com',
  //   pass: "socialappproject2",
  //   to:   'samuelriley1393@gmail.com',
  //   subject: 'test subject',
  // });

  
  ngOnInit() {
    // this.send({
    //   text:    'gmail-send example 1',  
    // }, (error, result, fullResult) => {
    //   if (error) console.error(error);
    //   console.log(result);
    // });
  }

  sendResetEmail(username){
    // this.proServ.
    console.log(username);
    this.fetchServ.fetchProfileByUserName(username).subscribe((profile)=>{
      this.proServ.resetPassword(profile).subscribe((data)=>{
        console.log(data)
      }, (error)=>{
        console.log("ruh roh")
        console.log(error)
      })
    })
  }

}
