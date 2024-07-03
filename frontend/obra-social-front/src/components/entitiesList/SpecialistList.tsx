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

import RowSchedulesSpecialist from "../rows/RowSchedules&Specialist";
import { useAppContext } from "../../hooks/AppContext";

const SpecialistList: React.FC = () => {
  const { specialists } = useAppContext();

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
        rowsPerPageOptions={[5, 10, 25]}
        component="div"
        count={specialists.length}
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
