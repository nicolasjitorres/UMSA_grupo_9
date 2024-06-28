import React from "react";
import {
  Button,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { Shift } from "../../redux/type"; // Ajusta esta importación según tu estructura de carpetas
import { AppDispatch } from "../../redux/store/store";
import { useDispatch } from "react-redux";
import { deleteShift } from "../../redux/slices/shiftSlice";
import DeleteIcon from "@mui/icons-material/Delete";
import BasicModal from "../modal/Modal";

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
  const dispatch: AppDispatch = useDispatch();

  const handleDelete = (id: number) => {
    dispatch(deleteShift(id));
  };

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
            shifts.map((shift) => (
              <TableRow hover role="checkbox" tabIndex={-1} key={shift.id}>
                {columns.map((column) => {
                  if (column.id === "actions") {
                    return (
                      <TableCell key={column.id} align={column.align}>
                        <BasicModal name="actualizar Modal" shift={shift} />
                        <Button
                          variant="contained"
                          color="error"
                          startIcon={<DeleteIcon />}
                          onClick={() => handleDelete(shift.id)}
                        >
                          Delete
                        </Button>
                      </TableCell>
                    );
                  }
                  const value = shift[column.id as keyof Shift];
                  return (
                    <TableCell key={column.id} align={column.align}>
                      {column.format && typeof value === "number"
                        ? column.format(value)
                        : value}
                    </TableCell>
                  );
                })}
              </TableRow>
            ))
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
