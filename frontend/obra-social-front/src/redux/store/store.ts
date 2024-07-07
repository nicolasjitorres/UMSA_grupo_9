import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/SpecialistSlice";
import afiliatedReducer from "../slices/AfiliatedSlice";
import shiftReduce from "../slices/ShiftSlice";
import prescriptionReducer from "../slices/PrescriptionSlice";
import schedulesReducer from "../slices/SchedulesSlice";
import specialityReducer from "../slices/SpecialitySlice";

export const store = configureStore({
  reducer: {
    specialists: specialistsReducer,
    afiliates: afiliatedReducer,
    shift: shiftReduce,
    prescription: prescriptionReducer,
    schedules: schedulesReducer,
    specialities: specialityReducer
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
