package servletProject.servlet;

import servletProject.dto.CityDto;
import servletProject.entity.City;
import servletProject.repository.CityJDBCRepository;
import servletProject.service.impl.CityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class CityServlet extends HttpServlet {
    CityServiceImpl cityService = new CityServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        City city = new City();

        Enumeration keys = req.getParameterNames();
        while (keys.hasMoreElements()){
            String key = (String)keys.nextElement();
            if (key.equals("name_city")){
                city.setName_city(req.getParameter(key));
            }
            if (key.equals("id")){
                city.setId(Integer.valueOf(req.getParameter(key)));
            }
        }

        cityService.addNewCity(city);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cityService.getAllCities(req, resp);
    }
}
