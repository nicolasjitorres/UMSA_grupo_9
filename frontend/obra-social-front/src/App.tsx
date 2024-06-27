import React, { useEffect } from "react";
import Router2 from "./routes/Router2";
import { BrowserRouter} from "react-router-dom";
import NavBar from "../src/pages/NavBar";
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
   <BrowserRouter>
    {<NavBar />}
      <Router2/>
   </BrowserRouter>
  );
};

export default App;
