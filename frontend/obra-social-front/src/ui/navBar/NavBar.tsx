import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import { Link as RouterLink } from "react-router-dom";
import "./NavBar.css";
import { useLocation } from "react-router-dom";

  
function NavBar() {

  const location = useLocation();
  const hideNavBarPaths = ["/"];

  if (hideNavBarPaths.includes(location.pathname)) {
    return null;}
  

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Obra Social - AlMedin
        </Typography>
        <Button color="inherit" component={RouterLink} to="/afiliados">
          Afiliados
        </Button>  
        <Button color="inherit" component={RouterLink} to="/especialistas">
          Especialistas
        </Button>
        <Button color="inherit" component={RouterLink} to="/turnos">
          Turnos
        </Button>
        <Button color="inherit" component={RouterLink} to="/home">
          Home
        </Button>
        <Button color="inherit" component={RouterLink} to="/">
          Salir
        </Button>
      </Toolbar>
    </AppBar>
  );
}

export default NavBar;
