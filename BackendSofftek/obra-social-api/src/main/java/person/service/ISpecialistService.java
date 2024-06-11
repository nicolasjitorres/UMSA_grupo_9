package person.service;

import java.util.List;

import person.model.Specialist;

public interface ISpecialistService {

	List<Specialist> getAllSpecialists();
	Specialist getOneSpecialist(Long id);
	void createSpecialist(Specialist specialist);
	void updateSpecialist(Long id);
	void deleteSpecialist(Long id);
}
