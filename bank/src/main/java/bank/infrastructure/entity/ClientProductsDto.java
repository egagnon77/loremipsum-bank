package bank.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name = "client_products")
@IdClass(ClientProductsPrimaryKeys.class)
public class ClientProductsDto {

    @Id
    @ManyToOne
    @JoinColumn(name="client_id")
    private ClientDto client_id;

    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductDto product_id;

    private Integer approbationStatus;

    public Integer getApprobationStatus() {
        return approbationStatus;
    }

    public void setApprobationStatus(Integer approbationStatus) {
        this.approbationStatus = approbationStatus;
    }

    public ClientDto getClient_id() {
        return client_id;
    }

    public void setClient_id(ClientDto client_id) {
        this.client_id = client_id;
    }

    public ProductDto getProduct_id() {
        return product_id;
    }

    public void setProduct_id(ProductDto product_id) {
        this.product_id = product_id;
    }
}