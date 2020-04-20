package bank.infrastructure.entity;

import bank.domain.model.ApprobationStatus;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "client_products")
public class ClientProductsDto {

    @EmbeddedId
    private ClientProductsPrimaryKeys id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clientId")
    private ClientDto clientDto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private ProductDto productDto;

    @Column(name = "approbation_status")
    private Integer approbationStatus = ApprobationStatus.NOT_SET.getValue();

    public ClientProductsDto() {}

    public ClientProductsDto(ClientDto clientDto, ProductDto productDto) {
        this.clientDto = clientDto;
        this.productDto = productDto;
        this.id = new ClientProductsPrimaryKeys(clientDto.getId(), productDto.getId());
    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public Integer getApprobationStatus() {
        return approbationStatus;
    }

    public void setApprobationStatus(Integer approbationStatus) {
        this.approbationStatus = approbationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ClientProductsDto that = (ClientProductsDto) o;
        return Objects.equals(clientDto, that.clientDto) &&
            Objects.equals(productDto, that.productDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientDto, productDto);
    }
}