package servletProject.repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import servletProject.configuration.DataBaseConfig;
import servletProject.entity.*;
import servletProject.dto.*;
import servletProject.mapper.BookMapper;

public class BookRepository {
   private static BookRepository bookRepository;
   private Transaction transaction;
   private Session session;
   private SessionFactory sessionFactory;
   private BookRepository(){
      sessionFactory = DataBaseConfig.sessionFactory();
      session = DataBaseConfig.getSession();
      transaction = session.getTransaction();
   }

   public static BookRepository getInstance(){
      if (bookRepository != null){
         return bookRepository;
      }

      bookRepository = new BookRepository();
      return bookRepository;
   }

   public ResponseDto<Book> addBookWithParams(Book book){
      transaction.begin();
      session.persist(book);
      transaction.commit();

      return new ResponseDto(200, "OK", true, BookMapper.toDto(book));
   }

   public void addBookUsingFromJson(Book book){
      transaction.begin();
      session.persist(book);
      transaction.commit();
   }

   public ResponseDto<Book> addBookNoSingleTon(Book book){
      session = sessionFactory.openSession();
      transaction = session.beginTransaction();

      try{
         session.persist(book);
         transaction.commit();
      }catch (Exception e){
         e.printStackTrace();
         transaction.rollback();
      }finally {
         session.close();
      }

      return new ResponseDto(200, "OK", true, BookMapper.toDto(book));
   }
}
