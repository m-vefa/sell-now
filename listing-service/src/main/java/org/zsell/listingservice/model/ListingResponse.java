package org.zsell.listingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zsell.listingservice.domain.Category;

import java.time.LocalDate;

@Data
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
    private Category category;
}
