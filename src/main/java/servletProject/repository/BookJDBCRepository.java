package servletProject.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import servletProject.configuration.DataBaseConfig;
import servletProject.entity.Book;
import servletProject.entity.mapper.BookRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
public class BookJDBCRepository {
    private DataBaseConfig config = DataBaseConfig.getInstance();
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(config.dataSourcePostgres());
    public List<Book> getAllBookByParamsUsingJDBCTemplate(TreeMap<String, String> mapParams){
        StringBuilder builder = new StringBuilder();
        queryBuilder(builder, mapParams);
        String queryString = "select * from book where 1=1" + builder;

        PreparedStatementSetter statement = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                setParameters(ps, mapParams);
            }
        };
        return jdbcTemplate.query(queryString, statement, new BookRowMapper());
    }

    public List<Book> getAllBooksByParamsUsingJDBC(TreeMap<String, String> map) throws ClassNotFoundException, SQLException {
        getClass().getClassLoader().loadClass("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:1997/postgres",
                "postgres","123");

        StringBuilder builder = new StringBuilder();
        String queryString = "select * from book where 1=1" + builder;
        queryBuilder(builder, map);

        PreparedStatement preparedStatement = connection.prepareStatement(queryString);

        setParameters(preparedStatement, map);

        ResultSet resultSet = preparedStatement.getResultSet();

        List<Book> bookList = new ArrayList<>();
        while (resultSet.next()){
            Book book = new BookRowMapper().mapRow(resultSet, resultSet.getRow());
            bookList.add(book);
        }

        return bookList;
    }

    public void queryBuilder(StringBuilder builder, TreeMap<String, String> map){
        if (map.containsKey("author_name")){
            builder.append(" and author_name = ?");
        }
        if (map.containsKey("id")){
            builder.append(" and id = ?");
        }
        if(map.containsKey("name")){
            builder.append(" and name = ?");
        }
        if (map.containsKey("price")){
            builder.append(" and price = ?");
        }
    }


    public void setParameters(PreparedStatement statement, TreeMap<String, String> map){
        AtomicInteger atomicInteger = new AtomicInteger(1);

        map.forEach((key, value) -> {
                try{
                    switch (key){
                        case "id":
                            statement.setInt(atomicInteger.getAndIncrement(), Integer.parseInt(value));
                            break;

                        case "name": case "author_name":
                            statement.setString(atomicInteger.getAndIncrement(), value); break;
                        case "price":
                            statement.setDouble(atomicInteger.getAndIncrement(), Double.parseDouble(value));
                            break;
                    }
                }catch (SQLException s){
                    s.printStackTrace();
                }
        });
    }
}
