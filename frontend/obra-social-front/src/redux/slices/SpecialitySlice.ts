import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";

interface SpecialitiesState {
  specialities: string[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: SpecialitiesState = {
  specialities: [],
  status: "idle",
  error: null,
};

export const fetchSpecialities = createAsyncThunk(
  "specialities/fetchSpecialities",
  async () => {
    const response = await axios.get<string[]>(
      "http://localhost:8080/enums/especialidades"
    );
    return response.data;
  }
);

const specialitiesSlice = createSlice({
  name: "specialities",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchSpecialities.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchSpecialities.fulfilled,
        (state, action: PayloadAction<string[]>) => {
          state.status = "succeeded";
          state.specialities = action.payload;
        }
      )
      .addCase(fetchSpecialities.rejected, (state, action) => {
        state.status = "failed";
        state.error =
          action.error.message || "Algo salió mal al cargar las especialidades";
      });
  },
});

export default specialitiesSlice.reducer;
