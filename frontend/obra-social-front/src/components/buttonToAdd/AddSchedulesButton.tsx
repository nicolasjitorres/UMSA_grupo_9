import React from "react";
import "./Button.css";
import BasicModal from "../../components/modal/Modal";

interface AddSchedulesButtonProp {
  specialistID?: number;
}

const AddSchedulesButton: React.FC<AddSchedulesButtonProp> = ({
  specialistID,
}) => {
  return (
    <BasicModal
      name="Agregar"
      proveniencia="schedules"
      title="Agregar Horario"
      specialistID={specialistID}
    />
  );
};

export default AddSchedulesButton;
