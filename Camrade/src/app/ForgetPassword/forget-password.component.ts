import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserFieldCheck } from "app/Login-Signup/user-field-check";
import { LoginSignupService } from "app/Login-Signup/login-signup.service";

@Component({
    templateUrl: './forget-password.component.html',
    styleUrls: ['./forget-password.component.css']
})

export class ForgetPasswordComponent implements OnInit {

    showByUserName: boolean = true;
    showByEmail: boolean = false;
    isUserNameExist: boolean = true;
    isEmailExist: boolean = true;
    isUserNameVerified:boolean=false;
    isEmailVerified:boolean=false;
    userName:string;
    forgetPassForm: FormGroup;
    userNameCheck: UserFieldCheck = new UserFieldCheck();
    emailCheck: UserFieldCheck = new UserFieldCheck();

    constructor(private fb: FormBuilder, private authService: LoginSignupService) { }

    ngOnInit() {
        this.createForm();
    }

    createForm() {
        this.forgetPassForm = this.fb.group({
            userName: ['', Validators.required],
            email: ['', Validators.required]
        });
        this.forgetPassForm.valueChanges
            .subscribe(data => this.onForgetPassValueChanged(data));
        this.onForgetPassValueChanged(); // (re)set validation messages now 
    }
    clickByUserName() {
        this.showByUserName = true;
        this.showByEmail = false;
    }
    clickByEmail() {
        this.showByEmail = true;
        this.showByUserName = false;
    }
    onUserNameChange(userName) {
        if(userName.length<1){
            this.isUserNameExist = true;
        }
        this.authService.isUserNameExist(userName).subscribe((data) => this.isUserNameExistSuccess(data), (error) => this.isUserNameExistFailure(error));
    }
     onEmailChange(email) {
         if(email.length<1){
            this.isEmailExist = true;
        }
        this.authService.isEmailExist(email).subscribe((data) => this.isEmailExistSuccess(data), (error) => this.isEmailExistFailure(error))
    }
    isUserNameExistSuccess(userFieldCheck: UserFieldCheck) {
        this.userNameCheck = userFieldCheck;
        this.isUserNameExist = false;
        if (this.userNameCheck.status == true) {
            this.isUserNameExist = true;
            this.isUserNameVerified=true;
        }else{
            this.isUserNameVerified=false;
        }
    }
    isUserNameExistFailure(error) {
    }
     isEmailExistSuccess(userFieldCheck: UserFieldCheck) {
        this.emailCheck = userFieldCheck;
        this.isEmailExist = false;
        if (this.emailCheck.status == true) {
            this.isEmailExist = true;
            this.isEmailVerified=true;
        }
        else{
            this.isEmailVerified=false;
        }
    }
    isEmailExistFailure(error) {
    }
    onSubmit(){
        if(this.isUserNameVerified==true){

        }else if(this.isEmailVerified==true){

        }else{
            
        }
    }
    onForgetPassValueChanged(data?: any) {
        if (!this.forgetPassForm) { return; }
        const form = this.forgetPassForm;
        for (const field in this.forgetPassFormErrors) {
            // clear previous error message (if any)
            this.forgetPassFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.forgetPassValidationMessages[field];
                for (const key in control.errors) {
                    this.forgetPassFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }

    forgetPassFormErrors = {
        'userName': '',
        'email': ''
    };
    forgetPassValidationMessages = {
        'userName': {
            'required': 'User name is required'
        },
        'email': {
            'required': 'Email is required'
        }
    };
}