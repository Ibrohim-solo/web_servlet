package servletProject.servlet;

import servletProject.dto.ResponseDto;
import servletProject.entity.Book;
import servletProject.repository.BookRepository;
import servletProject.service.impl.BookService2Impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BookServlet extends HttpServlet {
    private static Book book;
    private BookService2Impl bookService2 = new BookService2Impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        book = new Book(1, "Halol Luqma", "Rauf Jilasun", 25000D);
        writer.println(book);
        writer.close();
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        book.setName(req.getParameter("name"));
        book.setAuthor_name(req.getParameter("author_name"));
        PrintWriter writer = resp.getWriter();

        writer.println(writer);
        writer.close();
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        StringBuilder builder = new StringBuilder();
//
//        Enumeration enumeration = req.getParameterNames();
//
//        while(enumeration.hasMoreElements()){
//            String s = (String) enumeration.nextElement();
//
//            String [] parameters = req.getParameterValues(s);
//            builder.append("===== " + s + " ======\n");
//            for (String s1: parameters){
//                builder.append(s1);
//                builder.append("\n");
//            }
//        }
//        PrintWriter writer = resp.getWriter();
//        writer.println(builder);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bookService2.addBook(request, response);
    }
}
