package edu.bbte.idde.vbim2101.backend.model;

public class Advertisement extends BaseEntity {
    private String title;
    private String address;
    private Integer price;
    private Integer surfaceArea;

    public Advertisement(String title, String address, Integer price, Integer surfaceArea) {
        super();
        this.title = title;
        this.address = address;
        this.price = price;
        this.surfaceArea = surfaceArea;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSurfaceArea() {
        return surfaceArea;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSurfaceArea(Integer surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    @Override
    public String toString() {
        return "Advertisement{"
                + "title='" + title + '\''
                + ", address='" + address + '\''
                + ", price=" + price
                + ", surfaceArea=" + surfaceArea
                + '}';
    }
}
