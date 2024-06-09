package shift.entity.dto;

import person.model.Affiliate;
import person.model.Specialist;
import shift.entity.Shift;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShiftDTO {
    private Long id;
    private String description; // Descripción del turno
    private LocalDate date; // Fecha del turno
    private LocalTime time; // Hora del turno
    private Boolean state; // Estado vigente

    private Long specialistId; // ID del especialista
    private Long affiliatedId; // ID del afiliado

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Long specialistId) {
        this.specialistId = specialistId;
    }

    public Long getAffiliatedId() {
        return affiliatedId;
    }

    public void setAffiliatedId(Long affiliatedId) {
        this.affiliatedId = affiliatedId;
    }

    public Shift toEntity(ShiftDTO dto, Specialist specialist, Affiliate affiliated) {
        Shift shift = new Shift();
        //shift.setId(dto.getId());
        shift.setDescription(dto.getDescription());
        shift.setDate(dto.getDate());
        shift.setTime(dto.getTime());
        shift.setState(dto.getState());
        shift.setSpecialist(specialist);
        shift.setAffiliated(affiliated);
        return shift;
    }

    public static ShiftDTO toDTO(Shift shift) {
        ShiftDTO dto = new ShiftDTO();
        //dto.setId(shift.getId());
        dto.setDescription(shift.getDescription());
        dto.setDate(shift.getDate());
        dto.setTime(shift.getTime());
        dto.setState(shift.getState());
        dto.setSpecialistId(shift.getSpecialist().getId());
        dto.setAffiliatedId(shift.getAffiliated().getId());
        return dto;
    }
}