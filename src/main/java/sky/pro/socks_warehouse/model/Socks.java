package sky.pro.socks_warehouse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(SocksId.class)
public class Socks{

    @Id
    private String color;

    @Id
    private Integer cottonPart;

    private Integer quantity;


}
