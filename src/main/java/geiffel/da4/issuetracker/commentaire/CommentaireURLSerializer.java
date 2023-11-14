package geiffel.da4.issuetracker.commentaire;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CommentaireURLSerializer extends JsonSerializer<Commentaire>{
    @Override
    public void serialize(Commentaire value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", value.getId().toString());
        gen.writeStringField("url", "*/commentaires/"+value.getId());
        gen.writeEndObject();
    }
}
