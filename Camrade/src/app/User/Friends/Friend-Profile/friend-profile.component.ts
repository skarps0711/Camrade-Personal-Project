import { Component } from '@angular/core'
import { ActivatedRoute, Params, Router } from "@angular/router";
import { UserService } from "app/User/user.service";
import { NoOfMedias } from "app/User/Profile/no-of-medias";
import { UserDetails } from "app/User/user-details";
import { ProfileService } from "app/User/Profile/profile.service";
import { CheckFriend } from "app/User/Friends/check-friend";
import { FriendsService } from "app/User/Friends/friends.service";
import { FriendDetails } from "app/User/Friends/friend-details";

@Component({
    templateUrl: './friend-profile.component.html',
    styleUrls: ['./friend-profile.component.css']
})

export class FriendProfileComponent {
    friendId: number;
    userId: number;
    showProfileCount: number = 1;
    showFullProfile: boolean = false;
    checkFriend: CheckFriend = new CheckFriend();
    userDetails: UserDetails = new UserDetails();
    noOfMedias: NoOfMedias = new NoOfMedias();
    friendDetails: FriendDetails = new FriendDetails();

    constructor(private activatedRoute: ActivatedRoute, private userService: UserService, private profileService: ProfileService, private router: Router, private friendsService: FriendsService) { }

    ngOnInit() {
        this.activatedRoute.parent.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
        this.activatedRoute.params.subscribe((params: Params) => {
            this.friendId = params['friendId'];
            this.getUserDetails();
            this.getNoOfMedias();
            this.checkUserFriend();
        });
    }
    getUserDetails() {
        this.userService.getUser(this.friendId).subscribe((data) => this.getUserDetailsSuccess(data), (error) => this.getUserDetailsFailure(error));
    }
    getUserDetailsSuccess(details: UserDetails) {
        this.userDetails = details;
    }
    getUserDetailsFailure(error) {
    }
    getNoOfMedias() {
        this.profileService.getNoOfMedias(this.friendId).subscribe((data) => this.getNoOfMediasSuccess(data), (error) => this.getNoOfMediasFailure(error));
    }
    getNoOfMediasSuccess(data: NoOfMedias) {
        this.noOfMedias = data;
        console.log(this.noOfMedias.noOfAudios);
    }
    getNoOfMediasFailure(error) {
    }
    showProfile() {
        if (this.showProfileCount % 2 == 0) {
            this.showFullProfile = false;
        } else {
            this.showFullProfile = true;
        }
        this.showProfileCount += 1;
    }
    checkUserFriend() {
        this.checkFriend.userId = this.userId;
        this.checkFriend.friendOf = this.friendId;
        this.friendsService.checkFriendExist(this.checkFriend).subscribe((data) => this.checkUserFriendSuccess(data), (error) => this.checkUserFriendFailure(error));
    }
    checkUserFriendSuccess(data) {
        this.friendDetails = data;
        console.log(this.friendDetails);
    }
    checkUserFriendFailure(error) {

    }
}