import React from 'react';
import SpecialistList from '../components/entitiesList/SpecialistList';
import FilterForm from '../components/FilterForm/FilterFormSpecialist';
import AddSpecialistButton from '../components/buttonToAdd/AddSpecialistButton';
import './Users.css';

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
