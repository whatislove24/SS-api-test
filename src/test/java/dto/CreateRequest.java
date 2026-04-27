package dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateRequest {
    String title;
    String description;
    Boolean verified;
}