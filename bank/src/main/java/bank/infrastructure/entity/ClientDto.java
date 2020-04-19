package bank.infrastructure.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "client")
public class ClientDto implements Serializable {

    @Id
    private String id;

    @OneToMany
    @JoinTable(
        name="client_products",
    	joinColumns=@JoinColumn(name="client_id"),
        inverseJoinColumns=@JoinColumn(name="product_id")
    )
    private List<ProductDto> products = new ArrayList<>();

    private Integer productLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        this.productLevel = productLevel;
    }
}