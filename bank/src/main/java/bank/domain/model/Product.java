package bank.domain.model;

import bank.domain.exception.InvalidArgumentException;
import bank.domain.exception.MissingParameterException;
import org.apache.commons.lang3.StringUtils;

public class Product {

    private Integer id;
    private String name;
    private Integer productType;
    private Integer productLevel;
    private Integer approbationStatus;

    public Product() {

    }

    public Product(int id, String name, int productType, int productLevel, int approbationStatus) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        } else {
            throw new MissingParameterException("Client must have a name.");
        }
        if (ProductType.isValid(productType)) {
            this.productType = productType;
        } else {
            throw new InvalidArgumentException("Invalid product type.");
        }
        if (ProductLevel.isValid(productLevel)) {
            this.productLevel = productLevel;
        } else {
            throw new InvalidArgumentException("Invalid product level.");
        }
        validateApprobationStatus(approbationStatus, productType);
        this.setApprobationStatus(approbationStatus);

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

    public Integer getApprobationStatus() {
        return approbationStatus;
    }

    public void setApprobationStatus(Integer approbationStatus) {
        validateApprobationStatus(approbationStatus, this.productType);
        this.approbationStatus = approbationStatus;
    }

    private void validateApprobationStatus(Integer approbationStatus, Integer productType) {
        if (ApprobationStatus.isValid(approbationStatus)) {
            if (productType.equals(ProductType.AUTOMATIC.getValue()) && !approbationStatus
                .equals(ApprobationStatus.SUBSCRIBED.getValue())) {
                throw new InvalidArgumentException(
                    "A Product of type 'Automatic' cannot have an approbation Status other than 'Subscribe'");
            }

        } else {
            throw new InvalidArgumentException("Invalid approbation.");
        }

    }
}