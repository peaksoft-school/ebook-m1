package kg.peaksoft.ebookm1.specifications.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchDto {

    private List<SearchCriteria> searchCriteriaList;
    private  String dataOption;
}
