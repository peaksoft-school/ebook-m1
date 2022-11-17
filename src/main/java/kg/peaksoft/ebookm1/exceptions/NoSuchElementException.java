package kg.peaksoft.ebookm1.exceptions;

public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id not found", clazz.getSimpleName(), id));
    }

}
