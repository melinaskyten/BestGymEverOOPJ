package MemberHandling;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MemberCheck {

    private CheckIn checkIn = new CheckIn();

    //Metod som läser in data från fil och returnerar en lista med Person-objekt
    public List<Person> addMembersToList(String filePath) {
        List<Person> memberList = new ArrayList<Person>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner fileScanner = new Scanner(new FileReader(filePath))) {
            while (fileScanner.hasNextLine()) {
                String firstLine = fileScanner.nextLine();
                String secondLine = fileScanner.nextLine();
                String[] firstLineData = firstLine.split(",");
                Person member = new Person(firstLineData[1].trim(), firstLineData[0].trim(),
                        LocalDate.parse(secondLine.trim(), formatter));
                memberList.add(member);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas");
        }
        return memberList;
    }

    //Metod som kollar ifall en Sträng matchar information i en List <Person>
    //Returnerar i sådana fall personen, annars null.
    public Person checkIfMember(String userInput, List<Person> memberList) {
        boolean isMember = false;
        Person targetMember = null;

        for (Person member : memberList) {
            if (userInput.trim().equalsIgnoreCase(member.getName()) ||
                    userInput.trim().equalsIgnoreCase(member.getSocialSecurityNumber())) {
                targetMember = member;
                isMember = true;
                break;
            }
        }
        return isMember ? targetMember : null;
    }

    //Metod som returnerar true om person är aktiv medlem
    public boolean isActiveMember(Person targetMember, LocalDate aYearAgo, LocalDate today) {

        if (targetMember.getLastPaidFee().isBefore(aYearAgo)) {
            return false;
        } else {
            checkIn.addCheckIn(targetMember, "MemberCheckIns", today);
            return true;
        }
    }

}
