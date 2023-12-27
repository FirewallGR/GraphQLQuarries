import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import ru.meowmure.CommandsExecutable;

@QuarkusMain
public class Main {
    public static void main(String[] args) {
        Quarkus.run(CommandsExecutable.class, args);
    }
}
