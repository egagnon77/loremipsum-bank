package employee;

public class Employee {
    public static IResult createClient(String clientName) {
        return Result.Created(clientName);
    }
}
