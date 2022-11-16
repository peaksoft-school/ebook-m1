package kg.peaksoft.ebookm1.db.mapper;

import kg.peaksoft.ebookm1.api.payload.promocode.PromoCodeRequest;
import kg.peaksoft.ebookm1.db.entity.PromoCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromocodeEditMapper {

    public PromoCode create(PromoCodeRequest promocodeRequest) {
        PromoCode promocode = new PromoCode();
        promocode.setPromoName(promocodeRequest.getPromoName());
        promocode.setAmountOfPromo(promocodeRequest.getAmountOfPromo());
        promocode.setStartingDay(promocodeRequest.getStartingDay());
        promocode.setFinishingDay(promocodeRequest.getFinishingDay());
        return promocode;
    }

    public PromoCode update(PromoCode promocode, PromoCodeRequest promocodeRequest) {
        promocode.setPromoName(promocodeRequest.getPromoName());
        promocode.setAmountOfPromo(promocodeRequest.getAmountOfPromo());
        promocode.setStartingDay(promocodeRequest.getStartingDay());
        promocode.setFinishingDay(promocodeRequest.getFinishingDay());
        return promocode;
    }
}
