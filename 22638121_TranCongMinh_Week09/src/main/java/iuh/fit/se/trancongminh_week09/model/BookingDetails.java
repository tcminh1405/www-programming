package iuh.fit.se.trancongminh_week09.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDetails {
    private String number;
    private String firstName;
    private String lastName;
    private String from;
    private String to;
    private String date;
}
