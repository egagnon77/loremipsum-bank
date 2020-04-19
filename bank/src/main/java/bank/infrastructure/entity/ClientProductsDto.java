package bank.infrastructure.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_products")
public class ClientProductsDto implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="client_id")
    private ClientDto client;

    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductDto product;

    @Column(name = "approbation_status")
    private Integer approbationStatus;

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

    public Integer getApprobationStatus() {
        return approbationStatus;
    }

    public void setApprobationStatus(Integer approbationStatus) {
        this.approbationStatus = approbationStatus;
    }
}