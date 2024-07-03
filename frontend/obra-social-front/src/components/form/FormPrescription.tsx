import { useState } from "react";
import { Prescription } from "../../redux/type";
import { TextField, Button } from "@mui/material";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../../redux/store/store";
import {
  addPrescription,
  deletePrescription,
} from "../../redux/slices/PrescriptionSlice";
import { generatePdf } from "../../funcionalities/Funcionalities";
import DeleteIcon from "@mui/icons-material/Delete";

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
  const [description, setDescription] = useState(
    prescription?.description || ""
  );

  const dispatch = useDispatch<AppDispatch>();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    const prescriptionDTO = {
      description,
      idShift,
    };

    if (!prescription) {
      await dispatch(addPrescription({ prescriptionDTO }));
    }

    handleClose();
  };

  const handleDelete = (id: number) => {
    if (prescription) {
      dispatch(deletePrescription({ prescriptionID: id }));
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
          <button
            onClick={() => generatePdf(description)}
            color="primary"
            className="add-button"
          >
            Descargar Receta
          </button>
          <Button
            variant="contained"
            color="error"
            startIcon={<DeleteIcon />}
            onClick={() => handleDelete(prescription.id)}
          >
            Borrar
          </Button>
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
