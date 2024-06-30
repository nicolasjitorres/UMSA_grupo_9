import React from "react";
import { TableRow, TableCell, Button } from "@mui/material";
import { Affiliate } from "../../redux/type"; // Ajusta la ruta según la ubicación de tus tipos
import BasicModal from "../modal/Modal";
import { deleteAffiliate } from "../../redux/slices/AfiliatedSlice";
import { AppDispatch } from "../../redux/store/store";
import { useDispatch } from "react-redux";
import DeleteIcon from "@mui/icons-material/Delete";

interface RowProps {
  affiliate: Affiliate;
}

const RowAffiliate: React.FC<RowProps> = ({ affiliate }) => {
  const dispatch: AppDispatch = useDispatch();

  const handleDelete = (id: number) => {
    dispatch(deleteAffiliate(id));
  };

  return (
    <TableRow>
      <TableCell component="th" scope="row">
        {affiliate.firstName} {affiliate.lastName}
      </TableCell>
      <TableCell align="right">{affiliate.dni}</TableCell>
      <TableCell align="right">{affiliate.email}</TableCell>
      <TableCell align="right">
        {
          <BasicModal
            name="Actualizar afiliado"
            affiliate={affiliate}
            proveniencia="affiliate" //esto lo ponemos para diferenciar a que form llamar en el modal
          />
        }
      </TableCell>
      <Button
        variant="contained"
        color="error"
        startIcon={<DeleteIcon />}
        onClick={() => handleDelete(affiliate.id)}
      >
        Borrar
      </Button>
    </TableRow>
  );
};

export default RowAffiliate;
