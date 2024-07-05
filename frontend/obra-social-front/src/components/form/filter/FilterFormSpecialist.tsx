import React, { useState } from 'react';
import './FilterForm.css';
import { useAppContext } from '../../hooks/AppContext';

const FilterForm: React.FC = () => {
  const { filterSpecialists } = useAppContext();
  const [dni, setDni] = useState("");
  const [name, setName] = useState("");
  const [speciality, setSpeciality] = useState("");
  // const [location, setLocation] = useState("");

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    filterSpecialists(dni, name, speciality);
  };
  

  return (
    <div className="filter-form">
      <h2>Buscar por</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="filter-dni">DNI:</label>
          <input
            type="text"
            id="filter-dni"
            value={dni}
            onChange={(e) => setDni(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="filter-name">Nombre:</label>
          <input
            type="text"
            id="filter-name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="filter-speciality">Especialidad:</label>
          <select
            id="filter-speciality"
            value={speciality}
            onChange={(e) => setSpeciality(e.target.value)}
          >
            <option value="">Seleccione una especialidad</option>
            <option value="DERMATOLOGY">Dermatología</option>
            <option value="CARDIOLOGY">Cardiología</option>
            <option value="PEDIATRICS">Pediatría</option>
            <option value="ORTHOPEDICS">Ortopedia</option>
          </select>
        </div>
        {/* <div>
          <label htmlFor="filter-location">Localidad:</label>
          <input
            type="text"
            id="filter-location"
            value={location}
            onChange={(e) => setLocation(e.target.value)}
          />
        </div> */}
        <button type="submit" className="filter-button">Filtrar</button>
      </form>
    </div>
  );
};

export default FilterForm;
