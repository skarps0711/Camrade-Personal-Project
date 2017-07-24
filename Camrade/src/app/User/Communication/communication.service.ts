import {Injectable} from '@angular/core'
import { Http, Response, Headers } from "@angular/http";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import { Notifications } from "app/User/Communication/notification";

@Injectable()
export class CommunicationService{
     constructor(private http: Http) { }

    private headers = new Headers({ 'Content-Type': 'application/json' });

    getNotificationsUrl: string ;

    getMyNotifications(userId:number): Observable<Notifications[]> {
        this.getNotificationsUrl= "http://localhost:8080/user/"+userId+"/mynotifications";
        return this.http.get(this.getNotificationsUrl, this.headers)
            .map((res: Response) => <Notifications[]>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Notifications error'));
    }
}