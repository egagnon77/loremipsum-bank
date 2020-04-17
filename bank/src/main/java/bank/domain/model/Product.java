package bank.domain.model;

import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import org.apache.commons.lang3.StringUtils;

public class Product {

    private Integer id;
    private String name;
    private Integer productType;
    private Integer productLevel;

    public Product() {

    }

    public Product(int id, String name, int productType, int productLevel) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        } else {
            throw new MissingParameterException("Client must have a name.");
        }
        if (ProductLevel.isValid(productLevel)) {
            this.productLevel = productLevel;
        } else {
            throw new InvalidArgumentException("Invalid product level.");
        }
        if (ProductType.isValid(productType)) {
            this.productType = productType;
        } else {
            throw  new InvalidArgumentException("Invalid product type.");
        }

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getProductType() {
        return productType;
    }

    public Integer getProductLevel() {
        return productLevel;
    }


}