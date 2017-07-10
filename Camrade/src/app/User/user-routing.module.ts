import { RouterModule, Routes } from '@angular/router'
import { NgModule } from '@angular/core'
import { UserComponent } from "app/User/user.component";
import { MediaComponent } from "app/User/Media/media.component";
import { ProfileComponent } from "app/User/Profile/profile.component";
import { MessagesComponent } from "app/User/Communication/Messages/messages.component";
import { NotificationsComponent } from "app/User/Communication/Notifications/notifications.component";
import { FriendsComponent } from "app/User/Friends/friends.component";
import { MyMediaComponent } from "app/User/Media/MyMedia/my-media.component";
import { ShareMediaComponent } from "app/User/Media/ShareMedia/share-media.component";
import { SharedMediaComponent } from "app/User/Media/SharedMedia/shared-media.component";
import { UploadMediaComponent } from "app/User/Media/UploadMedia/upload-media.component";
import { MyFriendsComponent } from "app/User/Friends/MyFriends/my-friends.component";
import { AddFriendsComponent } from "app/User/Friends/AddFriends/add-friends.component";
import { InviteFriendsComponent } from "app/User/Friends/InviteFriends/invite-friends.component";
import { PersonalMessageComponent } from "app/User/Friends/PersonalMessage/personal-message.component";
import { FriendRequestComponent } from "app/User/Friends/FriendRequest/friend-request.component";

const userRoutes: Routes = [
  {
    path: 'user/:id',
    component: UserComponent,
    children: [
      { path: '', component: MediaComponent },
      { path: 'home', component: MediaComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'communication/messages', component: MessagesComponent },
      { path: 'communication/notifications', component: NotificationsComponent },
      {
        path: 'media',
        component: MediaComponent,
        children: [
          { path: '', component: MyMediaComponent },
          { path: 'mymedia', component: MyMediaComponent },
          { path: 'sharemedia', component: ShareMediaComponent },
          { path: 'sharedmedia', component: SharedMediaComponent },
          { path: 'uploadmedia', component: UploadMediaComponent }
        ]
      },
      {
        path: 'friends',
        component: FriendsComponent,
        children: [
          { path: '', component: MyFriendsComponent },
          { path: 'myfriends', component: MyFriendsComponent },
          { path: 'addfriends', component: AddFriendsComponent },
          { path: 'friendrequest', component: FriendRequestComponent },
          { path: 'invitefriends', component: InviteFriendsComponent },
          { path: 'personalmessage', component: PersonalMessageComponent }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(userRoutes)
  ],
  exports: [
    RouterModule
  ]
})

export class UserRoutingModule { }