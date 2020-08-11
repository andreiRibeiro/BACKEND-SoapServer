package br.com.aaribeiro.soapwebservice.client;

import br.com.aaribeiro.CustomerDetail;
import br.com.aaribeiro.DeleteCustomerResponse;
import br.com.aaribeiro.GetAllCustomerDetailResponse;
import br.com.aaribeiro.GetCustomerDetailResponse;
import br.com.aaribeiro.soapwebservice.model.Customer;
import br.com.aaribeiro.soapwebservice.repository.Repository;

public class CustomerClient {

    public static GetCustomerDetailResponse getCustomerDetail(int id) throws Exception {
        Customer customer = Repository.findById(id);
        CustomerDetail customerDetail = new CustomerDetail();

        if (customer != null) {
            customerDetail.setId(customer.getId());
            customerDetail.setNome(customer.getNome());
            customerDetail.setPhone(customer.getPhone());
            customerDetail.setEmail(customer.getEmail());
        }
        GetCustomerDetailResponse customerDetailResponse = new GetCustomerDetailResponse();
        customerDetailResponse.setCustomerDetail(customerDetail);

        return customerDetailResponse;
    }

    public static GetAllCustomerDetailResponse getAllCustomerDetail(){
        GetAllCustomerDetailResponse listAllCustomerDetailResponse = new GetAllCustomerDetailResponse();

        Repository.findAll().stream().forEach(customer -> {
            CustomerDetail customerDetail = new CustomerDetail();
            customerDetail.setId(customer.getId());
            customerDetail.setNome(customer.getNome());
            customerDetail.setPhone(customer.getPhone());
            customerDetail.setEmail(customer.getEmail());
            listAllCustomerDetailResponse.getCustomerDetail().add(customerDetail);
        });
        return listAllCustomerDetailResponse;
    }

    public static DeleteCustomerResponse deleteCustomerDetail(int id){
        DeleteCustomerResponse deleteCustomerResponse = new DeleteCustomerResponse();
        deleteCustomerResponse.setStatus(Repository.deleteById(id));
        return deleteCustomerResponse;
    }
}
