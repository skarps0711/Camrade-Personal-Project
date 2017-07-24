import { Component, OnInit } from '@angular/core'
import { FriendsService } from "app/User/Friends/friends.service";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { FriendDetails } from "app/User/Friends/friend-details";
import { CheckFriend } from "app/User/Friends/check-friend";

@Component({
    templateUrl: './friend-request.component.html',
    styleUrls: ['./friend-request.component.css']
})

export class FriendRequestComponent implements OnInit {
    haveRequest: boolean = false;
    userId: number;
    requestDetails: FriendDetails[] = new Array<FriendDetails>();
    sendDetails: CheckFriend = new CheckFriend();

    constructor(private activatedRoute: ActivatedRoute, private friendsService: FriendsService, private router: Router) { }

    ngOnInit() {
        this.activatedRoute.parent.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
        this.findMyRequests();
    }
    findMyRequests() {
        this.friendsService.findMyRequests(this.userId).subscribe((data) => this.findRequestsSuccess(data), (error) => this.findRequestFailure(error));
    }
    findRequestsSuccess(data) {
        this.requestDetails = data;
        this.haveRequest = true;
        console.log(this.requestDetails);
    }
    findRequestFailure(error) {
        this.haveRequest = false;
    }
    visitFriendProfile(friendId) {
        this.router.navigate(['user/' + this.userId + '/friends/' + friendId + '/profile']);
    }
    acceptFriendRequest(friendId) {
        this.sendDetails.userId = this.userId;
        this.sendDetails.friendOf = friendId;
        this.friendsService.acceptFriendRequest(this.sendDetails).subscribe((data) => this.acceptFriendSuccess(data), (error) => this.acceptFriendFailure(error));
    }
    acceptFriendSuccess(data) {
        location.reload();
    }
    acceptFriendFailure(error) {
        console.log("failure");
    }
    deleteFriendRequest(friendId) {
        this.sendDetails.userId = this.userId;
        this.sendDetails.friendOf = friendId;
        this.friendsService.deleteFriendRequest(this.sendDetails).subscribe((data) => this.acceptFriendSuccess(data), (error) => this.acceptFriendFailure(error));
    }
    deleteFriendRequestSuccess(data) {
        location.reload();
    }
    deleteFriendRequestFailure(error) {
        console.log("failure");
    }
}