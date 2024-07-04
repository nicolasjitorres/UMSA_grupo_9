import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/SpecialistSlice";
import afiliatedReducer from "../slices/AfiliatedSlice";
import shiftReduce from "../slices/ShiftSlice";
import prescriptionReducer from "../slices/PrescriptionSlice";
import schedulesReducer from "../slices/SchedulesSlice";
import locationReducer from "../slices/LocationSlice";


export const store = configureStore({
  reducer: {
    specialists: specialistsReducer,
    afiliates: afiliatedReducer,
    shift: shiftReduce,
    prescription: prescriptionReducer,
    schedules: schedulesReducer,
    locations: locationReducer
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
