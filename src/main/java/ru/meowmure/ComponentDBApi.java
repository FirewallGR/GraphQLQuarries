package ru.meowmure;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import ru.meowmure.model.Component;
import ru.meowmure.utils.JsonParser;

import java.time.LocalDate;

@ApplicationScoped
public class ComponentDBApi {

    @Inject
    JsonParser parser;
    private final PgPool client;
    private final boolean schemaCreate;

    public ComponentDBApi(PgPool client, @ConfigProperty(name = "myapp.schema.create", defaultValue = "true") boolean schemaCreate) {
        this.client = client;
        this.schemaCreate = schemaCreate;
    }
    @Startup
    void config(@Observes StartupEvent ev) {
        if (schemaCreate) {
            try {
                initdb();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initdb() throws InterruptedException {
        Component component = parser.getComponentFromJson();
        client.query("DROP TABLE IF EXISTS components").execute()
                .flatMap(r -> client.query(
                        "CREATE TABLE components (" +
                        "id TEXT NOT NULL PRIMARY KEY," +
                        "name TEXT," +
                        "descriptionShort TEXT," +
                        "guid TEXT," +
                        "lastVersion TEXT," +
                        "version_name TEXT," +
                        "version_date TEXT," +
                        "file_name TEXT," +
                        "file_arch TEXT," +
                        "file_os TEXT," +
                        "file_remotes3key TEXT," +
                        "file_size real," +
                        "file_id TEXT)")
                        .execute()).await().indefinitely();

        client.preparedQuery("INSERT INTO components (" +
                        "id, name, descriptionShort, guid, lastVersion," +
                        "version_name, version_date, file_name, file_arch," +
                        "file_os, file_remotes3key, file_size, file_id)" +
                        "VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13)"
                ).execute(Tuple.tuple()
                .addString(component.getId())
                .addString(component.getName())
                .addString(component.getDescriptionShort())
                .addString(component.getGuid())
                .addString(component.getLastVersion())
                .addString(component.versions.get(0).getName())
                .addString(component.versions.get(0).getDate())
                .addString(component.versions.get(0).getFiles().get(0).getName())
                .addString(component.versions.get(0).getFiles().get(0).getArchitecture())
                .addString(component.versions.get(0).getFiles().get(0).getOS())
                .addString(component.versions.get(0).getFiles().get(0).getRemoteS3Key())
                .addFloat(Float.valueOf(component.versions.get(0).getFiles().get(0).getSize()))
                .addString(component.versions.get(0).getFiles().get(0).getFileId())).await().indefinitely();
    }
}
