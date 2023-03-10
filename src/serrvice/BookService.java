package serrvice;

import dao.BookDao;
import dto.BookDto;
import lombok.SneakyThrows;
import mapper.BookMapper;

import java.util.List;
import java.util.Optional;

public class BookService {
    private static final BookService INSTANCE = new BookService();

    private BookService() {
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
private static final BookMapper bookMapper = BookMapper.getInstance();
    private static final BookDao bookDao = BookDao.getInstance();
    private static final ImageService imageService = ImageService.getInstance();

    @SneakyThrows
    public static void save(BookDto bookDto) {
        var map = bookMapper.map(bookDto);
        imageService.uploadImage(bookDto.getCover().getSubmittedFileName(), bookDto.getCover().getInputStream());
        bookDao.insert(map);
    }

    public List<BookDto> getList() {
        return bookDao.findAll().stream().map(BookMapper::toDto2).toList();
    }

    public void deleteBookById(Long id) {
        bookDao.deleteById(id);
    }

    public Optional<Long> findBookId(String title) {
        return bookDao.findIdByTitle(title);

    }

    public BookDto getBookByUserId(Long id) {
        var bookEntity = bookDao.findBookById(id);
        return bookEntity.map(bookMapper::toDto).orElseThrow();
    }

    public void updateAmountMinus(Long id) {
        bookDao.updateAmount(id);
    }

    public void updateAmountAdd(Long id) {
        bookDao.updateAmountAdd(id);
    }
}
