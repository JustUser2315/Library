package dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {
    String id;
    String title;
    String author;
    String year;
    String amount;
    Part cover;
    String coverString;
    String genre;
}
