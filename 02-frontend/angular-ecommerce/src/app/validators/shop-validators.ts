import { FormControl, ValidationErrors } from "@angular/forms";

export class ShopValidators {

  // whitespace validation
  // if validation check fails then return validation error(s). If validation check passes return null
  static notOnlyWhiteSpace(control: FormControl): ValidationErrors {

    // check if string only contains whitespace
    if (control.value != null && control.value.trim().length === 0) {
      // invalid, return error object
      return { 'notOnlyWhiteSpace': true };
    } else {
      // valid, return null
      return null;
    }
  }
}

