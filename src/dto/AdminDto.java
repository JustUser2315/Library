package dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AdminDto {
    String firstName;
    String lastName;
    String birthday;
    String email;
    String password;
}
