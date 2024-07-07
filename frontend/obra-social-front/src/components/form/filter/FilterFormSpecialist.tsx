import React, { useEffect, useState } from "react";
import "./FilterForm.css";
import { useAppContext } from "../../../hooks/AppContext";
import { RootState } from "../../../redux/store/store";
import { useSelector } from "react-redux";
import { fetchSpecialities } from "../../../redux/slices/SpecialitySlice";

const FilterForm: React.FC = () => {
  const { filterSpecialists } = useAppContext();
  const specialities = useSelector(
    (state: RootState) => state.specialities.specialities
  );
  const [dni, setDni] = useState("");
  const [name, setName] = useState("");
  const [speciality, setSpeciality] = useState("");

  useEffect(() => {
    fetchSpecialities();
  }, []);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    filterSpecialists(dni, name, speciality);
  };

  const handleSpecialityChange = (
    event: React.ChangeEvent<HTMLSelectElement>
  ) => {
    setSpeciality(event.target.value);
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
            onChange={handleSpecialityChange}
          >
            <option value="">Seleccione una especialidad</option>
            {Array.isArray(specialities) &&
              specialities.map((speciality, index) => (
                <option key={index} value={speciality}>
                  {speciality.replace("_", " ")}
                </option>
              ))}
          </select>
        </div>
        <button type="submit" className="filter-button">
          Filtrar
        </button>
      </form>
    </div>
  );
};

export default FilterForm;
