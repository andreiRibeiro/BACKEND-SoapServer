package br.com.aaribeiro.soapwebservice.endpoint;

import br.com.aaribeiro.*;
import br.com.aaribeiro.soapwebservice.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CustomerDetailEndPoint {

    @Autowired
    CustomerDetailService customerDetailservice;

    @ResponsePayload
    @PayloadRoot(namespace="http://aaribeiro.com.br", localPart="GetCustomerDetailRequest")
    public GetCustomerDetailResponse getCustomerDetail(@RequestPayload GetCustomerDetailRequest request) throws Exception {
        return customerDetailservice.getCustomerDetailService(request.getId());
    }

    @ResponsePayload
    @PayloadRoot(namespace="http://aaribeiro.com.br", localPart="GetAllCustomerDetailRequest")
    public GetAllCustomerDetailResponse getAllCustomerDetail(@RequestPayload GetAllCustomerDetailRequest request){
        return customerDetailservice.getAllCustomerDetailService();
    }

    @ResponsePayload
    @PayloadRoot(namespace="http://aaribeiro.com.br", localPart="DeleteCustomerRequest")
    public DeleteCustomerResponse getAllCustomerDetail(@RequestPayload DeleteCustomerRequest request){
        return customerDetailservice.deleteCustomerService(request.getId());
    }
}
