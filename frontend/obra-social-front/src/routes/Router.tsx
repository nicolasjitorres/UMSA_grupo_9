import {Routes, Route } from "react-router-dom";
import Specialist from "../pages/Specialist"
import Affiliate from "../pages/AffiliatePage";
import SignIn from "../pages/SignIn";
import Turnos from "../pages/ShiftPage";
import Home from "../pages/Home";

const Router = () => {
  return (
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/especialistas" element={<Specialist />} />
          <Route path="/afiliados" element={<Affiliate />} />
          <Route path="/home" element={<Home />} />
          <Route path="/turnos" element={<Turnos />} />
        </Routes>  
  );
}
export default Router;
