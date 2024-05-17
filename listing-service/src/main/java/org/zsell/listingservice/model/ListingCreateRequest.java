package org.zsell.listingservice.model;

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

    private String description;
    private Integer price;
    private Integer categoryId;


}