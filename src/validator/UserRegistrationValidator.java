package validator;

import dto.UserDto;
import util.DateFormatHelper;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class UserRegistrationValidator {
    public static ValidationResult isValid(UserDto userDto){
        var validationResult = new ValidationResult();
        if(userDto.getFirstName().isEmpty()){
            validationResult.addError(new ErrorContent("First name mustn't be empty"));
        }
        if(!userDto.getFirstName().matches("[^А-Яа-я]+")){
            validationResult.addError(new ErrorContent("Please, try again and input your first name using English layout"));
        }
        if(!userDto.getFirstName().matches("[^0-9]+")){
            validationResult.addError(new ErrorContent("Your first name shouldn't contain digit symbols"));
        }
        if(userDto.getLastName().isEmpty()){
            validationResult.addError(new ErrorContent("Last name mustn't be empty"));
        }
        if(!userDto.getLastName().matches("[^А-Яа-я]+")){
            validationResult.addError(new ErrorContent("Please, try again and input your last name using English layout"));
        }
        if(!userDto.getLastName().matches("[^0-9]+")){
            validationResult.addError(new ErrorContent("Your last name shouldn't contain digit symbols"));
        }
        if(DateFormatHelper.format(userDto.getBirthday()).getYear()>2005) {
            validationResult.addError(new ErrorContent("Your age isn't corresponding to our requirements"));
        }
        if (!userDto.getEmail().matches("\\w+@\\w+\\.\\w+")){
            validationResult.addError(new ErrorContent("Please, input correct email address"));
        }
        if (userDto.getPassword().length()<8){
            validationResult.addError(new ErrorContent("Password length must be 8 or more characters"));
        }
        return validationResult;

    }
}
