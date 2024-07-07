import React from "react";
import "./Footer.css";
import { useLocation } from "react-router-dom";
const Footer: React.FC = () => {
  const location = useLocation();
  const hideFooterPaths = ["/"];

  if (hideFooterPaths.includes(location.pathname)) {
    return null;}

  return (
    <footer className="footer">
      <div className="footer-content">
        <span>@UMSA 2024</span>
        <span>@Softtek</span>
        <span>Academia Java + React</span>
        <span>Proyecto Obra Social AlMedin</span>
        <span>Grupo 9</span>
      </div>
    </footer>
  );
};

export default Footer;
