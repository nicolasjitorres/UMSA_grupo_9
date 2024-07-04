import React, {
  createContext,
  useEffect,
  ReactNode,
  useContext,
  useState,
} from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  Affiliate,
  AffiliateDTO,
  Prescription,
  PrescriptionDTO,
  Schedule,
  ScheduleDTO,
  Shift,
  ShiftDTO,
  Specialist,
  SpecialistDTO,
} from "../redux/type";
import {
  addShift,
  deleteShift,
  fetchShift,
  updateShift,
} from "../redux/slices/ShiftSlice";
import {
  addSpecialist,
  deleteSpecialist,
  fetchSpecialists,
  updateSpecialist,
} from "../redux/slices/SpecialistSlice";
import {
  addSchedule,
  deleteSchedules,
  fetchSchedules,
  updateSchedule,
} from "../redux/slices/SchedulesSlice";
import {
  addAffiliate,
  deleteAffiliate,
  fetchAfiliados,
  updateAffiliate,
} from "../redux/slices/AfiliatedSlice";
import { AppDispatch, RootState } from "../redux/store/store";
import {
  addPrescription,
  deletePrescription,
  fetchPrescription,
  updatePrescription,
} from "../redux/slices/PrescriptionSlice";

type AppContextType = {
  shifts: Shift[];
  specialists: Specialist[];
  schedules: Schedule[];
  affiliates: Affiliate[];
  prescription: Prescription[];
  //funciones de turno
  add_Shift: (shiftDTO: ShiftDTO) => void;
  update_Shift: (shiftDTO: ShiftDTO, id: number) => void;
  delete_Shift: (id: number) => void;
  //funciones de specialist
  add_Specialist: (specialistDTO: SpecialistDTO) => void;
  update_Specialist: (specialistDTO: SpecialistDTO, id: number) => void;
  delete_Specialist: (id: number) => void;
  //funciones de horario
  add_Schedules: (scheduleDTO: ScheduleDTO, id: number) => void;
  update_Schedules: (scheduleDTO: ScheduleDTO, id: number) => void;
  delete_Schedules: (id: number) => void;
  //funciones de afiliado
  add_Affiliates: (affiliateDTO: AffiliateDTO) => void;
  update_Affiliates: (affiliateDTO: AffiliateDTO, id: number) => void;
  delete_Affiliates: (id: number) => void;
  //funciones de receta
  add_Prescription: (prescriptionDTO: PrescriptionDTO) => void;
  update_Prescription: (prescriptionDTO: PrescriptionDTO, id: number) => void;
  delete_Prescription: (id: number) => void;
  //funcioens de filtrado
  filterAffiliates: (dni: string, name: string) => void;
  filteredAffiliates: Affiliate[];
};

const AppContext = createContext<AppContextType | undefined>(undefined);

interface AppProviderProps {
  children: ReactNode;
}

export const AppProvider: React.FC<AppProviderProps> = ({ children }) => {
  const dispatch = useDispatch<AppDispatch>();
  const shifts = useSelector((state: RootState) => state.shift.shifts);
  const specialists = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const schedules = useSelector(
    (state: RootState) => state.schedules.schedules
  );
  const affiliates = useSelector(
    (state: RootState) => state.afiliates.afiliados
  );

  const prescription = useSelector(
    (state: RootState) => state.prescription.prescriptions
  );

  const [filteredAffiliates, setFilteredAffiliates] =
    useState<Affiliate[]>(affiliates);

  useEffect(() => {
    dispatch(fetchShift());
    dispatch(fetchSpecialists());
    dispatch(fetchSchedules());
    dispatch(fetchAfiliados());
    dispatch(fetchPrescription());
  }, [dispatch]);

  const add_Shift = (shiftDTO: ShiftDTO) => dispatch(addShift(shiftDTO));
  const update_Shift = (shiftDTO: ShiftDTO, id: number) =>
    dispatch(updateShift({ shiftDTO, id }));
  const delete_Shift = (id: number) => dispatch(deleteShift({ shiftId: id }));

  const add_Specialist = (specialistDTO: SpecialistDTO) =>
    dispatch(addSpecialist(specialistDTO));
  const update_Specialist = (specialistDTO: SpecialistDTO, id: number) =>
    dispatch(updateSpecialist({ specialistDTO, id }));
  const delete_Specialist = (id: number) => dispatch(deleteSpecialist(id));

  const add_Schedules = (scheduleDTO: ScheduleDTO, id: number) =>
    dispatch(addSchedule({ scheduleDTO, idSpecialist: id }));
  const update_Schedules = (scheduleDTO: ScheduleDTO, id: number) =>
    dispatch(updateSchedule({ scheduleDTO, idSchedule: id }));
  const delete_Schedules = (id: number) =>
    dispatch(deleteSchedules({ scheduleID: id }));

  const add_Affiliates = (affiliateDTO: AffiliateDTO) =>
    dispatch(addAffiliate(affiliateDTO));
  const update_Affiliates = (affiliateDTO: AffiliateDTO, id: number) =>
    dispatch(updateAffiliate({ affiliateDTO, id }));
  const delete_Affiliates = (id: number) =>
    dispatch(deleteAffiliate({ affiliateID: id }));

  const add_Prescription = (prescriptionDTO: PrescriptionDTO) =>
    dispatch(addPrescription({ prescriptionDTO }));
  const update_Prescription = (prescriptionDTO: PrescriptionDTO, id: number) =>
    dispatch(updatePrescription({ prescriptionDTO, idPrescription: id }));
  const delete_Prescription = (id: number) =>
    dispatch(deletePrescription({ prescriptionID: id }));

  const filterAffiliates = (dni: string, name: string) => {
    const filtered = affiliates.filter((affiliate) => {
      const fullName = `${affiliate.firstName} ${affiliate.lastName}`
        .toLowerCase()
        .trim();
      return (
        (dni === "" || affiliate.dni.includes(dni)) &&
        (name === "" || fullName.includes(name.toLowerCase().trim()))
      );
    });

    console.log(filtered);
    setFilteredAffiliates(filtered);
  };

  return (
    <AppContext.Provider
      value={{
        shifts,
        specialists,
        schedules,
        affiliates,
        prescription,
        add_Shift,
        update_Shift,
        delete_Shift,
        add_Specialist,
        update_Specialist,
        delete_Specialist,
        add_Schedules,
        update_Schedules,
        delete_Schedules,
        add_Affiliates,
        update_Affiliates,
        delete_Affiliates,
        add_Prescription,
        update_Prescription,
        delete_Prescription,
        filterAffiliates,
        filteredAffiliates,
      }}
    >
      {children}
    </AppContext.Provider>
  );
};

export const useAppContext = () => {
  const context = useContext(AppContext);
  if (context === undefined) {
    throw new Error("useAppContext must be used within an AppProvider");
  }
  return context;
};

export default AppContext;
