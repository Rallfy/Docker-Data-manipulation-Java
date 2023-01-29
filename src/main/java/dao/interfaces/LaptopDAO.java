package dao.interfaces;

import entities.Laptop;
import enums.Type;

import java.util.List;

public interface LaptopDAO extends GenericDAO<Laptop, Type>{

    @Override
    default void close() {
    }

    @Override
    default Laptop create(Laptop entity) {
        return null;
    }

    @Override
    default Laptop findByIdAndType(int id, Type type) {
        return null;
    }

    @Override
    default Laptop update(Laptop entity) {
        return null;
    }

    @Override
    default void delete(Laptop entity) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default List<Laptop> getAll() {
        return null;
    }

    default List<Laptop> getGamingLaptops() {
        return null;
    }
    default List<Laptop> getBusinessLaptops() {
        return null;
    }
    default List<Laptop> getDualLaptops() {
        return null;
    }

}
