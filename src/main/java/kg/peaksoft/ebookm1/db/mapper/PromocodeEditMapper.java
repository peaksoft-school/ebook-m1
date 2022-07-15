package kg.peaksoft.ebookm1.db.mapper;

import kg.peaksoft.ebookm1.api.payload.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.db.entity.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromocodeEditMapper {

    public Promocode create(PromocodeRequest promocodeRequest) {
        Promocode promocode = new Promocode();
        promocode.setPromoName(promocodeRequest.getPromoName());
        promocode.setAmountOfPromo(promocodeRequest.getAmountOfPromo());
        promocode.setStartingDay(promocodeRequest.getStartingDay());
        promocode.setFinishingDay(promocodeRequest.getFinishingDay());
        return promocode;
    }

    public Promocode update(Promocode promocode, PromocodeRequest promocodeRequest) {
        promocode.setPromoName(promocodeRequest.getPromoName());
        promocode.setAmountOfPromo(promocodeRequest.getAmountOfPromo());
        promocode.setStartingDay(promocodeRequest.getStartingDay());
        promocode.setFinishingDay(promocodeRequest.getFinishingDay());
        return promocode;
    }
}
