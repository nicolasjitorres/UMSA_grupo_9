import React from "react";
import "./Button.css";
import BasicModal from "../modal/Modal";

const AddAffiliateButton: React.FC = () => {
  return (
    <>
      <h2>Nuevo Afiliado</h2>
      <BasicModal name="Agregar" proveniencia="affiliate" title="Agregar Afiliado"/>
    </>
  );
};

export default AddAffiliateButton;
