import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/SpecialistSlices";
import afiliatedReducer from "../slices/AfiliatedSlices";
import shiftReduce from "../slices/ShiftSlices";
import prescriptionReducer from "../slices/PrescriptionSlices";
import schedulesReducer from "../slices/SchedulesSlices";

export const store = configureStore({
  reducer: {
    specialists: specialistsReducer,
    afiliates: afiliatedReducer,
    shift: shiftReduce,
    prescription: prescriptionReducer,
    schedules: schedulesReducer,
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
