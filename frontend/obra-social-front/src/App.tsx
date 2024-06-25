import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import SpecialistList from "./components/specialistList/SpecialistList";
import AffiliatedList from "./components/afiliatesList/AfiliatesList";
import SignIn from "./components/Login/SignIn";
import NotFound from "./components/Errors/Notfound";
import NavBar from "./components/NavBar/NavBar";
import Home from "./components/Home/Home";
import Turnos from "./components/shiftList/ShiftList";
import TablaEspecialistas from "./components/specialistList/SpecialistList";

const App: React.FC = () => {
  return (
    <Router>
      <div className="App">
        {<NavBar />}
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/specialists" element={<SpecialistList />} />
          <Route path="/affiliates" element={<AffiliatedList />} />
          <Route path="/home" element={<Home />} />
          <Route path="/turnos" element={<Turnos />} />
          <Route path="/especialistasTabla" element={<TablaEspecialistas />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
