import * as React from "react";
import Alert from "@mui/material/Alert";
import Stack from "@mui/material/Stack";

interface MasageProp {
  messege: string;
}

export const faildAlerts: React.FC<MasageProp> = ({ messege }) => {
  return (
    <Stack sx={{ width: "100%" }} spacing={2}>
      <Alert variant="filled" severity="error">
        {messege}
      </Alert>
    </Stack>
  );
};
