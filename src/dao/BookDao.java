package dao;

import entity.BookEntity;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao implements Dao<BookEntity, Long>{
    private static final BookDao INSTANCE = new BookDao();
    private BookDao(){

    }
    public static BookDao getInstance(){
        return INSTANCE;
    }
    private static final String INSERT_SQL = """
insert into books (id, title, author, year, amount, cover, genre) values (?,?,?,?,?,?,?);
""";
    private static final String FIND_ALL_SQL = """
            select  * from books
            """;
    private static final String DELETE_BY_ID_SQL = "delete from books where id = ?";
    private static final String FIND_BOOK_ID_BY_NAME_SQL = "select * from books where title = ?";
    private static final String AMOUNT_UPDATE_SQL = """
            update books set amount = amount-1 where id = ?
            """;
    private static final String AMOUNT_UPDATE_ADD_SQL = """
            update books set amount = amount+1 where id = ?
            """;
    private static final String FIND_BOOK_BY_ID_SQL= "select * from books where id = ?";
   @SneakyThrows
    public Optional<BookEntity>findBookById(Long id){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_BOOK_BY_ID_SQL)){
            BookEntity book = null;
            statement.setObject(1, id);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                book = bookEntityBuilder(resultSet);
            }
            return Optional.ofNullable(book);

        }
    }
    @SneakyThrows
    public void updateAmount (Long id){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(AMOUNT_UPDATE_SQL)){
            statement.setObject(1, id);
            statement.executeUpdate();
        }
    }
    @SneakyThrows
    public void updateAmountAdd (Long id){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(AMOUNT_UPDATE_ADD_SQL)){
            statement.setObject(1, id);
            statement.executeUpdate();
        }
    }
   @SneakyThrows
    public Optional<Long> findIdByTitle(String title){
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(FIND_BOOK_ID_BY_NAME_SQL)){
            statement.setObject(1, title);
            Long id = 0L;
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                id = (Long) resultSet.getObject("id");
            }
            return Optional.ofNullable(id);
        }
    }

    @Override
    @SneakyThrows
    public void insert(BookEntity bookEntity) {
        try (var connection = ConnectionManager.get();
             var statement = connection.prepareStatement(INSERT_SQL)) {

            statement.setObject(1, bookEntity.getId());
            statement.setObject(2, bookEntity.getTitle());
            statement.setObject(3, bookEntity.getAuthor());
            statement.setObject(4, bookEntity.getYear());
            statement.setObject(5, bookEntity.getAmount());
            statement.setObject(6, bookEntity.getCover());
            statement.setObject(7, bookEntity.getGenre());
            statement.executeUpdate();
        }

    }

    @Override
    public Optional<BookEntity> findByEmailAndPassword(String email, String Password) {
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        var connection = ConnectionManager.get();
        try (var statement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    @SneakyThrows
    public List<BookEntity> findAll() {
        var connection = ConnectionManager.get();
        try (var statement = connection.prepareStatement(FIND_ALL_SQL)) {
        List<BookEntity> list = new ArrayList<>();
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(bookEntityBuilder(resultSet));
            }

            return list;
        }
    }

    @Override
    public void update(BookEntity bookEntity) {

    }
    @SneakyThrows
    private static BookEntity bookEntityBuilder (ResultSet resultSet){
        return new BookEntity(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("title", String.class),
                resultSet.getObject("author", String.class),
                resultSet.getObject("year", String.class),
                resultSet.getObject("amount", Long.class),
                resultSet.getObject("cover", String.class),
                resultSet.getObject("genre", String.class)

        );
    }
}
