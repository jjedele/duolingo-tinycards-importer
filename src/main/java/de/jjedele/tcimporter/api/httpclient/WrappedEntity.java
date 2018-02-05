package de.jjedele.tcimporter.api.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.jjedele.tcimporter.api.entities.Entity;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An entity that can be used with HTTP components.
 */
public class WrappedEntity implements HttpEntity {

    private final Entity entity;

    public WrappedEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public boolean isChunked() {
        return false;
    }

    @Override
    public long getContentLength() {
        try {
            return JsonMapper.INSTANCE.writeValueAsBytes(entity).length;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Header getContentType() {
        return new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
    }

    @Override
    public Header getContentEncoding() {
        return new BasicHeader(HttpHeaders.CONTENT_ENCODING, "utf8");
    }

    @Override
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        byte[] data = JsonMapper.INSTANCE.writeValueAsBytes(entity);
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        return bis;
    }

    @Override
    public void writeTo(OutputStream outstream) throws IOException {
        JsonMapper.INSTANCE.writeValue(outstream, entity);
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void consumeContent() throws IOException {
        System.out.println("consume content called");
    }

}
