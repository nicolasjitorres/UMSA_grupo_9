import React, { useState, ChangeEvent, FormEvent, useEffect } from "react";
import { TextField, Select, MenuItem, FormControl, InputLabel, SelectChangeEvent } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../redux/store/store";
import {
  validateFormSpecialist,
  ValidationErrors,
} from "../../funcionalities/Validations";
import {
  addSpecialist,
  updateSpecialist,
} from "../../redux/slices/SpecialistSlice";
import { fetchLocations } from "../../redux/slices/LocationSlice";
import { fetchSpecialities } from "../../redux/slices/SpecialitySlice"; // Importar la acción para obtener especialidades
import { Specialist } from "../../redux/type";
import { useAppContext } from "../../hooks/AppContext";

interface FormSpecialistProps {
  specialist?: Specialist;
  handleClose: () => void;
}

const FormSpecialist: React.FC<FormSpecialistProps> = ({
  handleClose,
  specialist,
}) => {
  const { delete_Specialist } = useAppContext();
  const [formData, setFormData] = useState({
    firstName: specialist?.firstName || "",
    lastName: specialist?.lastName || "",
    dni: specialist?.dni || "",
    email: specialist?.email || "",
    speciality: specialist?.speciality || "",
    street: specialist?.location?.street || "",
    locality: specialist?.location?.locality || "",
    province: specialist?.location?.province || "",
    country: specialist?.location?.country || "",
  });

  const [errors, setErrors] = useState<ValidationErrors>({});
  const dispatch = useDispatch<AppDispatch>();

  // Obtener el estado de las especialidades desde Redux
  const specialities = useSelector((state: RootState) => state.specialities.specialities);

  useEffect(() => {
    dispatch(fetchLocations());
    dispatch(fetchSpecialities()); // Despachar la acción para obtener especialidades
  }, [dispatch]);

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSelectChange = (event: SelectChangeEvent<string>) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const validationErrors = validateFormSpecialist(
      formData.firstName,
      formData.lastName,
      formData.dni,
      formData.email
    );
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length === 0) {
      const specialistData = {
        ...formData,
        role: "USER",
        location: {
          street: formData.street,
          locality: formData.locality,
          province: formData.province,
          country: formData.country,
        },
      };

      if (specialist) {
        await dispatch(
          updateSpecialist({ specialistDTO: specialistData, id: specialist.id })
        );
      } else {
        await dispatch(addSpecialist(specialistData));
      }

      setFormData({
        firstName: "",
        lastName: "",
        dni: "",
        email: "",
        speciality: "",
        street: "",
        locality: "",
        province: "",
        country: "",
      });

      handleClose();
    }
  };

  const handleDelete = async (specialistID: number) => {
    if (specialist) {
      delete_Specialist(specialistID);
      handleClose();
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <TextField
        fullWidth
        label="Nombre"
        name="firstName"
        value={formData.firstName}
        onChange={handleInputChange}
        margin="normal"
        error={!!errors.firstName}
        helperText={errors.firstName}
        className="form-field"
      />
      <TextField
        fullWidth
        label="Apellido"
        name="lastName"
        value={formData.lastName}
        onChange={handleInputChange}
        margin="normal"
        error={!!errors.lastName}
        helperText={errors.lastName}
        className="form-field"
      />
      <TextField
        fullWidth
        label="DNI"
        name="dni"
        value={formData.dni}
        onChange={handleInputChange}
        margin="normal"
        error={!!errors.dni}
        helperText={errors.dni}
        className="form-field"
      />
      <TextField
        fullWidth
        label="Email"
        name="email"
        value={formData.email}
        onChange={handleInputChange}
        margin="normal"
        error={!!errors.email}
        helperText={errors.email}
        className="form-field"
      />
      <FormControl fullWidth margin="normal" className="form-field">
        <InputLabel id="speciality-label">Especialidad</InputLabel>
        <Select
          labelId="speciality-label"
          id="speciality"
          name="speciality"
          value={formData.speciality}
          onChange={handleSelectChange}
        >
          <MenuItem value="">
            <em>Seleccione una especialidad</em>
          </MenuItem>
          {specialities.map((speciality) => (
            <MenuItem key={speciality} value={speciality}>
              {speciality.replace("_", " ")}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <TextField
        fullWidth
        label="Calle"
        name="street"
        value={formData.street}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <TextField
        fullWidth
        label="Localidad"
        name="locality"
        value={formData.locality}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <TextField
        fullWidth
        label="Provincia"
        name="province"
        value={formData.province}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <TextField
        fullWidth
        label="País"
        name="country"
        value={formData.country}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <button
        type="submit"
        className={specialist ? "edit-button" : "add-button"}
      >
        {specialist ? "Actualizar" : "Agregar"}
      </button>

      {specialist && (
        <button
          className="delete-button"
          onClick={() => handleDelete(specialist.id)}
        >
          Borrar
        </button>
      )}
    </form>
  );
};

export default FormSpecialist;
