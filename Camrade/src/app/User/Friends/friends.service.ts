import { Injectable } from '@angular/core'
import { Http, Response, Headers } from "@angular/http";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

import { FriendDetails } from "app/User/Friends/friend-details";
import { SendEmail } from "app/User/Friends/send-email";
import { SearchUser } from "app/User/Friends/search-user";
import { ProcessCheck } from "app/User/process-check";
import { CheckFriend } from "app/User/Friends/check-friend";

@Injectable()
export class FriendsService {

    constructor(private http: Http) { }

    private headers = new Headers({ 'Content-Type': 'application/json' });

    inviteFriendUrl: string;
    suggestFriendUrl: string = "http://localhost:8080/user/searchfriends";
    checkFriendUrl: string;
    sendFriendRequestUrl: string;
    findMyRequestsUrl: string;
    acceptFriendUrl: string;
    deleteFriendRequestUrl: string;

    sendEmail(sendEmail: SendEmail, userId: number): Observable<ProcessCheck> {
        this.inviteFriendUrl = "http://localhost:8080/user/" + userId + "/invitefriend";
        return this.http.post(this.inviteFriendUrl, sendEmail, this.headers)
            .map((res: Response) => <ProcessCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Send email error'));
    }
    findFriends(searchDetails: SearchUser): Observable<FriendDetails[]> {
        return this.http.post(this.suggestFriendUrl, searchDetails, this.headers)
            .map((res: Response) => <FriendDetails[]>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'No suggestions available'));
    }
    checkFriendExist(checkFriend: CheckFriend): Observable<FriendDetails> {
        this.checkFriendUrl = "http://localhost:8080/user/checkfriendexist"
        return this.http.post(this.checkFriendUrl, checkFriend, this.headers)
            .map((res: Response) => <FriendDetails>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'No suggestions available'));
    }
    sendFriendRequest(friendDetails: CheckFriend): Observable<ProcessCheck> {
        this.sendFriendRequestUrl = "http://localhost:8080/user/sendfriendrequest"
        return this.http.post(this.sendFriendRequestUrl, friendDetails, this.headers)
            .map((res: Response) => <ProcessCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'No suggestions available'));
    }
    findMyRequests(id: number): Observable<FriendDetails[]> {
        this.findMyRequestsUrl = "http://localhost:8080/user/" + id + "/myfriendrequests";
        return this.http.get(this.findMyRequestsUrl, this.headers)
            .map((res: Response) => <FriendDetails[]>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'No requests available'));
    }
    acceptFriendRequest(friendDetails: CheckFriend): Observable<ProcessCheck> {
        this.acceptFriendUrl = "http://localhost:8080/user/acceptfriendrequest"
        return this.http.post(this.acceptFriendUrl, friendDetails, this.headers)
            .map((res: Response) => <ProcessCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Not accepted'));
    }
    deleteFriendRequest(deleteDetails: CheckFriend): Observable<ProcessCheck> {
        this.deleteFriendRequestUrl = "http://localhost:8080/user/deletefriendrequest"
        return this.http.post(this.deleteFriendRequestUrl, deleteDetails, this.headers)
            .map((res: Response) => <ProcessCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Not deleted'));
    }
}