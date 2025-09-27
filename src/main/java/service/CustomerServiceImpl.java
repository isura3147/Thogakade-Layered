package service;

import javafx.collections.ObservableList;
import model.Customer;
import repository.CustomerRepository;

import java.sql.*;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository = new CustomerRepository();


    @Override
    public void addCustomer(String id, String title, String name, String DOB, double salary, String address, String city, String province, String postalCode) {
        customerRepository.addCustomer(id, title, name, DOB, salary, address, city, province, postalCode);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public void updateCustomer(String id, String title, String name, String DOB, double salary, String address, String city, String province, String postalCode) {
        customerRepository.updateCustomer(id, title, name, DOB, salary, address, city, province, postalCode);
    }

    @Override
    public ResultSet viewCustomer(String id) throws SQLException {
        return customerRepository.viewCustomer(id);
    }

    @Override
    public ObservableList<Customer> loadDetails(ObservableList<Customer> customerInfos) {
        try {
            ResultSet resultSet = customerRepository.loadDetails();
            while (resultSet.next()) {
                Customer customerInfo = new Customer(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerInfos.add(customerInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerInfos;
    }


}
