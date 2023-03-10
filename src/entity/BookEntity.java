package entity;

import lombok.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class BookEntity {
   private Long id;
    private String title;
    private String author;
    private String year;
    private Long amount;
    private String cover;
    private String genre;
}
