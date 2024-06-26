import { DayOfWeek } from "../redux/type";

const dayOfWeekToIndex: { [key in DayOfWeek]: number } = {
  [DayOfWeek.MONDAY]: 0,
  [DayOfWeek.TUESDAY]: 1,
  [DayOfWeek.WEDNESDAY]: 2,
  [DayOfWeek.THURSDAY]: 3,
  [DayOfWeek.FRIDAY]: 4,
  [DayOfWeek.SATURDAY]: 5,
  [DayOfWeek.SUNDAY]: 6,
};

export const dayIndexToDayOfWeek: { [key: number]: DayOfWeek } = {
  0: DayOfWeek.MONDAY,
  1: DayOfWeek.TUESDAY,
  2: DayOfWeek.WEDNESDAY,
  3: DayOfWeek.THURSDAY,
  4: DayOfWeek.FRIDAY,
  5: DayOfWeek.SATURDAY,
  6: DayOfWeek.SUNDAY,
};

//esto trasnforma la fecha elegida en la mas proxima si se elige el domingo seria el 30 (?)
export const getClosestDate = (day: DayOfWeek): string => {
  const dayIndex = dayOfWeekToIndex[day];
  const today = new Date();
  const todayIndex = (today.getDay() + 1) % 7; // Ajustar índice de hoy de 1 a 7
  const daysUntilNext = (dayIndex - todayIndex + 7) % 7;

  // Si daysUntilNext es 0, sumamos 7 para obtener la misma fecha de la próxima semana
  const daysToAdd = daysUntilNext === 0 ? 7 : daysUntilNext;

  const nextDate = new Date(today);
  nextDate.setDate(today.getDate() + (daysToAdd + 2));

  return nextDate.toISOString().split("T")[0]; // Formato YYYY-MM-DD
};
