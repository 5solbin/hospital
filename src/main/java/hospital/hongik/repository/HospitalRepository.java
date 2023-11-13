package hospital.hongik.repository;

import hospital.hongik.domain.Hospital;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HospitalRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Hospital hospital) {
        em.persist(hospital);
    }

    public Hospital findOne(Long id) {
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findAll() {
        return em.createQuery("select h from Hospital h",Hospital.class)
                .getResultList();
    }



}
