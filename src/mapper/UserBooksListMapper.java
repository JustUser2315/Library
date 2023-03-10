package mapper;

import dto.UserBooksListDto;
import entity.UserBooksListEntity;
import lombok.SneakyThrows;

public class UserBooksListMapper implements Mapper<UserBooksListDto, UserBooksListEntity> {
    private static final UserBooksListMapper INSTANCE = new UserBooksListMapper();
    private UserBooksListMapper(){}
    public static UserBooksListMapper getInstance(){
        return INSTANCE;
    }

    @Override
    @SneakyThrows
    public UserBooksListEntity map(UserBooksListDto userBooksListDto) {
        return UserBooksListEntity.builder()
                .bookId(Long.valueOf(userBooksListDto.getBookId()))
                .userId(Long.valueOf(userBooksListDto.getUserId()))
                .build();
    }

}
