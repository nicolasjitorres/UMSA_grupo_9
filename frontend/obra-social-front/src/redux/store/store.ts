import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/specialistSlice";
import afiliatedReducer from "../slices/afiliatedSlice";
import shiftReduce from "../slices/shiftSlice";
import prescriptionReducer from "../slices/prescriptionSlice";

export const store = configureStore({
  reducer: {
    specialists: specialistsReducer,
    afiliates: afiliatedReducer,
    shift: shiftReduce,
    prescription: prescriptionReducer,
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
