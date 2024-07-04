import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Specialist, SpecialistDTO } from "../type"; // Ajusta la ruta según la ubicación de tu archivo de tipos
import { handleAxiosError } from "../../components/Errors/AxiosErrorHandler";

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

//traer todos
export const fetchSpecialists = createAsyncThunk(
  "specialists/fetchSpecialists",
  async () => {
    const response = await axios.get<Specialist[]>(
      "http://localhost:8080/especialistas"
    );
    return response.data;
  }
);

//add especialistas
export const addSpecialist = createAsyncThunk(
  "specialist/addSpecilist",
  async (specialistDTO: SpecialistDTO, { rejectWithValue }) => {
    try {
      const response = await axios.post<Specialist>(
        "http://localhost:8080/especialistas",
        specialistDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(
        error,
        "Error al agregar el especialista"
      );
      return rejectWithValue(errorMessage);
    }
  }
);

//update especialistas
export const updateSpecialist = createAsyncThunk(
  "specialist/updateSpecialist",
  async (
    { specialistDTO, id }: { specialistDTO: SpecialistDTO; id: number },
    { rejectWithValue }
  ) => {
    try {
      const response = await axios.put<Specialist>(
        `http://localhost:8080/especialistas/${id}`,
        specialistDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(
        error,
        "Error al actualizar el especialista"
      );
      return rejectWithValue(errorMessage);
    }
  }
);

//delete especialista
export const deleteSpecialist = createAsyncThunk(
  "specialist/deleteSpecialist",
  async (specialistId: number) => {
    await axios.delete(`http://localhost:8080/especialistas/${specialistId}`);
    return specialistId;
  }
);

const specialistsSlice = createSlice({
  name: "specialists",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //casos para el traer todos
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
      })
      //casos para el agregar
      .addCase(addSpecialist.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        addSpecialist.fulfilled,
        (state, action: PayloadAction<Specialist>) => {
          state.status = "succeeded";
          if (!Array.isArray(state.specialists)) {
            state.specialists = [];
          }
          //en este caso lo agregamos el especialista a la lsita de especialistas
          state.specialists.push(action.payload);
        }
      )
      .addCase(addSpecialist.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //casos para el actualizar
      .addCase(updateSpecialist.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        updateSpecialist.fulfilled,
        (state, action: PayloadAction<Specialist>) => {
          state.status = "succeeded";
          //buscamos el index del array donde estara el especialista a actualziar
          const index = state.specialists.findIndex(
            (shift) => shift.id === action.payload.id
          );
          //si es superios a -1 guarda el dato en el lugar de la specialists[index]
          if (index !== -1) {
            state.specialists[index] = action.payload;
          }
        }
      )
      .addCase(updateSpecialist.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //de aca para abajo delete
      .addCase(deleteSpecialist.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        deleteSpecialist.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          //si salio bien guarda un nuevo listado de especialistas donde
          //incluyen todos los que no tengan el id indicado
          state.specialists = state.specialists.filter(
            (specialist) => specialist.id !== action.payload
          );
        }
      )
      .addCase(deleteSpecialist.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      });
  },
});

export default specialistsSlice.reducer;
