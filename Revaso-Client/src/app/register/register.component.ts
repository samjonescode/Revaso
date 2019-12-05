import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { Profile } from '../profile/profile.model';
import { ProfilesService } from '../profile/profiles.service';
import { CryptoService } from '../crypto.service';
import { S3Service } from '../s3.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  genders: string[] = ['male','female'];
  filename: string;

  constructor(private profileServ: ProfilesService, private crypter: CryptoService,
   private S3Serv: S3Service) { }

  ngOnInit() {
    this.registerForm = new FormGroup({
      'userName': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'userPassword': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'fName': new FormControl(null, Validators.required),
      'lName': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      // 'profileImage': new FormControl(null),
      // 'imagePath': new FormControl(null),
      'city': new FormControl(null, Validators.required),
      'gender': new FormControl('male', Validators.required),
      // 'hobbies': new FormArray([])
    });
    console.log(this.registerForm);
  }

  onSubmit(){
    console.log(this.registerForm.value.profileImage)
      // this.S3Serv.putImage(this.registerForm.value.profileImage, this.registerForm.value.imagePath).subscribe((image)=>{
      //   console.log(image)
      // }, (error)=>{
      //   console.log(error);
      // })  
      //hash the password before registering user.
      this.registerForm.value.userPassword = this.crypter.hash(this.registerForm.value.userPassword
        );
    this.profileServ.addProfile(this.registerForm.value).subscribe((response)=>{
      console.log("REsponse from Add profile: " + response)
    }, (error)=>{
      console.log("Error, ruh roh! " + JSON.stringify(error));
    })
   
    this.registerForm.reset();
  }

  onAddHobby(){
    const control = new FormControl(null, Validators.required);
    (<FormArray>this.registerForm.get('hobbies')).push(control);
  }


  async uploadFile(event: any) {
    const file = event.target.files[0];
    this.filename = file.name;
    const urlResponse = await fetch('http://localhost:9005/SpringMVCPractice/s3/' + file.name, { method: 'PUT' });
    const signedUrl = await urlResponse.text();
    const s3Response = await fetch(signedUrl, { method: 'PUT', body: file });
  }
}
