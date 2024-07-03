import React from "react";
import "./Button.css";
import BasicModal from "../modal/Modal";

interface AddPrescriptionButtonProps {
  shiftID: number;
}

const AddPrescriptionButton: React.FC<AddPrescriptionButtonProps> = ({
  shiftID,
}) => {
  return (
    <>
      <BasicModal
        name="Agregar Receta"
        proveniencia="receta"
        title="Agregar Receta"
        shiftID={shiftID}
      />
    </>
  );
};

export default AddPrescriptionButton;
