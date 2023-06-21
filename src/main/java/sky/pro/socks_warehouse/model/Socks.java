package sky.pro.socks_warehouse.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Socks{

    @EmbeddedId
    private SocksId id;

    private Integer quantity;

    public Socks() {

    }

    public SocksId getId() {
        return id;
    }

    public void setId(SocksId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Socks(SocksId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
