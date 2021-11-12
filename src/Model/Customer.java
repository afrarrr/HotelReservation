package Model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    private static final String EMAIL_REGEX_PATTERN = "^(.+)@(.+).(.+)$";

    public Customer (String email, String firstName, String lastName){
        this.emailVerify(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // verify if the email format is right
    private void emailVerify(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    @Override
    public String toString(){
        return "First Name: " + this.firstName + 
               " Last Name: " + this.lastName + 
               " Email: " + this.email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj==null || obj.getClass() != this.getClass()){
            return false;
        }
        Customer customer = (Customer) obj;
        return (customer.firstName == this.firstName
                && customer.lastName == this.lastName 
                && customer.email == this.email);
    }

    @Override
    public int hashCode(){
        int prime = 31;
        return prime + Objects.hash(this.email,this.firstName,this.lastName); 
    }
}
