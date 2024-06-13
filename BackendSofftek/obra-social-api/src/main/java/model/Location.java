package model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location{
	@Id
	@GeneratedValue
	private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "locality")
    private String locality;
    @Column(name = "province")
    private String province;
    @Column(name = "country")
    private String country;

    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
	private List<Specialist> specialists; // especialistas asignados a la misma direccion

}