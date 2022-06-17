package kg.peaksoft.ebookm1.db.mappers.promocode;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.db.entities.others.Promocode;
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
