package controller.customerController;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerService {

    void addCustomer(String id, String title, String name, String DOB,
                     double salary, String address, String city,
                     String province, String postalCode);

    void deleteCustomer(String id);

    void updateCustomer(String id, String title, String name, String DOB,
                        double salary, String address, String city,
                        String province, String postalCode);

    ResultSet viewCustomer(String id) throws SQLException;

    ObservableList<Customer> loadDetails(ObservableList<Customer> customerDetails);
}
