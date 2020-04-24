package client.domain.model;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer id;
    private String name;
    private Integer productLevel;
    private Integer productType;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", product type=" + productType +
            ", product level=" + productLevel +
            '}';
    }
}
