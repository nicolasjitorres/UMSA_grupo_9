import {Routes, Route } from "react-router-dom";
import SpecialistList from "../pages/SpecialistList";
import AffiliatedList from "../pages/AfiliatesList";
import SignIn from "../pages/SignIn";
import Turnos from "../pages/ShiftPage";
import Home from "../pages/Home";

const Router = () => {
  return (
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/specialists" element={<SpecialistList />} />
          <Route path="/affiliates" element={<AffiliatedList />} />
          <Route path="/home" element={<Home />} />
          <Route path="/turnos" element={<Turnos />} />
        </Routes>  
  );
}
export default Router;
