import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../redux/store/store";
import { fetchSchedules } from "../../redux/slices/SchedulesSlice";
import { fetchSpecialists } from "../../redux/slices/SpecialistSlice";
import "./Table.css";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  TablePagination,
} from "@mui/material";
import RowSchedulesSpecialist from "../rows/RowSchedules&Specialist";

const SpecialistList: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();

  const specialists = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const specialistStatus = useSelector(
    (state: RootState) => state.specialists.status
  );
  const scheduleStatus = useSelector(
    (state: RootState) => state.schedules.status
  );

  useEffect(() => {
    if (specialistStatus === "idle") {
      dispatch(fetchSpecialists());
    }
    if (scheduleStatus === "idle") {
      dispatch(fetchSchedules());
    }
  }, [specialistStatus, scheduleStatus, dispatch]);

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

  return (
    <Paper
      sx={{
        width: "100%",
        height: "100%",
        display: "flex",
        flexDirection: "column",
      }}
    >
      <TableContainer sx={{ flex: 1, overflowY: "auto" }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell>Name</TableCell>
              <TableCell align="right">Speciality</TableCell>
              <TableCell align="right">Location</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {specialists.length > 0 ? (
              specialists
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((specialist) => (
                  <RowSchedulesSpecialist
                    key={specialist.id}
                    specialist={specialist}
                  />
                ))   
            ) : (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  No hay Especialistas
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={specialists.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default SpecialistList;
