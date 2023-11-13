package hospital.hongik.repository;

import hospital.hongik.domain.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public class DoctorRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findAll() {
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }



}
