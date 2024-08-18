package org.zsell.listingservice.model;

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
    private String title;
    private String description;
    private Integer price;
    private Date createdDate;
    private Date updatedDate;
}
