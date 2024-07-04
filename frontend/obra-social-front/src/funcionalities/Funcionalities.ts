import { DayOfWeek } from "../redux/type";
import jsPDF from "jspdf";

const dayOfWeekToIndex: { [key in DayOfWeek]: number } = {
  [DayOfWeek.MONDAY]: 1,
  [DayOfWeek.TUESDAY]: 2,
  [DayOfWeek.WEDNESDAY]: 3,
  [DayOfWeek.THURSDAY]: 4,
  [DayOfWeek.FRIDAY]: 5,
  [DayOfWeek.SATURDAY]: 6,
  [DayOfWeek.SUNDAY]: 0, // Asegúrate de que domingo sea 0 para coincidir con getDay()
};

export const dayIndexToDayOfWeek: { [key: number]: DayOfWeek } = {
  1: DayOfWeek.MONDAY,
  2: DayOfWeek.TUESDAY,
  3: DayOfWeek.WEDNESDAY,
  4: DayOfWeek.THURSDAY,
  5: DayOfWeek.FRIDAY,
  6: DayOfWeek.SATURDAY,
  0: DayOfWeek.SUNDAY,
};

//esto trasnforma la fecha elegida en la mas proxima si se elige el domingo seria el 30 (?)
export const getClosestDate = (day: DayOfWeek): string => {
  const dayIndex = dayOfWeekToIndex[day];
  const today = new Date();
  const todayIndex = today.getDay(); // El índice de hoy sin ajustes

  // Calcular los días hasta el próximo día de la semana deseado
  const daysUntilNext = (dayIndex - todayIndex + 7) % 7;

  // Si daysUntilNext es 0, eso significa que hoy es el día deseado, así que debemos sumar 7 días para obtener el mismo día la próxima semana
  const daysToAdd = daysUntilNext === 0 ? 7 : daysUntilNext;

  const nextDate = new Date(today);
  nextDate.setDate(today.getDate() + daysToAdd);

  return nextDate.toISOString().split("T")[0]; // Formato YYYY-MM-DD
};

export const generatePdf = (description: string) => {
  const doc = new jsPDF();
  doc.text(description, 10, 10);
  doc.save("prescription.pdf");
};
