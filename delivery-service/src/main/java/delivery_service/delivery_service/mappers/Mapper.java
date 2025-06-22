package delivery_service.delivery_service.mappers;


public interface Mapper<E, T> {
    public E map(T e);
}
