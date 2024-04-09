package org.zsell.listingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zsell.listingservice.domain.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Integer> {

}