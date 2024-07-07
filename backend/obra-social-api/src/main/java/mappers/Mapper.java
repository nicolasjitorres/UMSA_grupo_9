package mappers;

import dto.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.*;
import model.enums.Role;
import service.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Mapper {

    @Inject
    private LocationService locationService;

    public static ScheduleDTO toScheduleDTO(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(), schedule.getStartTime(), schedule.getEndTime(),
                schedule.getDayOfWeek(), schedule.getSpecialist().getId());
    }

    public static AffiliateDTO toAffiliateDTO(Affiliate affiliate) {
        return new AffiliateDTO(affiliate.getId(), affiliate.getFirstName(), affiliate.getLastName(),
                affiliate.getDni(), affiliate.getHealthInsuranceCode(), affiliate.getEmail());
    }

    public static List<AffiliateDTO> toAffiliateDTOList(List<Affiliate> affiliateList) {
        return affiliateList.stream()
                .map(Mapper::toAffiliateDTO)
                .collect(Collectors.toList());
    }

    public static ShiftDTO toShiftDTO(Shift shift) {
        return new ShiftDTO(shift.getId(), shift.getDescription(), shift.getDate(), shift.getTime(),
                shift.getSpecialist().getId(), shift.getAffiliate().getId());
    }

    public static List<ScheduleDTO> toScheduleDtoList(List<Schedule> scheduleList) {
        List<ScheduleDTO> schedules = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            schedules.add((toScheduleDTO(schedule)));
        }
        return schedules;
    }

    public static List<ShiftDTO> toShiftDTOList(List<Shift> shiftList) {
        List<ShiftDTO> shiftsDTO = new ArrayList<>();
        for (Shift shift : shiftList) {
            shiftsDTO.add(toShiftDTO(shift));
        }
        return shiftsDTO;
    }

    public SpecialistDTO toSpecialistDTO(Specialist specialist) {
        if (specialist == null) {
            return null;
        }
        SpecialistDTO specialistDTO = new SpecialistDTO();
        specialistDTO.setId(specialist.getId());
        specialistDTO.setSpeciality(specialist.getSpeciality());
        Location location = locationService.findLocationById(specialist.getLocation().getId());
        specialistDTO.setLocation(location);
        specialistDTO.setFirstName(specialist.getFirstName());
        specialistDTO.setLastName(specialist.getLastName());
        specialistDTO.setDni(specialist.getDni());
        specialistDTO.setEmail(null);
        specialistDTO.setPassword(null);
        specialistDTO.setRole(specialist.getRole());

        return specialistDTO;
    }

    public Specialist toSpecialist(SpecialistDTO specialistDto) {
        if (specialistDto == null) {
            return null;
        }
        Specialist specialist = new Specialist();
        specialist.setId(specialistDto.getId());
        specialist.setSpeciality(specialistDto.getSpeciality());
        specialist.setLocation(specialistDto.getLocation());
        specialist.setFirstName(specialistDto.getFirstName());
        specialist.setLastName(specialistDto.getLastName());
        specialist.setDni(specialistDto.getDni());
        specialist.setEmail(specialistDto.getEmail());
        specialist.setPassword(specialistDto.getPassword());
        specialist.setRole(Role.USER);

        return specialist;
    }

    public List<SpecialistDTO> toSpecialistDTOList(List<Specialist> specialistsList) {
        List<SpecialistDTO> specialistDTOS = new ArrayList<>();
        for (Specialist specialist : specialistsList) {
            specialistDTOS.add(toSpecialistDTO(specialist));
        }
        return specialistDTOS;
    }

    public PrescriptionDTO toPrescriptionDto(Prescription prescription) {
        return new PrescriptionDTO(prescription.getId(), prescription.getDescription(),
                prescription.getShift().getId());

    }

}
