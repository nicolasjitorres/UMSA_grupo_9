// types.ts
export interface Shift {
  id: number;
  description: string;
  date: string;
  time: string;
  state: boolean;
  specialistId: number;
  affiliatedId: number;
}

export interface Specialist {
  id: number;
  firstName: string;
  lastName: string;
  dni: string;
  speciality: string;
  location: Location; // Usa la ubicación con `id`
  role: string;
  email: string;
}

export enum Speciality {
  CARDIOLOGÍA = "Cardiología",
  DERMATOLOGÍA = "Dermatología",
  ENDOCRINOLOGÍA = "Endocrinología",
  ONCOLOGÍA = "Oncología",
  ORTOPEDÍA = "Ortopedia",
  UROLOGÍA = "Urología",
  ODONTOLOGÍA = "Odontología",
  NEUROLOGÍA = "Neurología",
  HEMATOLOGÍA = "Hematología",
  MEDICINAGENERAL = "Medicina General",
}

export enum DayOfWeek {
  SUNDAY = "SUNDAY",
  MONDAY = "MONDAY",
  TUESDAY = "TUESDAY",
  WEDNESDAY = "WEDNESDAY",
  THURSDAY = "THURSDAY",
  FRIDAY = "FRIDAY",
  SATURDAY = "SATURDAY",
}

export interface Schedule {
  id: number;
  startTime: string;
  endTime: string;
  dayOfWeek: DayOfWeek;
  specialistId: number;
}

export interface Affiliate {
  id: number;
  firstName: string;
  lastName: string;
  dni: string;
  healthInsuranceCode: string;
  role: string;
  email: string;
}

export interface Prescription {
  id: number;
  description: string;
  shift: Shift;
}

export interface Location {
  id?: number;
  street: string;
  locality: string;
  province: string;
  country: string;
}

//de aca abajo es dto

export interface ShiftDTO {
  description: string;
  date: string;
  time: string;
  state: boolean;
  specialistId: number;
  affiliatedId: number;
}

export interface AffiliateDTO {
  firstName: string;
  lastName: string;
  dni: string;
  healthInsuranceCode: string;
  role: string;
  email: string;
}

export interface SpecialistDTO {
  firstName: string;
  lastName: string;
  dni: string;
  speciality: string;
  location: Location;
  role: string;
  email: string;
}

export interface ScheduleDTO {
  startTime: string;
  endTime: string;
  dayOfWeek: DayOfWeek;
}

export interface PrescriptionDTO {
  description: string;
  idShift: number;
}

export interface EmailDTO {
  email: string;
  message: string;
}
