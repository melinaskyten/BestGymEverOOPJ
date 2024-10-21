package MemberHandling;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckIn {

    public void addCheckIn(Person member, String outFile, LocalDate today) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Path filePath = Paths.get(outFile);

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            List<String> allLines = Files.readAllLines(filePath);
            boolean checkedInBefore = false;

            StringBuilder checkIns = new StringBuilder();
            for (String line : allLines) {
                if (line.startsWith(member.getName())) {
                    line += ", " + formatter.format(today);
                    checkedInBefore = true;
                }
                checkIns.append(line).append("\n");
            }

            if (!checkedInBefore) {
                checkIns.append(member.getName()).append(": ").append(formatter.format(today));
            }

            try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath.toFile(), false))) {
                printWriter.print(checkIns);
            }

        } catch (NoSuchFileException e) {
            System.out.println("Filen kunde inte hittas");
        } catch (FileNotFoundException e) {
            System.out.println("Fel uppstod vid åtkomst till filen.");
        } catch (IOException e) {
            System.out.println("Generellt I/O-fel uppstod.");
        } catch (Exception e) {
            System.out.println("Oväntat fel uppstod.");
        }
    }

}
