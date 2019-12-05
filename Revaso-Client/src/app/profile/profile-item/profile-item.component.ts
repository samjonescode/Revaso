import { Component, OnInit, Input } from '@angular/core';
import { Profile } from 'selenium-webdriver/firefox';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { SelectService } from 'src/app/select.service';
import { S3Service } from 'src/app/s3.service';

@Component({
  selector: 'app-profile-item',
  templateUrl: './profile-item.component.html',
  styleUrls: ['./profile-item.component.css']
})
export class ProfileItemComponent implements OnInit {
  @Input() profile: any;
  image: any;
 
  constructor(private router: Router,
               private selectServ: SelectService,
               private s3Serv: S3Service) { }

  //redirects the user to the profile list component/profile-page component.
  //transmits the id of the selected user to make it available in the url params.
  navigateById(id: number){
    this.selectServ.foundUser = id;
    // this.router.navigate(['/profiles', id])
    this.router.navigate(['/profilepage'])
  }
  ngOnInit() {
    this.s3Serv.getImage(this.profile.userName).subscribe((data)=>{
      console.log(data)
    }, (error)=>{
      this.image = error.error.text;
      console.log(this.image)
      console.log(error)
    })
  }

}
