package dao;

import entity.AdminEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class AdminDao implements Dao<AdminEntity, Integer> {
    private static final AdminDao INSTANCE = new AdminDao();

    private AdminDao() {
    }

    public static AdminDao getInstance() {
        return INSTANCE;
    }

    private static final String INSERT_SQL = """
            insert into admin (first_name, last_name, birthday, email, password) 
            values (?,?,?,?,?)
             """;
    private static final String FIND_ADMIN_BY_EMAIL_AND_PASSWORD = "select * from admin where email = ? and password = ?";

    private static final String FIND_ADMIN_BY_ID = "select*from admin where id = ?";


    @SneakyThrows
    public Optional<AdminEntity> findById(Integer id) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ADMIN_BY_ID)) {
            statement.setObject(1, id);
            AdminEntity admin = null;
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = adminBuilder(resultSet);
            }


            return Optional.ofNullable(admin);
        }
    }

    @Override
    @SneakyThrows
    public void insert(AdminEntity adminEntity) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setObject(1, adminEntity.getFirstName());
            statement.setObject(2, adminEntity.getLastName());
            statement.setObject(3, adminEntity.getBirthday());
            statement.setObject(4, adminEntity.getEmail());
            statement.setObject(5, adminEntity.getPassword());
            statement.executeUpdate();

        }
    }

    @Override
    @SneakyThrows
    public Optional<AdminEntity> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL_AND_PASSWORD)) {
            statement.setObject(1, email);
            statement.setObject(2, password);
            AdminEntity admin = null;
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                admin = adminBuilder(resultSet);
            }


            return Optional.ofNullable(admin);
        }
    }

    @Override
    public void deleteById(Integer id) {
    }


    @Override
    public List<AdminEntity> findAll() {
        return null;
    }

    @Override
    public void update(AdminEntity adminEntity) {

    }

    @SneakyThrows
    private static AdminEntity adminBuilder(ResultSet r) {
        return new AdminEntity(
                r.getObject("id", Integer.class),
                r.getObject("first_name", String.class),
                r.getObject("last_name", String.class),
                r.getDate("birthday").toLocalDate(),
                r.getObject("email", String.class),
                r.getObject("password", String.class)
        );

    }
}
