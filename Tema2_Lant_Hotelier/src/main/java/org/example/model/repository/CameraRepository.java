package org.example.model.repository;

import org.example.model.entities.Camera;
import org.example.model.entities.Utilizator;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CameraRepository extends AbstractRepository<Camera>{
    public Camera findByNumarCamera(String numarCamera){
        try(var session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Camera> cq = cb.createQuery(Camera.class);
            Root<Camera> root = cq.from(Camera.class);
            cq.select(root).where(cb.equal(root.get("numarCamera"), numarCamera));
            TypedQuery<Camera> query = session.createQuery(cq);
            List<Camera> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
