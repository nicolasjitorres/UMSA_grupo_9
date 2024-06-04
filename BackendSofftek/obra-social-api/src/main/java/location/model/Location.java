package location.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Locations")
public class Location extends PanacheEntity {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", locality='" + locality + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(street, location.street) && Objects.equals(locality, location.locality) && Objects.equals(province, location.province) && Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, locality, province, country);
    }
}
