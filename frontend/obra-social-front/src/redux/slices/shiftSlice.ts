import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
// import { RootState } from '../store/store';

interface Shift {
  id: number;
  description: string;
  date: string;
  time: string;
}

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
      });
  },
});

export default shiftSlice.reducer;
