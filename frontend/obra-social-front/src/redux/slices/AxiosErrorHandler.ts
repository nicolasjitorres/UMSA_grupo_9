import axios from "axios";
import Swal from "sweetalert2";

export const handleAxiosError = (error: unknown, defaultMessage: string) => {
  if (axios.isAxiosError(error) && error.response) {
    const violations = error.response.data.violations;
    let errorMessage = defaultMessage;
    if (violations && Array.isArray(violations)) {
      errorMessage = violations
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
