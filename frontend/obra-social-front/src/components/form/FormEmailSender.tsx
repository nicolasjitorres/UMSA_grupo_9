import React, { useState } from "react";
import { TextField, Button, Container } from "@mui/material";
import { useAppContext } from "../../hooks/AppContext";

const FormEmailSender: React.FC = () => {
  const { emailSender } = useAppContext();
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = () => {
    const emailDTO = {
      email,
      message,
    };
    console.log(emailDTO);
    emailSender(emailDTO);
  };

  return (
    <Container>
      <form onSubmit={handleSubmit}>
        <TextField
          fullWidth
          label="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          margin="normal"
          required
        />
        <TextField
          fullWidth
          label="Mensaje"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          margin="normal"
          multiline
          rows={4}
          required
        />
        <Button type="submit" variant="contained" color="primary">
          Enviar
        </Button>
      </form>
    </Container>
  );
};

export default FormEmailSender;
