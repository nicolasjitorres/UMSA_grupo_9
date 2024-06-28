import React from 'react';
import './Button.css';

const AddAffiliateButton: React.FC = () => {
  return (
    <>
      <h2>Nuevo Afiliado</h2>
      <button type="button" className="add-button">Agregar</button>
    </>
  );
};

export default AddAffiliateButton;
