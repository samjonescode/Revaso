import { Injectable } from '@angular/core';
import { Profile } from './profile.model';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, Subject } from 'rxjs';
import {map, catchError} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ProfilesService {
  private _Url = 'http://localhost:9007/SpringMVCPractice/';
  private _oneProfileUrl = 'http://localhost:8080/Project2/'
  private _loginUrl = 'http://localhost:8080/';
  profilesFound: Subject<Profile[]>;

  private httpOptions = {
    headers: new HttpHeaders({
      // 'Accept' : ['application/json'],
      'Content-Type': 'application/json'
    })
  }

  constructor(private httpServ: HttpClient) { }
  
  addProfile(profile: any){
    console.log(profile)
    return this.httpServ.post(this._Url + "addProfile.do", profile,this.httpOptions).pipe(
      map(res => res as any)
    )
  }
 editProfile(profile){
   console.log(profile.userId);
   return this.httpServ.put(this._Url + "updateProfile/" + parseInt(profile.userId), profile).pipe(
     map(res => res as any)
   )
 }
  delProfile(profile: Profile){
    return this.httpServ.delete(this._Url + `deleteProfile.do`, this.httpOptions).pipe(
      map(res => res as any)
    )
  }

  resetPassword(profile){
    return this.httpServ.put(this._Url + "resetPassword/" + profile.userName, profile).pipe(
      map(res => res as any)
    )
  }
  private handleError(error: HttpErrorResponse){
    if(error.error instanceof ErrorEvent){
      //client-side error
      console.error(`An error occurred: `, error.message)
    } else {
      // server-side error
      console.error(
        `Server returned code ${error.status}, ` +
        `body was : ${JSON.stringify(error.error)}`
      )
    }

    //return a user-facing message
    return throwError(
      'Ruh roh, please try again later.'
    )
  }

  
  sendLoginCreds(username: string, password: string): Observable<any> {
    // let body = new URLSearchParams();
    // body.set('username', username);
    // body.set('password', password);
    let body = `username=${username}&password=${password}`;
    return this.httpServ.post<any>(this._loginUrl, body)
    .pipe(
      // map (res => res as any)
      catchError(this.handleError)
    )
  }
  private profiles: Profile[] = [
    // new Profile("Luc","Lucnel", "Nordelus", "luc@luc.com","https://media-waterdeep.cursecdn.com/avatars/thumbnails/6/365/420/618/636272701937419552.png"),
    // new Profile("Niro", "Niroj", "Nanganathan", "nn@nn.com", "https://media-waterdeep.cursecdn.com/avatars/thumbnails/6/365/420/618/636272701937419552.png"),
    // new Profile("Samwise", "Sam", "Jones", "lol@samwise.com", "https://media-waterdeep.cursecdn.com/avatars/thumbnails/6/365/420/618/636272701937419552.png"),
    // new Profile("Jimmy", "Minqiu","Yu", "jyu@jyu.com", "https://media-waterdeep.cursecdn.com/avatars/thumbnails/6/365/420/618/636272701937419552.png")
  ]
  
  getProfiles(){
    return this.profiles.slice();
  }

  setProfiles(profiles: Profile[]){
    this.profiles = profiles;
  }
}
