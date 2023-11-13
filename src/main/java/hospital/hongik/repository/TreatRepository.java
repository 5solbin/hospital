package hospital.hongik.repository;

import hospital.hongik.domain.Treat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreatRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Treat treat) {
        em.persist(treat);
    }

    public Treat findOne(Long id){
        return em.find(Treat.class,id);
    }

    public List<Treat> findAll() {
        return em.createQuery("select t from Treat t",Treat.class)
                .getResultList();
    }
}
