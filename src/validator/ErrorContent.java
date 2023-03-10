package validator;

import lombok.*;
import lombok.experimental.UtilityClass;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class ErrorContent {
    private String message;

}
