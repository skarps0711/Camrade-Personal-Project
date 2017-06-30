import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "app/app-routing.module";
import { LoginSignupComponent } from "app/Login-Signup/login-signup.component";
import { LoginSignupService } from "app/Login-Signup/login-signup.service";
import { ForgetPasswordComponent } from "app/ForgetPassword/forget-password.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginSignupComponent,
    ForgetPasswordComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [LoginSignupService],
  bootstrap: [AppComponent]
})
export class AppModule { }
