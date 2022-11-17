package servletProject.mapper;

import servletProject.dto.CityDto;
import servletProject.entity.City;

public class CityMapper {

    public static City toEntity(CityDto cityDto){
        if (cityDto == null){
            return null;
        }

        City city = new City(cityDto.getId(), cityDto.getName_city());

        return city;
    }

    public static CityDto toDto(City city){
        if (city == null){
            return null;
        }

        CityDto cityDto = new CityDto(city.getId(), city.getName_city());

        return cityDto;
    }
}
