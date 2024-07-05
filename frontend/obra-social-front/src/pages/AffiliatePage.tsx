import React from "react";
import FilterForm from "../components/form/filter/FilterFormAffiliate";
import AddAffiliateButton from "../components/form/add/AddAffiliate";
import AffiliatesList from "../components/tables/AfiliatesTable";
import "./style.css";

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
