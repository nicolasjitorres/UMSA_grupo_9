import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

// function getUserFromLocalStorage() {
//   const userString = localStorage.getItem("user");
//   if (userString) {
//     return JSON.parse(userString);
//   }
//   return null;
// }

const Home: React.FC = () => {
  // const [user, setUser] = useState<{ email: string; role: string } | null>(
  //   null
  // );
  // const navigate = useNavigate();

  // useEffect(() => {
  //   const userData = getUserFromLocalStorage();
  //   if (userData) {
  //     setUser(userData);
  //   } else {
  //     navigate("/"); // Redirigir a la página de inicio de sesión si no está autenticado
  //   }
  // }, [navigate]);

  // if (!user) {
  //   return <div>Loading...</div>;
  // }

  return (
    <section>
      <div className="Home">
        <h1>bienvenido</h1>
        {/* <h1>Welcome, {user.email}</h1>
        <h2>Your role is: {user.role}</h2> */}
      </div>
    </section>
  );
};
export default Home;
