package bank.infrastructure.entity;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientDto {

    @Id
    @Column(name = "client_id")
    private String id;

    @OneToMany(
        mappedBy = "clientDto",
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<ClientProductsDto> clientProductsDtos = new ArrayList<>();

    @Column(name = "product_Level")
    private Integer productLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ClientProductsDto> getClientProductsDtos() {
        return clientProductsDtos;
    }

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        this.productLevel = productLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(id, clientDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}