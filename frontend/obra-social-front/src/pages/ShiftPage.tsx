import React from "react";
import FilterForm from "../components/FilterForm/FilterFormShift";
import AddShiftButton from "../components/buttonToAdd/AddShiftButton";
//import BasicModal from "../components/modal/Modal";
import ShiftList from "../components/entitiesList/ShiftList";
import "./Users.css";
import { useAppContext } from "../hooks/AppContext";

const ShiftPage: React.FC = () => {
  const { shifts } = useAppContext();

  return (
    <div className="container">
      <h1>Gestión de Turnos</h1>
      <div className="content">
        <div className="right-section">
          <AddShiftButton />
          <FilterForm />
        </div>
        <div className="left-section">
          <ShiftList shifts={shifts} />
        </div>
      </div>
    </div>
  );
};

export default ShiftPage;
