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
  location: Location; // Usa la ubicación con `id`
  role: string;
  email: string;
  password: string;
}

export interface ScheduleDTO {
  startTime: string;
  endTime: string;
  dayOfWeek: DayOfWeek;
}
