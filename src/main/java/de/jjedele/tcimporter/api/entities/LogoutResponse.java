package de.jjedele.tcimporter.api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response of a successful logout operation.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogoutResponse {

    private final String status;

    @JsonCreator
    public LogoutResponse(@JsonProperty("status") String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
