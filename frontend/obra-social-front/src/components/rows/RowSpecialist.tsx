import React from "react";
import { TableRow, TableCell, Button } from "@mui/material";
import { Specialist } from "../../redux/type";
import BasicModal from "../modal/Modal";
import { deleteSpecialist } from "../../redux/slices/specialistSlice";
import { AppDispatch } from "../../redux/store/store";
import { useDispatch } from "react-redux";
import DeleteIcon from "@mui/icons-material/Delete";

interface RowProps {
  specialist: Specialist;
}

const RowAffiliate: React.FC<RowProps> = ({ specialist }) => {
  const dispatch: AppDispatch = useDispatch();

  const handleDelete = (id: number) => {
    dispatch(deleteSpecialist(id));
  };

  return (
    <TableRow>
      <TableCell component="th" scope="row">
        {specialist.firstName} {specialist.lastName}
      </TableCell>
      <TableCell align="right" style={{ minWidth: 170 }}>
        {specialist.dni}
      </TableCell>
      <TableCell align="right" style={{ minWidth: 170 }}>
        {specialist.email}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 170 }}>
        <div
          className="modal-button-container"
          style={{
            width: 170,
            margin: "auto",
            gap: "10px", // Para separar los botones opcionalmente
          }}
        >
          <BasicModal
            name="Editar"
            title="Actualizar Especialista"
            specialist={specialist}
            proveniencia="specialist" //esto lo ponemos para diferenciar a que form llamar en el modal
          />
        </div>
        <Button
          variant="contained"
          color="error"
          startIcon={<DeleteIcon />}
          onClick={() => handleDelete(specialist.id)}
          style={{ marginLeft: "10px" }} // Opcional, para separar los botones
        >
          Borrar
        </Button>
      </TableCell>
    </TableRow>
  );
};

export default RowAffiliate;
