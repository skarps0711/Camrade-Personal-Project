import { AbstractControl } from "@angular/forms";

export class ProfileValidator {
    static checkPassword(abs: AbstractControl) {
        let newPassword = abs.get('newPassword').value;
        let confPassword = abs.get('confPassword').value;
        if (confPassword.length > 5 && confPassword.length < 21) {
            if (newPassword === confPassword) {
                return null;
            }
            else {
                return abs.get('confPassword').setErrors({ matchPasswordError: true });
            }
        }
    }
}