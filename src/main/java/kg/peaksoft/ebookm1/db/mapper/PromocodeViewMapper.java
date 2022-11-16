package kg.peaksoft.ebookm1.db.mapper;


import kg.peaksoft.ebookm1.api.payload.promocode.PromoCodeResponse;
import kg.peaksoft.ebookm1.db.entity.PromoCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromocodeViewMapper {

    public PromoCodeResponse viewPromoMapper(PromoCode promocode) {
        PromoCodeResponse response = new PromoCodeResponse();
        response.setId(promocode.getId());
        response.setPromoName(promocode.getPromoName());
        response.setStartingDay(promocode.getStartingDay());
        response.setFinishingDay(promocode.getFinishingDay());
        response.setAmountOfPromo(promocode.getAmountOfPromo());
        response.setBookList(promocode.getBooks());
        return response;
    }

    public List<PromoCodeResponse> promocodeResponseList(List<PromoCode> promoCodes) {
        List<PromoCodeResponse> responseList = new ArrayList<>();
        for (PromoCode promocode : promoCodes
        ) {
            responseList.add(viewPromoMapper(promocode));
        }
        return responseList;
    }
}
