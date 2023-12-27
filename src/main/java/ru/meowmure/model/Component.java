package ru.meowmure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import org.eclipse.microprofile.graphql.NonNull;
import ru.meowmure.utils.FromStringJsonDeserializer;
import io.vertx.mutiny.pgclient.PgPool;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"userCreated"})
public class Component {

    @JsonProperty
    private @NonNull String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String descriptionShort;

    @JsonProperty
    private @NonNull String guid;

    @JsonProperty
    private String lastVersion;

    @JsonProperty
    //  String componentPath;
    private @NonNull String organizationID;

    @JsonDeserialize(using = FromStringJsonDeserializer.class)
    public List<ComponentVersion> versions;


    public Component(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Component() {};

    private static Component from(Row row) {
        Component component = new Component();
        component.id = row.getString("id");
        component.name = row.getString("name");
        component.guid = row.getString("guid");
        component.descriptionShort = row.getString("descriptionshort");
        return component;
    }

    public static Multi<Component> findAll(PgPool client) {
        return client.query("SELECT * FROM components").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Component::from);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public List<ComponentVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<ComponentVersion> versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        return "{\nid: " + id + ",\nguid: " + guid + ",\nname: " + name + ",\nDescription Short: " + descriptionShort + "\n}";
    }
}