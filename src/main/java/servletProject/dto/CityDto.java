package servletProject.dto;

public class CityDto {
    private Integer id;
    private String name_city;

    public CityDto(Integer id, String name_city) {
        this.id = id;
        this.name_city = name_city;
    }

    public Integer getId() {
        return id;
    }

    public String getName_city() {
        return name_city;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    @Override
    public String toString() {
        return "{" +
                " id: " + id + ",\n" +
                " name_city: " + name_city + "\n" +
                "}";
    }
}
