package bank.domain.model;

import bank.domain.exception.MissingParameterException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Client {

    private String name;

    public Client(String name) {
        if (StringUtils.isNotEmpty(name)) {
            this.name = name;
        } else {
            throw new MissingParameterException("Client must have a name.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}