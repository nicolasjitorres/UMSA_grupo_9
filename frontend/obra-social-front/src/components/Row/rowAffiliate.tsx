import React from "react";
import { TableRow, TableCell } from "@mui/material";
import { Affiliate } from "../../redux/type"; // Ajusta la ruta según la ubicación de tus tipos

interface RowProps {
  affiliate: Affiliate;
}

const Row: React.FC<RowProps> = ({ affiliate }) => {
  return (
    <TableRow>
      <TableCell component="th" scope="row">
        {affiliate.firstName} {affiliate.lastName}
      </TableCell>
      <TableCell align="right">{affiliate.dni}</TableCell>
      <TableCell align="right">{affiliate.email}</TableCell>
    </TableRow>
  );
};

export default Row;
