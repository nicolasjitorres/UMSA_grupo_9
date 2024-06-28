import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Prescription } from "../type";
// import { RootState } from '../store/store';

interface PrescriptionState {
  shifts: Prescription[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: PrescriptionState = {
  shifts: [],
  status: "idle",
  error: null,
};

export const fetchPrescription = createAsyncThunk(
  "prescription/fetchPrescription",
  async () => {
    const response = await axios.get<Prescription[]>(
      "http://localhost:8080/recetas"
    );
    return response.data;
  }
);

const prescriptionSlice = createSlice({
  name: "recetas",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchPrescription.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchPrescription.fulfilled,
        (state, action: PayloadAction<Prescription[]>) => {
          state.status = "succeeded";
          state.shifts = action.payload;
        }
      )
      .addCase(fetchPrescription.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      });
  },
});

export default prescriptionSlice.reducer;
