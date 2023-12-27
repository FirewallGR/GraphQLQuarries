package ru.meowmure;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import ru.meowmure.model.Component;

@Path("components")
public class ComponentDBService {
    private final PgPool client;

    public ComponentDBService(PgPool client) {
        this.client = client;
    }

    @GET
    public Multi<Component> get() {
        return Component.findAll(client);
    }
}
