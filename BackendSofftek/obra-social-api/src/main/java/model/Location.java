package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location extends PanacheEntity{

	@Column(name = "street")
    private String street;
	
	@Column(name = "locality")
    private String locality;
	
	@Column(name = "province")
    private String province;
	
	@Column(name = "country")
    private String country;
    
//    @JsonIgnore
//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	private List<Specialist> specialists; // especialistas asignados a la misma direccion

}