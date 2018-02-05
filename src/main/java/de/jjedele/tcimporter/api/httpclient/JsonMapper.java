package de.jjedele.tcimporter.api.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonMapper {

    private JsonMapper() {}

    /**
     * The configured instance of the JSON mapper.
     */
    public final static ObjectMapper INSTANCE = new ObjectMapper();

    {
        INSTANCE.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

}
