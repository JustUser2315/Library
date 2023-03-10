package entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class UserEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;


}
