package client.domain.model;

import java.util.List;

public class Client {

    private String name;
    private List<Product> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
