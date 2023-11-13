package hospital.hongik.repository;

import hospital.hongik.domain.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Department department) {
        em.persist(department);
    }

    public Department findOne(Long id) {
        return em.find(Department.class,id);
    }

    public List<Department> findAll() {
        return em.createQuery("select d from Department d",Department.class)
                .getResultList();
    }
}
