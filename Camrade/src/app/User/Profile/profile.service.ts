import { Injectable } from '@angular/core'

import { Http, Response, Headers } from "@angular/http";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import { NoOfMedias } from "app/User/Profile/no-of-medias";
import { UserDetails } from "app/User/user-details";
import { SaveImage } from "app/User/Profile/save-image";
import { ChangePassword } from "app/User/Profile/Change-Password/change-password";

@Injectable()
export class ProfileService {
    constructor(private http: Http) { }

    private headers = new Headers({ 'Content-Type': 'application/json' });

    getNoOfMediasUrl: string;
    userUpdateProfileUrl: string;
    userUpdateImageUrl:string;
    userUpdatePasswordUrl:string;

    getNoOfMedias(userId: number): Observable<NoOfMedias> {
        this.getNoOfMediasUrl = "http://localhost:8080/user/profile/" + userId + "/getnoofmedias";
        return this.http.get(this.getNoOfMediasUrl)
            .map((res: Response) => <NoOfMedias>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Media files error'));
    }
    updateUserProfile(user: UserDetails): Observable<UserDetails> {
        this.userUpdateProfileUrl = "http://localhost:8080/user/profile/updateprofile";
        return this.http.put(this.userUpdateProfileUrl, user, this.headers)
            .map((res: Response) => <UserDetails>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Update user failed'));

    }
    updateImage(saveImage:SaveImage): Observable<UserDetails> {
        this.userUpdateImageUrl = "http://localhost:8080/user/profile/updateimage";
        return this.http.put(this.userUpdateImageUrl, saveImage, this.headers)
            .map((res: Response) => <UserDetails>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Update image failed'));
    }
    updatePassword(passwords:ChangePassword): Observable<UserDetails> {
        this.userUpdatePasswordUrl = "http://localhost:8080/user/profile/updatepassword";
        return this.http.put(this.userUpdatePasswordUrl, passwords, this.headers)
            .map((res: Response) => <UserDetails>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Update password failed'));
    }
}