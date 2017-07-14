import {Injectable} from '@angular/core'
import { Http, Response, Headers } from "@angular/http";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

import { UserDetails } from "app/User/user-details";

@Injectable()
export class UserService{
     constructor(private http: Http) { }

    private headers = new Headers({ 'Content-Type': 'application/json' });

    getUserUrl: string ;

    getUser(userId:number): Observable<UserDetails> {
        this.getUserUrl= "http://localhost:8080/user/"+userId+"/getuser";
        return this.http.get(this.getUserUrl, this.headers)
            .map((res: Response) => <UserDetails>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'USer details error'));
    }
}