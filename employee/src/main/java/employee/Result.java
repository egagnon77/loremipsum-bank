package employee;

public class Result<T> implements IResult {
    private final ResultType type;
    public final T object;

    private Result(final ResultType type, final T object) {
        this.object = object;
        this.type = type;
    }
    
    public String message() {
        return String.format("Result: %s: %s", this.type.name(), object.toString());
    }

    public static <T> Result<T> Created(T object) {
        return new Result<T>(ResultType.Created, object);
    }

    public static <T> Result<T> Updated(T object) {
        return new Result<T>(ResultType.Updated, object);
    }

    public static <T> Result<T> Deleted(T object) {
        return new Result<T>(ResultType.Deleted, object);
    }

    public static <T> Result<T> Error(T object) {
        return new Result<T>(ResultType.Error, object);
    }
}
