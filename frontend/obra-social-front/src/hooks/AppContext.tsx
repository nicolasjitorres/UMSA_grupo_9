import React, { createContext, useEffect, ReactNode, useContext } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  Affiliate,
  Prescription,
  Schedule,
  Shift,
  Specialist,
} from "../redux/type";
import { fetchShift } from "../redux/slices/ShiftSlice";
import { fetchSpecialists } from "../redux/slices/SpecialistSlice";
import { fetchSchedules } from "../redux/slices/SchedulesSlice";
import { fetchAfiliados } from "../redux/slices/AfiliatedSlice";
import { AppDispatch, RootState } from "../redux/store/store";
import { fetchPrescription } from "../redux/slices/PrescriptionSlice";

type AppContextType = {
  shifts: Shift[];
  specialists: Specialist[];
  schedules: Schedule[];
  affiliates: Affiliate[];
  prescription: Prescription[];
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

  useEffect(() => {
    dispatch(fetchShift());
    dispatch(fetchSpecialists());
    dispatch(fetchSchedules());
    dispatch(fetchAfiliados());
    dispatch(fetchPrescription());
  }, [dispatch]);

  return (
    <AppContext.Provider
      value={{ shifts, specialists, schedules, affiliates, prescription }}
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
