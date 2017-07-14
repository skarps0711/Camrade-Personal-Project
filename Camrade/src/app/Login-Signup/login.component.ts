import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators, Validator } from "@angular/forms";
import { LoginSignupCustomValidator } from "app/Login-Signup/login-signup-validator";
import { LoginSignupService } from "app/Login-Signup/login-signup.service";
import { UserFieldCheck } from "app/Login-Signup/user-field-check";
import { Router } from "@angular/router";
import { Login } from "app/Login-Signup/Login";
import { User } from "app/Login-Signup/User";

@Component({
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginSignupComponent implements OnInit {
    isLoginError: boolean = false;
    loginForm: FormGroup;
    login: Login = new Login();
    user: User = new User();

    constructor(private fb: FormBuilder, private authService: LoginSignupService, private router: Router) {
    }

    ngOnInit() {
        this.createForms();
    }

    createForms() {
        this.loginForm = this.fb.group({
            userName: ['', Validators.required],
            password: ['', Validators.required]
        });
        this.loginForm.valueChanges
            .subscribe(data => this.onLoginValueChanged(data));
        this.onLoginValueChanged(); // (re)set validation messages now 

    }

    viewSignupForm() {
        this.router.navigate(['/users/signup']);
    }

    onLoginUser() {
        this.login = this.loginForm.value;
        this.authService.validateUser(this.login).subscribe((data) => this.onLoginSuccess(data), (error) => this.onLoginFailure(error));
    }

    onLoginSuccess(loginVal: User) {
        this.user = loginVal;
        this.router.navigate(['/user/' + this.user.userId]);
    }

    onLoginFailure(error) {
        this.isLoginError = true;
    }

    onLoginValueChanged(data?: any) {
        this.isLoginError = false; // hide login error message
        if (!this.loginForm) { return; }
        const form = this.loginForm;
        for (const field in this.loginFormErrors) {
            // clear previous error message (if any)
            this.loginFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.loginValidationMessages[field];
                for (const key in control.errors) {
                    this.loginFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }

    loginFormErrors = {
        'userName': '',
        'password': ''
    };

    loginValidationMessages = {
        'userName': {
            'required': 'User name is required'
        },
        'password': {
            'required': 'Password is required'
        }
    };
}