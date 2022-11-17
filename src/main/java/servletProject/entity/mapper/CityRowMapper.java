package servletProject.entity.mapper;

import org.springframework.jdbc.core.RowMapper;
import servletProject.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityRowMapper implements RowMapper {
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
        City city = new City();
        city.setId(rs.getInt("id"));
        city.setName_city(rs.getString("name_city"));
        return city;
    }
}
