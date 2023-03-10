package serrvice;

import dao.BookDao;
import dao.UserBooksListDao;
import dto.BookDto;
import dto.UserBooksListDto;
import entity.BookEntity;
import mapper.BookMapper;
import mapper.UserBooksListMapper;

import java.util.List;
import java.util.Optional;

public class UserBooksListService {
    private static final UserBooksListService INSTANCE = new UserBooksListService();
    private UserBooksListService(){}
    public static UserBooksListService getInstance(){
        return INSTANCE;
    }
    private static final BookService bookService = BookService.getInstance();
    private static final UserBooksListDao userBooksListDao = UserBooksListDao.getInstance();
    private static final BookDao bookDao = BookDao.getInstance();
    private static final UserBooksListMapper userBooksListMapper = UserBooksListMapper.getInstance();
    public  void save(UserBooksListDto userBooksListDto){
        var entity = userBooksListMapper.map(userBooksListDto);
        bookService.updateAmountMinus(userBooksListDto.getBookId());
        userBooksListDao.insert(entity);
    }
    public  List<BookDto> getMyBooksList(Long userId){
        var booksId = userBooksListDao.findBooksId(userId); // okay
        var list = booksId.stream().map(e -> bookDao.findBookById(e)).map((Optional<BookEntity> entity) -> BookMapper.toDto2(entity.get())).toList();
return list.stream().distinct().toList();

    }
    public  void deleteFromMyLibrary(Long bookId){
         userBooksListDao.deleteByBookId(bookId);
        bookService.updateAmountAdd(bookId);

    }
}
