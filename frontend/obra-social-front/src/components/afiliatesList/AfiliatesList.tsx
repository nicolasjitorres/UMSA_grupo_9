import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../redux/store/store";
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
import Row from "../Row/rowAffiliate";
import { fetchAfiliados } from "../../redux/slices/afiliatedSlice";

const AffiliatesList: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  const affiliates = useSelector(
    (state: RootState) => state.afiliates.afiliados
  );
  const status = useSelector((state: RootState) => state.afiliates.status);
  const error = useSelector((state: RootState) => state.afiliates.error);

  useEffect(() => {
    if (status === "idle") {
      dispatch(fetchAfiliados());
    }
  }, [status, dispatch]);

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

  if (status === "loading") {
    return <div>Loading...</div>;
  }

  if (status === "failed") {
    return <div>{error}</div>;
  }

  return (
    <Paper sx={{ width: "100%", overflow: "hidden" }}>
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              <TableCell>Nombre</TableCell>
              <TableCell align="right">DNI</TableCell>
              <TableCell align="right">Contacto</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {affiliates
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((affiliate) => (
                <Row key={affiliate.id} affiliate={affiliate} />
              ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={affiliates.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default AffiliatesList;
