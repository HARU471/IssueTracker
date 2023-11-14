package geiffel.da4.issuetracker.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserURLSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("nom", value.getNom());
        gen.writeStringField("url", "http://localhost:8080/users/"+value.getId());
        gen.writeEndObject();
    }
}
