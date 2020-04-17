package bank.infrastructure.entity;

import javax.persistence.*;

@Entity
@Table(name = "client_products")
public class ClientProductsDto {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="client_id")
    private ClientDto client;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductDto product;

    private Integer approbationStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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