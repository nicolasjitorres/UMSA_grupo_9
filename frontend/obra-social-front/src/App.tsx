import React, { useEffect } from "react";
import Router from "./routes/Router";
import { BrowserRouter } from "react-router-dom";
import NavBar from "./components/NavBar/NavBar";
import { useSelector } from "react-redux";
import { AppDispatch, RootState } from "./redux/store/store.ts";
import { useDispatch } from "react-redux";
import { fetchShift } from "./redux/slices/shiftSlice.ts";
import { fetchSpecialists } from "./redux/slices/specialistSlice";
import { fetchSchedules } from "./redux/slices/schedulesSlice.ts";

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
    <BrowserRouter>
      <NavBar />
      <Router />
    </BrowserRouter>
  );
};

export default App;
