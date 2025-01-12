package zsell.com.searchservice.domain;

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
public class Product {

    private String title;
    private String brand;
    private String description;
    private double price;
    private String processor;
    private String ram;
    private String storage;
    private String model;

}
