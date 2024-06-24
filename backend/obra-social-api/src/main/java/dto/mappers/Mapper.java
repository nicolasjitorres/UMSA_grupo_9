package dto.mappers;

import dto.AffiliateDTO;
import dto.ScheduleDTO;
import dto.ShiftDTO;
import model.Affiliate;
import model.Schedule;
import model.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper
{
    public static ScheduleDTO toScheduleDTO(Schedule schedule)
    {
        return new ScheduleDTO(schedule.getId(), schedule.getStartTime(), schedule.getEndTime(), schedule.getDayOfWeek(), schedule.getSpecialist().getId());
    }

    public static AffiliateDTO toAffiliateDTO(Affiliate affiliate)
    {
        return new AffiliateDTO(affiliate.getId(), affiliate.getFirstName(), affiliate.getLastName(), affiliate.getDni(), affiliate.getHealthInsuranceCode(), affiliate.getEmail());
    }
    public static List<AffiliateDTO> toAffiliateDTOList(List<Affiliate> affiliateList) {
        return affiliateList.stream()
                .map(Mapper::toAffiliateDTO)
                .collect(Collectors.toList());
    }

    public static ShiftDTO entityToDTO(Shift shift)
    {
        return new ShiftDTO(shift.getId(), shift.getDescription(), shift.getDate(),shift.getTime(),shift.getSpecialist().getId(),shift.getAffiliate().getId());
    }

    public static List<ShiftDTO> toShiftDTOList(List<Shift> shiftList) {
        List<ShiftDTO> shiftsDTO = new ArrayList<>();
        for (Shift shift : shiftList) {
            shiftsDTO.add(entityToDTO(shift));
        }
        return shiftsDTO;
    }

}
