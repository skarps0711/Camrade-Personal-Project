import { Component } from '@angular/core'
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { SendEmail } from "app/User/Friends/send-email.component";
import { FriendsService } from "app/User/Friends/friends.service";

@Component({
    templateUrl: './personal-message.component.html',
    styleUrls: ['./personal-message.component.css']
})

export class PersonalMessageComponent {
    formValid: boolean = false;
    isMessageSent: boolean = false;
    isMessageSentError: boolean = false;
    messageForm: FormGroup;
    emailDetails: SendEmail;

    constructor(private fb: FormBuilder, private friendsService: FriendsService) { }

    ngOnInit() {
        this.createForm();
    }

    createForm() {
        this.messageForm = this.fb.group({
            email: ['', [Validators.required, Validators.pattern('^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$')]],
            message: ['', Validators.required]
        });
        this.messageForm.valueChanges
            .subscribe(data => this.onMessageValueChanged(data));
        this.onMessageValueChanged(); // (re)set validation messages now 
    }

    onSubmit() {
        this.emailDetails = this.messageForm.value;
        this.friendsService.sendEmail(this.emailDetails).subscribe((data) => this.sendEmailSuccess(data), (error) => this.sendEmailFailure(error));
    }
    sendEmailSuccess(data) {
        this.isMessageSent = true;
        this.isMessageSentError = false;
    }
    sendEmailFailure(error) {
        this.isMessageSent = false;
        this.isMessageSentError = true;
    }

    onMessageValueChanged(data?: any) {
        // setting message show options
        this.isMessageSent = false;
        this.isMessageSentError = false;

        if (!this.messageForm) { return; }
        const form = this.messageForm;
        for (const field in this.messageFormErrors) {
            // clear previous error message (if any)
            this.messageFormErrors[field] = '';
            const control = form.get(field);
            if (control && control.dirty && !control.valid) {
                const messages = this.messageValidationMessages[field];
                for (const key in control.errors) {
                    this.messageFormErrors[field] += messages[key] + ' ';
                }
            }
        }
    }

    messageFormErrors = {
        'email': '',
        'message': ''
    };
    messageValidationMessages = {
        'email': {
            'required': 'Email is required',
            'pattern': 'Invalid email'
        },
        'message': {
            'required': 'Message is required'
        }
    };
}