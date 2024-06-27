import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../redux/store/store";
import { fetchShift } from "../redux/slices/shiftSlice"; // Asegúrate de importar deleteShift
import * as React from "react";
import Paper from "@mui/material/Paper";
import TablePagination from "@mui/material/TablePagination";
import { useEffect } from "react";
import BasicModal from "../components/modal/Modal";
import ShiftList from "../components/shiftPage/ShiftList";

const Shift: React.FC = () => {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const dispatch: AppDispatch = useDispatch();
  const shiftList = useSelector((state: RootState) => state.shift.shifts);
  const status = useSelector((state: RootState) => state.shift.status);
  const error = useSelector((state: RootState) => state.shift.error);

  useEffect(() => {
    if (status === "idle") {
      dispatch(fetchShift());
    }
  }, [status, dispatch]);

  let content;

  if (status === "loading") {
    content = <div>Loading...</div>;
  } else if (shiftList.length == 0) {
    content = <div>no hay shifts...</div>;
  } else if (status === "succeeded" && shiftList.length > 0) {
    content = (
      <div>
        <Paper sx={{ width: "100%", overflow: "hidden" }}>
          <ShiftList shifts={shiftList} />
          <TablePagination
            rowsPerPageOptions={[10, 25, 100]}
            component="div"
            count={shiftList.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
        </Paper>
      </div>
    );
  } else if (status === "failed") {
    content = <div>{error}</div>;
  }

  return (
    <section>
      <h2>Turnos</h2>
      <BasicModal name="Agregar Turno" />
      {content}
    </section>
  );
};

export default Shift;
