import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ProfilesService } from '../profile/profiles.service';
import { AuthService } from '../profile/auth.service';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {
  @ViewChild('editForm', {static:true}) editForm;
  profile: any;
  newProfile: any = {};


  // editForm: FormGroup;
  genders = ['male','female'];
  constructor(private auth: AuthService, private proServ: ProfilesService,
) { }

  ngOnInit() {
    this.profile = this.auth.user; 
    this.newProfile.userId = this.profile.userId;
    this.newProfile.userPassword=this.profile.userPassword;
    this.newProfile.gender = this.profile.gender;
    this.newProfile.dob = this.profile.dob;

   }
  

  editProfile(obj){
    this.proServ.editProfile(obj).subscribe((res)=>{
      this.editForm.resetForm();
      this.auth.user=res; 
      this.profile=res;//reset the value of logged in user.
    }, (error)=>{
      console.log(error.status);
    })
  }

  
}
