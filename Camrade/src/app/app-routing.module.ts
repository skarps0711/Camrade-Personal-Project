import { RouterModule, Routes } from '@angular/router'
import { NgModule } from '@angular/core'
import { LoginSignupComponent } from "app/Login-Signup/login-signup.component";
import { ForgetPasswordComponent } from "app/ForgetPassword/forget-password.component";

const routes: Routes = [
  { path: '', redirectTo: 'users/loginsignup', pathMatch: 'full' },
  { path: 'users/loginsignup', component: LoginSignupComponent },
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