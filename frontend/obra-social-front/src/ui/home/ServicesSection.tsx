import React from "react";
import "./home.css";
import BasicModal from "../../components/modal/Modal";

const ServicesSection: React.FC = () => {
  return (
    <section className="services-section">
      <h2>Nuestros Servicios</h2>
      <p>Descubre los servicios médicos y beneficios que ofrecemos a nuestros afiliados.</p>

      <BasicModal
        title="Nuestros servicios"
        proveniencia="servicios"
        name="Ver servicios"
      />

    </section>
    
  );
};

export default ServicesSection;
