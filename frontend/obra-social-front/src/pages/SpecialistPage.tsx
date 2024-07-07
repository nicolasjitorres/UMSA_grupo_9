import React from "react";
import SpecialistList from "../components/tables/SpecialistTable";
import FilterForm from "../components/form/filter/FilterFormSpecialist";
import AddSpecialistButton from "../components/form/add/AddSpecialist";
import "./Style.css";

const Specialist: React.FC = () => {
  return (
    <div className="container">
      <h1>Gestión de Especialistas</h1>
      <div className="content">
        <div className="right-section">
          <AddSpecialistButton />
          <FilterForm />
        </div>
        <div className="left-section">
          <SpecialistList />
        </div>
      </div>
    </div>
  );
};

export default Specialist;
