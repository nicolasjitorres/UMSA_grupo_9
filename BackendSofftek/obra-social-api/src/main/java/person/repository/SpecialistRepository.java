package person.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import person.model.Specialist;

@ApplicationScoped
public class SpecialistRepository implements PanacheRepository<Specialist>{
}
