package org.zsell.agentgateway.model.response.listing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListingCreateRequest {
    private String title;
    private Integer price;
    private String description;
    private Integer categoryId;
    private Integer firmId;
}