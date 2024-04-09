package org.zsell.listingservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zsell.listingservice.enums.PublishingType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.ORDINAL)
    private PublishingType publishingType;

    private String title;

    private String description;
    private Integer price;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdDate;
    @Column(name = "updated_at")
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private List<Image> images;

    // Residential-specific fields
    private int residentialBedrooms;
    private int residentialBathrooms;
    private double residentialArea;
    private int residentialFloor;
    private boolean residentialFurnished;
    private boolean residentialParking;
    private boolean residentialGarden;

    // Commercial-specific fields
    private double commercialArea;
    private double commercialFloorSpace;
    private int commercialNumberOfRooms;
    private boolean commercialParking;
    private String commercialLeaseTerm;

    // Land-specific fields
    private double landArea;
    private String landZoning;
    private String landSoilType;
    private boolean landUtilitiesAvailable;
    private String landRoadAccess;
    private String landTopography;

}
