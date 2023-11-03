import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String from;
    private String to;

    public Reservation(String trainNumber, String classType, String dateOfJourney, String from, String to) {
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        // You can implement logic to fetch the train name from a database or other source
        return "Sample Train Name";
    }

    // Implement getters for other properties
}

public class OnlineReservationSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            isLoggedIn = login(username, password);

            if (!isLoggedIn) {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        System.out.println("Login successful. You can now make a reservation or cancel a reservation.");

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. Cancel a reservation");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static boolean login(String username, String password) {
        // Implement logic to validate user credentials
        // For simplicity, a hardcoded user is added in this example
        User user = new User("Likki", "Micky123");
        users.add(user);
        return users.stream().anyMatch(u -> u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    private static void makeReservation() {
        // Implement reservation logic
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        // You can fetch the train name from a database or other source
        String trainName = "Sample Train Name";
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter from (place): ");
        String from = scanner.nextLine();
        System.out.print("Enter to (destination): ");
        String to = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, classType, dateOfJourney, from, to);
        reservations.add(reservation);
        System.out.println("Reservation successfully made.");
    }

    private static void cancelReservation() {
        // Implement reservation cancellation logic
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PNR number: ");
        String pnr = scanner.nextLine();

        // Search for the reservation by PNR and remove it
        reservations.removeIf(r -> r.getTrainNumber().equals(pnr));
        System.out.println("Reservation with PNR " + pnr + " has been canceled.");
    }
}


