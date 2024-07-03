import React from "react";
import Router from "./routes/Router";
import { BrowserRouter } from "react-router-dom";
import Footer from "./components/footer/Footer";
import NavBar from "./components/NavBar/NavBar";
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
