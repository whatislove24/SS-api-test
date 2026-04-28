package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Addition(
        @JsonProperty("additional_info")
        String additionalInfo,

        @JsonProperty("additional_number")
        Integer additionalNumber
) {
}