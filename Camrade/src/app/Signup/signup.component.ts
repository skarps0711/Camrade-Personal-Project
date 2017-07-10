import { Component, OnInit } from '@angular/core'
import { FormGroup, Validators, FormBuilder, Validator } from "@angular/forms";
import { LoginSignupCustomValidator } from "app/Login-Signup/login-signup-validator";
import { LoginSignupService } from "app/Login-Signup/login-signup.service";
import { Router } from "@angular/router";
import { User } from "app/Login-Signup/User";
import { UserFieldCheck } from "app/Login-Signup/user-field-check";
import { SignupUser } from "app/Signup/SignupUser";

@Component({
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {
    isSignupSuccess: boolean = false;
    isSignupFailure: boolean = false;
    isUserNameExist: boolean = false;
    isEmailExist: boolean = false;
    userNameCheck: UserFieldCheck = new UserFieldCheck();
    emailCheck: UserFieldCheck = new UserFieldCheck();
    signupForm: FormGroup;
    validator: Validator;
    userNameCheckValidator: Validator;
    signupUserVal: SignupUser = new SignupUser();

    constructor(private fb: FormBuilder, private authService: LoginSignupService, private router: Router) {
    }
    ngOnInit() {
        this.createForms();
    }

    createForms() {
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
        this.signupForm.valueChanges
            .subscribe(data => this.onSignupValueChanged(data));
        this.onSignupValueChanged(); // (re)set validation messages now 
    }
    viewLoginForm() {
        console.log("sdsd");
        this.router.navigate(['/users/login']);
    }
    onSignupUser() {
        this.isSignupSuccess = false;
        this.isSignupFailure = false;
        this.signupUserVal = this.signupForm.value;
        this.authService.addUser(this.signupUserVal).subscribe((data) => this.onSignupSuccess(data), (error) => this.onSignupFailure(error));
    }
    onSignupSuccess(data: User) {
        this.isSignupSuccess = true;
    }
    onSignupFailure(error) {
        this.isSignupFailure = true;
    }
    onUserNameChange(username: string) {
        this.authService.isUserNameExist(username).subscribe((data) => this.isUserNameExistSuccess(data), (error) => this.isUserNameExistFailure(error))
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
    onEmailChange(email: string) {
        this.authService.isEmailExist(email).subscribe((data) => this.isEmailExistSuccess(data), (error) => this.isEmailExistFailure(error))
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
    resetSignupForm(){
        location.reload();
    }

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