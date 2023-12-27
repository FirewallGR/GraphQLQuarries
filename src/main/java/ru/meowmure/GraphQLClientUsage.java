package ru.meowmure;

import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.meowmure.model.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class GraphQLClientUsage {
//    @Inject
//    @GraphQLClient("component-dynamic")
//    DynamicGraphQLClient dynamicClient;
//
//    public void execute() throws ExecutionException, InterruptedException {
//        String strQuery =
//                "query  {" +
//                "  getComponents{" +
//                "    id" +
//                "    name" +
//                "  }" +
//                "}";
//
//        strQuery =
//                "query($id:String!)  {" +
//                        "  getComponentById(id:$id){" +
//                        "    id" +
//                        "    name" +
//                        "  }" +
//                        "}";
//
//        strQuery =
//                "query($guid:String!)  {" +
//                        "  getComponentByGuid(guid:$guid){" +
//                        "    id" +
//                        "    guid" +
//                        "    name" +
//                        "  }" +
//                        "}";
//        Map<String, Object> variables = new HashMap<>();
//        //variables.put("id", "99671077-5962-489a-adeb-a6f9867b05ae");
//        variables.put("guid", "7996CD7FC112475D8C8F374579D84205");
//        Response response = dynamicClient.executeSync(strQuery,variables);
//        System.out.println(response);
//    }


    @Inject
    GraphQLClientService service;
    public void findById(String id) {
        Component componentById = service.getComponentById("99671077-5962-489a-adeb-a6f9867b05ae");

        if (componentById != null) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n\n");
            System.out.println(componentById);
        } else {
            System.out.println("NULL");
        }
    }

    public void findAll() {
        List<Component> components = service.listComponents().getItems();
        if (!components.isEmpty()) {
            System.out.println("\n\n\n");
            components.forEach(System.out::println);
            System.out.println("\n\n\n");
        }
    }

    public void findByGuid(String guid) {
        Component component = service.getComponentByGuid(guid);
        if (component != null)
            System.out.println(component);
    }
}
