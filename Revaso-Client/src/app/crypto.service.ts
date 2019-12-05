import { Injectable } from '@angular/core';
import * as CryptoJs from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class CryptoService {

  constructor() { }
  hash(value: string): string {
    let hashedVal: string = CryptoJs.MD5(value).toString();

    return hashedVal;
  }
}
