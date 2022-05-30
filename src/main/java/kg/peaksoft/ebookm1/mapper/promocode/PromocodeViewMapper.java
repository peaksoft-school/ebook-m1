package kg.peaksoft.ebookm1.mapper.promocode;


import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeResponse;
import kg.peaksoft.ebookm1.entity.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromocodeViewMapper {

    public PromocodeResponse viewPromoMapper(Promocode promocode){
        PromocodeResponse response = new PromocodeResponse();
        response.setId(promocode.getId());
        response.setPromo_name(promocode.getPromoName());
        response.setStarting_day(promocode.getStartingDay());
        response.setFinishing_day(promocode.getFinishingDay());
        response.setAmount_of_promo(promocode.getAmountOfPromo());

        return response;
    }
    public List<PromocodeResponse> promocodeResponseList (List<Promocode> promocodes){
        List<PromocodeResponse> responseList =  new ArrayList<>();
        for (Promocode promocode:promocodes
             ) {
            responseList.add(viewPromoMapper(promocode));
        }
        return responseList;
    }
}
