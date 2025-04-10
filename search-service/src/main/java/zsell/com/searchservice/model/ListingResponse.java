package zsell.com.searchservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ListingResponse {

    private  String listingId;
    private  Integer firmId;
    private  Integer price;
    private  Integer statusId;
    private  Integer categoryId;
}
