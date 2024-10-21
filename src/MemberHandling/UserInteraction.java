package MemberHandling;

import java.util.Scanner;

public class UserInteraction {

    public String programStart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ange namn eller personnummer (A - Avbryt)");
        String input = scanner.nextLine();
        if (input.trim().equalsIgnoreCase("A") || input.isEmpty()) {
            System.exit(0);
        }
        return input;
    }

    public String memberStatus(Person person, Boolean isActiveMember) {

        return isActiveMember ? person.getName() + ", " + person.getSocialSecurityNumber() + ": Aktiv medlem" :
                person.getName() + ", " + person.getSocialSecurityNumber() + ": Inaktiv medlem. Obetald årsavgift";

    }

}
