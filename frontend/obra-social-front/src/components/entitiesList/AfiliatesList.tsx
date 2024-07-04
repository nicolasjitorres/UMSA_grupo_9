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

import Row from "../rows/RowAffiliate";
import { useAppContext } from "../../hooks/AppContext";

const AffiliatesList: React.FC = () => {
  const { affiliates, filteredAffiliates } = useAppContext();

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

  const dataToShow =
    filteredAffiliates.length > 0 ? filteredAffiliates : affiliates;

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
              <TableCell align="center">Nombre</TableCell>
              <TableCell align="center">DNI</TableCell>
              <TableCell align="center">Email - Contacto</TableCell>
              <TableCell align="center">Acciones</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {dataToShow.length > 0 ? (
              dataToShow
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((affiliate) => (
                  <Row key={affiliate.id} affiliate={affiliate} />
                ))
            ) : (
              <TableRow>
                <TableCell colSpan={4} align="center">
                  No hay afiliados
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

export default AffiliatesList;
