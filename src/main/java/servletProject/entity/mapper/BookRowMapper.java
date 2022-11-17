package servletProject.entity.mapper;

import org.springframework.jdbc.core.RowMapper;
import servletProject.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
public class BookRowMapper implements RowMapper<Book>{
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book(rs.getInt("id"), rs.getString("name"),
                rs.getString("author_name"), rs.getDouble("price"));
        return book;
    }
}
