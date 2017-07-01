import { Component, OnInit } from '@angular/core'
import { FormBuilder, FormGroup, Validators, Validator } from "@angular/forms";
import { LoginSignupCustomValidator } from "app/Login-Signup/login-signup-validator";
import { Login } from "app/Login-Signup/Login";
import { User } from "app/Login-Signup/User";
import { LoginSignupService } from "app/Login-Signup/login-signup.service";
import { SignupUser } from "app/Login-Signup/SignupUser";
import { UserFieldCheck } from "app/Login-Signup/user-field-check";

@Component({
    templateUrl: './login-signup.component.html',
    styleUrls: ['./login-signup.component.css']
})

export class LoginSignupComponent implements OnInit {
    showSignup: boolean = false;
    showLogin: boolean = true;
    isLoginError: boolean = false;
    isUserNameExist: boolean = false;
    isEmailExist: boolean = false;
    loginForm: FormGroup;
    signupForm: FormGroup;
    validator: Validator;
    userNameCheckValidator: Validator;
    login: Login = new Login();
    user: User = new User();
    signupUserVal: SignupUser = new SignupUser();
    userNameCheck: UserFieldCheck = new UserFieldCheck();
    emailCheck: UserFieldCheck = new UserFieldCheck();

    constructor(private fb: FormBuilder, private authService: LoginSignupService) {
    }

    ngOnInit() {
        this.createForms();
    }
    createForms() {
        this.loginForm = this.fb.group({
            userName: ['', Validators.required],
            password: ['', Validators.required]
        });
        this.signupForm = this.fb.group({
            userId: ['0'],
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            userName: ['', [Validators.required, Validators.minLength(5)]],
            password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
            confPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
            email: ['', [Validators.required, Validators.pattern('^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
            phoneNo: ['', [Validators.required, Validators.pattern('^[0-9]{10,12}$')]],
            birthDate: ['', [Validators.required]],
            gender: ['', [Validators.required]]
        }, {
                validator: LoginSignupCustomValidator.matchPassword,
                userNameCheckValidator: LoginSignupCustomValidator
            });
        this.loginForm.valueChanges
            .subscribe(data => this.onLoginValueChanged(data));
        this.onLoginValueChanged(); // (re)set validation messages now 
        this.signupForm.valueChanges
            .subscribe(data => this.onSignupValueChanged(data));
        this.onSignupValueChanged(); // (re)set validation messages now 
    }
    onUserNameChange(username: string) {
        this.authService.isUserNameExist(username).subscribe((data) => this.isUserNameExistSuccess(data), (error) => this.isUserNameExistFailure(error))
    }
    onEmailChange(email: string) {
        this.authService.isEmailExist(email).subscribe((data) => this.isEmailExistSuccess(data), (error) => this.isEmailExistFailure(error))
    }
    isUserNameExistSuccess(userFieldCheck: UserFieldCheck) {
        this.userNameCheck = userFieldCheck;
        this.isUserNameExist = false;
        if (this.userNameCheck.status == true) {
            this.isUserNameExist = true;
        }
    }
    isUserNameExistFailure(error) {

    }
    isEmailExistSuccess(userFieldCheck: UserFieldCheck) {
        this.emailCheck = userFieldCheck;
        this.isEmailExist = false;
        if (this.emailCheck.status == true) {
            this.isEmailExist = true;
        }
    }
    isEmailExistFailure(error) {
    }
    viewSignupForm() {
        this.showSignup = true;
        this.showLogin = false;
    }
    viewLoginForm() {
        this.showLogin = true;
        this.showSignup = false;
    }
    loginUser() {
        this.login = this.loginForm.value;
        this.authService.validateUser(this.login).subscribe((data) => this.onLoginSuccess(data), (error) => this.onLoginFailure(error));
    }
    onLoginSuccess(loginVal: User) {
        this.user = loginVal;
        console.log("Login success!!");
    }
    onLoginFailure(error) {
        this.isLoginError = true;
        console.log("login failure");
    }
    signupUser() {
        this.signupUserVal = this.signupForm.value;
        this.authService.addUser(this.signupUserVal).subscribe((data) => this.onSignupSuccess(data), (error) => this.onSignupFailure(error));
    }
    onSignupSuccess(data: User) {
        console.log("Signup success");
    }
    onSignupFailure(error) {
        console.log("Signupfailure");
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

    onSignupValueChanged(data?: any) {
        if (!this.signupForm) { return; }
        const form = this.signupForm;
        for (const field in this.signupFormErrors) {
            // clear previous error message (if any)
            this.signupFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.signupValidationMessages[field];
                for (const key in control.errors) {
                    this.signupFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }
    signupFormErrors = {
        'firstName': '',
        'lastName': '',
        'userName': '',
        'password': '',
        'confPassword': '',
        'email': '',
        'phoneNo': '',
        'birthDate': '',
        'gender': ''
    };
    signupValidationMessages = {
        'firstName': {
            'required': 'First name is required'
        },
        'lastName': {
            'required': 'Last name is required'
        },
        'userName': {
            'required': 'User name is required',
            'minlength': 'User name should have minimum 5 characters',
            'maxlength': 'User name should have maximum 20 characters'
        },
        'password': {
            'required': 'Password is required',
            'minlength': 'Password should have minimum 6 characters',
            'maxlength': 'Password should have maximum 20 characters'
        },
        'confPassword': {
            'required': 'Password is required',
            'minlength': 'Password should have minimum 6 characters',
            'maxlength': 'Password should have maximum 20 characters',
            'matchPasswordError': 'Password is not matching'
        },
        'email': {
            'required': 'Email is required',
            'pattern': 'Invalid email'
        },
        'phoneNo': {
            'required': 'Phone no is required',
            'pattern': 'Invalid phone no'
        },
        'birthDate': {
            'required': 'Birth date is required'
        },
        'gender': {
            'required': 'Gender is required'
        }
    };
}