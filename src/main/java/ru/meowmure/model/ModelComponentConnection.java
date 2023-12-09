package ru.meowmure.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.graphql.NonNull;

import java.util.List;

public class ModelComponentConnection {


    @NonNull
    @JsonProperty("items")
    private List<Component> items;

    public List<Component> getItems() {
        return items;
    }

    public void setItems(List<Component> components) {
        this.items = components;
    }


}