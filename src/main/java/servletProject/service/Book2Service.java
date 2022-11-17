package servletProject.service;

import servletProject.dto.BookDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Book2Service {
    void getAllBooksByParamsUsingJDBCTemplate(HttpServletRequest request, HttpServletResponse response);
    void getAllBooksByParamsUsingJDBC(HttpServletRequest request, HttpServletResponse response);
    void addBook(HttpServletRequest request, HttpServletResponse response);
}
