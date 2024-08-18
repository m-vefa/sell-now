package org.zsell.listingservice.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zsell.listingservice.enums.PublishingType;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "listings")
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listingId;

    @Enumerated(EnumType.STRING)
    private PublishingType publishingType;

    private String title;
    private String description;
    private Integer price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

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
