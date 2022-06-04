package kg.peaksoft.ebookm1.dataBase.mappers.promocode;

import kg.peaksoft.ebookm1.api.payloads.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.dataBase.entities.others.Promocode;
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
