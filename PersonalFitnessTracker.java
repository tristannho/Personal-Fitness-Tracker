import java.util.ArrayList;
import java.util.Scanner;

class Workout 
{
    String date;
    String type; // e.g., Running, Cycling
    double distance; // in kilometers
    double duration; // in minutes

    public Workout(String date, String type, double distance, double duration) 
    {
        this.date = date;
        this.type = type;
        this.distance = distance;
        this.duration = duration;
    }
}

public class PersonalFitnessTracker 
{
    private static ArrayList<Workout> workouts = new ArrayList<>();

    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        String moreWorkouts;

        do 
        {
            String date;
            while (true) 
            {
                System.out.print("Enter workout date (YYYY-MM-DD): ");
                date = scan.nextLine();
                if (date.matches("\\d{4}-\\d{2}-\\d{2}")) 
                {
                    break; // Valid date format
                } else 
                {
                    System.out.println("Invalid date format. Please try again.");
                }
            }

            System.out.print("Enter workout type (e.g., Running, Cycling): ");
            String type = scan.nextLine();

            double distance = -1;
            while (distance < 0) 
            {
                System.out.print("Enter distance (in kilometers): ");
                distance = scan.nextDouble();
                if (distance < 0) 
                {
                    System.out.println("Distance cannot be negative. Please enter a valid distance.");
                }
            }

            double duration = -1;
            while (duration < 0) 
            {
                System.out.print("Enter duration (in minutes): ");
                duration = scan.nextDouble();
                if (duration < 0) 
                {
                    System.out.println("Duration cannot be negative. Please enter a valid duration.");
                }
            }
            scan.nextLine(); // Consume newline

            workouts.add(new Workout(date, type, distance, duration));

            System.out.print("Do you want to add another workout? (yes/no): ");
            moreWorkouts = scan.nextLine();
        } while (moreWorkouts.equalsIgnoreCase("yes"));

        displaySummary();
        scan.close();
    }

    private static void displaySummary() 
    {
        double totalDistance = 0;
        double totalDuration = 0;

        System.out.println("\n--- Fitness Summary Report ---");
        for (Workout workout : workouts) 
        {
            System.out.printf("Date: %s | Type: %s | Distance: %.2f km | Duration: %.2f min%n",
                    workout.date, workout.type, workout.distance, workout.duration);
            totalDistance += workout.distance;
            totalDuration += workout.duration;
        }

        double averagePace = totalDistance > 0 ? totalDuration / totalDistance : 0;
        System.out.printf("Total Distance: %.2f km%n", totalDistance);
        System.out.printf("Total Duration: %.2f min%n", totalDuration);
        System.out.printf("Average Pace: %.2f min/km%n", averagePace);
    }
}
