package org.zsell.agentgateway.model.request;

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
public class ListingFilterRequest {
    private Integer firmId;
    private String min;
    private String max;
    private Integer statusId;
    private Integer categoryId;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}