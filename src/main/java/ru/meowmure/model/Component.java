package ru.meowmure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.eclipse.microprofile.graphql.NonNull;
import ru.meowmure.utils.FromStringJsonDeserializer;

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
}