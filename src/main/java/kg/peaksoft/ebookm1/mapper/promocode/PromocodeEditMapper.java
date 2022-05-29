package kg.peaksoft.ebookm1.mapper.promocode;


import kg.peaksoft.ebookm1.dto.promocode.PromocodeRequest;
import kg.peaksoft.ebookm1.entity.Promocode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromocodeEditMapper {


    public Promocode create(PromocodeRequest promocodeRequest){
        Promocode promocode = new Promocode();
        promocode.setPromoname(promocodeRequest.getPromoname());
        promocode.setStartingDay(promocodeRequest.getStartingday());
        promocode.setFinishingDay(promocodeRequest.getFinishingday());
        promocode.setAmountofpromo(promocodeRequest.getAmountofpromo());
        return promocode;
    }
    public Promocode update(Promocode promocode,PromocodeRequest promocodeRequest){
        promocode.setPromoname(promocodeRequest.getPromoname());
        promocode.setStartingDay(promocodeRequest.getStartingday());
        promocode.setFinishingDay(promocodeRequest.getFinishingday());
        promocode.setAmountofpromo(promocodeRequest.getAmountofpromo());
        return promocode;
    }
}
