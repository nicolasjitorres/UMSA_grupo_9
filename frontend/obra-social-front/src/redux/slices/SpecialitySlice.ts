import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { handleAxiosError } from "../../components/Errors/AxiosErrorHandler";

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
    try {
      const response = await axios.get<string[]>(
        "http://localhost:8080/enums/especialidades"
      );
      return response.data;
    } catch (error: unknown) {
      throw new Error(handleAxiosError(error, "Error al obtener las especialidades"));
    }
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
      .addCase(fetchSpecialities.fulfilled, (state, action: PayloadAction<string[]>) => {
        state.status = "succeeded";
        state.specialities = action.payload;
      })
      .addCase(fetchSpecialities.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salió mal al cargar las especialidades";
      });
  },
});

export default specialitiesSlice.reducer;
