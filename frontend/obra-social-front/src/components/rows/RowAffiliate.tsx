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
      <TableCell align="center" component="th" scope="row">
        {affiliate.firstName} {affiliate.lastName}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 170 }}>
        {affiliate.dni}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 170 }}>
        {affiliate.email}
      </TableCell>
      <TableCell align="center" style={{ minWidth: 170 }}>  
          <BasicModal
            name="Gestionar"
            title="Actualizar Afiliado"
            affiliate={affiliate}
            proveniencia="affiliate"
          />
      </TableCell>
    </TableRow>
  );
};

export default RowAffiliate;
