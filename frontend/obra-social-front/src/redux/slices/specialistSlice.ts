import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Specialist } from "../type"; // Ajusta la ruta según la ubicación de tu archivo de tipos

interface SpecialistsState {
  specialists: Specialist[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: SpecialistsState = {
  specialists: [],
  status: "idle",
  error: null,
};

export const fetchSpecialists = createAsyncThunk(
  "specialists/fetchSpecialists",
  async () => {
    const response = await axios.get<Specialist[]>(
      "http://localhost:8080/especialistas"
    );
    return response.data;
  }
);

const specialistsSlice = createSlice({
  name: "specialists",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchSpecialists.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchSpecialists.fulfilled,
        (state, action: PayloadAction<Specialist[]>) => {
          state.status = "succeeded";
          state.specialists = action.payload;
        }
      )
      .addCase(fetchSpecialists.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Something went wrong";
      });
  },
});

export default specialistsSlice.reducer;
