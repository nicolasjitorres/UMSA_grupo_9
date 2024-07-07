import React from "react";
import BasicModal from "../../modal/Modal";

interface AddPrescriptionProps {
  shiftID: number;
}

const AddPrescription: React.FC<AddPrescriptionProps> = ({
  shiftID,
}) => {
  return (
    <>
      <BasicModal
        name="Agregar"
        proveniencia="receta"
        title="Agregar Receta"
        shiftID={shiftID}
      />
    </>
  );
};

export default AddPrescription;
