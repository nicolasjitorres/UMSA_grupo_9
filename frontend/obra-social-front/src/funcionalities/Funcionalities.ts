import { DayOfWeek } from "../redux/type";
import jsPDF from "jspdf";

export const dayOfWeekToIndex: { [key in DayOfWeek]: number } = {
  [DayOfWeek.LUNES]: 1,
  [DayOfWeek.MARTES]: 2,
  [DayOfWeek.MIERCOLES]: 3,
  [DayOfWeek.JUEVES]: 4,
  [DayOfWeek.VIERNES]: 5,
  [DayOfWeek.SABADO]: 6,
  [DayOfWeek.DOMINGO]: 0,
};

export const dayIndexToDayOfWeek: { [key: number]: DayOfWeek } = {
  1: DayOfWeek.LUNES,
  2: DayOfWeek.MARTES,
  3: DayOfWeek.MIERCOLES,
  4: DayOfWeek.JUEVES,
  5: DayOfWeek.VIERNES,
  6: DayOfWeek.SABADO,
  0: DayOfWeek.DOMINGO,
};

//esto trasnforma la fecha elegida en la mas proxima si se elige el domingo seria el 30 (?)
export const getClosestDate = (day: DayOfWeek): string => {
  const dayIndex = dayOfWeekToIndex[day];
  const today = new Date();
  const todayIndex = today.getDay(); // El índice de hoy sin ajustes

  // Calcular los días hasta el próximo día de la semana deseado
  let daysUntilNext = (dayIndex - todayIndex + 7) % 7;

  // Si daysUntilNext es 0, eso significa que hoy es el día deseado
  // Así que debemos sumar 7 días para obtener el mismo día la próxima semana
  if (daysUntilNext === 0) {
    daysUntilNext = 7;
  }

  const nextDate = new Date(today);
  nextDate.setDate(today.getDate() + daysUntilNext);

  return nextDate.toISOString().split("T")[0]; // Formato YYYY-MM-DD
};

export const generatePdf = (description: string) => {
  const doc = new jsPDF();
  doc.text(description, 10, 10);
  doc.save("prescription.pdf");
};
