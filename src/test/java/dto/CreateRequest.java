package dto;

public record CreateRequest(
        String title,
        String description,
        Boolean verified
) {}