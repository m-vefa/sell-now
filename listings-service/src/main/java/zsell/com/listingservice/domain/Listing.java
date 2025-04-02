package zsell.com.listingservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listingId;

    private Integer firmId;
    private String title;
    private String description;
    private Integer price;
    private Integer statusId;
    private Integer categoryId;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private Double area;
    private Integer room;
    private Integer hall;
    private Integer residentialBathrooms;
    private Integer residentialFloor;
    private Boolean residentialFurnished;
    private Boolean residentialParking;
    private Boolean residentialGarden;

    private Double commercialFloorSpace;
    private Boolean commercialParking;
    private String commercialLeaseTerm;

    private String landZoning;
    private String landSoilType;
    private Boolean landUtilitiesAvailable;
    private Boolean landRoadAccess;
    private String landTopography;
}
