import { DayOfWeek } from "../redux/type";

// validations.ts
export interface ValidationErrors {
  firstName?: string;
  lastName?: string;
  dni?: string;
  healthInsuranceCode?: string;
  email?: string;
  startTime?: string;
  endTime?: string;
  selectedDay?: string;
}

export const validateForm = (
  firstName: string,
  lastName: string,
  dni: string,
  healthInsuranceCode: string,
  email: string
): ValidationErrors => {
  const newErrors: ValidationErrors = {};

  if (!firstName) {
    newErrors.firstName = "El campo 'nombre' no debe estar vacío.";
  } else if (firstName.length < 2) {
    newErrors.firstName = "El campo 'nombre' debe tener al menos 2 caracteres.";
  }

  if (!lastName) {
    newErrors.lastName = "El campo 'apellido' no debe estar vacío.";
  } else if (lastName.length < 2) {
    newErrors.lastName =
      "El campo 'apellido' debe tener al menos 2 caracteres.";
  }

  if (!dni) {
    newErrors.dni = "El campo 'dni' no debe estar vacío.";
  } else if (dni.length < 7 || dni.length > 8) {
    newErrors.dni = "El campo 'dni' debe tener entre 7 y 8 dígitos.";
  } else if (!/^\d+$/.test(dni)) {
    newErrors.dni = "El campo 'dni' debe contener solo dígitos númericos.";
  }

  if (!healthInsuranceCode) {
    newErrors.healthInsuranceCode =
      "El campo 'numero de obra social' no debe estar vacío.";
  }

  //const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email) {
    newErrors.email = "El campo 'email' no debe estar vacío.";
  }

  return newErrors;
};

export const validationTime = (
  startTime: string,
  endTime: string,
  selectedDay: DayOfWeek | null
): ValidationErrors => {
  const newErrors: ValidationErrors = {};

  if (!startTime) {
    newErrors.startTime = "El campo 'Horr Inicio' no debe estar vacío.";
  }

  if (!endTime) {
    newErrors.endTime = "El campo 'Hora Fin' no debe estar vacío.";
  }

  if (!selectedDay) {
    newErrors.selectedDay = "El campo Dia no debe estar vacío.";
  }
  return newErrors;
};



export const validateFormSpecialist = (
  firstName: string,
  lastName: string,
  dni: string,
  email: string
): ValidationErrors => {
  const newErrors: ValidationErrors = {};

  if (!firstName) {
    newErrors.firstName = "El campo 'nombre' no debe estar vacío.";
  } else if (firstName.length < 2) {
    newErrors.firstName = "El campo 'nombre' debe tener al menos 2 caracteres.";
  }

  if (!lastName) {
    newErrors.lastName = "El campo 'apellido' no debe estar vacío.";
  } else if (lastName.length < 2) {
    newErrors.lastName =
      "El campo 'apellido' debe tener al menos 2 caracteres.";
  }

  if (!dni) {
    newErrors.dni = "El campo 'dni' no debe estar vacío.";
  } else if (dni.length < 7 || dni.length > 8) {
    newErrors.dni = "El campo 'dni' debe tener entre 7 y 8 dígitos.";
  } else if (!/^\d+$/.test(dni)) {
    newErrors.dni = "El campo 'dni' debe contener solo dígitos númericos.";
  }

  //const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!email) {
    newErrors.email = "El campo 'email' no debe estar vacío.";
  }
  //   } else if (!emailRegex.test(email)) {
  //     newErrors.email =
  //       "El campo 'email' debe ser una dirección de correo electrónico válida.";
  //   }

  return newErrors;
};