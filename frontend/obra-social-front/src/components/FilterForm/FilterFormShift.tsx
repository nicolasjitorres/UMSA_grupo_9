import React from 'react';
import './FilterForm.css';

const FilterForm: React.FC = () => {
  return (
    <div className="filter-form">
      <h2>Buscar por</h2>
      <form>
        <div>
          <label htmlFor="filter-id-specialist">ID Especialista:</label>
          <input type="text" id="filter-id-specialist" />
        </div>
        <div>
          <label htmlFor="filter-id-affiliate">ID Afiliado:</label>
          <input type="text" id="filter-id-affiliate" />
        </div>
 
        <button type="submit" className="filter-button">Filtrar</button>
        
      </form>
    </div>
  );
};

export default FilterForm;
