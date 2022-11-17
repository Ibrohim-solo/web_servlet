package servletProject.service.impl;

import servletProject.dto.CityDto;
import servletProject.entity.City;
import servletProject.mapper.CityMapper;
import servletProject.repository.CityJDBCRepository;
import servletProject.service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.prefs.Preferences;

public class CityServiceImpl implements CityService {
    CityJDBCRepository repository = new CityJDBCRepository();

    @Override
    public void getAllCities(HttpServletRequest request, HttpServletResponse response) {
        List<City> cityList = repository.getAllCities();
        List<CityDto> cityDtoList = cityList.stream()
                .map(CityMapper::toDto)
                .toList();

        response.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        writer.println(cityDtoList);
    }
    @Override
    public void addNewCity(City city){
        repository.addCity(city);
    }
}
