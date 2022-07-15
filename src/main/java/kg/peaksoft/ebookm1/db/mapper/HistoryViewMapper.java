package kg.peaksoft.ebookm1.db.mapper;

import kg.peaksoft.ebookm1.api.payload.dto.history.HistoryResponse;
import kg.peaksoft.ebookm1.db.entity.HistoryOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryViewMapper {

    public HistoryResponse viewHistory(HistoryOperation operation) {
        HistoryResponse historyResponse = new HistoryResponse();
        historyResponse.setId(historyResponse.getId());
        historyResponse.setBasket(operation.getBasket());
        historyResponse.setWishList(operation.getWishList());
        return historyResponse;
    }

    public List<HistoryResponse> viewAllHistory(List<HistoryOperation> historyOperationList) {
        List<HistoryResponse> operationResponses = new ArrayList<>();
        for (HistoryOperation operation : historyOperationList) {
            operationResponses.add(viewHistory(operation));
        }
        return operationResponses;
    }
}
