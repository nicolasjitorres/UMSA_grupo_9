import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import {
  Button,
  Checkbox,
  FormControl,
  FormControlLabel,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  TextField,
} from "@mui/material";

import { getClosestDate } from "../../funcionalities/Funcionalities";
import React from "react";
import { DayOfWeek, Schedule, Specialist } from "../../redux/type";
import { AppDispatch, RootState } from "../../redux/store/store";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { addShift } from "../../redux/slices/shiftSlice";

interface FormShiftProps {
  handleClose: () => void;
}

const FormShift: React.FC<FormShiftProps> = ({ handleClose }) => {
  const [description, setDescription] = React.useState(""); //descripcion
  const [selectedTime, setTime] = React.useState(""); //hora caul va asistir
  const [selectedDay, setSelectedDay] = React.useState<DayOfWeek | null>(null);
  //esto trae los registros almacenados de specialist y schedules
  const specialists: Specialist[] = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const schedules: Schedule[] = useSelector(
    (state: RootState) => state.schedules.schedules
  );
  const [selectedSpecialist, setSelectedSpecialist] = React.useState<
    number | null
  >(null);

  const dispatch = useDispatch<AppDispatch>();

  //esto guarda el id del specialist
  const handleChange = (event: SelectChangeEvent<string>) => {
    setSelectedSpecialist(parseInt(event.target.value));
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (selectedSpecialist !== null && selectedDay !== null) {
      const time = selectedTime + ":00";
      const date = getClosestDate(selectedDay);
      console.log(date);
      const shiftDTO = {
        description,
        date,
        time,
        state: true,
        specialistId: selectedSpecialist,
        affiliatedId: 1,
      };
      console.log(shiftDTO);
      await dispatch(addShift(shiftDTO));
      handleClose(); // Cierra el modal después de agregar el turno
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

    for (let i = startTime; i < endTime; i += 0.5) {
      const timeString = `${Math.floor(i).toString().padStart(2, "0")}:${
        i % 1 === 0 ? "00" : "30"
      }`;
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

  return (
    <form onSubmit={handleSubmit}>
      <TextField
        fullWidth
        label="Descripción"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        margin="normal"
      />
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">
          Seleccionar Especialista
        </InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={
            selectedSpecialist !== null ? selectedSpecialist.toString() : ""
          }
          label="Especialista"
          onChange={handleChange}
        >
          {specialists.map((specialist) => (
            <MenuItem key={specialist.id} value={specialist.id.toString()}>
              {specialist.firstName} {specialist.lastName}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
      {selectedSpecialist !== null && (
        <Accordion>
          <AccordionSummary>Seleccionar Día</AccordionSummary>
          <AccordionDetails>{renderDayOptions()}</AccordionDetails>
        </Accordion>
      )}
      {selectedDay !== null && (
        <Accordion>
          <AccordionSummary>Seleccionar Hora</AccordionSummary>
          <AccordionDetails>{renderTimeOptions()}</AccordionDetails>
        </Accordion>
      )}
      <Button type="submit" variant="contained" color="primary">
        Agregar
      </Button>
    </form>
  );
};

export default FormShift;
