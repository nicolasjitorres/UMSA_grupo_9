import React from 'react';
import BasicModal from "../../modal/Modal";

const AddSpecialist: React.FC = () => {
  return (
    <>
      <h2>Nuevo Especialista</h2>
      <BasicModal name="Agregar" proveniencia="specialist" title="Agregar Especialista"/>
    </>
  );
};

export default AddSpecialist;
