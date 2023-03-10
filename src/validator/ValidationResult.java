package validator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class ValidationResult {
    @Getter
    private final List<ErrorContent> errors = new ArrayList<>();
    public void addError(ErrorContent error) {
        this.errors.add(error);
    }
    public boolean hasNoException(){
        return errors.isEmpty();
    }
}
