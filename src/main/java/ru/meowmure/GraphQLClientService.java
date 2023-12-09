package ru.meowmure;

import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;
import org.jetbrains.annotations.Blocking;
import ru.meowmure.model.Component;
import ru.meowmure.model.ModelComponentConnection;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@GraphQLApi //GRAPHQL SCHEMA CLASS
public class GraphQLClientService {

    @Inject
    ComponentsService service;


    @Query("getComponents")
    public List<Component> getComponents() {
        return service.getComponents();
    }
    @Query("getComponentById")
    public Component getComponentById(@NonNull String id) {
        return service.getComponentByID(id);
    }
    @Query("getComponentByGuid")
    public Component getComponentByGuid(@NonNull String guid) {
        return service.getComponentByGUID(guid);
    }
}