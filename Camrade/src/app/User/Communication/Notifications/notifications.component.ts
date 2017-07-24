import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Params } from "@angular/router";
import { Notifications } from "app/User/Communication/notification";
import { CommunicationService } from "app/User/Communication/communication.service";

@Component({
    templateUrl: './notifications.component.html',
    styleUrls: ['./notifications.component.css']
})

export class NotificationsComponent implements OnInit {

    userId: number;
    haveNotifications: boolean = false;
    notifications: Notifications[] = new Array<Notifications>();

    constructor(private activatedRoute: ActivatedRoute, private communicationService: CommunicationService) { }

    ngOnInit() {
        this.activatedRoute.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
    }
    getMyNotifications() {
        this.communicationService.getMyNotifications(this.userId).subscribe((data) => this.getNotificationsSuccess(data), (error) => this.getNotificationsFailure(error));
    }
    getNotificationsSuccess(data) {
        this.notifications = data;
        this.haveNotifications = true;
        console.log(this.notifications);
    }
    getNotificationsFailure(error) {
        this.haveNotifications = false;
        console.log("No notifications");
    }
}