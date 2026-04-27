package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PatchRequest {
    String title;
    String description;
    Boolean verified;
}