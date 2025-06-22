package test_technical.order_service.mappers;

import java.util.List;

public interface Mapper<E, T> {
    public E map(T e);
}
