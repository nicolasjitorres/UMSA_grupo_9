import React from "react";
import BasicModal from "../../modal/Modal"

const AddShift: React.FC = () => {
  return (
    <>
      <h2>Nuevo Turno</h2>
      <BasicModal name="Agregar" proveniencia="shift" title="Agregar turno" />
    </>
  );
};

export default AddShift;
