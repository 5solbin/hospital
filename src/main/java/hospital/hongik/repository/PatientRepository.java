package hospital.hongik.repository;

import hospital.hongik.domain.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Patient patient){
        em.persist(patient);
    }

    public Patient findOne(Long id) {
        return em.find(Patient.class,id);
    }

    public List<Patient> findAll(){
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }
}
