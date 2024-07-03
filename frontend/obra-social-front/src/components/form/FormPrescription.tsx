import { useState } from "react";
import { Prescription } from "../../redux/type";
import { TextField } from "@mui/material";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../../redux/store/store";
import {
  addPrescription,
  deletePrescription,
} from "../../redux/slices/PrescriptionSlice";
import { generatePdf } from "../../funcionalities/Funcionalities";

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
          <button className="edit-button">
            Actualizar
          </button>
          <button
            onClick={() => generatePdf(description)}        
            className="download-button">
            Descargar Receta
          </button>
          <button className="delete-button"                     
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
