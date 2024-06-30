import React, { useEffect } from "react";
import {
  Accordion,
  AccordionSummary,
  AccordionDetails,
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
import {
  dayIndexToDayOfWeek,
  getClosestDate,
} from "../../funcionalities/Funcionalities";
import { DayOfWeek, Schedule, Shift, Specialist } from "../../redux/type";
import { AppDispatch, RootState } from "../../redux/store/store";
import { useDispatch, useSelector } from "react-redux";
import "./Form.css";
import "../buttonToAdd/Button.css";
import { addShift, updateShift } from "../../redux/slices/ShiftSlice";


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

  const specialists: Specialist[] = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const schedules: Schedule[] = useSelector(
    (state: RootState) => state.schedules.schedules
  );
  const shifts: Shift[] = useSelector((state: RootState) => state.shift.shifts);
  const dispatch = useDispatch<AppDispatch>();

  useEffect(() => {
    if (shift) {
      const shiftDate = new Date(shift.date);
      const dayOfWeekIndex = shiftDate.getDay();
      const dayOfWeek = dayIndexToDayOfWeek[dayOfWeekIndex];
      setSelectedDay(dayOfWeek);
    }
  }, [shift]);

  const handleChange = (event: SelectChangeEvent<string>) => {
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
        affiliatedId: 2,
      };
      if (shift) {
        await dispatch(updateShift({ shiftDTO, id: shift.id }));
      } else {
        await dispatch(addShift(shiftDTO));
      }

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
          dayIndexToDayOfWeek[new Date(shift.date).getDay()] === selectedDay
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
        <InputLabel id="demo-simple-select-label" className="select-label">
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
      <button type="submit" color="primary" className="add-button">
        {shift ? "Actualizar" : "Agregar"}
      </button>

      
    </form>
  );
};

export default FormShift;