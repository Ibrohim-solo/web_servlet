package servletProject.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import servletProject.dto.ResponseDto;
import servletProject.entity.Book;
import servletProject.mapper.BookMapper;
import servletProject.repository.BookJDBCRepository;
import servletProject.repository.BookRepository;
import servletProject.service.Book2Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeMap;

public class BookService2Impl implements Book2Service {
    BookJDBCRepository jdbcrepository = new BookJDBCRepository();
    BookRepository bookRepository = BookRepository.getInstance();
    @Override
    public void  getAllBooksByParamsUsingJDBCTemplate(HttpServletRequest request,
                                                     HttpServletResponse response) {
        Enumeration<String> keys = request.getParameterNames();

        TreeMap<String, String> map = new TreeMap<>();

        while (keys.hasMoreElements()){
            String key = keys.nextElement();
            map.put(key, request.getParameter(key));
        }

        List<Book> bookList = jdbcrepository.getAllBookByParamsUsingJDBCTemplate(map);

        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        writer.println(bookList);
        writer.close();
    }

    @Override
    public void getAllBooksByParamsUsingJDBC(HttpServletRequest request, HttpServletResponse response) {
        TreeMap<String, String> map = new TreeMap<>();

        Enumeration<String> keys = request.getParameterNames();
        while (keys.hasMoreElements()){
            String key = keys.nextElement();

        }
    }

    public void addBookFromJson(HttpServletRequest req, HttpServletResponse response){
        try {
            byte[] body = req.getInputStream().readAllBytes();
            ObjectMapper objectMapper = new ObjectMapper();
            Book book = objectMapper.readValue(body, Book.class);
            bookRepository.addBookUsingFromJson(book);

            PrintWriter out = response.getWriter();
            out.println(BookMapper.toDto(book));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addBook(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> keys = request.getParameterNames();
        Book book = new Book();

        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            switch (key) {
                case "name" -> book.setName(request.getParameter(key));
                case "author_name" -> book.setAuthor_name(request.getParameter(key));
                case "price" -> book.setPrice(Double.parseDouble(request.getParameter(key)));
            }
        }

        ResponseDto<Book> responseDto = bookRepository.addBookWithParams(book);

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.println(responseDto);
    }
}
