import React from "react";
import Header from "../components/home/Header";
import AboutSection from "../components/home/AboutSection";
import ServicesSection from "../components/home/ServicesSection";
import ContactSection from "../components/home/ContactSection";
import FaqData from "../components/home/FaqData";
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
      <FaqData />
    </div>
  );
};

export default Home;
