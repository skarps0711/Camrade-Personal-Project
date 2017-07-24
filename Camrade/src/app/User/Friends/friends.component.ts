import { Component } from '@angular/core'
import { FriendDetails } from "app/User/Friends/friend-details";
import { ActivatedRoute, Params } from "@angular/router";
import { FriendsService } from "app/User/Friends/friends.service";

@Component({
    templateUrl: './friends.component.html',
    styleUrls: ['./friends.component.css']
})

export class FriendsComponent {
    userId: number;
    noOfFriendRequests:number;
    requestDetails: FriendDetails[] = new Array<FriendDetails>();
    constructor(private activatedRoute: ActivatedRoute, private friendsService: FriendsService) { }
    ngOnInit() {
        this.activatedRoute.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
        this.findMyRequests();
    }
    findMyRequests() {
        this.friendsService.findMyRequests(this.userId).subscribe((data) => this.findRequestsSuccess(data), (error) => this.findRequestFailure(error));
    }
    findRequestsSuccess(data) {
        this.requestDetails = data;
        this.noOfFriendRequests=this.requestDetails.length;
    }
    findRequestFailure(error) {
    }
}