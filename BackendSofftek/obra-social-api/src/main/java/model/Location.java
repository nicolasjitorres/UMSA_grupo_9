package model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location extends PanacheEntity {
    @Column(name = "street")
    private String street;
    @Column(name = "locality")
    private String locality;
    @Column(name = "province")
    private String province;
    @Column(name = "country")
    private String country;

}
