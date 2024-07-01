import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Schedule, ScheduleDTO } from "../type"; // Ajusta la ruta según la ubicación de tu archivo de tipos
import { handleAxiosError } from "./AxiosErrorHandler";

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

//get all Horarios
export const fetchSchedules = createAsyncThunk(
  "schedules/fetchSchedules",
  async () => {
    const response = await axios.get<Schedule[]>(
      "http://localhost:8080/horarios"
    );
    return response.data;
  }
);

//add Horarios
export const addSchedule = createAsyncThunk(
  "schedule/addSchedule",
  async (
    {
      schedulesDTO,
      idSpecialsit,
    }: { schedulesDTO: ScheduleDTO; idSpecialsit: number },
    { rejectWithValue }
  ) => {
    try {
      const response = await axios.post<Schedule>(
        `http://localhost:8080/horarios/${idSpecialsit}`,
        schedulesDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(error, "Error al agregar el turno");
      return rejectWithValue(errorMessage);
    }
  }
);

//update Horarios
export const updateSchedule = createAsyncThunk(
  "schedule/updateSchedule",
  async (
    { schedulesDTO, id }: { schedulesDTO: ScheduleDTO; id: number },
    { rejectWithValue }
  ) => {
    try {
      const response = await axios.put<Schedule>(
        `http://localhost:8080/horarios/${id}`,
        schedulesDTO
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

//delete Horarios
export const deleteSchedules = createAsyncThunk(
  "schedule/deleteSchedules",
  async (scheduleID: number) => {
    await axios.delete(`http://localhost:8080/horarios/${scheduleID}`);
    return scheduleID;
  }
);

const schedulesSlice = createSlice({
  name: "horarios",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //de aca para abajo get all
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
        state.error = action.error.message || "Algo salio mal";
      })
      //de aca para abajo delete
      .addCase(deleteSchedules.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        deleteSchedules.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          //si salio bien guarda un nuevo listado de schedule donde
          //incluyen todos los que no tengan el id indicado
          state.schedules = state.schedules.filter(
            (schedule) => schedule.id !== action.payload
          );
        }
      )
      .addCase(deleteSchedules.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      })
      //de aca para abajo add
      .addCase(addSchedule.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        addSchedule.fulfilled,
        (state, action: PayloadAction<Schedule>) => {
          state.status = "succeeded";
          if (!Array.isArray(state.schedules)) {
            state.schedules = [];
          }
          //en este caso lo agregamos el horario a la lista de horarios
          state.schedules.push(action.payload);
        }
      )
      .addCase(addSchedule.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //de aca para abajo update
      .addCase(updateSchedule.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        updateSchedule.fulfilled,
        (state, action: PayloadAction<Schedule>) => {
          state.status = "succeeded";
          //buscamos el index del array donde estara el horario a actualziar
          const index = state.schedules.findIndex(
            (schedule) => schedule.id === action.payload.id
          );
          //si es superios a -1 guarda el dato en el lugar de horaros[index]
          if (index !== -1) {
            state.schedules[index] = action.payload;
          }
        }
      )
      .addCase(updateSchedule.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      });
  },
});

export default schedulesSlice.reducer;
