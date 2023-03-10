package mapper;

import dto.UserDto;
import entity.UserEntity;
import util.DateFormatHelper;

public class UserMapper implements Mapper<UserDto, UserEntity>{
    private static final UserMapper INSTANCE = new UserMapper();
    private UserMapper(){}
    public static UserMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public UserEntity map(UserDto userDto) {
        return UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthday(DateFormatHelper.format(userDto.getBirthday()))
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
    public  UserDto mapToDto(UserEntity entity){
       return UserDto.builder()
               .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthday(entity.getBirthday().toString())
                .email(entity.getEmail())
                .password(entity.getPassword())
                 .build();

    }
}
