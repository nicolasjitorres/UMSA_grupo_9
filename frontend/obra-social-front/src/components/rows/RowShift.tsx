import { TableCell, TableRow } from "@mui/material";
import { Prescription, Shift } from "../../redux/type";
import BasicModal from "../modal/Modal";
import AddPrescriptionButton from "../buttonToAdd/AddPrescriptionButton";
import { useAppContext } from "../../hooks/AppContext";
import { useEffect, useState } from "react";
interface RowProps {
  shift: Shift;
}

interface Column {
  id: "description" | "date" | "time" | "actions" | "receta";
  label: string;
  minWidth?: number;
  align?: "right" | "center"; // Añade "center" para la columna de acciones
  format?: (value: number) => string;
}

const columns: Column[] = [
  { id: "description", label: "Description", minWidth: 100,align: "center" },
  { id: "date", label: "Date", minWidth: 170, align: "center" },
  { id: "time", label: "Time", minWidth: 170, align: "center" },
  { id: "actions", label: "Actions", minWidth: 170, align: "center" },
  { id: "receta", label: "Receta", minWidth: 170, align: "center" },
];

const RowShift: React.FC<RowProps> = ({ shift }) => {
  const { prescription } = useAppContext();

  const [shiftPrescription, setShiftPrescription] =
    useState<Prescription | null>(null);

  useEffect(() => {
    if (prescription && shift && shift.id) {
      const prescriptionOfShift = prescription.find(
        (prescription) => prescription.shift.id === shift.id
      );
      setShiftPrescription(prescriptionOfShift || null);
    }
  }, [prescription, shift.id]);

  return (
    <TableRow hover role="checkbox" tabIndex={-1} key={shift.id}>
      {columns.map((column) => {
        if (column.id === "actions") {
          return (
            <TableCell key={column.id} align={column.align}>
              <BasicModal
                name="Gestionar"
                title="Actualizar turno"
                shift={shift}
                proveniencia="shift"
              />
            </TableCell>
          );
        }
        if (column.id === "receta") {
          return (
            <TableCell key={column.id} align={column.align}>
              {shiftPrescription ? (
                <BasicModal
                  name="Gestionar"
                  title="Ver Receta"
                  prescription={shiftPrescription}
                  proveniencia="receta"
                  shiftID={shift.id}
                />
              ) : (
                <AddPrescriptionButton shiftID={shift.id} />
              )}
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
