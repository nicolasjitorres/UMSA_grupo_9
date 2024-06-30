import { Button, TableCell, TableRow } from "@mui/material";
import { Shift } from "../../redux/type";
import BasicModal from "../modal/Modal";
import { AppDispatch } from "../../redux/store/store";
import { useDispatch } from "react-redux";
import { deleteShift } from "../../redux/slices/shiftSlice";
import DeleteIcon from "@mui/icons-material/Delete";

interface RowProps {
  shift: Shift;
}

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

const RowShift: React.FC<RowProps> = ({ shift }) => {
  const dispatch: AppDispatch = useDispatch();

  const handleDelete = (id: number) => {
    dispatch(deleteShift(id));
  };
  return (
    <TableRow hover role="checkbox" tabIndex={-1} key={shift.id}>
      {columns.map((column) => {
        if (column.id === "actions") {
          return (
            <TableCell key={column.id} align={column.align}>
              <BasicModal
                name="actualizar turno"
                shift={shift}
                proveniencia="shift"
              />
              <Button
                variant="contained"
                color="error"
                startIcon={<DeleteIcon />}
                onClick={() => handleDelete(shift.id)}
              >
                Borrar
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
  );
};

export default RowShift;
