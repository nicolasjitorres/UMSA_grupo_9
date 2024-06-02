package location.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Location extends PanacheEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String locality;
    private String province;
    private String country;
    public Location() {
    }
    public Location(Long id, String street, String locality, String province, String country) {
        this.id = id;
        this.street = street;
        this.locality = locality;
        this.province = province;
        this.country = country;
    }
}
