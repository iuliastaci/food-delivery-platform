package main.validator;

import main.exceptions.ValidationException;
import main.model.Venue;

public class VenueValidator implements Validator<Venue> {
    @Override
    public void validate(Venue entity) throws ValidationException {
        String errors = "";

        if(entity.getName().isBlank()) {
            errors += "Invalid name\n";
        }
        if(entity.getAddress().isBlank()){
            errors += "Invalid address\n";
        }
        if(entity.getPhoneNumber().isBlank()){
            errors += "Invalid phone number\n";
        }

        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
