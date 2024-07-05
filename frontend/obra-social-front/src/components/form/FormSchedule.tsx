import React, { useState } from "react";
import { DayOfWeek, Schedule } from "../../redux/type";
import {
  FormControl,
  NativeSelect,
  TextField,
  Typography,
} from "@mui/material";
import {
  ValidationErrors,
  validationTime,
} from "../../funcionalities/Validations";
import { useAppContext } from "../../hooks/AppContext";

interface FormScheduleProp {
  schedule?: Schedule;
  specialistID: number;
  handleClose: () => void;
}

const FormSchedule: React.FC<FormScheduleProp> = ({
  schedule,
  specialistID,
  handleClose,
}) => {
  const [startTime, setStartTime] = useState(schedule?.startTime || "");
  const [endTime, setEndTime] = useState(schedule?.endTime || "");
  const [selectedDay, setSelectedDay] = useState<DayOfWeek | null>(
    schedule?.dayOfWeek || null
  );
  const [errors, setErrors] = useState<ValidationErrors>({});

  const { add_Schedules, update_Schedules, delete_Schedules } = useAppContext();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const validationErrors = validationTime(startTime, endTime, selectedDay);
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length > 0) {
      return; // Evitar enviar el formulario si hay errores de validación
    }
    if (selectedDay) {
      const scheduleDTO = {
        startTime: startTime.length === 5 ? `${startTime}:00` : startTime, // ensure hh:mm:ss format
        endTime: endTime.length === 5 ? `${endTime}:00` : endTime, // ensure hh:mm:ss format
        dayOfWeek: selectedDay,
      };

      schedule
        ? update_Schedules(scheduleDTO, schedule.id)
        : add_Schedules(scheduleDTO, specialistID);

      handleClose();
    }
  };

  const handleDelete = (id: number) => {
    if (schedule) {
      delete_Schedules(id);
      handleClose();
    }
  };

  const handleDayChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const value = event.target.value as DayOfWeek;
    setSelectedDay(value);
  };

  return (
    <form onSubmit={handleSubmit}>
      <FormControl fullWidth>
        <NativeSelect
          value={selectedDay || ""}
          onChange={handleDayChange}
          inputProps={{
            name: "day",
            id: "day-select",
          }}
        >
          <option value="" disabled>
            Selecciona un día
          </option>
          {Object.values(DayOfWeek).map((day) => (
            <option key={day} value={day}>
              {day}
            </option>
          ))}
        </NativeSelect>
        {errors.selectedDay && (
          <Typography variant="body2" color="error">
            {errors.selectedDay}
          </Typography>
        )}
      </FormControl>

      <TextField
        label="Hora de inicio"
        type="time"
        value={startTime}
        onChange={(e) => setStartTime(e.target.value)}
        error={!!errors.startTime}
        helperText={errors.startTime}
        InputLabelProps={{
          shrink: true,
          style: { color: "white" }, // color del label
        }}
        inputProps={{
          step: 300, // 5 minutos
          style: { color: "white" }, // color del texto
        }}
        fullWidth
        margin="normal"
      />

      <TextField
        label="Hora de fin"
        type="time"
        value={endTime}
        onChange={(e) => setEndTime(e.target.value)}
        error={!!errors.endTime}
        helperText={errors.endTime}
        InputLabelProps={{
          shrink: true,
          style: { color: "white" }, // color del label
        }}
        inputProps={{
          step: 300, // 5 minutos
          style: { color: "white" }, // color del texto
        }}
        fullWidth
        margin="normal"
      />
      <button color="primary" onClick={handleSubmit} className="add-button">
        {schedule ? "Actualizar" : "Agregar"}
      </button>

      {schedule && (
        <button
          className="delete-button"
          onClick={() => handleDelete(schedule.id)}
        >
          Borrar
        </button>
      )}
    </form>
  );
};

export default FormSchedule;
