import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SpecialistList from "./components/specialistList";
import AffiliatedList from "./components/afiliatesList";
import SignIn from "./components/Login/SignIn";
import NotFound from "./components/Errors/Notfound";
import NavBar from "./components/NavBar/NavBar";
import Table from "./Table";
import Home from "./Home";
import TablaEspecialistas from "./components/TableEspecialistas";

const App: React.FC = () => {
  return (
    <Router>
      <div className="App">
        {<NavBar/>}
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/specialists" element={<SpecialistList />} />
          <Route path="/affiliates" element={<AffiliatedList />} />
          <Route path="/table" element={<Table />} />
          <Route path="/home" element={<Home />} />
          <Route path="/especialistasTabla" element={<TablaEspecialistas />} />        
          
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};
export default App;