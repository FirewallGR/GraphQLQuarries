package ru.meowmure;

import jakarta.enterprise.context.ApplicationScoped;
import ru.meowmure.model.Component;
import ru.meowmure.utils.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ComponentsService {
    private List<Component> components = new ArrayList<>();

    public ComponentsService() {
        components.add(new JsonParser().getComponentFromJson());
    }

    public List<Component> getComponents() {
        return components;
    }

    public Component getComponentByID(String id) {
        Component component = null;
        Iterator<Component> iterator = components.iterator();
        while (iterator.hasNext()) {
            component = iterator.next();
            if (component.getId().equals(id)) break;
        }
        return component;
    }

    public Component getComponentByGUID(String guid) {
        return components.stream().filter(component -> component.getGuid().equals(guid)).toList().get(0);
    }


}
