// types.ts
export interface Shift {
  id: number; // Hacer que 'id' sea opcional
  description: string;
  date: string;
  time: string;
  state: boolean;
}

export interface ShiftDTO {
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
  password: string;
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
  specialist: Specialist;
}

export interface Affiliate {
  id: number;
  firstName: string;
  lastName: string;
  dni: string;
  healthInsuranceCode: string;
  role: string;
  email: string;
  password: string;
}
