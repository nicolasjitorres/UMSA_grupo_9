import { TableCell, TableRow } from "@mui/material";
import { Prescription, Shift } from "../../redux/type";
import BasicModal from "../modal/Modal";
import AddPrescriptionButton from "../buttonToAdd/AddPrescriptionButton";
import { useAppContext } from "../../hooks/AppContext";
import { useEffect, useState } from "react";

interface RowProps {
  shift: Shift;
}

const RowShift: React.FC<RowProps> = ({ shift }) => {
  const { prescription, specialists } = useAppContext();
  const [shiftPrescription, setShiftPrescription] =
    useState<Prescription | null>(null);
  const [specialistName, setSpecialistName] = useState<string>("");

  useEffect(() => {
    if (prescription && shift && shift.id) {
      const prescriptionOfShift = prescription.find(
        (prescription) => prescription.shift.id === shift.id
      );
      setShiftPrescription(prescriptionOfShift || null);
    }
  }, [prescription, shift.id]);

  useEffect(() => {
    const specialist = specialists.find(
      (specialist) => specialist.id === shift.specialistId
    );
    setSpecialistName(
      specialist
        ? `${specialist.firstName} ${specialist.lastName}`
        : "Unknown Specialist"
    );
  }, [specialists, shift.specialistId]);

  return (
    <TableRow>
      <TableCell align="center" component="th" scope="row">
        {shift.description}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 100 }}>
        {shift.date}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 100 }}>
        {shift.time}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 100 }}>
        {specialistName}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 100 }}>
        <BasicModal
          name="Gestionar"
          title="Actualizar turno"
          shift={shift}
          proveniencia="shift"
        />
      </TableCell>
      <TableCell align="center" style={{ minWidth: 100 }}>
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
    </TableRow>
  );
};

export default RowShift;
