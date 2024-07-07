import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Prescription, PrescriptionDTO } from "../type";
import { handleAxiosError } from "../../components/Errors/AxiosErrorHandler";

interface PrescriptionState {
  prescriptions: Prescription[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: PrescriptionState = {
  prescriptions: [],
  status: "idle",
  error: null,
};

export const fetchPrescription = createAsyncThunk(
  "prescription/fetchPrescription",
  async () => {
    const response = await axios.get<Prescription[]>(
      "http://localhost:8080/recetas"
    );
    return response.data;
  }
);

//add Receta
export const addPrescription = createAsyncThunk<
  Prescription,
  { prescriptionDTO: PrescriptionDTO },
  { rejectValue: string }
>(
  "prescription/addPrescription",
  async ({ prescriptionDTO }, { rejectWithValue }) => {
    try {
      const response = await axios.post<Prescription>(
        "http://localhost:8080/recetas",
        prescriptionDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(
        error,
        "Error al agregar la receta"
      );
      return rejectWithValue(errorMessage);
    }
  }
);

//update recetas
export const updatePrescription = createAsyncThunk<
  Prescription,
  { prescriptionDTO: PrescriptionDTO; idPrescription: number },
  { rejectValue: string }
>(
  "prescription/updatePrescription",
  async ({ prescriptionDTO, idPrescription }, { rejectWithValue }) => {
    try {
      const response = await axios.put<Prescription>(
        `http://localhost:8080/recetas/${idPrescription}`,
        prescriptionDTO
      );
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(
        error,
        "Error al actualizar la receta"
      );
      return rejectWithValue(errorMessage);
    }
  }
);

//delete Recetas
export const deletePrescription = createAsyncThunk(
  "prescription/deletePrescription",
  async (
    { prescriptionID }: { prescriptionID: number },
    { rejectWithValue }
  ) => {
    try {
      await axios.delete(`http://localhost:8080/recetas/${prescriptionID}`);
      return prescriptionID;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(
        error,
        "Error al eliminar la receta"
      );
      return rejectWithValue(errorMessage);
    }
  }
);

const prescriptionSlice = createSlice({
  name: "recetas",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchPrescription.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        fetchPrescription.fulfilled,
        (state, action: PayloadAction<Prescription[]>) => {
          state.status = "succeeded";
          state.prescriptions = action.payload;
        }
      )
      .addCase(fetchPrescription.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      })
      //de aca para abajo delete
      .addCase(deletePrescription.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        deletePrescription.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          //si salio bien guarda un nuevo listado de recetas donde
          //incluyen todos los que no tengan el id indicado
          state.prescriptions = state.prescriptions.filter(
            (prescription) => prescription.id !== action.payload
          );
        }
      )
      .addCase(deletePrescription.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      })
      //de aca para abajo add
      .addCase(addPrescription.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        addPrescription.fulfilled,
        (state, action: PayloadAction<Prescription>) => {
          state.status = "succeeded";
          if (!Array.isArray(state.prescriptions)) {
            state.prescriptions = [];
          }
          //en este caso lo agregamos la Recetas a la lista de horarios
          state.prescriptions.push(action.payload);
        }
      )
      .addCase(addPrescription.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //de aca para abajo update
      .addCase(updatePrescription.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        updatePrescription.fulfilled,
        (state, action: PayloadAction<Prescription>) => {
          state.status = "succeeded";
          //buscamos el index del array donde estara la Recetas a actualziar
          const index = state.prescriptions.findIndex(
            (prescription) => prescription.id === action.payload.id
          );
          //si es superios a -1 guarda el dato en el lugar de Recetas[index]
          if (index !== -1) {
            state.prescriptions[index] = action.payload;
          }
        }
      )
      .addCase(updatePrescription.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      });
  },
});

export default prescriptionSlice.reducer;
