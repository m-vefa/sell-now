package org.zsell.agentgateway.model.response.listing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListingResponse {
    private Integer listingId;
    private Integer firmId;

    private String title;
    private String description;
    private Integer price;
    private Date createdDate;
    private Date updatedDate;
}
