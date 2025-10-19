package com.mycompany.Speak_Apartments;

import java.util.Scanner;

public class Speak_Apartments {

    private static int gymOccupants = 0;
    private static int poolOccupants = 0;
    private static boolean[] gymLights = new boolean[3];
    private static boolean[] poolLights = new boolean[3];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String activeArea = "G";

        System.out.println("===== SPEKE APARTMENTS AREA CONTROL SYSTEM =====");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("S – Select active area (G=Gym, P=Swimming)");
            System.out.println("W – Add occupants");
            System.out.println("X – Remove occupants");
            System.out.println("Y – Switch ON light (1–3)");
            System.out.println("Z – Switch OFF light (1–3)");
            System.out.println("R – Report status");
            System.out.println("Q – Quit");
            System.out.print("Enter option: ");

            String choice = input.nextLine().trim().toUpperCase();

            switch (choice) {
                case "S":
                    System.out.print("Select area (G/P): ");
                    activeArea = input.nextLine().trim().toUpperCase();
                    if (!activeArea.equals("G") && !activeArea.equals("P")) {
                        System.out.println("Invalid area! Defaulting to Gym.");
                        activeArea = "G";
                    } else {
                        System.out.println("Active area set to " + (activeArea.equals("G") ? "Gym" : "Swimming Pool"));
                    }
                    break;

                case "W":
                    System.out.print("Enter number of occupants to add: ");
                    int add = getValidInteger(input);
                    if (activeArea.equals("G")) {
                        gymOccupants += add;
                        System.out.println(add + " occupant(s) added to Gym.");
                    } else {
                        poolOccupants += add;
                        System.out.println(add + " occupant(s) added to Swimming Pool.");
                    }
                    break;

                case "X":
                    System.out.print("Enter number of occupants to remove: ");
                    int remove = getValidInteger(input);
                    if (activeArea.equals("G")) {
                        gymOccupants = Math.max(0, gymOccupants - remove);
                        System.out.println(remove + " occupant(s) removed from Gym.");
                    } else {
                        poolOccupants = Math.max(0, poolOccupants - remove);
                        System.out.println(remove + " occupant(s) removed from Swimming Pool.");
                    }
                    break;

                case "Y":
                    System.out.print("Enter light number (1–3) to switch ON: ");
                    int on = getValidLight(input);
                    if (activeArea.equals("G")) {
                        gymLights[on - 1] = true;
                        System.out.println("Light " + on + " switched ON in Gym.");
                    } else {
                        poolLights[on - 1] = true;
                        System.out.println("Light " + on + " switched ON in Swimming Pool.");
                    }
                    break;

                case "Z":
                    System.out.print("Enter light number (1–3) to switch OFF: ");
                    int off = getValidLight(input);
                    if (activeArea.equals("G")) {
                        gymLights[off - 1] = false;
                        System.out.println("Light " + off + " switched OFF in Gym.");
                    } else {
                        poolLights[off - 1] = false;
                        System.out.println("Light " + off + " switched OFF in Swimming Pool.");
                    }
                    break;

                case "R":
                    if (activeArea.equals("G")) {
                        System.out.println(getStatus("Gym", gymOccupants, gymLights));
                    } else {
                        System.out.println(getStatus("Swimming Pool", poolOccupants, poolLights));
                    }
                    break;

                case "Q":
                    System.out.println("Exiting system... Goodbye!");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static String getStatus(String name, int occupants, boolean[] lights) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- " + name + " Status ---\n");
        sb.append("Occupants: ").append(occupants).append("\n");
        sb.append("Lights: ");
        for (int i = 0; i < lights.length; i++) {
            sb.append("L").append(i + 1).append("=").append(lights[i] ? "ON " : "OFF ");
        }
        return sb.toString();
    }

    private static int getValidInteger(Scanner input) {
        while (true) {
            try {
                int n = Integer.parseInt(input.nextLine());
                if (n >= 0) return n;
                System.out.print("Please enter a positive number: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid number: ");
            }
        }
    }

    private static int getValidLight(Scanner input) {
        while (true) {
            try {
                int n = Integer.parseInt(input.nextLine());
                if (n >= 1 && n <= 3) return n;
            } catch (NumberFormatException e) {}
            System.out.print("Light number must be between 1 and 3: ");
        }
    }
}
