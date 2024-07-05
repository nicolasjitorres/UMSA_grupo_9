import React from "react";
import FilterForm from "../components/form/filter/FilterFormShift";
import AddShiftButton from "../components/form/add/AddShift";
//import BasicModal from "../components/modal/Modal";
import ShiftList from "../components/tables/ShiftTable";
import "./style.css";
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
