import { Component, OnInit } from '@angular/core'
import { ProfileService } from "app/User/Profile/profile.service";
import { FormGroup, FormBuilder, Validators, Validator } from "@angular/forms";
import { ProfileValidator } from "app/User/Profile/profile-validator";
import { ChangePassword } from "app/User/Profile/Change-Password/change-password";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { UserDetails } from "app/User/user-details";

@Component({
    templateUrl: './change-password.component.html',
    styleUrls: ['./change-password.component.css']
})

export class ChangePasswordComponent implements OnInit {
    isPasswordChangedSuccessfully: boolean = false;
    isWrongPassword: boolean = false;
    userId: number;
    userDetails: UserDetails = new UserDetails();
    passwordForm: FormGroup;
    validator: Validator;
    changePassword: ChangePassword = new ChangePassword();
    constructor(private profileService: ProfileService, private fb: FormBuilder, private activatedRoute: ActivatedRoute, private router: Router) { }

    ngOnInit() {
        this.activatedRoute.parent.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
        this.createForm();
    }
    createForm() {
        this.passwordForm = this.fb.group({
            userId: [''],
            oldPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
            newPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
            confPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
        }, {
                validator: ProfileValidator.checkPassword
            });
        this.passwordForm.valueChanges
            .subscribe(data => this.onPasswordChanged(data));
        this.onPasswordChanged(); // (re)set validation messages now 
    }

    onUpdatePassword() {
        this.changePassword = this.passwordForm.value;
        this.changePassword.userId = this.userId;
        this.profileService.updatePassword(this.changePassword).subscribe((data) => this.updatePasswordSuccess(data), (error) => this.updatePasswordFailure(error));
    }
    updatePasswordSuccess(data: UserDetails) {
        this.userDetails = data;
        this.isPasswordChangedSuccessfully = true;
        setTimeout(() => { this.router.navigate(['/user/' + this.userId + '/profile']); }, 2000);
        
    }
    updatePasswordFailure(error) {
        this.isWrongPassword = true;
        setTimeout(() => { location.reload(); }, 2000);
    }
    onCancel() {
        this.router.navigate(['/user/' + this.userId + '/profile']);
    }
    onPasswordChanged(data?: any) {
        if (!this.passwordForm) { return; }
        this.isWrongPassword = false;
        const form = this.passwordForm;
        for (const field in this.passwordFormErrors) {
            // clear previous error message (if any)
            this.passwordFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.passwordValidationMessages[field];
                for (const key in control.errors) {
                    this.passwordFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }
    passwordFormErrors = {
        'oldPassword': '',
        'newPassword': '',
        'confPassword': ''
    };
    passwordValidationMessages = {
        'oldPassword': {
            'required': 'Old password is required',
            'minlength': 'Password should have minimum 6 characters',
            'maxlength': 'Password should have maximum 20 characters'
        },
        'newPassword': {
            'required': 'New password is required',
            'minlength': 'Password should have minimum 6 characters',
            'maxlength': 'Password should have maximum 20 characters'
        },
        'confPassword': {
            'required': 'New password is required',
            'minlength': 'Password should have minimum 6 characters',
            'maxlength': 'Password should have maximum 20 characters',
            'matchPasswordError': 'New password should match'
        }
    };
}