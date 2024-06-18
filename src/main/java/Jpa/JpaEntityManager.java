package Jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaEntityManager {
    protected final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    protected final EntityManager entityManager = entityManagerFactory.createEntityManager();
    protected final EntityTransaction transaction = entityManager.getTransaction();

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}
