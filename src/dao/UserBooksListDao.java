package dao;

import entity.UserBooksListEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.util.ArrayList;
import java.util.List;

public class UserBooksListDao {
    private static final UserBooksListDao INSTANCE = new UserBooksListDao();
    private UserBooksListDao(){

    }
    public static UserBooksListDao getInstance(){
        return INSTANCE;
    }
    private static final String INSERT_SQL = """
            insert into user_books_list (book_id, user_id) VALUES (?,?);
            """;
    private static final String FIND_BOOK_ID_BY_USER_ID = "select book_id from user_books_list where user_id = ?";
    private static final String DELETE_BOOK_BY_ID = """
            delete from user_books_list where book_id = ?;
            """;
    @SneakyThrows
    public void insert(UserBooksListEntity entity){
       try (var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(INSERT_SQL)){
           statement.setObject(1, entity.getBookId());
           statement.setObject(2, entity.getUserId());
           statement.executeUpdate();
       }
    }
    @SneakyThrows
    public List<Long> findBooksId(Long userId){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_BOOK_ID_BY_USER_ID)){
            List<Long>id = new ArrayList<>();
            statement.setObject(1, userId);
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                id.add(resultSet.getLong("book_id"));
            }
            return id;
        }
    }
    @SneakyThrows
    public void deleteByBookId(Long bookId){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(DELETE_BOOK_BY_ID)){
            statement.setObject(1, bookId);
            statement.executeUpdate();

        }
    }

}
