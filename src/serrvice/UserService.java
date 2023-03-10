package serrvice;

import dao.UserDao;
import dto.UserDto;
import mapper.UserMapper;
import validator.AdminLoginValidator;
import validator.UserLoginValidator;
import validator.UserRegistrationValidator;
import exception.ValidationException;

import java.util.List;
import java.util.Optional;

public class UserService {
private static final     UserService INSTANCE = new UserService();
private UserService(){}
    public static UserService getInstance(){
    return INSTANCE;
    }
    private static final UserDao userDao = UserDao.getInstance();
private static final UserMapper userMapper = UserMapper.getInstance();

    public  void save(UserDto userDto) {
        var valid = UserRegistrationValidator.isValid(userDto);
        if(!valid.hasNoException()){
            throw new ValidationException(valid.getErrors());
        }
        var map = userMapper.map(userDto);
        userDao.insert(map);

    }

    public  List<UserDto> getList() {
        var users = userDao.findAll();
        return users.stream().map(userMapper::mapToDto).toList();
    }

    public  Optional<UserDto> loginUser(String email, String password) {
        var valid = new UserLoginValidator().isValid(email, password);
        if(!valid.hasNoException()){
            throw new ValidationException(valid.getErrors());
        }
        var user = userDao.findByEmailAndPassword(email, password);
        return user.map(userMapper::mapToDto);
    }

}
