import React, { useState } from "react";
import "./FilterForm.css";
import { useAppContext } from "../../../hooks/AppContext";

const FilterForm: React.FC = () => {
  const { filterShifts } = useAppContext();

  const [name, setName] = useState("");
  const [hora, setHora] = useState("");
  const [dia, setDia] = useState("");

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    filterShifts(name, hora, dia);
  };

  return (
    <div className="filter-form">
      <h2>Buscar por</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="filter-id-specialist">Nombre del especialista:</label>
          <input
            type="text"
            id="filter-dni"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="filter-id-affiliate">Horario</label>
          <input
            type="text"
            id="filter-dni"
            value={hora}
            onChange={(e) => setHora(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="filter-id-affiliate">Día</label>
          <input
            type="text"
            id="filter-dni"
            value={dia}
            onChange={(e) => setDia(e.target.value)}
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
