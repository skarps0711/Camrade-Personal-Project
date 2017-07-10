import { RouterModule, Routes } from '@angular/router'
import { NgModule } from '@angular/core'
import { ForgetPasswordComponent } from "app/ForgetPassword/forget-password.component";
import { UserComponent } from "app/User/user.component";
import { SignupComponent } from "app/Signup/signup.component";
import { InvalidRequestComponent } from "app/InvalidRequest/invalid-request.component";
import { LoginSignupComponent } from "app/Login-Signup/login.component";

const routes: Routes = [
  { path: '', redirectTo: 'users/login', pathMatch: 'full' },
  { path: 'users/login', component: LoginSignupComponent },
  { path: 'users/signup', component: SignupComponent },
  { path: 'users/forgetpassword', component: ForgetPasswordComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule { }