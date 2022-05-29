package kg.peaksoft.ebookm1.mapper.customer;


import kg.peaksoft.ebookm1.dto.customer.CustomerResponse;
import kg.peaksoft.ebookm1.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerViewMapper {


    public CustomerResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        CustomerResponse response = new CustomerResponse();
        if (user.getId() != null) {
            response.setId(Long.valueOf(user.getId()));
        }
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setBookList(user.getBooks());
        response.setPromocodeList(user.getPromocode());
        return response;
    }

    public List<CustomerResponse> viewUsers(List<User> users) {
        List<CustomerResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(viewUser(user));
        }
        return responses;
    }
}
