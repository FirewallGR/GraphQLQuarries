package ru.meowmure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClientBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;
import jakarta.json.stream.JsonGenerator;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;
import ru.meowmure.model.Component;
import ru.meowmure.model.ModelComponentConnection;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static io.smallrye.graphql.client.core.Argument.arg;
import static io.smallrye.graphql.client.core.Argument.args;
import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.InputObject.inputObject;
import static io.smallrye.graphql.client.core.InputObjectField.prop;
import static io.smallrye.graphql.client.core.Operation.operation;



@ApplicationScoped
public class GraphQLClientService {

    @ConfigProperty(name = "quarkus.smallrye-graphql-client.component-dynamic.url")
    String url;
    ObjectMapper objectMapper = new ObjectMapper();

    public ModelComponentConnection listComponents() {
        ModelComponentConnection componentConnection = null;
        DynamicGraphQLClient client = DynamicGraphQLClientBuilder.newBuilder().url(url).build();

        System.out.println("///////////////////BEFORE REQUEST////////////////////////");

        String strQuery =
                "query {" +
                        "listComponents{" + "items{" + "id " + "name" + "}" + "}" + "}";

        System.out.println("\n/////////////////////////AFTER REQUEST//////////////////////////");
        try {
            Response response = client.executeSync(strQuery);

            if (response.hasData() && !response.hasError()) {
                JsonObject data = response.getData().asJsonObject().getJsonObject("listComponents");
                JsonArray items = data.getJsonArray("items");
                StringWriter stringWriter = new StringWriter();

                JsonWriter jsonWriter = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true)).createWriter(stringWriter);

                jsonWriter.writeArray(items);

                jsonWriter.close();

                if (System.getProperty("debug") != null && System.getProperty("debug").equals("true")) {
                    String prettyPrintedJson = stringWriter.toString();
                    System.out.println("Response Json prettified: " + prettyPrintedJson);
                }

                componentConnection = objectMapper.readValue(data.toString(), ModelComponentConnection.class);

            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return componentConnection;
    }

    public Component getComponentById(String id) {
        Component compResult = null;

        DynamicGraphQLClient client = DynamicGraphQLClientBuilder.newBuilder().url(url).build();

        System.out.println("///////////////////BEFORE REQUEST////////////////////////");

        String strQuery =
                "query($id:String!)  {" +
                        "  getComponentById(id:$id){" +
                        "    id" +
                        "    name" +
                        "    guid" +
                        "    descriptionShort" +
                        "  }" +
                        "}";

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);

        try {
            Response response = client.executeSync(strQuery, variables);
            System.out.println(response);

            System.out.println("\n/////////////////////////AFTER REQUEST//////////////////////////");
            if (response.hasData() && !response.hasError()) {
                JsonObject data = response.getData().asJsonObject().getJsonObject("getComponentById");
                StringWriter stringWriter = new StringWriter();

                JsonWriter jsonWriter = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true)).createWriter(stringWriter);

                jsonWriter.writeObject(data);

                jsonWriter.close();

                if (System.getProperty("debug") != null && System.getProperty("debug").equals("true")) {
                    String prettyPrintedJson = stringWriter.toString();
                    System.out.println("Response Json prettified: " + prettyPrintedJson);
                }

                compResult = objectMapper.readValue(data.toString(), Component.class);

            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return compResult;
    }
    public Component getComponentByGuid(String guid) {
        Component compResult = null;

        DynamicGraphQLClient client = DynamicGraphQLClientBuilder.newBuilder().url(url).build();

        System.out.println("///////////////////BEFORE REQUEST////////////////////////");

        String strQuery =
                "query($guid:String!)  {" +
                        "  getComponentByGuid(guid:$guid){" +
                        "    id" +
                        "    name" +
                        "    guid" +
                        "    descriptionShort" +
                        "  }" +
                        "}";

        Map<String, Object> variables = new HashMap<>();
        variables.put("guid", guid);

        try {
            Response response = client.executeSync(strQuery, variables);
            System.out.println(response);

            System.out.println("\n/////////////////////////AFTER REQUEST//////////////////////////");
            if (response.hasData() && !response.hasError()) {
                JsonObject data = response.getData().asJsonObject().getJsonObject("getComponentByGuid");
                StringWriter stringWriter = new StringWriter();

                JsonWriter jsonWriter = Json.createWriterFactory(Map.of(JsonGenerator.PRETTY_PRINTING, true)).createWriter(stringWriter);

                jsonWriter.writeObject(data);

                jsonWriter.close();

                if (System.getProperty("debug") != null && System.getProperty("debug").equals("true")) {
                    String prettyPrintedJson = stringWriter.toString();
                    System.out.println("Response Json prettified: " + prettyPrintedJson);
                }

                compResult = objectMapper.readValue(data.toString(), Component.class);

            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return compResult;
    }
}