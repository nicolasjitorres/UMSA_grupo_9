import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../redux/store/store";
import { fetchSpecialists } from "../redux/slices/specialistSlice";

const SpecialistList: React.FC = () => {
  const dispatch: AppDispatch = useDispatch();
  const specialists = useSelector(
    (state: RootState) => state.specialists.specialists
  );
  const status = useSelector((state: RootState) => state.specialists.status);
  const error = useSelector((state: RootState) => state.specialists.error);

  useEffect(() => {
    if (status === "idle") {
      dispatch(fetchSpecialists());
    }
  }, [status, dispatch]);

  let content;

  if (status === "loading") {
    content = <div>Loading...</div>;
  } else if (status === "succeeded") {
    content = (
      <div>
        {specialists && specialists.length > 0 ? (
          <ul>
            {specialists.map((specialist) => (
              <li key={specialist.id}>
                {specialist.firstName} {specialist.lastName}
                {specialist.schedules.map((schedule) => (
                  <li key={schedule.id}>{schedule.dayOfWeek}</li>
                ))}
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
      <h2>Specialists</h2>
      {content}
    </section>
  );
};

export default SpecialistList;
