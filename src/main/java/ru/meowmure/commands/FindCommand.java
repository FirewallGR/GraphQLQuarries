package ru.meowmure.commands;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.security.AuthenticationFailedException;
import jakarta.inject.Inject;
import picocli.CommandLine;
import ru.meowmure.GraphQLClientService;
import ru.meowmure.model.Component;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "find")
public class FindCommand implements Callable<Integer> {

    @CommandLine.ArgGroup(exclusive = true, multiplicity = "0..1")
    Exclusive exclusiveGroup;

    static class Exclusive {
        @CommandLine.Option(names = { "-i", "--id"}, required = true, description="specify id of the component metadata record in registry")
        public String id = null;

        @CommandLine.Option(names = { "-u", "--uguid", "--guid"}, required = true, description="specify UGUID of the component")
        public String uguid = null;
    }

    @CommandLine.Option(names = { "-o", "-p", "--os", "--platform" }, description="specify target platform OS")
    String os;

    @CommandLine.Option(names = { "-a", "--arch", "--cpu", "--architecture" }, description="specify type of CPU architecture")
    String architecture;

    @CommandLine.Option(names = { "-v", "--ver",  "--version" }, description="specify a version name (number)")
    String versionName;


    @Inject
    GraphQLClientService service;

    @Override
    public Integer call() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Component result = null;

        if (exclusiveGroup == null)
            exclusiveGroup = new Exclusive();
        System.out.println(exclusiveGroup.id + " " + exclusiveGroup.uguid);
        if (exclusiveGroup.id != null) {
            result = service.getComponentById(exclusiveGroup.id);
            if (result != null) System.out.println("FindCommand: getComponent (by ID) is successful");
            else {
                System.out.println("FindCommand: getComponent (by ID): not found");
                return 0;
            }
        } else if (exclusiveGroup.uguid != null) {
            result = service.getComponentByGuid(exclusiveGroup.uguid);

            if (result != null) System.out.println("getComponentByGUID by UGUID is found!");
            else {
                System.out.println("getComponentByGUID is null");
                return 0;
            }
        } else {
            List<Component> components = null;

            components = service.listComponents().getItems();

            if (components == null)
                return 0;

            components.stream().forEach(c ->
            {
                try {

                    String jsonComponent = mapper.writeValueAsString(c);
                    String indentedComponent = mapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(c);

                    System.out.println(jsonComponent);
                    System.out.println(indentedComponent);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return CommandLine.ExitCode.OK;
     }
}
