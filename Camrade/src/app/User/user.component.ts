import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Params } from "@angular/router";
import { UserService } from "app/User/user.service";
import { UserDetails } from "app/User/user-details";

@Component({
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})

export class UserComponent implements OnInit {
    userId: number;
    userDetails:UserDetails=new UserDetails();
    constructor(private activatedRoute: ActivatedRoute,private userService:UserService) { }

    ngOnInit() {
        this.activatedRoute.params.subscribe((params: Params) => {
            this.userId = params['id'];
            this.getUserDetails();
        });

    }
    getUserDetails(){
        this.userService.getUser(this.userId).subscribe((data)=>this.getUserDetailsSuccess(data),(error)=>this.getUserDetailsFailure(error));
    }
    getUserDetailsSuccess(details:UserDetails){
        this.userDetails=details;
    }
    getUserDetailsFailure(error){
    }
}