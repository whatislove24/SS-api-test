package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityResponse(
        Integer id,
        String title,
        Boolean verified
) {
}