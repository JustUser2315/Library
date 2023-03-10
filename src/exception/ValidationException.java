package exception;

import lombok.Getter;
import validator.ErrorContent;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends RuntimeException{
    @Getter
  private  List<ErrorContent> errors = new ArrayList<>();
    public ValidationException(List<ErrorContent> errors){
        this.errors = errors;
    }
}
