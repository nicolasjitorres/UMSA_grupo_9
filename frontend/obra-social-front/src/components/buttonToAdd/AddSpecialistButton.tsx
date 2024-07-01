import React from 'react';
import './Button.css';
import BasicModal from "../modal/Modal";

const AddSpecialistButton: React.FC = () => {
  return (
    <>
      <h2>Nuevo Especialista</h2>

      <BasicModal name="Agregar" proveniencia="specialist" title="Agregar Especialista"/>
    </>
  );
};

export default AddSpecialistButton;
