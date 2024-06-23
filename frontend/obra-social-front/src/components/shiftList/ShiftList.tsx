import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../redux/store/store";
import { fetchShift, deleteShift } from "../../redux/slices/shiftSlice"; // Asegúrate de importar deleteShift
import * as React from "react";
import Button from "@mui/material/Button"; // Corrige la importación de Button
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import { useEffect } from "react";

interface Column {
  id: "id" | "description" | "date" | "time" | "actions";
  label: string;
  minWidth?: number;
  align?: "right" | "center"; // Añade "center" para la columna de acciones
  format?: (value: number) => string;
}

const columns: Column[] = [
  { id: "id", label: "ID", minWidth: 170 },
  { id: "description", label: "Description", minWidth: 100 },
  { id: "date", label: "Date", minWidth: 170, align: "right" },
  { id: "time", label: "Time", minWidth: 170, align: "right" },
  { id: "actions", label: "Actions", minWidth: 170, align: "center" },
];

const ShiftList: React.FC = () => {
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

  const handleDelete = (id: number) => {
    dispatch(deleteShift(id));
  };

  let content;

  if (status === "loading") {
    content = <div>Loading...</div>;
  } else if (status === "succeeded") {
    content = (
      <div>
        <Paper sx={{ width: "100%", overflow: "hidden" }}>
          <TableContainer sx={{ maxHeight: 440 }}>
            <Table stickyHeader aria-label="sticky table">
              <TableHead>
                <TableRow>
                  {columns.map((column) => (
                    <TableCell
                      key={column.id}
                      align={column.align}
                      style={{ minWidth: column.minWidth }}
                    >
                      {column.label}
                    </TableCell>
                  ))}
                </TableRow>
              </TableHead>
              <TableBody>
                {shiftList
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((shift) => (
                    <TableRow
                      hover
                      role="checkbox"
                      tabIndex={-1}
                      key={shift.id}
                    >
                      {columns.map((column) => {
                        if (column.id === "actions") {
                          return (
                            <TableCell key={column.id} align={column.align}>
                              <Button
                                variant="contained"
                                color="secondary"
                                onClick={() => handleDelete(shift.id)}
                              >
                                Delete
                              </Button>
                            </TableCell>
                          );
                        }
                        const value = shift[column.id];
                        return (
                          <TableCell key={column.id} align={column.align}>
                            {column.format && typeof value === "number"
                              ? column.format(value)
                              : value}
                          </TableCell>
                        );
                      })}
                    </TableRow>
                  ))}
              </TableBody>
            </Table>
          </TableContainer>
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
      {content}
    </section>
  );
};

export default ShiftList;
