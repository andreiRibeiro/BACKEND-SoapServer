package br.com.aaribeiro.soapwebservice.repository;

import br.com.aaribeiro.Status;
import br.com.aaribeiro.soapwebservice.error.CustomerNotFoundException;
import br.com.aaribeiro.soapwebservice.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository {

    private static List<Customer> customers = new ArrayList<>();

    static {
        Customer customer1 = new Customer(1, "Bob", "99999", "bob@gmail.com");
        Customer customer2 = new Customer(2, "Mark", "88888", "mark@gmail.com");
        Customer customer3 = new Customer(3, "Pedro", "77777", "pedro@gmail.com");
        Customer customer4 = new Customer(4, "Jose", "66666", "jose@gmail.com");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
    }

    public static Customer findById(int id) throws Exception {
        Optional<Customer> find = customers.stream().filter(customer -> customer.getId() == id).findFirst();
        if (find.isPresent()){
            return find.get();
        } else {
            throw new CustomerNotFoundException("Cliente nao localizado");
        }
    }

    public static List<Customer> findAll(){
        return customers;
    }

    public static Status deleteById(int id){
        Optional<Customer> find = customers.stream().filter(customer -> customer.getId() == id).findFirst();
        if (find.isPresent()){
            customers.remove(find.get());
            return Status.SUCESS;
        } else {
            return Status.FAILURE;
        }
    }
}
