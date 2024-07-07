import axios from "axios";
import Swal from "sweetalert2";

export const handleAxiosError = (error: unknown, defaultMessage: string) => {
  if (axios.isAxiosError(error) && error.response) {
    const data = error.response.data;
    let errorMessage = defaultMessage;

    // Verifica si hay un mensaje específico en la respuesta del servidor
    if (data && typeof data === "string") {
      errorMessage = data;
    } else if (data.violations && Array.isArray(data.violations)) {
      // Maneja el caso de violaciones específicas
      errorMessage = data.violations
        .map(
          (violation: { field: string; message: string }) =>
            `${violation.message}`
        )
        .join("\n");
    }

    Swal.fire({
      title: "Error",
      text: errorMessage,
      icon: "error",
      confirmButtonText: "Continue",
    });
    return errorMessage;
  } else {
    Swal.fire({
      title: "Error",
      text: defaultMessage,
      icon: "error",
      confirmButtonText: "Continue",
    });
    return defaultMessage;
  }
};
