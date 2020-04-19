package bank.infrastructure.entity;

import bank.domain.exception.NotFoundException;
import bank.domain.model.ApprobationStatus;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductDto {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ClientProductsDto> clientProductsDto = new HashSet<>();

    private String name;

    private Integer productLevel;

    private Integer productType;

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

    public Integer getApprobationStatus() {
        for (Iterator<ClientProductsDto> iterator = this.clientProductsDto.iterator();
            iterator.hasNext(); ) {
            ClientProductsDto current = iterator.next();
            if (current.getProduct().id.equals(this.id)) {
                return current.getApprobationStatus();
            }
        }
        return ApprobationStatus.NOT_SET.getValue();
    }
}