import { Injectable } from '@angular/core'
import { Http,Response, Headers } from "@angular/http";

import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';

import { Login } from "app/Login-Signup/Login";
import { User } from "app/Login-Signup/User";
import { SignupUser } from "app/Login-Signup/SignupUser";
import { UserFieldCheck } from "app/Login-Signup/user-field-check";

@Injectable()
export class LoginSignupService {

    constructor(private http: Http) { }
    private headers = new Headers({ 'Content-Type': 'application/json' });
    loginUrl: string = "http://localhost:8080/users/authendicateuser";
    addUserUrl: string = "http://localhost:8080/users/adduser";
    userNameCheckUrl: string = "http://localhost:8080/users/isusernameexist";
    emailCheckUrl: string = "http://localhost:8080/users/isemailexist";
    sendEmailUrl: string = "http://localhost:8080/users/sendemail";

    validateUser(login: Login): Observable<User> {
        return this.http.post(this.loginUrl, login, this.headers)
            .map((res: Response) => <User>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Login credentials error'));
    }
    addUser(user:SignupUser):Observable<User>{
        return this.http.post(this.addUserUrl, user, this.headers)
            .map((res: Response) => <User>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Signup credentials error'));
    }
    isUserNameExist(userName:String):Observable<UserFieldCheck>{
        return this.http.post(this.userNameCheckUrl, userName, this.headers)
            .map((res: Response) => <UserFieldCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Signup credentials error'));
    }
    isEmailExist(email:String):Observable<UserFieldCheck>{
        return this.http.post(this.emailCheckUrl, email, this.headers)
            .map((res: Response) => <UserFieldCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'Signup credentials error'));
    }
    sendEmail(userNameOrEmail:String):Observable<UserFieldCheck>{
        return this.http.post(this.emailCheckUrl, userNameOrEmail, this.headers)
            .map((res: Response) => <UserFieldCheck>res.json())
            .catch((error: any) => Observable.throw(error.json().error || 'User credentials error'));
    }
}