package kg.peaksoft.ebookm1.db.services;

import kg.peaksoft.ebookm1.api.controllers.payloads.dto.promocode.PromocodeResponse;
import kg.peaksoft.ebookm1.db.entities.others.Promocode;
import kg.peaksoft.ebookm1.db.enums.RequestStatus;
import kg.peaksoft.ebookm1.db.mappers.promocode.PromocodeViewMapper;
import kg.peaksoft.ebookm1.db.repositories.PromocodeRepository;
import kg.peaksoft.ebookm1.db.repositories.specifications.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromoService {

    private final PromocodeRepository promocodeRepository;
    private final PromocodeViewMapper promocodeViewMapper;


    public List<PromocodeResponse> getAllPromoRelatedBooks(String name, int page){
        int size =10;
        Pageable pageable = PageRequest.of(page,size);
        Specification<Promocode> filter = BookSpecification.getPromocode(name,RequestStatus.APPROVED);
        return promocodeViewMapper.promocodeResponseList(promocodeRepository.findAll(filter,pageable));
    }
}
