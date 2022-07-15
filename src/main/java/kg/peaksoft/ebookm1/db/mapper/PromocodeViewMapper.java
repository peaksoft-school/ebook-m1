package kg.peaksoft.ebookm1.db.mapper;


import kg.peaksoft.ebookm1.api.payload.dto.promocode.PromocodeResponse;
import kg.peaksoft.ebookm1.db.entity.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PromocodeViewMapper {

    public PromocodeResponse viewPromoMapper(Promocode promocode) {
        PromocodeResponse response = new PromocodeResponse();
        response.setId(promocode.getId());
        response.setPromoName(promocode.getPromoName());
        response.setStartingDay(promocode.getStartingDay());
        response.setFinishingDay(promocode.getFinishingDay());
        response.setAmountOfPromo(promocode.getAmountOfPromo());
        response.setBookList(promocode.getBooks());
        return response;
    }

    public List<PromocodeResponse> promocodeResponseList(List<Promocode> promocodes) {
        List<PromocodeResponse> responseList = new ArrayList<>();
        for (Promocode promocode : promocodes
        ) {
            responseList.add(viewPromoMapper(promocode));
        }
        return responseList;
    }
}
