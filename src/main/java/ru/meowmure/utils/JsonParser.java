package ru.meowmure.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import ru.meowmure.model.Component;

import java.io.File;
import java.io.IOException;

@ApplicationScoped
public class JsonParser {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("src/main/resources/testComponent.json");
    public Component getComponentFromJson() {
        try {
            return mapper.readValue(file, Component.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
