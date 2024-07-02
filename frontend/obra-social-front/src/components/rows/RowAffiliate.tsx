import React from "react";
import { TableRow, TableCell } from "@mui/material";
import { Affiliate } from "../../redux/type"; // Ajusta la ruta según la ubicación de tus tipos
import BasicModal from "../modal/Modal";

interface RowProps {
  affiliate: Affiliate;
}

const RowAffiliate: React.FC<RowProps> = ({ affiliate }) => {
  return (
    <TableRow>
      <TableCell component="th" scope="row">
        {affiliate.firstName} {affiliate.lastName}
      </TableCell>
      <TableCell align="right" style={{ minWidth: 170 }}>
        {affiliate.dni}
      </TableCell>
      <TableCell align="right" style={{ minWidth: 170 }}>
        {affiliate.email}
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
            title="Actualizar Afiliado"
            affiliate={affiliate}
            proveniencia="affiliate" //esto lo ponemos para diferenciar a que form llamar en el modal
          />
        </div>
      </TableCell>
    </TableRow>
  );
};

export default RowAffiliate;
