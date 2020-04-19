package bank.domain.model;

import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.StringUtils;

public class Client {

    private String name;
    private List<Product> products = new ArrayList<>();
    private Integer productLevel;

    public Client(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
            this.productLevel = ProductLevel.NORMAL.getValue();
        } else {
            throw new MissingParameterException("Client must have a name.");
        }
    }

    // Constructor with no args for cucumber test.
    public Client() {
    }

    public void upgradeStatus() {
        if (ProductLevel.NORMAL.getValue().equals(productLevel)) {
            setProductLevel(ProductLevel.VIP.getValue());
        }
    }

    public void downgradeStatus() {
        if (ProductLevel.VIP.getValue().equals(productLevel)) {
            setProductLevel(ProductLevel.NORMAL.getValue());
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public Integer getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(Integer productLevel) {
        if (ProductLevel.isValid(productLevel)) {
            this.productLevel = productLevel;
        } else {
            throw new InvalidArgumentException("Invalid productLevel");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}