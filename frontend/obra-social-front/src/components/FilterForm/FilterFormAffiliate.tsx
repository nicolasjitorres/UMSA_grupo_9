import React, { useState } from "react";

import "./FilterForm.css";
import { useAppContext } from "../../hooks/AppContext";

const FilterForm: React.FC = () => {
  const { filterAffiliates } = useAppContext();
  const [dni, setDni] = useState("");
  const [name, setName] = useState("");

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    filterAffiliates(dni, name);
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
        <button type="submit" className="filter-button">
          Filtrar
        </button>
      </form>
    </div>
  );
};

export default FilterForm;
