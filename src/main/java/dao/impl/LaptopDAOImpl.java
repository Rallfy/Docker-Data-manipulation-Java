package dao.impl;

import dao.interfaces.LaptopDAO;
import entities.Laptop;
import enums.Type;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class LaptopDAOImpl implements LaptopDAO {


    private final EntityManager postgres1Em;
    private final EntityManager postgres2Em;
    private final EntityManager postgres3Em;

    public LaptopDAOImpl() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE); // Sets logging level


        this.postgres1Em = Persistence.createEntityManagerFactory("postgres1").createEntityManager();
        this.postgres2Em = Persistence.createEntityManagerFactory("postgres2").createEntityManager();
        this.postgres3Em = Persistence.createEntityManagerFactory("postgres3").createEntityManager();
    }

    @Override
    public void close() {
        this.postgres1Em.close();
        this.postgres2Em.close();
        this.postgres3Em.close();
    }

    @Override
    public Laptop create(Laptop entity) {

        EntityManager em = getEm(entity.getType());

        em.getTransaction().begin();

        em.persist(entity);

        em.getTransaction().commit();

        return entity;
    }

    @Override
    public Laptop findByIdAndType(int id, Type type) {
        return getEm(type).find(Laptop.class, id);
    }

    @Override
    public Laptop update(Laptop entity) {

        EntityManager em = getEm(entity.getType());

        em.getTransaction().begin();

        em.merge(entity);

        em.getTransaction().commit();

        return entity;
    }

    @Override
    public void delete(Laptop entity) {
        EntityManager em = getEm(entity.getType());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        // Delete all from postgres
        this.postgres1Em.getTransaction().begin();
        this.postgres1Em.createNativeQuery("DELETE FROM public.laptop").executeUpdate();
        this.postgres1Em.getTransaction().commit();

        this.postgres2Em.getTransaction().begin();
        this.postgres2Em.createNativeQuery("DELETE FROM public.laptop").executeUpdate();
        this.postgres2Em.getTransaction().commit();

        this.postgres3Em.getTransaction().begin();
        this.postgres3Em.createNativeQuery("DELETE FROM public.laptop").executeUpdate();
        this.postgres3Em.getTransaction().commit();

    }

    @Override
    public List<Laptop> getAll() {

        List<Laptop> allGamingLaptops = this.postgres1Em.createNativeQuery("Select * from public.laptop", Laptop.class).getResultList();

        List<Laptop> allBusinessLaptops = this.postgres2Em.createNativeQuery("Select * from public.laptop", Laptop.class).getResultList();

        List<Laptop> allDualLaptops = this.postgres3Em.createNativeQuery("Select * from public.laptop", Laptop.class).getResultList();


        List<Laptop> allLaptops = new ArrayList<>(allGamingLaptops);

        allLaptops.addAll(allBusinessLaptops);
        allLaptops.addAll(allDualLaptops);

        return allLaptops;

    }

    @Override
    public List<Laptop> getGamingLaptops() {
        return this.postgres1Em.createNativeQuery("SELECT * from public.laptop where type = 'GAMING'", Laptop.class).getResultList();
    }

    @Override
    public List<Laptop> getBusinessLaptops() {
        return this.postgres2Em.createNativeQuery("SELECT * from public.laptop where type = 'BUSINESS'", Laptop.class).getResultList();
    }

    @Override
    public List<Laptop> getDualLaptops() {
        return this.postgres3Em.createNativeQuery("SELECT * from public.laptop where type = 'DUAL'", Laptop.class).getResultList();
    }

    protected EntityManager getEm(Type type) {
        if (type == Type.GAMING)
            return this.postgres1Em;
        else if (type == Type.BUSINESS)
            return this.postgres2Em;
        return this.postgres3Em;
    }

}
