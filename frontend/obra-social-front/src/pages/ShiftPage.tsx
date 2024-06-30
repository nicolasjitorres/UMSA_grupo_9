import React from "react";
import FilterForm from "../components/FilterForm/FilterFormShift";
import AddShiftButton from "../components/buttonToAdd/AddShiftButton";

//import BasicModal from "../components/modal/Modal";
import ShiftList from "../components/entitiesList/ShiftList";
import "./Users.css";
import { RootState } from "../redux/store/store";
import { useSelector } from "react-redux";

const ShiftPage: React.FC = () => {
  const shiftList = useSelector((state: RootState) => state.shift.shifts);

  return (
    <div className="container">
      <h1>Gestión de Turnos</h1>
      <div className="content">
        <div className="right-section">
          <AddShiftButton />
          <FilterForm />
        </div>
        <div className="left-section">
             <ShiftList shifts={shiftList}/>
        </div>
      </div>
    </div>
  );
};

export default ShiftPage;
