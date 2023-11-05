import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OnlineExam {
    private String username;
    private String password;
    private boolean loggedIn;
    private int timeRemaining;
    private boolean timerActive;
    private ScheduledExecutorService timerExecutor;

    public OnlineExam() {
        this.username = null;
        this.password = null;
        this.loggedIn = false;
        this.timeRemaining = 0;
        this.timerActive = false;
        this.timerExecutor = Executors.newScheduledThreadPool(1);
    }

    public void login(String username, String password) {
        // Simulate user authentication (replace with your authentication logic)
        if ("Likki".equals(username) && "Micky123".equals(password)) {
            this.username = username;
            this.password = password;
            this.loggedIn = true;
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    public void updateProfile(String newPassword) {
        if (loggedIn) {
            this.password = newPassword;
            System.out.println("Profile/Password updated.");
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void selectAnswersForMCQs() {
        if (loggedIn) {
            System.out.println("Answer the MCQ questions here.");
            // Implement the logic to select answers for MCQs.
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void startTimer(int duration) {
        if (loggedIn && !timerActive) {
            this.timeRemaining = duration;
            this.timerExecutor.scheduleAtFixedRate(this::updateTimer, 0, 1, TimeUnit.SECONDS);
            this.timerActive = true;
        } else {
            System.out.println("Please log in and make sure the timer is not already active.");
        }
    }

    private void updateTimer() {
        if (timeRemaining > 0) {
            timeRemaining--;
            System.out.println("Time remaining: " + timeRemaining + " seconds.");
        } else {
            autoSubmit();
        }
    }

    public void autoSubmit() {
        if (loggedIn && timerActive) {
            this.timerExecutor.shutdown();
            System.out.println("Auto-submitting your answers.");
            this.timerActive = false;
        } else {
            System.out.println("Auto-submit cannot be triggered.");
        }
    }

    public void logout() {
        this.username = null;
        this.password = null;
        this.loggedIn = false;
        this.timeRemaining = 0;
        this.timerActive = false;
        this.timerExecutor.shutdownNow();
        System.out.println("Logged out.");
    }

    public static void main(String[] args) {
        OnlineExam app = new OnlineExam();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Update Profile/Password");
            System.out.println("3. Select Answers for MCQs");
            System.out.println("4. Start Timer");
            System.out.println("5. Auto-Submit");
            System.out.println("6. Logout");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    app.login(username, password);
                    break;
                case 2:
                    if (app.loggedIn) {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        app.updateProfile(newPassword);
                    } else {
                        System.out.println("Please log in first.");
                    }
                    break;
                case 3:
                    app.selectAnswersForMCQs();
                    break;
                case 4:
                    System.out.print("Enter the timer duration (in seconds): ");
                    int duration = scanner.nextInt();
                    app.startTimer(duration);
                    break;
                case 5:
                    app.autoSubmit();
                    break;
                case 6:
                    app.logout();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
