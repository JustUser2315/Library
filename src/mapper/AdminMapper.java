package mapper;

import dto.AdminDto;
import entity.AdminEntity;
import util.DateFormatHelper;

public class AdminMapper implements Mapper<AdminDto, AdminEntity> {
    private static final AdminMapper INSTANCE = new AdminMapper();
    private AdminMapper(){}
    public static AdminMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public AdminEntity map(AdminDto adminDto) {
        return AdminEntity.builder()
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .birthday(DateFormatHelper.format(adminDto.getBirthday()))
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .build();
    }

    public  AdminDto mapToDto(AdminEntity adminEntity) {
        return AdminDto.builder()
                .firstName(adminEntity.getFirstName())
                .lastName(adminEntity.getLastName())
                .email(adminEntity.getEmail())
                .birthday(adminEntity.getBirthday().toString())
                .build();
    }


}
