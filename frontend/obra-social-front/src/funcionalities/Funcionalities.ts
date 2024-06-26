import { DayOfWeek } from "../redux/type";

const dayOfWeekToIndex: { [key in DayOfWeek]: number } = {
  [DayOfWeek.SUNDAY]: 1,
  [DayOfWeek.MONDAY]: 2,
  [DayOfWeek.TUESDAY]: 3,
  [DayOfWeek.WEDNESDAY]: 4,
  [DayOfWeek.THURSDAY]: 5,
  [DayOfWeek.FRIDAY]: 6,
  [DayOfWeek.SATURDAY]: 7,
};

//esto trasnforma la fecha elegida en la mas proxima si se elige el domingo seria el 30 (?)
export const getClosestDate = (day: DayOfWeek): string => {
  const dayIndex = dayOfWeekToIndex[day];
  const today = new Date();
  const todayIndex = ((today.getDay() + 1) % 7) + 1; // Ajustar índice de hoy de 1 a 7
  const daysUntilNext = (dayIndex - todayIndex + 7) % 7;

  // Si daysUntilNext es 0, sumamos 7 para obtener la misma fecha de la próxima semana
  const daysToAdd = daysUntilNext === 0 ? 7 : daysUntilNext;

  const nextDate = new Date(today);
  nextDate.setDate(today.getDate() + daysToAdd);

  return nextDate.toISOString().split("T")[0]; // Formato YYYY-MM-DD
};
