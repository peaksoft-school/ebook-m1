package kg.peaksoft.ebookm1.mapper.promocode;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.entity.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromocodeEditMapper {


    public Promocode create(PromocodeRequest promocodeRequest){
        Promocode promocode = new Promocode();
        promocode.setPromoName(promocodeRequest.getPromo_name());
        promocode.setStartingDay(promocodeRequest.getStarting_day());
        promocode.setFinishingDay(promocodeRequest.getFinishing_day());
        promocode.setAmountOfPromo(promocodeRequest.getAmount_of_promo());
        return promocode;
    }
    public Promocode update(Promocode promocode,PromocodeRequest promocodeRequest){
        promocode.setPromoName(promocodeRequest.getPromo_name());
        promocode.setStartingDay(promocodeRequest.getStarting_day());
        promocode.setFinishingDay(promocodeRequest.getFinishing_day());
        promocode.setAmountOfPromo(promocodeRequest.getAmount_of_promo());
        return promocode;
    }
}
