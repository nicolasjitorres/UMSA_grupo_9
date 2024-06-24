// types.ts
export interface Location {
  id: number; // Asegúrate de que todos los lugares incluyan `id`
  street: string;
  locality: string;
  province: string;
  country: string;
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
