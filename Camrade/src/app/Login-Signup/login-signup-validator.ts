import { AbstractControl } from "@angular/forms";

export class LoginSignupCustomValidator {

    static matchPassword(abs: AbstractControl) {
        let password = abs.get('password').value;
        let confPassword = abs.get('confPassword').value;
        if (confPassword.length > 5 && confPassword.length < 21) {
            if (password === confPassword) {
                return null;
            }
            else {
                return abs.get('confPassword').setErrors({ matchPasswordError: true });
            }
        }
    }
}