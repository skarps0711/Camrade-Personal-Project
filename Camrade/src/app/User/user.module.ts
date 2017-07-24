import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UserComponent } from "app/User/user.component";
import { MediaComponent } from "app/User/Media/media.component";
import { RouterModule } from "@angular/router";
import { UserRoutingModule } from "app/User/user-routing.module";
import { ProfileComponent } from "app/User/Profile/profile.component";
import { NotificationsComponent } from "app/User/Communication/Notifications/notifications.component";
import { MessagesComponent } from "app/User/Communication/Messages/messages.component";
import { FriendsComponent } from "app/User/Friends/friends.component";
import { ChatComponent } from "app/User/Communication/Chat/chat.component";
import { MyMediaComponent } from "app/User/Media/MyMedia/my-media.component";
import { SharedMediaComponent } from "app/User/Media/SharedMedia/shared-media.component";
import { ShareMediaComponent } from "app/User/Media/ShareMedia/share-media.component";
import { UploadMediaComponent } from "app/User/Media/UploadMedia/upload-media.component";
import { AddFriendsComponent } from "app/User/Friends/AddFriends/add-friends.component";
import { InviteFriendsComponent } from "app/User/Friends/InviteFriends/invite-friends.component";
import { MyFriendsComponent } from "app/User/Friends/MyFriends/my-friends.component";
import { PersonalMessageComponent } from "app/User/Friends/PersonalMessage/personal-message.component";
import { FriendsService } from "app/User/Friends/friends.service";
import { FriendRequestComponent } from "app/User/Friends/FriendRequest/friend-request.component";
import { UserService } from "app/User/user.service";
import { ProfileService } from "app/User/Profile/profile.service";
import { EditProfileComponent } from "app/User/Profile/Edit-Profile/edit-profile.component";
import { ChangePasswordComponent } from "app/User/Profile/Change-Password/change-password.component";
import { FriendProfileComponent } from "app/User/Friends/Friend-Profile/friend-profile.component";
import { CommunicationService } from "app/User/Communication/communication.service";

@NgModule({
  declarations: [
    UserComponent, MediaComponent, ProfileComponent, MessagesComponent, NotificationsComponent, FriendsComponent,
    ChatComponent, MyMediaComponent, SharedMediaComponent, ShareMediaComponent, UploadMediaComponent, AddFriendsComponent,
    InviteFriendsComponent, MyFriendsComponent, PersonalMessageComponent, FriendRequestComponent, EditProfileComponent,
    ChangePasswordComponent, FriendProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    UserRoutingModule,
    ReactiveFormsModule
  ],
  providers: [UserService, FriendsService, ProfileService,CommunicationService],
  exports: [UserComponent, MediaComponent, ProfileComponent, MessagesComponent, NotificationsComponent, FriendsComponent,
    ChatComponent, MyMediaComponent, SharedMediaComponent, ShareMediaComponent, UploadMediaComponent, AddFriendsComponent,
    InviteFriendsComponent, MyFriendsComponent, PersonalMessageComponent, FriendRequestComponent, EditProfileComponent,
    ChangePasswordComponent, FriendProfileComponent
  ]
})
export class UserModule { }