package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PatchRequest(
        Addition addition,

        @JsonProperty("important_numbers")
        List<Integer> importantNumbers,

        String title,
        Boolean verified
) {
}