import React from "react";
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
import { Specialist, Schedule } from "../../redux/type"; // Ajusta la ruta según la ubicación de tus tipos

interface RowProps {
  specialist: Specialist;
  schedules: Schedule[];
}

const Row: React.FC<RowProps> = ({ specialist, schedules }) => {
  const [open, setOpen] = React.useState(false);

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
                Schedule
              </Typography>
              <Table size="small" aria-label="schedule">
                <TableHead>
                  <TableRow>
                    <TableCell>Day</TableCell>
                    <TableCell align="right">Start Time</TableCell>
                    <TableCell align="right">End Time</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {schedules.map((schedule) => (
                    <TableRow key={schedule.id}>
                      <TableCell>{schedule.dayOfWeek}</TableCell>
                      <TableCell align="right">{schedule.startTime}</TableCell>
                      <TableCell align="right">{schedule.endTime}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
};

export default Row;
