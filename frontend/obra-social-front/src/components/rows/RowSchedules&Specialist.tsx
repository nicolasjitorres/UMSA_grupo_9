import React, { useEffect, useState } from "react";
import {
  TableRow,
  TableCell,
  IconButton,
  Collapse,
  Box,
  Typography,
  Table,
  TableHead,
  TableBody,
} from "@mui/material";
import { KeyboardArrowDown, KeyboardArrowUp } from "@mui/icons-material";
import { Schedule, Specialist } from "../../redux/type"; // Ajusta la ruta según la ubicación de tus tipos
import AddSchedulesButton from "../buttonToAdd/AddSchedulesButton";
import BasicModal from "../modal/Modal";
import { useAppContext } from "../../hooks/AppContext";

interface RowProps {
  specialist: Specialist;
}

const RowSchedulesSpecialist: React.FC<RowProps> = ({ specialist }) => {
  const [open, setOpen] = React.useState(false);
  const { schedules } = useAppContext();

  const [schedulesFromSpecialist, setSchedulesFromSpecialist] = useState<
    Schedule[]
  >([]);

  useEffect(() => {
    if (schedules.length > 0) {
      setSchedulesFromSpecialist(
        schedules.filter((schedule) => schedule.specialistId === specialist.id)
      );
    } else {
      setSchedulesFromSpecialist([]);
    }
  }, [schedules, specialist.id]);

  return (
    <React.Fragment>
      <TableRow>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUp /> : <KeyboardArrowDown />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {specialist.firstName} {specialist.lastName}
        </TableCell>
        <TableCell align="right">{specialist.speciality}</TableCell>
        <TableCell align="right">
          {`${specialist.location.street}, ${specialist.location.locality}, ${specialist.location.province}, ${specialist.location.country}`}
        </TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "flex-start",
                  }}
                >
                  Schedule
                  <div style={{ marginLeft: "10px" }}>
                    <AddSchedulesButton specialistID={specialist.id} />
                  </div>
                </div>
              </Typography>
              <Table size="small" aria-label="schedule">
                <TableHead>
                  <TableRow>
                    <TableCell>Dia</TableCell>
                    <TableCell align="right">Hora Inico</TableCell>
                    <TableCell align="right">Hora Fin</TableCell>
                    <TableCell align="right">Acciones</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {schedulesFromSpecialist.length > 0 ? (
                    schedulesFromSpecialist.map((schedule) => (
                      <TableRow key={schedule.id}>
                        <TableCell>{schedule.dayOfWeek}</TableCell>
                        <TableCell align="right">
                          {schedule.startTime}
                        </TableCell>
                        <TableCell align="right">{schedule.endTime}</TableCell>
                        <TableCell align="right">
                          <BasicModal
                            name="Editar"
                            title="Actualizar horario"
                            schedule={schedule}
                            proveniencia="schedules"
                            specialistID={specialist.id}
                          />
                        </TableCell>
                      </TableRow>
                    ))
                  ) : (
                    <TableRow>
                      <TableCell colSpan={4} align="center">
                        No hay horarios
                      </TableCell>
                    </TableRow>
                  )}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
};

export default RowSchedulesSpecialist;
