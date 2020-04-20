package bank.infrastructure.entity;

import java.io.Serializable;

public class ClientProductsPrimaryKeys implements Serializable {

    private ClientDto client_id;
    private ProductDto product_id;

	public ClientDto getClientDto() {
		return client_id;
	}

	public void setClientDto(ClientDto clientDto) {
		this.client_id = clientDto;
	}

	public ProductDto getProductDto() {
		return product_id;
	}

	public void setProductDto(ProductDto productDto) {
		this.product_id = productDto;
	}
}