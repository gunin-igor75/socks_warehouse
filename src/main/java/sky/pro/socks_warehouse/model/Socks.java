package sky.pro.socks_warehouse.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Носки
 */
@Entity
public class Socks{

    /** Индентификатор */
    @EmbeddedId
    private SocksId id;

    /** Количество */
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

    @Embeddable
    public static class SocksId implements Serializable {

        /** Цвет носков */
        private String color;

        /** Процент содержания хлопка */
        private Integer cottonPart;

        public SocksId() {
        }

        public SocksId(String color, Integer cottonPart) {
            this.color = color;
            this.cottonPart = cottonPart;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getCottonPart() {
            return cottonPart;
        }

        public void setCottonPart(Integer cottonPart) {
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
}
