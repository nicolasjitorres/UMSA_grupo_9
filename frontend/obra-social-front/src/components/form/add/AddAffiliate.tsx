import React from "react";
import BasicModal from "../../modal/Modal";

const AddAffiliate: React.FC = () => {
  return (
    <>
      <h2>Nuevo Afiliado</h2>
      <BasicModal name="Agregar" proveniencia="affiliate" title="Agregar Afiliado"/>
    </>
  );
};

export default AddAffiliate;
