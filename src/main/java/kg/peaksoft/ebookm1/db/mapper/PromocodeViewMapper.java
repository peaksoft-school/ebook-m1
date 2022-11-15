package kg.peaksoft.ebookm1.db.mapper;


import kg.peaksoft.ebookm1.api.payload.promocode.PromoCodeResponse;
import kg.peaksoft.ebookm1.db.entity.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromocodeViewMapper {

    public PromoCodeResponse viewPromoMapper(Promocode promocode) {
        PromoCodeResponse response = new PromoCodeResponse();
        response.setId(promocode.getId());
        response.setPromoName(promocode.getPromoName());
        response.setStartingDay(promocode.getStartingDay());
        response.setFinishingDay(promocode.getFinishingDay());
        response.setAmountOfPromo(promocode.getAmountOfPromo());
        response.setBookList(promocode.getBooks());
        return response;
    }

    public List<PromoCodeResponse> promocodeResponseList(List<Promocode> promocodes) {
        List<PromoCodeResponse> responseList = new ArrayList<>();
        for (Promocode promocode : promocodes
        ) {
            responseList.add(viewPromoMapper(promocode));
        }
        return responseList;
    }
}
