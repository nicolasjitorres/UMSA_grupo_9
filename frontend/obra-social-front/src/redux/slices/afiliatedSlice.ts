import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Affiliate } from "../type";
// import { RootState } from '../store/store';

interface AffiliateState {
  afiliados: Affiliate[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: AffiliateState = {
  afiliados: [],
  status: "idle",
  error: null,
};

export const fetchAfiliados = createAsyncThunk(
  "afiliados/fetchAfiliados",
  async () => {
    const response = await axios.get<Affiliate[]>(
      "http://localhost:8080/afiliados"
    );
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
        (state, action: PayloadAction<Affiliate[]>) => {
          state.status = "succeeded";
          state.afiliados = action.payload;
        }
      )
      .addCase(fetchAfiliados.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      });
  },
});

export default afiliadosSlice.reducer;
