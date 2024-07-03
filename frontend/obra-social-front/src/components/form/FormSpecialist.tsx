import React, { useState, ChangeEvent, FormEvent } from "react";
import { TextField } from "@mui/material";
import { Specialist } from "../../redux/type";

interface FormSpecialistProps {
  specialist?: Specialist;
}

const FormSpecialist: React.FC<FormSpecialistProps> = ({ specialist }) => {
  const [formData, setFormData] = useState({
    firstName: specialist?.firstName || "",
    lastName: specialist?.lastName || "",
    dni: specialist?.dni || "",
    email: specialist?.email || "",
    speciality: specialist?.speciality || "",
    street: specialist?.location.street || "",
    locality: specialist?.location.locality || "",
    province: specialist?.location.province || "",
    country: specialist?.location.country || "",
  });

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // Limpia el formulario después de enviar
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
        className="form-field"
      />
      <TextField
        fullWidth
        label="Apellido"
        name="lastName"
        value={formData.lastName}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <TextField
        fullWidth
        label="DNI"
        name="dni"
        value={formData.dni}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <TextField
        fullWidth
        label="Email"
        name="email"
        value={formData.email}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
      <TextField
        fullWidth
        label="Especialidad"
        name="speciality"
        value={formData.speciality}
        onChange={handleInputChange}
        margin="normal"
        className="form-field"
      />
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
      <button className="add-button">Agregar</button>
    </form>
  );
};

export default FormSpecialist;
