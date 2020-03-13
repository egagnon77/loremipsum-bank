package bank.domain.model;

import org.apache.commons.lang3.StringUtils;
import bank.domain.exception.MissingParameterException;

public class Product {
    private Integer id;
    private String name;
    private Integer category;

    public Product() {

    }

    public Product(int id, String name, int category) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
        } else {
            throw new MissingParameterException("Client must have a name.");
        }
        
        this.id = id;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Integer getCategory() {
        return category;
    }
}