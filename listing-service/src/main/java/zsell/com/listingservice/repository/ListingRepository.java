package zsell.com.listingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zsell.com.listingservice.domain.Listing;

import java.util.List;

public interface ListingRepository  extends JpaRepository<Listing,Integer> {
    List<Listing> findByFirmId(Integer firmId);
}
