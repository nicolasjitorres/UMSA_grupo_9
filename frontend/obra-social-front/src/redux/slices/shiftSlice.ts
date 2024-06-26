import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Shift, ShiftDTO } from "../type";
// import { RootState } from '../store/store';

interface ShiftState {
  shifts: Shift[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: ShiftState = {
  shifts: [],
  status: "idle",
  error: null,
};

export const fetchShift = createAsyncThunk("shift/fetchShift", async () => {
  const response = await axios.get<Shift[]>("http://localhost:8080/turnos");
  return response.data;
});

export const deleteShift = createAsyncThunk(
  "shift/deleteShift",
  async (shiftId: number) => {
    console.log(shiftId);
    await axios.delete(`http://localhost:8080/turnos/${shiftId}`);
    return shiftId;
  }
);

export const addShift = createAsyncThunk(
  "shift/addShift",
  async (shiftDTO: ShiftDTO, { rejectWithValue }) => {
    try {
      const response = await axios.post<Shift>(
        "http://localhost:8080/turnos",
        shiftDTO
      );
      return response.data; // Suponiendo que el backend devuelve el turno creado
    } catch (error: unknown) {
      console.log(error);
      if (axios.isAxiosError(error) && error.response) {
        return rejectWithValue(
          error.response.data.message || "Failed to add shift"
        );
      } else {
        return rejectWithValue("Failed to add shift");
      }
    }
  }
);

const shiftSlice = createSlice({
  name: "turnos",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchShift.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchShift.fulfilled,
        (state, action: PayloadAction<Shift[]>) => {
          state.status = "succeeded";
          state.shifts = action.payload;
        }
      )
      .addCase(fetchShift.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Something went wrong";
      })
      .addCase(deleteShift.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        deleteShift.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          state.shifts = state.shifts.filter(
            (shift) => shift.id !== action.payload
          );
        }
      )
      .addCase(deleteShift.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Something went wrong";
      })
      .addCase(addShift.pending, (state) => {
        state.status = "loading";
      })
      .addCase(addShift.fulfilled, (state, action: PayloadAction<Shift>) => {
        state.status = "succeeded";
        state.shifts.push(action.payload);
      })
      .addCase(addShift.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      });
  },
});

export default shiftSlice.reducer;
