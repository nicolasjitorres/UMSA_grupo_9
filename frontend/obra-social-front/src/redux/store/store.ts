import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/specialistSlice";
import afiliatedReducer from "../slices/afiliatedSlice";
import shiftReduce from "../slices/shiftSlice";
import prescriptionReducer from "../slices/prescriptionSlice";
import schedulesReducer from "../slices/schedulesSlice";

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
