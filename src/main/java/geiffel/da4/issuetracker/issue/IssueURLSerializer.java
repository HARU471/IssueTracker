package geiffel.da4.issuetracker.issue;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class IssueURLSerializer extends JsonSerializer<Issue> {
    @Override
    public void serialize(Issue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("title", value.getTitle());
        gen.writeStringField("url", "http://localhost:8080/issues/"+value.getCode());
        gen.writeEndObject();
    }
}
