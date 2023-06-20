package sky.pro.socks_warehouse.model;

import java.io.Serializable;
import java.util.Objects;

public class SocksId implements Serializable {

    private String color;

    private Integer cottonPart;

    public SocksId() {
    }

    public SocksId(String color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksId socksId = (SocksId) o;
        return Objects.equals(color, socksId.color) && Objects.equals(cottonPart, socksId.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
