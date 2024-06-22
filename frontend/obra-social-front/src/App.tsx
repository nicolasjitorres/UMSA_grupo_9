import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SpecialistList from "./components/specialistList/SpecialistList";
import AffiliatedList from "./components/afiliatesList/AfiliatesList";
import SignIn from "./components/singin/singIn";
import NotFound from "./components/Errors/Notfound";
import Home from "./components/home/Home";

const App: React.FC = () => {
  return (
    <Router>
      <div className="App">
        {/* Componente Navbar, si tienes uno */}
        <Routes>
          {/* Definición de las rutas */}
          <Route path="/" element={<SignIn />} />
          <Route path="/specialists" element={<SpecialistList />} />
          <Route path="/affiliates" element={<AffiliatedList />} />
          <Route path="/home" element={<Home />} />
          {/* Otras rutas */}
          {/* Página de error 404 */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </div>
    </Router>
  );
};
export default App;
