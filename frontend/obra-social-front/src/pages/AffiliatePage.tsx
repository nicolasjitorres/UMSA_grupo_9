import React from "react";
import FilterForm from "../components/FilterForm/FilterFormAffiliate";
import AddAffiliateButton from "../components/buttonToAdd/AddAffiliateButton";
import AffiliatesList from "../components/entitiesList/AfiliatesList";
import "./Users.css";

const AffiliatePage: React.FC = () => {
  return (
    <div className="container">
      <h1>Gestión de Afiliados</h1>
      <div className="content">
        <div className="right-section">
          <AddAffiliateButton />
          <FilterForm />
        </div>
        <div className="left-section">
          <AffiliatesList />
        </div>
      </div>
    </div>
  );
};

export default AffiliatePage;
