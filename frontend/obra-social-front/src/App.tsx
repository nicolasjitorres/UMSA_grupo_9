import React from "react";
import SpecialistList from "./components/specialistList";
import AffiliatedList from "./components/afiliatesList";

const App: React.FC = () => {
  return (
    <div className="App">
      <h1>Specialist Directory</h1>
      <SpecialistList />
      <AffiliatedList />
    </div>
  );
};

export default App;
