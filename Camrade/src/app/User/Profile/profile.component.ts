import { Component, OnInit } from '@angular/core'
import { UserDetails } from "app/User/user-details";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { UserService } from "app/User/user.service";
import { NoOfMedias } from "app/User/Profile/no-of-medias";
import { ProfileService } from "app/User/Profile/profile.service";

@Component({
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
    userId: number;
    showProfileCount: number = 1;
    showFullProfile: boolean = false;
    showChangeProfile = false;
    showChangeCover = false;
    userDetails: UserDetails = new UserDetails();
    noOfMedias: NoOfMedias = new NoOfMedias();

    constructor(private activatedRoute: ActivatedRoute, private userService: UserService, private profileService: ProfileService, private router: Router) { }

    ngOnInit() {
        this.activatedRoute.parent.params.subscribe((params: Params) => {
            this.userId = params['id'];
            this.getUserDetails();
            this.getNoOfMedias();
        });
    }
    getUserDetails() {
        this.userService.getUser(this.userId).subscribe((data) => this.getUserDetailsSuccess(data), (error) => this.getUserDetailsFailure(error));
    }
    getUserDetailsSuccess(details: UserDetails) {
        this.userDetails = details;

    }
    getUserDetailsFailure(error) {
    }
    getNoOfMedias() {
        this.profileService.getNoOfMedias(this.userId).subscribe((data) => this.getNoOfMediasSuccess(data), (error) => this.getNoOfMediasFailure(error));
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
    editProfile() {
        this.router.navigate(['user/' + this.userId + "/editprofile"]);
    }
    onProfileImageClick() {
        this.showChangeProfile = true;
        this.showChangeCover = false;
    }
    onCoverImageClick() {
        this.showChangeCover = true;
        this.showChangeProfile = false;
    }
}