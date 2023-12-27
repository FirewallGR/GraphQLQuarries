package ru.meowmure;

import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import ru.meowmure.model.Component;
import ru.meowmure.model.ModelComponentConnection;

@GraphQLApi//GRAPHQL SCHEMA CLASS
public class GraphQLClientAPI {

    @Inject
    ComponentsService service;
    @Query("listComponents")
    public ModelComponentConnection listComponents() {
        ModelComponentConnection componentConnection = new ModelComponentConnection();
        componentConnection.setItems(service.getComponents());
        return componentConnection;
    }

    @Query("getComponentById")
    public Component getComponentById(@Name(value = "id") String id) {
        return service.getComponentByID(id);
    }

    @Query("getComponentByGuid")
    public Component getComponentByGuid(@Name(value = "guid") String guid) {
        return service.getComponentByGUID(guid);
    }
}
