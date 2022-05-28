package kg.peaksoft.ebookm1.dto.promocode;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromocodeResponse {

    private Long id;
    private String promoname;
    private int amountofpromo;
    private LocalDate startingDay;
    private LocalDate finishingDay;
}
