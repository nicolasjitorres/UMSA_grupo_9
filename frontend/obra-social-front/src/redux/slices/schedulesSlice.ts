import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Schedule } from "../type"; // Ajusta la ruta según la ubicación de tu archivo de tipos

interface SchedulesState {
  schedules: Schedule[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: SchedulesState = {
  schedules: [],
  status: "idle",
  error: null,
};

export const fetchSchedules = createAsyncThunk(
  "schedules/fetchSchedules",
  async () => {
    const response = await axios.get<Schedule[]>(
      "http://localhost:8080/horarios"
    );
    return response.data;
  }
);

const schedulesSlice = createSlice({
  name: "schedules",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchSchedules.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchSchedules.fulfilled,
        (state, action: PayloadAction<Schedule[]>) => {
          state.status = "succeeded";
          state.schedules = action.payload;
        }
      )
      .addCase(fetchSchedules.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Something went wrong";
      });
  },
});

export default schedulesSlice.reducer;
