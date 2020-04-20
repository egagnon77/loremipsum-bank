package bank.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductDto {

    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "product_level")
    private Integer productLevel;

    @Column(name = "product_type")
    private Integer productType;

    @OneToMany(
        mappedBy = "productDto",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ClientProductsDto> clientProductsDtos = new ArrayList<>();

    public List<ClientProductsDto> getClientProductsDtos() {
        return clientProductsDtos;
    }

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

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        this.productLevel = productLevel;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto productDto = (ProductDto) o;
        return Objects.equals(id, productDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}