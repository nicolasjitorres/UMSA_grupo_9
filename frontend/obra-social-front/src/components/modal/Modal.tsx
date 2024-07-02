import React from "react";

import { Modal, Typography } from "@mui/material";
import FormShift from "../form/FormShift";
import "./Modal.css";
import FormSpecialist from "../form/FormSpecialist";
import { Affiliate, Schedule, Shift, Specialist } from "../../redux/type";
import FormAffiliate from "../form/FormAffiliate";
import FormSchedule from "../form/FormSchedule";

interface propModal {
  name: string;
  title: string;
  proveniencia: string;
  shift?: Shift;
  affiliate?: Affiliate;
  specialist?: Specialist;
  schedule?: Schedule;
  specialistID?: number;
}

const BasicModal: React.FC<propModal> = ({
  name,
  title,
  shift,
  proveniencia,
  affiliate,
  specialist,
  schedule,
  specialistID = 0,
}) => {
  const [open, setOpen] = React.useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <button onClick={handleOpen} className="add-button">
        {name}
      </button>

      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <div className="modal-content">
          <Typography id="modal-modal-title" variant="h6" component="h2">
            {title}
          </Typography>
          {proveniencia === "shift" && (
            <FormShift handleClose={handleClose} shift={shift} />
          )}
          {proveniencia === "affiliate" && (
            <FormAffiliate affiliate={affiliate} handleClose={handleClose} />
          )}
          {proveniencia === "specialist" && (
            <FormSpecialist specialist={specialist} />
          )}
          {proveniencia === "schedules" && (
            <FormSchedule
              schedule={schedule}
              specialistID={specialistID ?? 0}
              handleClose={handleClose}
            />
          )}
        </div>
      </Modal>
    </div>
  );
};

export default BasicModal;
