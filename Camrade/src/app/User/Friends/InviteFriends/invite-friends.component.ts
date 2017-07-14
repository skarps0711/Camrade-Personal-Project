import { Component, OnInit } from '@angular/core'
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { FriendsService } from "app/User/Friends/friends.service";
import { SendEmail } from "app/User/Friends/send-email";
import { ActivatedRoute, Params } from "@angular/router";

@Component({
    templateUrl: './invite-friends.component.html',
    styleUrls: ['./invite-friends.component.css']
})

export class InviteFriendsComponent implements OnInit {
    formValid: boolean = false;
    isInviteSent: boolean = false;
    isInviteSentError: boolean = false;
    inviteForm: FormGroup;
    emailDetails: SendEmail;
    userId:number;

    constructor(private fb: FormBuilder, private friendsService: FriendsService,private activatedRoute: ActivatedRoute) { }

    ngOnInit() {
        this.createForm();
         this.activatedRoute.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
    }

    createForm() {
        this.inviteForm = this.fb.group({
            email: ['', [Validators.required, Validators.pattern('^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
            message: ['Hello !! I am using Camrade account to share my files with friends. Lets get sign up on www.camrade.com and enjoy the file sharing and chat with friends online.', Validators.required]
        });
        this.inviteForm.valueChanges
            .subscribe(data => this.onInviteValueChanged(data));
        this.onInviteValueChanged(); // (re)set validation messages now 
    }

    onSubmit() {
        this.emailDetails = this.inviteForm.value;
        this.friendsService.sendEmail(this.emailDetails,this.userId).subscribe((data) => this.sendEmailSuccess(data), (error) => this.sendEmailFailure(error));
    }
    sendEmailSuccess(data) {
        this.isInviteSent = true;
        this.isInviteSentError = false;
    }
    sendEmailFailure(error) {
        this.isInviteSent = false;
        this.isInviteSentError = true;
    }

    onInviteValueChanged(data?: any) {
        // setting invite show message options
        this.isInviteSent = false;
        this.isInviteSentError = false;

        if (!this.inviteForm) { return; }
        const form = this.inviteForm;
        for (const field in this.inviteFormErrors) {
            // clear previous error message (if any)
            this.inviteFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.inviteValidationMessages[field];
                for (const key in control.errors) {
                    this.inviteFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }

    inviteFormErrors = {
        'email': '',
        'message': ''
    };
    inviteValidationMessages = {
        'email': {
            'required': 'Email is required',
            'pattern': 'Invalid email'
        },
        'message': {
            'required': 'Invite message is required'
        }

    };
}