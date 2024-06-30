import React from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { Shift } from "../../redux/type"; // Ajusta esta importación según tu estructura de carpetas
import RowShift from "../rows/RowShift";

interface Column {
  id: "id" | "description" | "date" | "time" | "actions";
  label: string;
  minWidth?: number;
  align?: "right" | "center"; // Añade "center" para la columna de acciones
  format?: (value: number) => string;
}

const columns: Column[] = [
  { id: "id", label: "ID", minWidth: 170 },
  { id: "description", label: "Description", minWidth: 100 },
  { id: "date", label: "Date", minWidth: 170, align: "right" },
  { id: "time", label: "Time", minWidth: 170, align: "right" },
  { id: "actions", label: "Actions", minWidth: 170, align: "center" },
];

interface ShiftListProps {
  shifts: Shift[];
}

const ShiftList: React.FC<ShiftListProps> = ({ shifts }) => {

  return (
    <TableContainer sx={{ maxHeight: 440 }}>
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
            shifts.map((shift) => <RowShift shift={shift} />)
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
  );
};

export default ShiftList;
