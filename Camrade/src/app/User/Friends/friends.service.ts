import { Injectable } from '@angular/core'
import { Http, Response, Headers } from "@angular/http";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

import { ProcessCheck } from "app/User/process-check.component";
import { FriendDetails } from "app/User/Friends/friend-details";
import { SendEmail } from "app/User/Friends/send-email";

@Injectable()
export class FriendsService {

    constructor(private http: Http) { }

    private headers = new Headers({ 'Content-Type': 'application/json' });

    inviteFriendUrl: string;
    suggestFriendUrl: string = "http://localhost:8080/user/searchfriends";

    sendEmail(sendEmail: SendEmail, userId: number): Observable<ProcessCheck> {
        this.inviteFriendUrl = "http://localhost:8080/user/"+userId+"/invitefriend";
        return this.http.post(this.inviteFriendUrl, sendEmail, this.headers)
            .map((res: Response) => <ProcessCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Send email error'));
    }
    findFriends(friendDetails: string): Observable<FriendDetails[]> {
        return this.http.post(this.suggestFriendUrl, friendDetails, this.headers)
            .map((res: Response) => <FriendDetails[]>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'No suggestions available'));
    }
}