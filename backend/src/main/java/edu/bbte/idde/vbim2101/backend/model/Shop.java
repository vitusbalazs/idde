package edu.bbte.idde.vbim2101.backend.model;

public class Shop extends BaseEntity {
    private String name;
    private String address;
    private Integer rating; // 1-5

    public Shop() {
        super();
    }

    public Shop(String name, String address, Integer rating) {
        super();
        this.name = name;
        this.address = address;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Shop{"
                + "name='" + name + '\''
                + ", address='" + address + '\''
                + ", rating=" + rating
                + '}';
    }
}
