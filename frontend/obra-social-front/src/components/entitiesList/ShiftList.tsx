import React from "react";
import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
} from "@mui/material";
import { Shift } from "../../redux/type"; // Ajusta esta importación según tu estructura de carpetas
import RowShift from "../rows/RowShift";
import "./Table.css";

interface Column {
  id: "descripcion" | "dia" | "hora" | "acciones" | "receta";
  label: string;
  minWidth?: number;
  align?: "right" | "center"; // Añade "center" para la columna de acciones
  format?: (value: number) => string;
}

const columns: Column[] = [
  { id: "descripcion", align: "center", label: "Descripcion", minWidth: 100 },
  { id: "dia", label: "Dia", minWidth: 170, align: "center" },
  { id: "hora", label: "Hora", minWidth: 170, align: "center" },
  { id: "acciones", label: "Acciones", minWidth: 170, align: "center" },
  { id: "receta", label: "Receta", minWidth: 170, align: "center" },
];

interface ShiftListProps {
  shifts: Shift[];
}

const ShiftList: React.FC<ShiftListProps> = ({ shifts }) => {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
    <Paper
      sx={{
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
      }}
    >
      <TableContainer sx={{ flex: 1, overflowY: "auto" }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {shifts.length > 0 ? (
              shifts
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((shift) => <RowShift shift={shift} />)
            ) : (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  No hay Turnos
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={shifts.length}
        rowsPerPage={rowsPerPage}
        labelRowsPerPage={"Filas por pagina"}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default ShiftList;
