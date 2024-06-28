import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../redux/store/store";
import { fetchSchedules } from "../../redux/slices/schedulesSlice";
import { fetchSpecialists } from "../../redux/slices/specialistSlice";
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
import Row from "../Row/rowSchedules&Specialist";

const SpecialistList: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  const specialists = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const schedules = useSelector(
    (state: RootState) => state.schedules.schedules
  );
  const specialistStatus = useSelector(
    (state: RootState) => state.specialists.status
  );
  const scheduleStatus = useSelector(
    (state: RootState) => state.schedules.status
  );
  const specialistError = useSelector(
    (state: RootState) => state.specialists.error
  );
  const scheduleError = useSelector(
    (state: RootState) => state.schedules.error
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

  if (specialistStatus === "loading" || scheduleStatus === "loading") {
    return <div>Loading...</div>;
  }

  if (specialistStatus === "failed") {
    return <div>{specialistError}</div>;
  }

  if (scheduleStatus === "failed") {
    return <div>{scheduleError}</div>;
  }

  if (!specialists || specialists.length === 0) {
    return <div>No specialists found</div>;
  }

  console.log("Specialists:", specialists);
  console.log("Schedules:", schedules);

  return (
    <Paper sx={{ width: "100%", overflow: "hidden" }}>
      <TableContainer sx={{ maxHeight: 440 }}>
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
            {specialists
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((specialist) => (
                <Row
                  key={specialist.id}
                  specialist={specialist}
                  schedules={schedules.filter(
                    (schedule) => schedule.specialistId === specialist.id
                  )}
                />
              ))}
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
