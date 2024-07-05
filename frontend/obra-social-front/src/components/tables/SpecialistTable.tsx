import React from "react";

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

import RowSchedulesSpecialist from "./rows/RowSchedules&Specialist";
import { useAppContext } from "../../hooks/AppContext";

const SpecialistList: React.FC = () => {
  const { specialists, filteredSpecialists } = useAppContext();

  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const dataToShow = filteredSpecialists.length > 0 ? filteredSpecialists : specialists;

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
              <TableCell align="center">Name</TableCell>
              <TableCell align="center">DNI</TableCell>
              <TableCell align="center">Speciality</TableCell>
              <TableCell align="center">Location</TableCell>
              <TableCell align="center">Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {dataToShow.length > 0 ? (
              dataToShow
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
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={dataToShow.length}
        rowsPerPage={rowsPerPage}
        labelRowsPerPage={"Filas por pagina"}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default SpecialistList;
