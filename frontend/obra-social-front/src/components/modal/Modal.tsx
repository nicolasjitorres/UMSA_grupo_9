import React from "react";

import {Modal, Box, Typography } from "@mui/material";
import FormShift from "../form/formShift"; // Asegúrate de importar tu componente FormShift
import "./Modal.css"; // Importa tu archivo CSS aquí
import { Affiliate, Shift } from "../../redux/type";
import FormAffiliate from "../form/FormAffiliate";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

interface propModal {
  name: string;
  proveniencia: string;
  shift?: Shift;
  affiliate?: Affiliate;
}

const BasicModal: React.FC<propModal> = ({
  name,
  shift,
  proveniencia,
  affiliate,
}) => {
  const [open, setOpen] = React.useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  
  return (
    <div>
       <button        
        onClick={handleOpen}
        className="add-button"
      >
        {name}
      </button>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            {name}
          </Typography>
          {proveniencia === "shift" ? (
            <FormShift handleClose={handleClose} shift={shift} />
          ) : proveniencia === "affiliate" ? (
            <FormAffiliate affiliate={affiliate} />
          ) : null}
        </Box>
      </Modal>
    </div>
  );
};

export default BasicModal;
