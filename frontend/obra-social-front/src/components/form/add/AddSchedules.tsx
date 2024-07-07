import React from "react";
import BasicModal from "../../modal/Modal"

interface AddSchedulesProp {
  specialistID?: number;
}

const AddSchedules: React.FC<AddSchedulesProp> = ({
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

export default AddSchedules;
