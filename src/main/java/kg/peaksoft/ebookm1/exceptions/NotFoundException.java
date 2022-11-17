package kg.peaksoft.ebookm1.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
    }
}
