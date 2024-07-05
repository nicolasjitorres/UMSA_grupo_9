import React from "react";
import Header from "../ui/home/Header";
import AboutSection from "../ui/home/AboutSection";
import ServicesSection from "../ui/home/ServicesSection";
import ContactSection from "../ui/home/ContactSection";
import FaqData from "../ui/home/FaqData";
import "./HomePage.css";

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
