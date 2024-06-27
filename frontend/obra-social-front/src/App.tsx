import React, { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import SpecialistList from "./components/specialistList/SpecialistList";
import AffiliatedList from "./components/afiliatesList/AfiliatesList";
import SignIn from "./components/Login/SignIn";
import NotFound from "./components/Errors/Notfound";
import NavBar from "./components/NavBar/NavBar";
import Turnos from "./components/shiftPage/ShiftPage";
import Home from "./components/home/Home";
import TablaEspecialistas from "./components/specialistList/SpecialistList";
import { useSelector } from "react-redux";
import { AppDispatch, RootState } from "./redux/store/store";
import { useDispatch } from "react-redux";
import { fetchShift } from "./redux/slices/shiftSlice";
import { fetchSpecialists } from "./redux/slices/specialistSlice";
import { fetchSchedules } from "./redux/slices/schedulesSlice";

const App: React.FC = () => {
  const dispatch = useDispatch<AppDispatch>();

  //esto lo puse apra cargar los datos de entrada
  useEffect(() => {
    dispatch(fetchShift());
    dispatch(fetchSpecialists());
    dispatch(fetchSchedules());
  }, [dispatch]);

  useSelector((state: RootState) => state.specialists.specialists);
  useSelector((state: RootState) => state.schedules.schedules);
  useSelector((state: RootState) => state.shift.shifts);

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
