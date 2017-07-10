import {Component} from '@angular/core'
import { FriendsService } from "app/User/Friends/friends.service";
import { FriendDetails } from "app/User/Friends/friend-details";

@Component({
    templateUrl:'./add-friends.component.html',
    styleUrls:['./add-friends.component.css']
})

export class AddFriendsComponent{
    userField:string;
    friendDetails:FriendDetails[]=new Array<FriendDetails>();

    constructor(private friendsService:FriendsService){}

    suggestFriends(friendDetails){
        this.friendsService.findFriends(friendDetails).subscribe((data)=>this.findFriendSuccess(data),(error)=>this.findFriendFailure(error));
    }       
    findFriendSuccess(suggestedFriends:FriendDetails[]){
        this.friendDetails=suggestedFriends;
    }
    findFriendFailure(error){
    }
}