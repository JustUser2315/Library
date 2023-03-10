package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserBooksListDto {
    Long bookId;
    Long userId;
}
