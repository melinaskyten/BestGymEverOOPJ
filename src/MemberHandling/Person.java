package MemberHandling;

import java.time.LocalDate;

public class Person {

    private String name;
    private String socialSecurityNumber;
    private LocalDate lastPaidFee;

    public Person(String name, String socialSecurityNumber, LocalDate lastPaidFee) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.lastPaidFee = lastPaidFee;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public LocalDate getLastPaidFee() {
        return lastPaidFee;
    }

}
