import * as React from "react";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Modal from "@mui/material/Modal";
import Box from "@mui/material/Box";
import FormShift from "../form/formShift";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.white",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

interface nameModal {
  name: string;
}

const BasicModal: React.FC<nameModal> = ({ name }) => {
  const [open, setOpen] = React.useState(false);

  //esto es del modal
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  //esto trasnforma la fecha elegida en la mas proxima si se elige el domingo seria el 30 (?)

  return (
    <div>
      <Button variant="contained" color="success" onClick={handleOpen}>
        {name}
      </Button>
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
          <FormShift handleClose={handleClose} />
        </Box>
      </Modal>
    </div>
  );
};

export default BasicModal;
