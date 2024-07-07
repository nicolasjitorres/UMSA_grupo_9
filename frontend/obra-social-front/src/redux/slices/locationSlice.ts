// src/redux/slices/locationSlice.ts
import { createSlice, createAsyncThunk, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import { Location } from "../type";
import { handleAxiosError } from "../../components/Errors/AxiosErrorHandler";

interface LocationsState {
  locations: Location[];
  status: "idle" | "loading" | "succeeded" | "failed";
  error: string | null;
}

const initialState: LocationsState = {
  locations: [],
  status: "idle",
  error: null,
};

// Obtener todas las ubicaciones
export const fetchLocations = createAsyncThunk(
  "locations/fetchLocations",
  async () => {
    const response = await axios.get<Location[]>("http://localhost:8080/ubicaciones");
    return response.data;
  }
);

// Agregar una nueva ubicación
export const addLocation = createAsyncThunk(
  "locations/addLocation",
  async (location: Location, { rejectWithValue }) => {
    try {
      const response = await axios.post<Location>("http://localhost:8080/ubicaciones", location);
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(error, "Error al agregar la ubicación");
      return rejectWithValue(errorMessage);
    }
  }
);

// Actualizar una ubicación
export const updateLocation = createAsyncThunk(
  "locations/updateLocation",
  async (
    { location, id }: { location: Location; id: number },
    { rejectWithValue }
  ) => {
    try {
      const response = await axios.put<Location>(`http://localhost:8080/ubicaciones/${id}`, location);
      return response.data;
    } catch (error: unknown) {
      const errorMessage = handleAxiosError(error, "Error al actualizar la ubicación");
      return rejectWithValue(errorMessage);
    }
  }
);

// Eliminar una ubicación
export const deleteLocation = createAsyncThunk(
  "locations/deleteLocation",
  async (locationId: number) => {
    await axios.delete(`http://localhost:8080/ubicaciones/${locationId}`);
    return locationId;
  }
);

const locationsSlice = createSlice({
  name: "locations",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchLocations.pending, (state) => {
        state.status = "loading";
      })
      .addCase(fetchLocations.fulfilled, (state, action: PayloadAction<Location[]>) => {
        state.status = "succeeded";
        state.locations = action.payload;
      })
      .addCase(fetchLocations.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salió mal";
      })
      .addCase(addLocation.pending, (state) => {
        state.status = "loading";
      })
      .addCase(addLocation.fulfilled, (state, action: PayloadAction<Location>) => {
        state.status = "succeeded";
        state.locations.push(action.payload);
      })
      .addCase(addLocation.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      .addCase(updateLocation.pending, (state) => {
        state.status = "loading";
      })
      .addCase(updateLocation.fulfilled, (state, action: PayloadAction<Location>) => {
        state.status = "succeeded";
        const index = state.locations.findIndex((location) => location.id === action.payload.id);
        if (index !== -1) {
          state.locations[index] = action.payload;
        }
      })
      .addCase(updateLocation.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.payload as string;
      })
      .addCase(deleteLocation.pending, (state) => {
        state.status = "loading";
      })
      .addCase(deleteLocation.fulfilled, (state, action: PayloadAction<number>) => {
        state.status = "succeeded";
        state.locations = state.locations.filter((location) => location.id !== action.payload);
      })
      .addCase(deleteLocation.rejected, (state, action) => {
        state.status = "failed";
        state.error = action.error.message || "Algo salió mal";
      });
  },
});

export default locationsSlice.reducer;
