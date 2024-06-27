import React from "react";
import Header from "../components/Home/Header";
import AboutSection from "../components/Home/AboutSection";
import ServicesSection from "../components/Home/ServicesSection";
import ContactSection from "../components/Home/ContactSection";
import "./style.css";

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
