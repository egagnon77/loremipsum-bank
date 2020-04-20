package bank.infrastructure.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClientProductsPrimaryKeys implements Serializable {

    @Column(name = "client_id")
	private String clientId;

    @Column(name = "product_id")
    private Integer productId;

	public ClientProductsPrimaryKeys() {
	}

	public ClientProductsPrimaryKeys(String clientId, Integer productId) {
		this.clientId = clientId;
		this.productId = productId;
	}

	public String getClientId() {
		return clientId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ClientProductsPrimaryKeys that = (ClientProductsPrimaryKeys) o;
		return Objects.equals(clientId, that.clientId) &&
			Objects.equals(productId, that.productId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, productId);
	}
}