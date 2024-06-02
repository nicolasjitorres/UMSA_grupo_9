package location.model;

public class Location {
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
