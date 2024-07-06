import React from "react";
import BasicModal from "../../components/modal/Modal";

const ContactSection: React.FC = () => {
  return (
    <section className="contact-section">
      <h2>Contacto</h2>
      <p>
        ¿Tienes alguna pregunta? Contáctanos y estaremos encantados de ayudarte.
      </p>
      <BasicModal
        title="Contacto"
        proveniencia="contacto"
        name="Contactate con Nosotros"
      />
    </section>
  );
};

export default ContactSection;
