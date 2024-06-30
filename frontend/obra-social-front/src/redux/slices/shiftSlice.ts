import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Shift, ShiftDTO } from "../type";
import { handleAxiosError } from "./AxiosErrorHandler";

//estructura del shift state
interface ShiftState {
  shifts: Shift[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

//estado inicial de la shift
const initialState: ShiftState = {
  shifts: [],
  status: "idle",
  error: null,
};

//getall turnos
export const fetchShift = createAsyncThunk("shift/fetchShift", async () => {
  const response = await axios.get<Shift[]>("http://localhost:8080/turnos");
  return response.data;
});

//delete turnos
export const deleteShift = createAsyncThunk(
  "shift/deleteShift",
  async (shiftId: number) => {
    await axios.delete(`http://localhost:8080/turnos/${shiftId}`);
    return shiftId;
  }
);

//add turnos
export const addShift = createAsyncThunk(
  "shift/addShift",
  async (shiftDTO: ShiftDTO, { rejectWithValue }) => {
    try {
      const response = await axios.post<Shift>(
        "http://localhost:8080/turnos",
        shiftDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(error, "Error al agregar el turno");
      return rejectWithValue(errorMessage);
    }
  }
);

//update turnos
export const updateShift = createAsyncThunk(
  "shift/updateShift",
  async (
    { shiftDTO, id }: { shiftDTO: ShiftDTO; id: number },
    { rejectWithValue }
  ) => {
    try {
      const response = await axios.put<Shift>(
        `http://localhost:8080/turnos/${id}`,
        shiftDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(
        error,
        "Error al actualizar el turno"
      );
      return rejectWithValue(errorMessage);
    }
  }
);

const shiftSlice = createSlice({
  name: "turnos",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //de aca para abajo get
      .addCase(fetchShift.pending, (state) => {
        //si el metodo queda como pending guardamos esto en el estado
        state.status = "loading";
      })
      .addCase(
        fetchShift.fulfilled,
        (state, action: PayloadAction<Shift[]>) => {
          //si salio todo bien guardamos el listado que nos da el back y en el state el succeeded
          state.status = "succeeded";
          state.shifts = action.payload;
        }
      )
      .addCase(fetchShift.rejected, (state, action) => {
        //si  fracaso el estado qeudara como failed y tendra un error, ya sea hardcodeado y por el messege
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      })
      //de aca para abajo delete
      .addCase(deleteShift.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        deleteShift.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          //si salio bien guarda un nuevo listado de shift donde
          //incluyen todos los que no tengan el id indicado
          state.shifts = state.shifts.filter(
            (shift) => shift.id !== action.payload
          );
        }
      )
      .addCase(deleteShift.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      })
      //de aca para abajo add
      .addCase(addShift.pending, (state) => {
        state.status = "loading";
      })
      .addCase(addShift.fulfilled, (state, action: PayloadAction<Shift>) => {
        state.status = "succeeded";
        if (!Array.isArray(state.shifts)) {
          state.shifts = [];
        }
        //en este caso lo agregamos el turno a la lsita de turnos
        state.shifts.push(action.payload);
      })
      .addCase(addShift.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //de aca para abajo update
      .addCase(updateShift.pending, (state) => {
        state.status = "loading";
      })
      .addCase(updateShift.fulfilled, (state, action: PayloadAction<Shift>) => {
        state.status = "succeeded";
        //buscamos el index del array donde estara la shift a actualziar
        const index = state.shifts.findIndex(
          (shift) => shift.id === action.payload.id
        );
        //si es menor a 0 guarda el el dato en el lugar de la shift[index]
        if (index !== -1) {
          state.shifts[index] = action.payload;
        }
      })
      .addCase(updateShift.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      });
  },
});

export default shiftSlice.reducer;
