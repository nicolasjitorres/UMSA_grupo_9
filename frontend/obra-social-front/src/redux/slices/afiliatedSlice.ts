import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Affiliate, AffiliateDTO } from "../type";
import Swal from "sweetalert2";

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

export const addAffiliate = createAsyncThunk(
  "affiliate/addAffiliate",
  async (affiliate: AffiliateDTO, { rejectWithValue }) => {
    try {
      //intentamos el llamado a la api con axios y guardamos la respuesta
      const response = await axios.post<Affiliate>(
        "http://localhost:8080/afiliados",
        affiliate
      );
      return response.data; //y retornamos el turno para que lo guarde en el state
    } catch (error: unknown) {
      console.log(error);
      //en caso de que sea una axios error
      if (axios.isAxiosError(error) && error.response) {
        //de la response verificacmos las violaciones que nos lelgan por el @valid del back
        const violations = error.response.data.violations;
        let errorMessage = "Error al agregar el afiliado";
        //recorremos las vioalciones y la guardamos en uan variable mensaje
        if (violations && Array.isArray(violations)) {
          errorMessage = violations
            .map(
              (violation: { field: string; message: string }) =>
                `${violation.message}`
            )
            .join("\n");
        }
        //y aca usamos la libreria de Swal para hacer las alerts
        Swal.fire({
          title: "Error",
          text: errorMessage,
          icon: "error",
          confirmButtonText: "Continue",
        });
        //y retornamos el error especifico para que se guarde en el stado de la shift
        return rejectWithValue(errorMessage);
      } else {
        Swal.fire({
          title: "Error",
          text: "Error al agregar el afiliado",
          icon: "error",
          confirmButtonText: "Continue",
        });
        //y retornamos el error creado por nosotros para que se guarde en el stado de la shift
        return rejectWithValue("Error al agregar el afiliado");
      }
    }
  }
);

export const updateAffiliate = createAsyncThunk(
  "affiliate/updateAffiliate",
  async (
    { afffiliateDTO, id }: { afffiliateDTO: AffiliateDTO; id: number },
    { rejectWithValue }
  ) => {
    try {
      const response = await axios.put<Affiliate>(
        `http://localhost:8080/afiliados/${id}`,
        afffiliateDTO
      );
      return response.data;
    } catch (error: unknown) {
      //en caso de que sea una axios error
      if (axios.isAxiosError(error) && error.response) {
        //de la response verificacmos las violaciones que nos lelgan por el @valid del back
        const violations = error.response.data.violations;
        let errorMessage = "Error al actualizar el Afiliado";
        //recorremos las vioalciones y la guardamos en uan variable mensaje
        if (violations && Array.isArray(violations)) {
          errorMessage = violations
            .map(
              (violation: { field: string; message: string }) =>
                `${violation.message}`
            )
            .join("\n");
        }
        //y aca usamos la libreria de Swal para hacer las alerts
        Swal.fire({
          title: "Error",
          text: errorMessage,
          icon: "error",
          confirmButtonText: "Continue",
        });
        //y retornamos el error especifico para que se guarde en el stado de la shift
        return rejectWithValue(errorMessage);
      } else {
        Swal.fire({
          title: "Error",
          text: "Error al actualizar el Afiliado",
          icon: "error",
          confirmButtonText: "Continue",
        });
        //y retornamos el error creado por nosotros para que se guarde en el stado de la shift
        return rejectWithValue("Error al actualizar el Afiliado");
      }
    }
  }
);

export const deleteAffiliate = createAsyncThunk(
  "affiliate/deleteAffiliate",
  async (affiliateID: number) => {
    await axios.delete(`http://localhost:8080/afiliados/${affiliateID}`);
    return affiliateID;
  }
);

const afiliadosSlice = createSlice({
  name: "afiliados",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //case de getAll
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
      })
      //case de Add afiliado
      .addCase(addAffiliate.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        addAffiliate.fulfilled,
        (state, action: PayloadAction<Affiliate>) => {
          state.status = "succeeded";
          if (!Array.isArray(state.afiliados)) {
            state.afiliados = [];
          }
          //en este caso lo agregamos el afiliado a la lsita de afiliados
          state.afiliados.push(action.payload);
        }
      )
      .addCase(addAffiliate.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //de aca para abajo update
      .addCase(updateAffiliate.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        updateAffiliate.fulfilled,
        (state, action: PayloadAction<Affiliate>) => {
          state.status = "succeeded";
          //buscamos el index del array donde estara el afiliados a actualziar
          const index = state.afiliados.findIndex(
            (affiliate) => affiliate.id === action.payload.id
          );
          //si es menor a 0 guarda el el dato en el lugar del afiliados[index]
          if (index !== -1) {
            state.afiliados[index] = action.payload;
          }
        }
      )
      .addCase(updateAffiliate.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      //de aca para abajo delete
      .addCase(deleteAffiliate.pending, (state) => {
        state.status = "loading";
      })
      .addCase(
        deleteAffiliate.fulfilled,
        (state, action: PayloadAction<number>) => {
          state.status = "succeeded";
          //si salio bien guarda un nuevo listado de afiliados donde
          //incluyen todos los que no tengan el id indicado
          state.afiliados = state.afiliados.filter(
            (affiliate) => affiliate.id !== action.payload
          );
        }
      )
      .addCase(deleteAffiliate.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salio mal";
      });
  },
});

export default afiliadosSlice.reducer;
