import { useState } from "react";
import { Prescription } from "../../redux/type";
import { TextField } from "@mui/material";
import { generatePdf } from "../../funcionalities/Funcionalities";
import { useAppContext } from "../../hooks/AppContext";

interface FormPrescriptionProp {
  prescription?: Prescription;
  idShift: number;
  handleClose: () => void;
}

const FormPrescription: React.FC<FormPrescriptionProp> = ({
  idShift,
  prescription,
  handleClose,
}) => {
  const { add_Prescription, update_Prescription, delete_Prescription } =
    useAppContext();
  const [description, setDescription] = useState(
    prescription?.description || ""
  );

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    const prescriptionDTO = {
      description,
      idShift,
    };

    prescription
      ? update_Prescription(prescriptionDTO, prescription.id)
      : add_Prescription(prescriptionDTO);

    handleClose();
  };

  const handleDelete = (id: number) => {
    if (prescription) {
      delete_Prescription(id);
      handleClose();
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-container">
      <TextField
        fullWidth
        label="Descripción"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        margin="normal"
        className="form-field"
      />
      {prescription ? (
        <div>
          <button className="edit-button">Actualizar</button>
          <button
            onClick={() => generatePdf(description)}
            className="download-button"
          >
            Descargar Receta
          </button>
          <button
            className="delete-button"
            onClick={() => handleDelete(prescription.id)}
          >
            Borrar
          </button>
        </div>
      ) : (
        <button type="submit" color="primary" className="add-button">
          {"Agregar"}
        </button>
      )}
    </form>
  );
};

export default FormPrescription;
