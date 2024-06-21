import { configureStore } from "@reduxjs/toolkit";
import specialistsReducer from "../slices/specialistSlice";
import afiliatedReducer from "../slices/afiliatedSlice";

export const store = configureStore({
  reducer: {
    specialists: specialistsReducer,
    afiliates: afiliatedReducer,
  },
});

export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
