package serrvice;

import dao.AdminDao;
import dao.UserDao;
import dto.AdminDto;
import exception.ValidationException;
import lombok.SneakyThrows;
import mapper.AdminMapper;
import validator.AdminLoginValidator;
import validator.ValidationResult;

import java.util.Optional;

public class AdminService {
    private static final AdminService INSTANCE = new AdminService();
    private AdminService(){}
    public static AdminService getInstance(){
        return INSTANCE;
    }
    private static final AdminMapper adminMapper = AdminMapper.getInstance();
    private static final UserDao userDao = UserDao.getInstance();
    private static final AdminDao adminDao = AdminDao.getInstance();
    public void save(AdminDto adminDto){
        var map = adminMapper.map(adminDto);
        adminDao.insert(map);
    }
    @SneakyThrows
    public  Optional<AdminDto> loginAdmin(String email, String password){
        var valid = new AdminLoginValidator().isValid(email, password);
        if(!valid.hasNoException()){
            throw new ValidationException(valid.getErrors());
        }
        var admin = adminDao.findByEmailAndPassword(email, password);
        return admin.map(adminMapper::mapToDto);
    }
    public static void deleteUserFromLibraryById(Long id){
        userDao.deleteById(id);
    }

}
