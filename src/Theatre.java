import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Theatre {
    public static void main(String[] args) {

        System.out.println("Welcome to the New Theatre");
        //display menu
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Please select an option:");
        System.out.println("1) Buy a ticket");
        System.out.println("2) Print seating area");
        System.out.println("3) Cancel ticket ");
        System.out.println("4) List available seats");
        System.out.println("5) Save to file");
        System.out.println("6) Load from file");
        System.out.println("7) Print ticket information and total price ");
        System.out.println("8) Sort ticket by price");
        System.out.println("9)      Quit");
        System.out.println("-------------------------------------------------------------------------");

        //Intialize the seats array with all seats set to free(0)
        boolean[] row_1 = new boolean[12];
        boolean[] row_2 = new boolean[16];
        boolean[] row_3 = new boolean[20];

        List<Ticket> ticket_list = new ArrayList<>();
        //create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("0")) {
            System.out.println("Please select an option :");
            //Read the user's input
            input = scanner.next();
            //Process users choice
            switch (input) {
                case "1":
                    buy_ticket(scanner, row_1, row_2, row_3, ticket_list);
                    break;
                case "2":
                    print_seating_area(row_1, row_2, row_3);
                    break;
                case "3":
                    cancel_ticket(scanner, row_1, row_2, row_3, ticket_list);
                    break;
                case "4":
                    show_available(row_1, row_2, row_3);
                    break;
                case "5":
                    save(row_1, row_2, row_3);
                    break;
                case "6":
                    load(row_1, row_2, row_3);
                    break;
                case "7":
                    show_tickets_info(ticket_list);
                    break;
                case "8":
                    sort_tickets(ticket_list);
                    break;
                case "0":
                    System.out.println("Thank you for visiting us. Have a nice day !!!");
                    break;
                default:
                    System.out.println("Invalid Input, Please try again");
                    break;
            }
        }
    }

    private static void buy_ticket(Scanner scanner, boolean[] row_1, boolean[] row_2, boolean[] row_3, List<Ticket> ticket_list) {
        //Ask the user to input a row number and seat number
        System.out.println("Enter row number (1-3) : ");
        int row = scanner.nextInt();
        System.out.println("Enter seat number (1-20) : ");
        int seat = scanner.nextInt();
        switch (row) {
            case 1:
                //Check that the row and seat are correct
                if (seat < 1 || seat > row_1.length) {
                    System.out.println("Invalid seat, Please try again.");
                    return;
                } else if (row_1[seat-1]) {
                    System.out.println("Sorry! seat is already booked.");
                    return;
                }
                row_1[seat-1] = true;
                break;
            case 2:
                if (seat < 1 || seat > row_2.length) {
                    System.out.println("Invalid seat, Please try again.");
                    return;
                } else if (row_2[seat-1]) {
                    System.out.println("Sorry! seat is already booked.");
                    return;
                }
                row_2[seat-1] = true;
                break;
            case 3:
                if (seat < 1 || seat > row_3.length) {
                    System.out.println("Invalid seat, Please try again.");
                    return;
                } else if (row_3[seat-1]) {
                    System.out.println("Sorry! seat is already booked.");
                    return;
                }
                row_3[seat-1] = true;
                break;
            default:
                System.out.println("Invalid Row, Please try again.");
                return;
        }

        Random random = new Random();
        double price = 10 + 40 * random.nextDouble(); // pick random decimal number between 10 and 50
        price = Math.round(price * 100.0)/100.0; // round off price to two decimal places
        System.out.println("Enter Name : ");
        String name = scanner.next();
        System.out.println("Enter Surname : ");
        String surname = scanner.next();
        System.out.println("Enter Email : ");
        String email = scanner.next();
        Person person = new Person(name, surname, email);
        Ticket ticket = new Ticket(row, seat, price, person);
        ticket_list.add(ticket);
        System.out.println("Thank you for your purchase!! Seat -: row "+ row + " seat " + seat + " has been booked.");
    }

    private static void print_seating_area(boolean[] row_1, boolean[] row_2, boolean[] row_3) {
        System.out.println("      *********");
        System.out.println("      * STAGE *");
        System.out.println("      *********");
        print_seating(row_1, "%4s");
        print_seating(row_2, "%2s");
        print_seating(row_3, "%s");
    }

    private static  void print_seating(boolean[] list, String format){
        System.out.printf(format,"");
        for (int i = 0; i < list.length; i++) {
            if(list.length/2==i){
                System.out.print(" ");
            }
            if(list[i]) {
                System.out.print("X");
            } else {
                System.out.print("O");
            }
        }
        System.out.println();
    }

    private static void cancel_ticket(Scanner scanner, boolean[] row_1, boolean[] row_2, boolean[] row_3, List<Ticket> ticket_list) {
        //Ask user to input row number and seat number
        System.out.println("Enter row number (1-3) : ");
        int row = scanner.nextInt();
        System.out.println("Enter seat number (1-20) : ");
        int seat = scanner.nextInt();
        switch (row) {
            case 1:
                //Check if row and seat numbers are valid
                if (seat < 1 || seat > row_1.length) {
                    System.out.println("Invalid seat");
                    return;
                } else if (!row_1[seat-1]) {
                    System.out.println("Sorry! seat is already available.");
                    return;
                }
                row_1[seat-1] = false;
                break;
            case 2:
                if (seat < 1 || seat > row_2.length) {
                    System.out.println("Invalid seat");
                    return;
                } else if (!row_2[seat-1]) {
                    System.out.println("Sorry! seat is already available.");
                    return;
                }
                row_2[seat-1] = false;
                break;
            case 3:
                if (seat < 1 || seat > row_3.length) {
                    System.out.println("Invalid seat");
                    return;
                } else if (!row_3[seat-1]) {
                    System.out.println("Sorry! seat is already available.");
                    return;
                }
                row_3[seat-1] = false;
                break;
            default:
                System.out.println("Invalid Row");
                return;
        }
        System.out.println("Thank you for cancelling !! Seat -: row "+ row + " seat " + seat + " has been cancelled");
        Ticket removeTicket = null;
        for (Ticket ticket : ticket_list) {
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                removeTicket = ticket;
                break;
            }
        }
        if (removeTicket != null) {
            ticket_list.remove(removeTicket);
        }
    }

    private static void show_available(boolean[] row_1, boolean[] row_2, boolean[] row_3) {
        System.out.print("Seats available in row 1 :");
        print_availability(row_1);
        System.out.println();

        System.out.print("Seats available in row 2 :");
        print_availability(row_2);
        System.out.println();

        System.out.print("Seats available in row 3 :");
        print_availability(row_3);
        System.out.println();

    }

    private static void print_availability(boolean[] row) {
        for (int i = 0; i < row.length; i++) {
            if (i == row.length-1 && !row[i]) {
                System.out.print(i+1);
            }
            else if(!row[i]) {
                System.out.print(i+1 + ",");
            }
        }
    }

    private static void save(boolean[] row_1, boolean[] row_2, boolean[] row_3) {
        try {
            FileWriter writer = new FileWriter("seats.txt");
            // write row 1
            write(row_1, writer);
            // write row 2
            write(row_2, writer);
            // write row 3
            write(row_3, writer);
            writer.close();
            System.out.println("All seats saved to seats.txt file.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void write(boolean[] row, FileWriter writer) throws IOException {
        for (int i = 0; i < row.length; i++) {
            writer.write(row[i] + ",");
        }
        writer.write("\n");
    }

    private static void load(boolean[] row_1, boolean[] row_2, boolean[] row_3) {
        try {
            File file = new File("seats.txt");
            Scanner scanner = new Scanner(file);
            int row = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++) {
                    // enhanced switch
                    switch (row) {
                        case 1 -> row_1[i] = Boolean.parseBoolean(values[i]);
                        case 2 -> row_2[i] = Boolean.parseBoolean(values[i]);
                        case 3 -> row_3[i] = Boolean.parseBoolean(values[i]);
                    }
                }
                row++;
            }
            scanner.close();
            System.out.println("Seats loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found, Seats could not load");
        }
    }

    private static void show_tickets_info(List<Ticket> ticket_list) {
        double total = 0;
        for (Ticket ticket : ticket_list) {
            total += ticket.getPrice();
            ticket.print();
        }
        // round off total to have two decimal places
        System.out.println("Total : " + Math.round(total * 100.0)/100.0);
    }

    private static void sort_tickets(List<Ticket> ticket_list) {
        //"used one of sorting methods stated in following stackoverflow answer."
        //"https://stackoverflow.com/questions/16252269/how-to-sort-a-list-arraylist"
        ticket_list.sort(Comparator.comparing(Ticket::getPrice));
        for (Ticket ticket : ticket_list) {
            ticket.print();
        }
    }

}