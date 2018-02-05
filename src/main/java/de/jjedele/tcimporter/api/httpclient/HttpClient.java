package de.jjedele.tcimporter.api.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.jjedele.tcimporter.api.entities.Entity;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;

/**
 * HTTP client with some convenience functions for accessing the RESTful JSON API.
 */
public class HttpClient {

    private final Executor httpExecutor;

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    {
        JSON_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public HttpClient() {
        this.httpExecutor = Executor.newInstance();
        this.httpExecutor.use(new BasicCookieStore());
    }

    /**
     * Do a POST request.
     *
     * @param uri URI for the request
     * @param body The body to send
     * @param clazz The class to which the response should be parsed
     * @param <T> Type of the response
     * @return The response
     * @throws IOException
     */
    public <T> T post(URI uri, Entity body, Class<T> clazz) throws IOException {
        WrappedEntity entity = new WrappedEntity(body);
        Request request = Request.Post(uri).body(entity);
        Response response = httpExecutor.execute(request);
        return JSON_MAPPER.readValue(response.returnContent().asStream(), clazz);
    }

    /**
     * Do a POST request.
     *
     * @param uri URI for the request
     * @param body A low-level HTTP entity, e.g. for multipart requests
     * @param clazz The class to which the response should be parsed
     * @param <T> Type of the response
     * @return The response
     * @throws IOException
     */
    public <T> T post(URI uri, HttpEntity body, Class<T> clazz) throws IOException {
        Request request = Request.Post(uri).body(body);
        Response response = httpExecutor.execute(request);
        return JSON_MAPPER.readValue(response.returnContent().asStream(), clazz);
    }


}
