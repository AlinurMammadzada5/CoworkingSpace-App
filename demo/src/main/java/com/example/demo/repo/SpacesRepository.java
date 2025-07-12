package com.example.demo.repo;

import com.example.demo.entity.Spaces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class SpacesRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Spaces space) {
        em.persist(space);
    }

    public void update(Spaces space) {
        em.merge(space);
    }

    public void deleteById(int id) {
        Spaces space = em.find(Spaces.class, id);
        if (space != null) {
            em.remove(space);
        }
    }

    public Optional<Spaces> findById(int id) {
        return Optional.ofNullable(em.find(Spaces.class, id));
    }

    public List<Spaces> findAll() {
        return em.createQuery("SELECT s FROM Spaces s", Spaces.class).getResultList();
    }

    public List<Spaces> findAvailableSpaces() {
        return em.createQuery("SELECT s FROM Spaces s WHERE s.reserved = false", Spaces.class).getResultList();
    }

    public List<Spaces> findByUsername(String username) {
        return em.createQuery("SELECT s FROM Spaces s WHERE s.username = :username", Spaces.class)
                .setParameter("username", username)
                .getResultList();
    }

    public long countByUsername(String username) {
        return em.createQuery("SELECT COUNT(s) FROM Spaces s WHERE s.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
