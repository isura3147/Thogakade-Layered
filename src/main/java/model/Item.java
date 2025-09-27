package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Item {
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private int qtyOnHand;
}
