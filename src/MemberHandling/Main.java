package MemberHandling;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public Main() {
        MemberCheck memberCheck = new MemberCheck();
        UserInteraction userInteraction = new UserInteraction();
        String filePath = "src/MemberHandling/Members";

        List<Person> allMembers = memberCheck.addMembersToList(filePath);

        while (true) {
            String input = userInteraction.programStart();
            Person targetPerson = memberCheck.checkIfMember(input, allMembers);

            if (targetPerson == null) {
                System.out.println(input + " finns inte sparad i systemet.");
            } else {
                boolean isActive = memberCheck.isActiveMember(targetPerson, LocalDate.now().minusYears(1),
                        LocalDate.now());
                System.out.println(userInteraction.memberStatus(targetPerson, isActive));
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}