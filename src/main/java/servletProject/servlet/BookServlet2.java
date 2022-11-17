package servletProject.servlet;

import servletProject.service.impl.BookService2Impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookServlet2 extends HttpServlet {
    BookService2Impl bookService2 = new BookService2Impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService2.getAllBooksByParamsUsingJDBCTemplate(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookService2.addBookFromJson(req, resp);
    }
}
