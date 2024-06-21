import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
// import { RootState } from '../store/store';

interface Afiliado {
  id: 1;
  firstName: string;
  lastName: string;
  dni: string;
  healthInsuranceCode: string;
  role: string;
  email: string;
  password: string;
}

interface AfiliadosState {
  afiliados: Afiliado[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: AfiliadosState = {
  afiliados: [],
  status: "idle",
  error: null,
};

export const fetchAfiliados = createAsyncThunk(
  "afiliados/fetchAfiliados",
  async () => {
    const response = await axios.get<Afiliado[]>(
      "http://localhost:8080/afiliados"
    ); // Ajusta la URL según tu backend
    return response.data;
  }
);

const afiliadosSlice = createSlice({
  name: "afiliados",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchAfiliados.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchAfiliados.fulfilled,
        (state, action: PayloadAction<Afiliado[]>) => {
          state.status = "succeeded";
          state.afiliados = action.payload;
        }
      )
      .addCase(fetchAfiliados.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Something went wrong";
      });
  },
});

export default afiliadosSlice.reducer;
