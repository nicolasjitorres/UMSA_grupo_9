import React from 'react';
import './FilterForm.css';

const FilterForm: React.FC = () => {
  return (
    <div className="filter-form">
      <h2>Buscar por</h2>
      <form>
        <div>
          <label htmlFor="filter-dni">DNI:</label>
          <input type="text" id="filter-dni" />
        </div>
        <div>
          <label htmlFor="filter-name">Nombre:</label>
          <input type="text" id="filter-name" />
        </div>
        <div>
          <label htmlFor="filter-speciality">Especialidad:</label>
          <select id="filter-speciality">
            <option value="">Seleccione una especialidad</option>
            <option value="DERMATOLOGY">Dermatología</option>
            <option value="CARDIOLOGY">Cardiología</option>
            <option value="PEDIATRICS">Pediatría</option>
            <option value="ORTHOPEDICS">Ortopedia</option>
          </select>
        </div>
        <div>
          <label htmlFor="filter-location">Localidad:</label>
          <input type="text" id="filter-location" />
        </div>
        <button type="submit" className="filter-button">Filtrar</button>
      </form>
    </div>
  );
};

export default FilterForm;
