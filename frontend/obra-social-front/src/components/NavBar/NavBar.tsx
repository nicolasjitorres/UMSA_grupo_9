import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import { Link as RouterLink } from "react-router-dom";
import "./NavBar.css";

function NavBar() {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Obra Social - AlMedin
        </Typography>
        <Button color="inherit" component={RouterLink} to="/especialistas">
          Especialistas
        </Button>
        <Button color="inherit" component={RouterLink} to="/afiliados">
          Afiliados
        </Button>
        <Button color="inherit" component={RouterLink} to="/">
          Sign In
        </Button>
        <Button color="inherit" component={RouterLink} to="/turnos">
          Turnos
        </Button>
        <Button color="inherit" component={RouterLink} to="/home">
          Contacto
        </Button>
        <Button color="inherit" component={RouterLink} to="/home">
          Home
        </Button>
      </Toolbar>
    </AppBar>
  );
}

export default NavBar;
