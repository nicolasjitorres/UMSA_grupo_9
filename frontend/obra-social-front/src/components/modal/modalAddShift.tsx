import * as React from "react";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../redux/store/store";
import { addShift } from "../../redux/slices/shiftSlice"; // Asegúrate de importar addShift
import { Specialist, Schedule, DayOfWeek } from "../../redux/type"; // Asegúrate de importar los modelos correctamente
import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  TextField,
} from "@mui/material";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.white",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

function BasicModal() {
  const [open, setOpen] = React.useState(false);
  const [description, setDescription] = React.useState(""); //descripcion
  const [selectedDate, setDate] = React.useState(""); //fecha caul va asistir
  const [selectedTime, setTime] = React.useState(""); //hora caul va asistir

  //
  const [selectedSpecialist, setSelectedSpecialist] = React.useState<
    number | null
  >(null);

  const [selectedDay, setSelectedDay] = React.useState<DayOfWeek | null>(null);

  const dispatch = useDispatch<AppDispatch>();

  //esto trae los registros almacenados de specialist y schedules
  const specialists: Specialist[] = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const schedules: Schedule[] = useSelector(
    (state: RootState) => state.schedules.schedules
  );

  //esto es del modal
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  //esto guarda el id del specialist
  const handleChange = (event: SelectChangeEvent<string>) => {
    setSelectedSpecialist(parseInt(event.target.value));
  };

  //esto trasnforma la fecha elegida en la mas proxima si se elige el domingo seria el 30 (?)
  const getClosestDate = (day: DayOfWeek): string => {
    const dayIndex = Object.values(DayOfWeek).indexOf(day);
    const today = new Date();
    const todayIndex = today.getDay();
    const daysUntilNext = (dayIndex - todayIndex + 7) % 7;
    const nextDate = new Date(today);
    nextDate.setDate(today.getDate() + daysUntilNext);
    return nextDate.toISOString().split("T")[0]; // Formato YYYY-MM-DD
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (selectedSpecialist !== null && selectedDay !== null) {
      setDate(getClosestDate(selectedDay));
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
      dispatch(addShift(shiftDTO));
      handleClose();
    }
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

  return (
    <div>
      <Button onClick={handleOpen}>Open modal</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Agregar Turno
          </Typography>
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
                  selectedSpecialist !== null
                    ? selectedSpecialist.toString()
                    : ""
                }
                label="Especialista"
                onChange={handleChange}
              >
                {specialists.map((specialist) => (
                  <MenuItem
                    key={specialist.id}
                    value={specialist.id.toString()}
                  >
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
        </Box>
      </Modal>
    </div>
  );
}

export default BasicModal;
