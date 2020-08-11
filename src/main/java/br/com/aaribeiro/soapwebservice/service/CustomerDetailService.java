package br.com.aaribeiro.soapwebservice.service;

import br.com.aaribeiro.DeleteCustomerResponse;
import br.com.aaribeiro.GetAllCustomerDetailResponse;
import br.com.aaribeiro.GetCustomerDetailResponse;
import br.com.aaribeiro.soapwebservice.client.CustomerClient;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailService {

    public GetCustomerDetailResponse getCustomerDetailService(int id ) throws Exception {
        return CustomerClient.getCustomerDetail(id);
    }

    public GetAllCustomerDetailResponse getAllCustomerDetailService(){
        return CustomerClient.getAllCustomerDetail();
    }

    public DeleteCustomerResponse deleteCustomerService(int id){
        return CustomerClient.deleteCustomerDetail(id);
    }
}
