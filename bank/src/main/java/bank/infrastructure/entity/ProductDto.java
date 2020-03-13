package bank.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductDto {
    
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}