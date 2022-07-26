package ro.andreimihalcea.food_app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility class used for Json conversion
 */
@Slf4j
public class JsonUtil {

    /**
     * Converts an object to a Json String
     *
     * @param response - the input {@link Object}
     * @return Json String
     */
    public static String objectToJsonString(Object response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.findAndRegisterModules();
            String json = mapper.writeValueAsString(response);
            log.info("Resulting JSON string: " + json);
            return json;
        } catch (JsonProcessingException e) {
            log.error("Error processing input ", e.getMessage());
        }
        return null;
    }

    /**
     * Converts a json string into an object.
     *
     * @param json  the input {@link String}
     * @param clazz the input {@link Class<T>}
     * @return {@link Object}
     */
    public static <T> Object convertJson(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Something went wrong with converting JSON to Object.");
        }
    }
}
