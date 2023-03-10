package mapper;

import dto.BookDto;
import entity.BookEntity;
import lombok.SneakyThrows;

public class BookMapper implements Mapper<BookDto, BookEntity> {
    private static final BookMapper INSTANCE = new BookMapper();
    private BookMapper(){}
    public static BookMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public BookEntity map(BookDto bookDto) {
        return BookEntity.builder()
                .id(Long.parseLong(bookDto.getId()))
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .year(bookDto.getYear())
                .amount(Long.parseLong(bookDto.getAmount()))
                .cover(bookDto.getCover().getSubmittedFileName())
                .genre(bookDto.getGenre())
                .build();

    }

    @SneakyThrows
    public  BookDto toDto(BookEntity entity) {
        return BookDto.builder()
                .id(String.valueOf(entity.getId()))
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .year(entity.getYear())
                .amount(String.valueOf(entity.getAmount()))
                .build();
    }
    public static BookDto toDto2(BookEntity entity) {
        return BookDto.builder()
                .id(String.valueOf(entity.getId()))
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .year(entity.getYear())
                .amount(String.valueOf(entity.getAmount()))
                .coverString(entity.getCover())
                .genre(entity.getGenre())
                .build();
    }
}
