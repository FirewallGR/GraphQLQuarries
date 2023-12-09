import io.quarkus.runtime.Application;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import ru.meowmure.GraphQLClientUsage;

@QuarkusMain
public class ExecuteMain implements QuarkusApplication {

    @Inject
    GraphQLClientUsage usage;

    @Override
    public int run(String... args) throws Exception {
        if (args.length!=0) {
            Application.currentApplication().stop();
        }
        return 0;
    }
}
