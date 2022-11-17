package servletProject.mapper;

import servletProject.dto.BookDto;
import servletProject.entity.Book;

public class BookMapper {
    public static Book toEntity(BookDto book){
        if (book == null){
            return null;
        }
        return new Book(book.getId(), book.getName(), book.getAuthor_name(), book.getPrice());
    }

    public static BookDto toDto(Book book){
        if (book == null){
            return null;
        }
        return new BookDto(book.getId(), book.getName(), book.getAuthor_name(), book.getPrice());
    }
}
