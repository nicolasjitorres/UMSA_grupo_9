import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";

enum DayOfWeek {
  SUNDAY = "SUNDAY",
  MONDAY = "MONDAY",
  TUESDAY = "TUESDAY",
  WEDNESDAY = "WEDNESDAY",
  THURSDAY = "THURSDAY",
  FRIDAY = "FRIDAY",
  SATURDAY = "SATURDAY",
}

interface Location {
  id: number;
  street: string;
  locality: string;
  province: string;
  country: string;
}

interface Schedule {
  id: number;
  startTime: string;
  endTime: string;
  dayOfWeek: DayOfWeek;
}

interface Specialist {
  id: number;
  firstName: string;
  lastName: string;
  dni: string;
  speciality: string; // Ajustar según la estructura de `Speciality`
  schedules: Schedule[]; // Ajustar según la estructura de `Schedule`
  location: Location; // Ajustar según la estructura de `Location`
  role: string; // Ajustar según la estructura de `Role`
  email: string;
  password: string;
}

interface SpecialistsState {
  specialists: Specialist[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: SpecialistsState = {
  specialists: [], // Inicializar como un array vacío
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
