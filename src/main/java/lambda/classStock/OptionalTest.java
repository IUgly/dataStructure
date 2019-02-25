package lambda.classStock;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Optional<String> a = Optional.of("a");
        System.out.println(a.get());

        Optional emptyOptional = Optional.empty();
        Optional alsoEmpty = Optional.ofNullable(null);

        System.out.println(emptyOptional.isPresent());
        System.out.println(emptyOptional.orElse("b"));
        System.out.println(emptyOptional.orElseGet(() ->"c"));

    }
}
