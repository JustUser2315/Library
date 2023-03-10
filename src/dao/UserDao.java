package dao;

import entity.UserEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<UserEntity, Long>{
    private static final UserDao INSTANCE = new UserDao();
    private  UserDao (){

    }
    public static UserDao getInstance(){
        return INSTANCE;
    }
    private static final String FIND_ALL_SQL = "select * from users";
    private static final String INSERT_SQL = """
            insert into users(first_name, last_name, birthday, email, password) VALUES (?,?,?,?,?);
            """;
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD_SQL = """
            select * from users where email = ? and password = ?;
            """;
    private static final String DELETE_BY_ID_SQL = "delete from users where id = ?";
    @Override
    @SneakyThrows
    public void insert(UserEntity userEntity) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setObject(1, userEntity.getFirstName());
            statement.setObject(2, userEntity.getLastName());
            statement.setObject(3, userEntity.getBirthday());
            statement.setObject(4, userEntity.getEmail());
            statement.setObject(5, userEntity.getPassword());
            statement.executeUpdate();


        }
    }

    @Override
    @SneakyThrows
    public Optional<UserEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD_SQL)) {
            UserEntity user = null;
            statement.setObject(1, email);
            statement.setObject(2, password);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = builderUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
try(var connection = ConnectionManager.get();
    var statement = connection.prepareStatement(DELETE_BY_ID_SQL)){
    statement.setObject(1, id);
    statement.executeUpdate();
}
    }

    @Override
    @SneakyThrows
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_ALL_SQL)){
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                users.add(builderUser(resultSet));
            }


        }

        return users;
    }
    @Override
    public void update(UserEntity userEntity) {

    }

    @SneakyThrows
    private static UserEntity builderUser(ResultSet resultSet){
        return new UserEntity(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class),
                resultSet.getDate("birthday").toLocalDate(),
                resultSet.getObject("email", String.class),
                resultSet.getObject("password", String.class)
        );
    }
}
