import { createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { EmailDTO } from "../type";
import { handleAxiosError } from "../../components/Errors/AxiosErrorHandler";

export const sendEmail = createAsyncThunk(
  "email/sendEmail",
  async (email: EmailDTO) => {
    try {
      await axios.post("http://localhost:8080/send-email", {
        email,
      });
    } catch (error: unknown) {
      handleAxiosError(error, "Error enviar el email");
    }
  }
);
