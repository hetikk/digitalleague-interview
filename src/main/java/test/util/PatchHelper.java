package test.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Utility class allows to apply patch
 */
@Component
@AllArgsConstructor
public class PatchHelper {

    private final ObjectMapper objectMapper;

    public <T> T apply(T updates, T currentState, Class<T> type) {
        JsonNode node = updates == null
                ? objectMapper.createObjectNode()
                : objectMapper.convertValue(updates, JsonNode.class);
        try {
            JsonMergePatch patch = JsonMergePatch.fromJson(node);
            JsonNode mergedSite = patch.apply(objectMapper.valueToTree(currentState));
            return objectMapper.readerFor(type).readValue(mergedSite);
        } catch (IOException | JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }

}
