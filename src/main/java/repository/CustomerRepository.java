package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerRepository {

    void addCustomer(String id, String title, String name, String DOB, double salary, String address, String city, String province, String postalCode);

    void deleteCustomer(String id);

    void updateCustomer(String id, String title, String name, String DOB, double salary, String address, String city, String province, String postalCode);

    ResultSet viewCustomer(String id) throws SQLException;

    ResultSet loadDetails() throws SQLException;
}
