import React from "react";
import Header from "./Header";
import AboutSection from "./AboutSection";
import ServicesSection from "./ServicesSection";
import ContactSection from "./ContactSection";
import './home.css';

const Home: React.FC = () => {
  return (
    <div className="home-container">
      <Header />
      <AboutSection />
      <div className="home-sections">
        <ServicesSection />
        <ContactSection />
      </div>
    </div>
  );
};

export default Home;
