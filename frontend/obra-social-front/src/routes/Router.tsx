import { Routes, Route } from "react-router-dom";
import Specialist from "../pages/SpecialistPage";
import Affiliate from "../pages/AffiliatePage";
import SignIn from "../pages/SignInPage";
import Turnos from "../pages/ShiftPage";
import Home from "../pages/HomePage";

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
};
export default Router;
