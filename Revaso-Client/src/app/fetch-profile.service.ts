import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from './profile/profile.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FetchProfileService {
  private _Url = 'http://localhost:9007/SpringMVCPractice/';
  
  constructor(private httpServ: HttpClient) { }

  fetchProfilesFromDB(): Observable<Profile[]>{
    return this.httpServ.get(this._Url + "getAllProfiles.do").pipe(
      map (res => res as any)
    )
  }

  fetchProfileById(userId: number){
    return this.httpServ.get(this._Url + `selectProfileByID/${userId}.do`).pipe(
      map (res => res as any)
    )
  }

  fetchProfileByUserName(userName: string){
    return this.httpServ.get(this._Url + `selectProfileByUsername/${userName}.do`).pipe(
      map (res => res as any)
    )
  }

  fetchProfileByEmail(email: string){
    return this.httpServ.get(this._Url + `selectProfileByEmail/${email}.do`).pipe(
      map (res => res as any)
    )
  }

  fetchProfileByFirstName(fname: string){
    return this.httpServ.get(this._Url + `selectProfileByFirstName/${fname}.do`).pipe(
      map (res => res as any)
    )
  }
}
