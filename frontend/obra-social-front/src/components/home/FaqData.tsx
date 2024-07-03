// FaqData.tsx
import React from 'react';

const data = [
  {
    question: "¿Cuáles son los servicios principales que ofrece Almedin?",
    answer: "Almedin ofrece una amplia gama de servicios médicos, incluyendo consultas con especialistas, estudios diagnósticos, y seguimiento de tratamientos.",
  },
  {
    question: "¿Cómo puedo contactar a Almedin para resolver dudas o consultas?",
    answer: "Puedes contactarnos a través de nuestro formulario en línea o por teléfono al número XXX-XXX-XXXX.",
  },
  {
    question: "¿Almedin tiene convenios con centros médicos y hospitales?",
    answer: "Sí, Almedin cuenta con una red de prestadores y hospitales afiliados para garantizar atención médica de calidad.",
  },
  {
    question: "¿Cómo puedo afiliarme a Almedin?",
    answer: "Puedes iniciar el proceso de afiliación a través de nuestra página web o visitando nuestras oficinas centrales.",
  },
];

const FaqData: React.FC = () => {
  return (
    <div className="faq-section">
      <h2>Preguntas Frecuentes</h2>
      {data.map((faq, index) => (
        <div key={index}>
          <h3 >{faq.question}</h3>
          <p>{faq.answer}</p>
        </div>
      ))}
    </div>
  );
}

export default FaqData;
