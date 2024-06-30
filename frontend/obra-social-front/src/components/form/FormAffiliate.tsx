import { Box, Button, TextField, Typography } from "@mui/material";
import React, { useState } from "react";
import {
  ValidationErrors,
  validateForm,
} from "../../funcionalities/Validations";
import {
  addAffiliate,
  updateAffiliate,
} from "../../redux/slices/AfiliatedSlice";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../../redux/store/store";
import { Affiliate } from "../../redux/type";

interface FormShiftProps {
  affiliate?: Affiliate;
}

const FormAffiliate: React.FC<FormShiftProps> = ({ affiliate }) => {
  const [firstName, setFirstName] = React.useState(affiliate?.firstName || "");
  const [lastName, setLastName] = React.useState(affiliate?.lastName || "");
  const [dni, setDni] = React.useState(affiliate?.dni || "");
  const [healthInsuranceCode, sethealthInsuranceCode] = React.useState(
    affiliate?.healthInsuranceCode || ""
  );
  const [email, setEmail] = React.useState(affiliate?.email || "");
  const [errors, setErrors] = useState<ValidationErrors>({});
  const dispatch = useDispatch<AppDispatch>();

  const handleSubmit = async (event: React.FormEvent) => {
    const validationErrors = validateForm(
      firstName,
      lastName,
      dni,
      email,
      healthInsuranceCode
    );
    setErrors(validationErrors);

    event.preventDefault();
    if (Object.keys(validationErrors).length === 0) {
      const affiliatedDTO = {
        firstName,
        lastName,
        dni,
        role: "USER",
        email,
        healthInsuranceCode,
      };
      if (affiliate) {
        await dispatch(
          updateAffiliate({ afffiliateDTO: affiliatedDTO, id: affiliate.id })
        );
      } else {
        await dispatch(addAffiliate(affiliatedDTO));
      }
    }
  };

  return (
    <Box>
      <Typography variant="h6" component="h2">
        Formulario
      </Typography>
      <TextField
        fullWidth
        label="Nombre"
        value={firstName}
        onChange={(e) => setFirstName(e.target.value)}
        margin="normal"
        error={!!errors.firstName}
        helperText={errors.firstName}
      />
      <TextField
        fullWidth
        label="Apellido"
        value={lastName}
        onChange={(e) => setLastName(e.target.value)}
        margin="normal"
        error={!!errors.lastName}
        helperText={errors.lastName}
      />
      <TextField
        fullWidth
        label="DNI"
        value={dni}
        onChange={(e) => setDni(e.target.value)}
        margin="normal"
        error={!!errors.dni}
        helperText={errors.dni}
      />
      <TextField
        fullWidth
        label="N° Obra social"
        value={healthInsuranceCode}
        onChange={(e) => sethealthInsuranceCode(e.target.value)}
        margin="normal"
        error={!!errors.healthInsuranceCode}
        helperText={errors.healthInsuranceCode}
      />
      <TextField
        fullWidth
        label="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        margin="normal"
        error={!!errors.email}
        helperText={errors.email}
      />
      <Button variant="contained" color="primary" onClick={handleSubmit}>
        Submit
      </Button>
    </Box>
  );
};

export default FormAffiliate;
