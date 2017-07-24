import { Component, OnInit } from '@angular/core'
import { FriendsService } from "app/User/Friends/friends.service";
import { FriendDetails } from "app/User/Friends/friend-details";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { SearchUser } from "app/User/Friends/search-user";
import { CheckFriend } from "app/User/Friends/check-friend";

@Component({
    templateUrl: './add-friends.component.html',
    styleUrls: ['./add-friends.component.css']
})

export class AddFriendsComponent implements OnInit {
    userId: number;
    searchDetails:string;
    userField: string;
    searchUser: SearchUser = new SearchUser();
    sendRequestDetails:CheckFriend=new CheckFriend();
    friendDetails: FriendDetails[] = new Array<FriendDetails>();

    constructor(private friendsService: FriendsService, private activatedRoute: ActivatedRoute, private router: Router) { }
    ngOnInit() {
        this.activatedRoute.parent.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
        });
    }

    suggestFriends(friendDetails) {
        this.searchDetails=friendDetails;
        this.searchUser.userId = this.userId;
        this.searchUser.searchDetails = friendDetails;
        this.friendsService.findFriends(this.searchUser).subscribe((data) => this.findFriendSuccess(data), (error) => this.findFriendFailure(error));
    }
    findFriendSuccess(suggestedFriends: FriendDetails[]) {
        this.friendDetails = suggestedFriends;
    }
    findFriendFailure(error) {
    }
    visitFriendProfile(friendId) {
        this.router.navigate(['user/' + this.userId + '/friends/' + friendId + '/profile']);
    }
    sendRequest(friendId:number){
        this.sendRequestDetails.userId=this.userId;
        this.sendRequestDetails.friendOf=friendId;
        this.friendsService.sendFriendRequest(this.sendRequestDetails).subscribe((data)=>this.sendRequestSuccess(data),(error)=>this.sendRequestFailure(error));
    }
    sendRequestSuccess(data){
        this.suggestFriends(this.searchDetails);
    }
    sendRequestFailure(error){

    }
    
}