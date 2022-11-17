package servletProject.entity;

public class Book {
    private Integer id;
    private String name;
    private String author_name;
    private Double price;

    public Book(Integer id, String name, String author_name, Double price) {
        this.id = id;
        this.name = name;
        this.author_name = author_name;
        this.price = price;
    }

    public Book(){
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" +
                "id: "  + id + ",\n" +
                "name: " + name + ",\n" +
                "author_name: " + author_name + ",\n" +
                "price: " + price + "\n" +
                "}";
    }
}
