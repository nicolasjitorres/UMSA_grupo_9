import React from 'react';
import SpecialistList from '../components/specialist/SpecialistList';
import FilterForm from '../components/specialist/FilterForm';
import AddSpecialistButton from '../components/specialist/AddSpecialistButton';
import './Specialist.css';

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
