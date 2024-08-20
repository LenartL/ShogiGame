import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

const url = 'http://localhost:8080/games/'

@Injectable({
  providedIn: 'root'
})
export class GameService {
  http: HttpClient


  constructor(http: HttpClient) {
    this.http = http;
  }

  newGame(): Observable<any> {
    return this.http.get(url + 'new')
  }
}
