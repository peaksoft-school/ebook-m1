package kg.peaksoft.ebookm1.dto.promocode;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PromocodeRequest {

    private String promo_name;
    private int amount_of_promo;
    private LocalDate starting_day;
    private LocalDate finishing_day;

}
