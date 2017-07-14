import { Component, OnInit } from '@angular/core'
import { UserDetails } from "app/User/user-details";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { UserService } from "app/User/user.service";
import { ProfileService } from "app/User/Profile/profile.service";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";

@Component({
    templateUrl: './edit-profile.component.html',
    styleUrls: ['./edit-profile.component.css']
})

export class EditProfileComponent implements OnInit {
    userId: number;
    userName: string;
    password: string;
    profilePicture: string;
    coverImage: string;
    userForm: FormGroup;
    userDetails: UserDetails = new UserDetails();

    constructor(private activatedRoute: ActivatedRoute, private userService: UserService, private profileService: ProfileService, private router: Router, private fb: FormBuilder) { }

    ngOnInit() {
        this.activatedRoute.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
            this.getUserDetails();
        });
        this.createForm();
    }
    createForm() {
        this.userForm = this.fb.group({
            userId: [''],
            firstName: ['', [Validators.required]],
            lastName: ['', [Validators.required]],
            userName: [''],
            alterName: [''],
            password: [''],
            email: ['', [Validators.required, Validators.pattern('^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
            phoneNo: ['', [Validators.required, Validators.pattern('^[0-9]{10,12}$')]],
            birthDate: ['', [Validators.required]],
            gender: ['', [Validators.required]],
            profilePicture: [''],
            coverImage: [''],
            address: [''],
            quotes: [''],
            about: [''],
            work: [''],
            schoolName: [''],
            collegeName: ['']
        });
        this.userForm.valueChanges
            .subscribe(data => this.onUserValueChanged(data));
        this.onUserValueChanged(); // (re)set validation messages now 
    }
    getUserDetails() {
        this.userService.getUser(this.userId).subscribe((data) => this.getUserDetailsSuccess(data), (error) => this.getUserDetailsFailure(error));
    }
    getUserDetailsSuccess(details: UserDetails) {
        this.userDetails = details;
        this.userName = this.userDetails.userName;
        this.password = this.userDetails.password;
        this.profilePicture = this.userDetails.profilePicture;
        this.coverImage = this.userDetails.coverImage;
    }
    getUserDetailsFailure(error) {
    }
    onSubmit() {
        this.userDetails = this.userForm.value;
        this.userDetails.userId = this.userId;
        this.userDetails.userName = this.userName;
        this.userDetails.password = this.password;
        this.userDetails.profilePicture = this.profilePicture;
        this.userDetails.coverImage = this.coverImage;
        this.profileService.updateUserProfile(this.userDetails).subscribe((user) => this.updateUserProfileSuccess(user), (error) => this.updateUserProfileFailed(error));
    }
    updateUserProfileSuccess(user: UserDetails) {
        this.userDetails = user;
    }
    updateUserProfileFailed(error) {
    }
    onCancel(){
        this.router.navigate(['user/'+this.userId+'/profile']);
    }
    onUserValueChanged(data?: any) {
        if (!this.userForm) { return; }
        const form = this.userForm;
        for (const field in this.userFormErrors) {
            // clear previous error message (if any)
            this.userFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.userValidationMessages[field];
                for (const key in control.errors) {
                    this.userFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }
    userFormErrors = {
        'firstName': '',
        'lastName': '',
        'email': '',
        'phoneNo': '',
        'birthDate': '',
        'gender': ''
    };
    userValidationMessages = {
        'firstName': {
            'required': 'First name is required'
        },
        'lastName': {
            'required': 'Last name is required'
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