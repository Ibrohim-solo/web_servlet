package servletProject.service;

import servletProject.entity.City;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CityService {
    void getAllCities(HttpServletRequest request, HttpServletResponse response);
    void addNewCity(City city);
}
