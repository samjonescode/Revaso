import { Injectable} from '@angular/core';
import { ProfilesService } from './profile/profiles.service';

@Injectable({
  providedIn: 'root'
})
export class SelectService {
  foundUser: any;
  constructor(private proServ: ProfilesService) { }

  
}
