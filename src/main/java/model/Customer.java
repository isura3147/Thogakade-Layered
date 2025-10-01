package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Customer {
  @Id private String id;
  private String title;
  private String name;
  private LocalDate dob;
  private double salary;
  private String address;
  private String city;
  private String province;
  private String postalCode;
}
