package ru.meowmure.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.eclipse.microprofile.graphql.NonNull;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"size", "text", "user"})
public class ComponentVersion {
    @Getter
    @JsonProperty
    private @NonNull String name;

    @Getter
    @JsonProperty
    private String date;
    @JsonProperty
    private List<ComponentFile> files;

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) { this.date = date;}

    public void setFiles(List<ComponentFile> files) {
        this.files = files;
    }

    public List<ComponentFile> getFiles() {
        return files == null? Collections.emptyList():files ;
    }


}
