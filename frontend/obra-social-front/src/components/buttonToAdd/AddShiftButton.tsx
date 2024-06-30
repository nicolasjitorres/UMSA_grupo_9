import React from 'react';
import './Button.css';
import BasicModal from "../../components/modal/Modal";

const AddShiftButton: React.FC = () => {
  return (
    <>
      <h2>Nuevo Turno</h2>
      <BasicModal name="Agregar" />
      
    </>
  );
};

export default AddShiftButton;
