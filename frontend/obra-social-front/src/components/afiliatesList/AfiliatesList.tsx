import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../redux/store/store";
import { fetchAfiliados } from "../../redux/slices/afiliatedSlice";

const AffiliatesList: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  const affilates = useSelector(
    (state: RootState) => state.afiliates.afiliados
  );
  const status = useSelector((state: RootState) => state.afiliates.status);
  const error = useSelector((state: RootState) => state.afiliates.error);

  useEffect(() => {
    if (status === "idle") {
      dispatch(fetchAfiliados());
    }
  }, [status, dispatch]);

  let content;

  if (status === "loading") {
    content = <div>Loading...</div>;
  } else if (status === "succeeded") {
    content = (
      <div>
        {affilates && affilates.length > 0 ? (
          <ul>
            {affilates.map((affiliate) => (
              <li key={affiliate.id}>
                {affiliate.firstName} {affiliate.lastName}
              </li>
            ))}
          </ul>
        ) : (
          <p>No hay especialistas disponibles.</p>
        )}
      </div>
    );
  } else if (status === "failed") {
    content = <div>{error}</div>;
  }

  return (
    <section>
      <h2>Afiliados</h2>
      {content}
    </section>
  );
};

export default AffiliatesList;
