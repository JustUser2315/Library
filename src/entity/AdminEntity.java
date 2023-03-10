package entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminEntity {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
}
