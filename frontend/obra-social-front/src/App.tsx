import React from "react";
import Router from "./routes/Router";
import { BrowserRouter } from "react-router-dom";
import Footer from "./ui/footer/Footer";
import NavBar from "./ui/navBar/NavBar";
import { AppProvider } from "./hooks/AppContext";

const App: React.FC = () => {
  return (
    <AppProvider>
      <BrowserRouter>
        <NavBar />
        <Router />
        <Footer />
      </BrowserRouter>
    </AppProvider>
  );
};

export default App;
