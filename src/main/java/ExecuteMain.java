import io.quarkus.runtime.Application;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import ru.meowmure.GraphQLClientUsage;

public class ExecuteMain implements QuarkusApplication {

    @Inject
    GraphQLClientUsage usage;

    @Override
    public int run(String... args) throws Exception {
        if (args.length!=0) {
            if (args[0].equals("findId"))
                usage.findById(args[1]);
            if (args[0].equals("findAll"))
                usage.findAll();
            if (args[0].equals("findGuid"))
                usage.findByGuid(args[1]);
        }
        Quarkus.waitForExit();
        return 0;
    }
}
