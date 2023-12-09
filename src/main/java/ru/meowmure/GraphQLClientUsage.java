package ru.meowmure;

import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class GraphQLClientUsage {
    @Inject
    @GraphQLClient("component-dynamic")
    DynamicGraphQLClient dynamicClient;

    public void execute() throws ExecutionException, InterruptedException {
        String strQuery =
                "query  {" +
                "  getComponents{" +
                "    id" +
                "    name" +
                "  }" +
                "}";

        strQuery =
                "query($id:String!)  {" +
                        "  getComponentById(id:$id){" +
                        "    id" +
                        "    name" +
                        "  }" +
                        "}";

        strQuery =
                "query($guid:String!)  {" +
                        "  getComponentByGuid(guid:$guid){" +
                        "    id" +
                        "    guid" +
                        "    name" +
                        "  }" +
                        "}";
        Map<String, Object> variables = new HashMap<>();
        //variables.put("id", "99671077-5962-489a-adeb-a6f9867b05ae");
        variables.put("guid", "7996CD7FC112475D8C8F374579D84205");
        Response response = dynamicClient.executeSync(strQuery,variables);
        System.out.println(response);
    }
}
