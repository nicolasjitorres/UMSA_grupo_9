import React, { useEffect } from "react";
import {
  Accordion,
  AccordionSummary,
  AccordionDetails,
  Checkbox,
  FormControl,
  FormControlLabel,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  TextField,
} from "@mui/material";
import {
  dayIndexToDayOfWeek,
  getClosestDate,
} from "../../funcionalities/Funcionalities";
import { DayOfWeek, Shift, Speciality } from "../../redux/type";
import "./Form.css";
import { parseISO } from "date-fns";
import { toZonedTime } from "date-fns-tz";

import { useAppContext } from "../../hooks/AppContext";

interface FormShiftProps {
  handleClose: () => void;
  shift?: Shift;
}

const FormShift: React.FC<FormShiftProps> = ({ handleClose, shift }) => {
  const [description, setDescription] = React.useState(
    shift?.description || ""
  );
  const [selectedTime, setTime] = React.useState(shift?.time.slice(0, 5) || "");
  const [selectedDay, setSelectedDay] = React.useState<DayOfWeek | null>(null);
  const [selectedSpecialist, setSelectedSpecialist] = React.useState<
    number | null
  >(shift?.specialistId || null);
  const [selectedSpeciality, setSelectedSpeciality] =
    React.useState<Speciality | null>(null);

  const {
    shifts,
    specialists,
    schedules,
    add_Shift,
    update_Shift,
    delete_Shift,
  } = useAppContext();

  useEffect(() => {
    if (shift) {
      // Parse the ISO date string
      const shiftDate = parseISO(shift.date);
      // Convert to the correct timezone (Argentina)
      const timeZone = "America/Argentina/Buenos_Aires";
      const zonedDate = toZonedTime(shiftDate, timeZone);
      const dayOfWeekIndex = zonedDate.getDay();
      const dayOfWeek = dayIndexToDayOfWeek[dayOfWeekIndex];
      setSelectedDay(dayOfWeek);
    }
  }, [shift]);

  const handleSpecialityChange = (event: SelectChangeEvent<string>) => {
    setSelectedSpeciality(event.target.value as Speciality);
    setSelectedSpecialist(null); // Reset the selected specialist
  };

  const handleSpecialistChange = (event: SelectChangeEvent<string>) => {
    setSelectedSpecialist(parseInt(event.target.value));
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (selectedSpecialist !== null && selectedDay !== null) {
      const time = selectedTime + ":00";
      const date = getClosestDate(selectedDay);
      const shiftDTO = {
        description,
        date,
        time,
        state: true,
        specialistId: selectedSpecialist,
        affiliatedId: 1,
      };
      shift ? update_Shift(shiftDTO, shift.id) : add_Shift(shiftDTO);

      handleClose();
    }
  };

  const handleDelete = (id: number) => {
    if (shift) {
      delete_Shift(id);
      handleClose();
    }
  };

  const renderTimeOptions = () => {
    if (selectedSpecialist === null || selectedDay === null) return null;

    const filteredSchedules = schedules.filter(
      (schedule) =>
        schedule.specialistId === selectedSpecialist &&
        schedule.dayOfWeek === selectedDay
    );

    if (filteredSchedules.length === 0) return null;

    const schedule = filteredSchedules[0];
    const startTime = parseInt(schedule.startTime.split(":")[0], 10);
    const endTime = parseInt(schedule.endTime.split(":")[0], 10);
    const timeOptions = [];

    // Filtrar los horarios ya asignados
    const assignedTimes = (shifts || [])
      .filter(
        (shift) =>
          shift.specialistId === selectedSpecialist &&
          dayIndexToDayOfWeek[new Date(shift.date).getDay() + 1] === selectedDay
      )
      .map((shift) => shift.time.slice(0, 5));

    for (let i = startTime; i < endTime; i += 0.5) {
      const timeString = `${Math.floor(i).toString().padStart(2, "0")}:${
        i % 1 === 0 ? "00" : "30"
      }`;
      // Excluir horarios ya asignados
      if (!assignedTimes.includes(timeString)) {
        timeOptions.push(
          <FormControlLabel
            key={timeString}
            control={
              <Checkbox
                checked={selectedTime === timeString}
                onChange={() => setTime(timeString)}
              />
            }
            label={timeString}
          />
        );
      }
    }
    return timeOptions;
  };

  const renderDayOptions = () => {
    if (selectedSpecialist === null) return null;
    const days = schedules
      .filter((schedule) => schedule.specialistId === selectedSpecialist)
      .map((schedule) => schedule.dayOfWeek);
    return days.map((day) => {
      return (
        <FormControlLabel
          key={day}
          control={
            <Checkbox
              checked={selectedDay === day}
              onChange={() => setSelectedDay(day)}
            />
          }
          label={`${day}`}
        />
      );
    });
  };

  const renderSpecialistOptions = () => {
    if (selectedSpeciality === null) return null;

    const filteredSpecialists = specialists.filter(
      (specialist) => specialist.speciality === selectedSpeciality
    );

    return filteredSpecialists.map((specialist) => (
      <MenuItem key={specialist.id} value={specialist.id.toString()}>
        {specialist.firstName} {specialist.lastName}
      </MenuItem>
    ));
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <TextField
        fullWidth
        label="Descripción"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        margin="normal"
        className="form-field"
      />
      <FormControl fullWidth className="form-field">
        <InputLabel id="speciality-select-label" className="select-label">
          Seleccionar Especialidad
        </InputLabel>
        <Select
          labelId="speciality-select-label"
          id="speciality-select"
          value={selectedSpeciality || ""}
          label="Especialidad"
          onChange={handleSpecialityChange}
        >
          {Object.keys(Speciality).map((speciality) => (
            <MenuItem key={speciality} value={speciality}>
              {speciality}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      <FormControl fullWidth className="form-field">
        <InputLabel id="specialist-select-label" className="select-label">
          Seleccionar Especialista
        </InputLabel>
        <Select
          labelId="specialist-select-label"
          id="specialist-select"
          value={
            selectedSpecialist !== null ? selectedSpecialist.toString() : ""
          }
          label="Especialista"
          onChange={handleSpecialistChange}
          disabled={selectedSpeciality === null}
        >
          {renderSpecialistOptions()}
        </Select>
      </FormControl>
      {selectedSpecialist !== null && (
        <Accordion className="form-field">
          <AccordionSummary className="accordion-summary">
            Seleccionar Día
          </AccordionSummary>
          <AccordionDetails>{renderDayOptions()}</AccordionDetails>
        </Accordion>
      )}
      {selectedDay !== null && (
        <Accordion className="form-field">
          <AccordionSummary className="accordion-summary">
            Seleccionar Hora
          </AccordionSummary>
          <AccordionDetails className="accordion-details">
            {renderTimeOptions()}
          </AccordionDetails>
        </Accordion>
      )}
      <button type="submit" className={shift ? "edit-button" : "add-button"}>
        {shift ? "Actualizar" : "Agregar"}
      </button>

      {shift && (
        <button
          className="delete-button"
          onClick={() => handleDelete(shift.id)}
        >
          Borrar
        </button>
      )}
    </form>
  );
};

export default FormShift;
