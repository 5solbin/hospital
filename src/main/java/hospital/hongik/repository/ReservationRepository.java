package hospital.hongik.repository;

import hospital.hongik.domain.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Reservation reservation){
        em.persist(reservation);
    }

    public Reservation findOne(Long id){
        return em.find(Reservation.class,id);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r",Reservation.class)
                .getResultList();
    }

}
