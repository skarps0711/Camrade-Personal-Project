import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "app/app-routing.module";
import { LoginSignupService } from "app/Login-Signup/login-signup.service";
import { ForgetPasswordComponent } from "app/ForgetPassword/forget-password.component";
import { UserModule } from "app/User/user.module";
import { SignupComponent } from "app/Signup/signup.component";
import { InvalidRequestComponent } from "app/InvalidRequest/invalid-request.component";
import { LoginSignupComponent } from "app/Login-Signup/login.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginSignupComponent,
    SignupComponent,
    ForgetPasswordComponent,
    InvalidRequestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    AppRoutingModule,
    UserModule
  ],
  providers: [LoginSignupService],
  bootstrap: [AppComponent]
})
export class AppModule { }
