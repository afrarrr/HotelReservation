package Service;

import java.util.Collection;
import java.util.HashSet;
import Model.Customer;

public class CustomerService {

    Collection <Customer> setOfCustomers = new HashSet <Customer>();
    //https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples
    private static CustomerService instance;

    private CustomerService(){}

    public static CustomerService getInstance(){
        if (instance == null){
            instance = new CustomerService();
        }
        return instance;
    }
    
    public void addCustomer (String email, String firstName, String lastName){
        Customer newCustomer = new Customer(email, firstName, lastName);
        setOfCustomers.add(newCustomer);
    }

    public Customer getCustomer (String customerEmail){
        for (Customer customer : setOfCustomers) {
            if((customer.getEmail()).equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return setOfCustomers;
    }


}
