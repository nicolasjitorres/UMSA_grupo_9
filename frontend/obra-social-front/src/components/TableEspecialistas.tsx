import * as React from 'react';
import Box from '@mui/material/Box';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';

// Interfaces para los datos de especialistas, ubicación y horario
interface Specialist {
  id: number;
  nombre: string;
  apellido: string;
  especialidad: string;
  ubicacion: Location;
  horario: Schedule[];
}

interface Location {
  calle: string;
  numero: string;
  pais: string;
  localidad: string;
}

interface Schedule {
  dia: string;
  horaInicio: string;
  horaFin: string;
}

// Función para crear datos de especialistas
function createData(
  id: number,
  nombre: string,
  apellido: string,
  especialidad: string,
  ubicacion: Location,
  horario: Schedule[],
) {
  return {
    id,
    nombre,
    apellido,
    especialidad,
    ubicacion,
    horario,
    history: [], // No se utiliza history en este ejemplo
  };
}

// Componente de fila para cada especialista
function Row(props: { row: Specialist }) {
  const { row } = props;
  const [open, setOpen] = React.useState(false);

  return (
    <React.Fragment>
      <TableRow sx={{ '& > *': { borderBottom: 'unset' } }}>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={() => setOpen(!open)}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell component="th" scope="row">
          {row.nombre} {row.apellido}
        </TableCell>
        <TableCell align="right">{row.especialidad}</TableCell>
        <TableCell align="right">{`${row.ubicacion.calle}, ${row.ubicacion.numero}, ${row.ubicacion.localidad}, ${row.ubicacion.pais}`}</TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box sx={{ margin: 1 }}>
              <Typography variant="h6" gutterBottom component="div">
                Horario
              </Typography>
              <Table size="small" aria-label="schedule">
                <TableHead>
                  <TableRow>
                    <TableCell>Día</TableCell>
                    <TableCell align="right">Hora de Inicio</TableCell>
                    <TableCell align="right">Hora de Fin</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {row.horario.map((scheduleRow, index) => (
                    <TableRow key={index}>
                      <TableCell>{scheduleRow.dia}</TableCell>
                      <TableCell align="right">{scheduleRow.horaInicio}</TableCell>
                      <TableCell align="right">{scheduleRow.horaFin}</TableCell>
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
}

// Datos de ejemplo de especialistas
const rows: Specialist[] = [
  createData(1, 'John', 'Doe', 'Cardiología', { calle: 'Avenida Principal', numero: '123', pais: 'Argentina', localidad: 'Buenos Aires' }, [
    { dia: 'Lunes', horaInicio: '08:00', horaFin: '17:00' },
    { dia: 'Miércoles', horaInicio: '09:00', horaFin: '16:00' },
  ]),
  createData(2, 'Jane', 'Smith', 'Pediatría', { calle: 'Calle Secundaria', numero: '456', pais: 'Argentina', localidad: 'Córdoba' }, [
    { dia: 'Martes', horaInicio: '10:00', horaFin: '18:00' },
    { dia: 'Jueves', horaInicio: '08:30', horaFin: '15:30' },
  ]),
  // Agrega más filas según sea necesario
];

// Componente principal de la tabla colapsable de especialistas
export default function CollapsibleTable() {
  return (
    <TableContainer component={Paper}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            <TableCell />
            <TableCell>Especialista</TableCell>
            <TableCell align="right">Especialidad</TableCell>
            <TableCell align="right">Ubicación</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <Row key={row.id} row={row} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
