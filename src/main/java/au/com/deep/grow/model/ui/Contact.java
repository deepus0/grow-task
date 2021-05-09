package au.com.deep.grow.model.ui;

import lombok.Data;

@Data
public class Contact {

    private String contactId;
    private boolean customer;
    private boolean supplier;
    private String name;
    private double totalAmount;
}
