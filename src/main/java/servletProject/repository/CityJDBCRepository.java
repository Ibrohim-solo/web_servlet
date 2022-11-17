package servletProject.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import servletProject.configuration.DataBaseConfig;
import servletProject.entity.City;
import servletProject.entity.mapper.CityRowMapper;
import servletProject.mapper.CityMapper;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CityJDBCRepository {
    DataBaseConfig dataBaseConfig = DataBaseConfig.getInstance();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataBaseConfig.dataSourcePostgres());

    public List<City> getAllCities(){
        List<City> cityList = jdbcTemplate.query("select * from city", new CityRowMapper());
        return cityList;
    }

    public void addCity(City city){
        jdbcTemplate.update("insert into city(id, name_city) values (?,?)", city.getId(), city.getName_city());
    }
}
