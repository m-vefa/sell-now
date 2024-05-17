package org.zsell.agentgateway.model.listing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListingResponse {
    private Integer listingId;
    private String title;
    private String description;
    private Integer price;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}