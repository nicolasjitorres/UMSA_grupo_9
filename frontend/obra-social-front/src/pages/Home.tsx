import React from "react";
import Header from "../components/home/Header";
import AboutSection from "../components/home/AboutSection";
import ServicesSection from "../components/home/ServicesSection";
import ContactSection from "../components/home/ContactSection";
import "./style.css";

const Home: React.FC = () => {
  return (
    <div className="container">
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
