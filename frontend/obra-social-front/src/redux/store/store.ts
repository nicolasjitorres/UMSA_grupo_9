import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/specialistSlice";
import afiliatedReducer from "../slices/afiliatedSlice";
import shiftReduce from "../slices/shiftSlice";
import prescriptionReducer from "../slices/prescriptionSlice";
import schedulesReducer from "../slices/schedulesSlice";
import locationReducer from "../slices/locationSlice";


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
