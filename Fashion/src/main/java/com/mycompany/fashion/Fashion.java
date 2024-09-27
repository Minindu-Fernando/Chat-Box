/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.fashion;

/**
 *
 * @author Minindu 
 */
// clearconsole Method is not working for Net Beans IDA.There fore I didn't use it.I comment them.

import java.util.Scanner;

public class Fashion {

    public static Scanner input = new Scanner(System.in);
    final static String[] statusArray = {"Proccessing", "Delivering", "Delivered"};

    public static String[] orderIds = new String[0];
    public static String[] customerIds = new String[0];
    public static String[] sizes = new String[0];
    public static int[] qtys = new int[0];
    public static String[] status = new String[0];
    public static double[] amount = new double[0];

    public static final int PROCESSING = 0;
    public static final int DELIVERING = 1;
    public static final int DELIVERED = 2;

    public static void extendsAllArray() {
        String[] tempOrderIds = new String[orderIds.length + 1];
        String[] tempCustIds = new String[customerIds.length + 1];
        String[] tempSizes = new String[orderIds.length + 1];
        int[] tempQtys = new int[orderIds.length + 1];
        String[] tempStatus = new String[status.length + 1];
        double[] tempAmount = new double[amount.length + 1];

        for (int i = 0; i < orderIds.length; i++) {
            tempOrderIds[i] = orderIds[i];
            tempCustIds[i] = customerIds[i];
            tempSizes[i] = sizes[i];
            tempQtys[i] = qtys[i];
            tempStatus[i] = status[i];
            tempAmount[i] = amount[i];

        }

        orderIds = tempOrderIds;
        customerIds = tempCustIds;
        sizes = tempSizes;
        qtys = tempQtys;
        status = tempStatus;
        amount = tempAmount;
    }

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
// Handle any exceptions.
        }
    }

    public static String generateId() {
        if (orderIds.length > 0) {
            String id = orderIds[orderIds.length - 1];
            int num = Integer.parseInt(id.split("[#]")[1]);
            num++;
            return String.format("ORD#%05d", num);
        }
        return "ORD#00001";

    }
    public static final String sizeType[] = {"XS", "S", "M", "L", "XL", "XXL"};
    public static final double[] prices = {600.00, 800.00, 900.00, 1000.00, 1100.00, 1200.00};

    public static void placeOrder() {
        System.out.println("   \t\t _____  _                   ____          _");
        System.out.println("   \t\t|  __ \\| |                 / __ \\        | |  ");
        System.out.println("   \t\t| |__) | | __ _  ___ ___  | |  | |_ __ __| | ___ _ __");
        System.out.println("   \t\t|  ___/| |/ _` |/ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
        System.out.println("   \t\t| |    | | (_| | (_|  __/ | |__| | | | (_| |  __/ |");
        System.out.println("   \t\t|_|    |_|\\__,_|\\___\\___|  \\____/|_|  \\__,_|\\___|_|");
        System.out.println("\n\t\t_________________________________________________________");
        double amount1 = 0;
        String size = null;
        int qty;
        String answer;
        String phone_num;

        L1:
        do {

            String orderId = generateId();
            System.out.println("\tOrder Id : " + orderId);
            System.out.println("");

            L2:
            while (true) {
                System.out.print("\tEnter Customer Phone Number : ");

                phone_num = input.next();

                if (phone_num.charAt(0) != '0' || phone_num.length() != 10) {
                    System.out.println("\t\tInvalid Phone Number!...Try again\n");
                    System.out.println("\tDo you want to add phone number again ? (Y/N)");
                    answer = input.next().toUpperCase();
                    if ("Y".equals(answer)) {
                        continue;
                    } else if ("N".equals(answer)) {
                        home();
                    }

                }
                break;

            }

            do {
                System.out.println("");
                System.out.print("\tEnter T-Shirt size (XS/S/M/L/XL/XXL) : ");
                size = input.next().toUpperCase();

            } while (!"XS".equals(size) && !"S".equals(size) && !"M".equals(size) && !"L".equals(size) && !"XL".equals(size) && !"XXL".equals(size));
            {

                System.out.println("");

                do {
                    System.out.print("\tEnter QTY : ");
                    qty = input.nextInt();

                } while (qty <= 0);
                if (qty < 0) {
                    qty = -qty;
                }

                if (qty > 0) {

                    switch (size) {
                        case "XS":
                            amount1 = qty * prices[0];
                            break;
                        case "S":
                            amount1 = qty * prices[1];
                            break;
                        case "M":
                            amount1 = qty * prices[2];
                            break;
                        case "L":
                            amount1 = qty * prices[3];
                            break;
                        case "XL":
                            amount1 = qty * prices[4];
                            break;
                        case "XXL":
                            amount1 = qty * prices[5];
                            break;
                    }
                }

            }
            System.out.println("");
            System.out.println("\tAmount : " + amount1);
            System.out.println("");
            System.out.print("Do you want to place this order ? : (Y/N) ");
            String conclusion = input.next().toUpperCase();
            String a = "Y";
            String b = "N";

            if (conclusion.equals(a)) {

                extendsAllArray();
                orderIds[orderIds.length - 1] = orderId;
                customerIds[customerIds.length - 1] = phone_num;
                sizes[sizes.length - 1] = size;
                qtys[qtys.length - 1] = qty;
                status[status.length - 1] = statusArray[0];
                amount[amount.length - 1] = amount1;

                System.out.println("\tOrder placed...\n");
                System.out.print("Do you want add another order ? (Y/N) : ");
                String answer1 = input.next().toUpperCase();

                if ("Y".equals(answer1)) {
                    continue;

                }
                if ("N".equals(answer1)) {
                    home();
                }

            } else if (conclusion.equals(b)) {
                home();

            }
            break;

        } while (true);

    }

    public static void searchCustomer() {
        clearConsole();
        int counts = 0;
        int countm = 0;
        int countxl = 0;
        int countxxl = 0;
        int countxs = 0;
        int countl = 0;

        double amountxs = 0;
        double amounts = 0;
        double amountm = 0;
        double amountl = 0;
        double amountxl = 0;
        double amountxxl = 0;
        int i;
        double totalAmount = 0;

        System.out.println("\n\n\n");
        System.out.println();
        String phonenumber;
        boolean pnum = false;

        do {
            System.out.println(" \t\t _____                      _        _____          _  ");
            System.out.println(" \t\t/ ____|                    | |      / ____|        | |  ");
            System.out.println(" \t\t| (___   ___  __ _ _ __ ___| |__   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ");
            System.out.println(" \t\t\\___  \\ / _ \\/ _` | '__/ __| '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__|");
            System.out.println("  \t\t ____) |  __/ (_| | | | (__| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |");
            System.out.println(" \t\t|_____/ \\___|\\__,_|_|  \\___|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_| ");
            System.out.println("\t\t____________________________________________________________________________\n");

            System.out.print("\tEnter Customer Phone Number : ");
            phonenumber = input.next();
            if (phonenumber.matches("0\\d+") && phonenumber.length() == 10) {
                for (i = 0; i < customerIds.length; i++) {
                    if (customerIds[i].equals(phonenumber)) {
                        switch (sizes[i]) {
                            case "XS":
                                countxs += qtys[i];
                                amountxs += amount[i];
                                break;
                            case "S":
                                counts += qtys[i];
                                amounts += amount[i];
                                break;
                            case "M":
                                countm += qtys[i];
                                amountm += amount[i];
                                break;
                            case "L":
                                countl += qtys[i];
                                amountl += amount[i];
                                break;
                            case "XL":
                                countxl += qtys[i];
                                amountxl += amount[i];
                                break;
                            case "XXL":
                                countxxl += qtys[i];
                                amountxxl += amount[i];
                                break;
                        }
                        pnum = true;

                    }
                }
                if (pnum == true) {
                    System.out.printf("%-13s%-11s%-22s\n", "+------------", "+----------", "+--------------------+");
                    System.out.printf("%-13s%-11s%-22s\n", "|   Size    ", "|    QTY   |", "       Amount       |");
                    System.out.printf("%-13s%-11s%-22s\n", "+------------", "+----------", "+--------------------+");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|", "|", "|", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  M", "|" + countm, "|" + amountm + "0", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  ", "|", "|", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  XL", "|" + countxl, "|" + amountxl + "0", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|", "|", "|", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  XXL", "|" + countxxl, "|" + amountxxl + "0", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|", "|", "|", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  XS", "|" + countxs, "|" + amountxs + "0", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|", "|", "|", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  S", "|" + counts, "|" + amounts + "0", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|", "|", "|", "|");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|  L", "|" + countl, "|" + amountl + "0", "|");
                    totalAmount = amountxs + amounts + amountm + amountl + amountxl + amountxxl;
                    System.out.printf("%-13s%-11s%-22s\n", "+------------", "+----------", "+--------------------+");
                    System.out.printf("%-13s%-11s%-21s%s\n", "|    Total", " Amount", "|" + totalAmount + "0", "|");
                    System.out.printf("%-13s%-11s%-22s\n", "+------------", "+----------", "+--------------------+");

                    System.out.println("\n\n\n");

                    System.out.print("\n\n\t Do You want to Search Another Customer Report? (Y/N) : ");
                    String ans = input.next().toUpperCase();
                    if (ans.equalsIgnoreCase("Y")) {
                        searchCustomer();

                    } else if (ans.equalsIgnoreCase("N")) {
                        home();

                    }

                } else {
                    for (int j = 0; j < customerIds.length; j++) {
                        if (!customerIds[j].equals(phonenumber)) {
                            System.out.println("Invalid phone Number...");
                            System.out.println("Do you want add phone number again...(Y/N) ?");
                            String answer = input.next().toUpperCase();
                            if ("Y".equals(answer)) {
                                searchCustomer();
                            }
                            if (!"Y".equals(answer)) {
                                home();
                            }

                        }
                    }

                }

            }// phone num check
            else {
                System.out.println("Invalid phone Number...");
                System.out.println("Do you want add phone number again...(Y/N) ?");
                String answer = input.next().toUpperCase();
                if ("Y".equals(answer)) {
                    searchCustomer();
                }
                if (!"Y".equals(answer)) {
                    home();
                }

            }

        } while (true);
    }

    public static void searchOrder() {

        String oderId;
        do {
            System.out.println("\n\t   _____                     _        ____          _           ");
            System.out.println("\t  / ____|                   | |      / __ \\        | |          ");
            System.out.println("\t | (___   ___  __ _ _ __ ___| |__   | |  | |_ __ __| | ___ _ __ ");
            System.out.println("\t  \\___ \\ / _ \\/ _` | '__/ __| '_ \\  | |  | | '__/ _` |/ _ \\ '__|");
            System.out.println("\t  ____) |  __/ (_| | | | (__| | | | | |__| | | | (_| |  __/ |   ");
            System.out.println("\t |_____/ \\___|\\__,_|_|  \\___|_| |_|  \\____/|_|  \\__,_|\\___|_|   ");

            System.out.println("");
            System.out.println("\t  _______________________________________________________________");
            System.out.println("\n\n\n");
            System.out.print("\tEnter order ID : ");
            oderId = input.next();

            for (int i = 0; i < orderIds.length; i++) {
                if (orderIds[i].equals(oderId)) {
                    System.out.println("\tPhone Number : " + customerIds[i]);
                    System.out.println("\tSize         : " + sizes[i]);
                    System.out.println("\tQTY          : " + qtys[i]);
                    System.out.println("\tAmount       : " + amount[i]);
                    System.out.println("\tStatus       : " + status[i]);
                    System.out.print("\tDo you want to search another order ? (Y/N) : ");
                    String answer = input.next().toUpperCase();
                    if ("Y".equals(answer)) {
                        searchOrder();
                    } else {
                        home();
                    }

                }

            }
            break;

        } while (true);

        for (int i = 0; i < customerIds.length; i++) {
            if (!orderIds[i].equals(oderId)) {
                System.out.print("\t\tInvalid ID...\n\tDo you want to search another order? (Y/N) :");
                String answer = input.next().toUpperCase();
                if ("Y".equals(answer)) {
                    searchOrder();
                } else {
                    home();
                }

            }

        }

    }

    public static String[] extendStringArray(String[] sr) {
        String[] temp = new String[sr.length + 1];
        for (int i = 0; i < sr.length; i++) {
            temp[i] = sr[i];
        }
        return temp;
    }

    public static void bestInCustomers() {

        clearConsole();
        System.out.println("\t  ____            _     _____          _____          _                                ");
        System.out.println("\t |  _ \\          | |   |_   _|        / ____|        | |                               ");
        System.out.println("\t | |_) | ___  ___| |_    | |  _ __   | |    _   _ ___| |_ ___  _ __ ___   ___ _ __ ___ ");
        System.out.println("\t |  _ < / _ \\/ __| __|   | | | '_ \\  | |   | | | / __| __/ _ \\| '_ ` _ \\ / _ \\ '__/ __|");
        System.out.println("\t | |_) |  __/\\__ \\ |_   _| |_| | | | | |___| |_| \\__ \\ || (_) | | | | | |  __/ |  \\__ \\");
        System.out.println("\t |____/ \\___||___/\\__| |_____|_| |_|  \\_____\\__,_|___/\\__\\___/|_| |_| |_|\\___|_|  |___/");
        System.out.println("\t   _____________________________________________________________________________");
        System.out.println("\n\n\n");
        if (customerIds.length > 0) {
            String customers[] = new String[0];
            L1:
            for (int i = 0; i < customerIds.length; i++) {
                for (int j = 0; j < customers.length; j++) {
                    if (customers[j].equals(customerIds[i])) {
                        continue L1;
                    }
                }
                customers = extendStringArray(customers);
                customers[customers.length - 1] = customerIds[i];
            }
            int[] sumOfQty = new int[customers.length];
            double[] sumOfAmount = new double[customers.length];

            for (int i = 0; i < customers.length; i++) {

                int totalQty = 0;
                double totalAmount = 0;
                for (int j = 0; j < customerIds.length; j++) {
                    if (customers[i].equals(customerIds[j])) {
                        totalQty += qtys[j];
                        totalAmount += amount[j];
                    }
                }

                sumOfQty[i] = totalQty;
                sumOfAmount[i] = totalAmount;

            }
            for (int j = 0; j < customers.length; j++) {
                for (int i = 0; i < customers.length - 1; i++) {
                    if (sumOfAmount[i + 1] > sumOfAmount[i]) {
                        double t = sumOfAmount[i];
                        sumOfAmount[i] = sumOfAmount[i + 1];
                        sumOfAmount[i + 1] = t;

                        String a = customers[i];
                        customers[i] = customers[i + 1];
                        customers[i + 1] = a;

                        int b = sumOfQty[i];
                        sumOfQty[i] = sumOfQty[i + 1];
                        sumOfQty[i + 1] = b;

                    }
                }
            }
            System.out.println("");
            System.out.printf("+----------------+-----------+----------------+\n");
            System.out.printf("|%-16s|%-11s|%-16s|%n", "  Customer ID", "  All QTY", "  Total Amount");
            System.out.printf("+----------------+-----------+----------------+\n");

            for (int i = 0; i < customers.length; i++) {
                System.out.printf("|%-16s|%-11s|%-16s|%n", "", "", "");
                System.out.printf("|%-16s|%-11s|%-16s|%n", "    " + customers[i], "     " + sumOfQty[i], "   " + sumOfAmount[i] + "0");
            }
            System.out.printf("+----------------+-----------+----------------+\n");

        } else {
            System.out.println("     \t\t<<<<<<<<EMPTY DATA>>>>>>>");
        }
        System.out.print("To access Main Menu, Please enter 0 :");
        int answer = input.nextInt();
        if (answer == 0) {
            home();
        }
    }

    public static void allCustomerReports() {

        System.out.println("""
                               \t\t __      ___                  ____          _               
                               \t\t \\ \\    / (_)                / __ \\        | |              
                               \t\t  \\ \\  / / _  _____      __ | |  | |_ __ __| | ___ _ __ ___ 
                               \t\t   \\ \\/ / | |/ _ \\ \\ /\\ / / | |  | | '__/ _` |/ _ \\ '__/ __|
                               \t\t    \\  /  | |  __/\\ V  V /  | |__| | | | (_| |  __/ |  \\__ \\
                               \t\t     \\/   |_|\\___| \\_/\\_/    \\____/|_|  \\__,_|\\___|_|  |___/""");
        System.out.println("\t\t______________________________________________________________\n\n");

        System.out.printf("+------------+---------------+-------+--------+---------------+---------------+\n");
        System.out.printf("|%-12s|%-15s|%-7s|%-8s|%-15s|%-15s|%n", " Order Id ", " Customer Id ", " Size ", " Qty ", " Amount ", " Status ");
        System.out.printf("+------------+---------------+-------+--------+---------------+---------------+\n");
        for (int i = orderIds.length - 1; i >= 0; i--) {
            System.out.printf("|%-12s|%-15s|%-7s|%-8s|%-15s|%-15s|\n", orderIds[i], customerIds[i], sizes[i], qtys[i], amount[i] + "0", status[i]);
        }
        System.out.printf("+------------+---------------+-------+--------+---------------+---------------+\n");
        int answer;
        do {
            System.out.println("To access the main menu,please enter 0 : ");
            answer = input.nextInt();
            if (answer == 0) {
                home();
            }

        } while (answer != 0);

    }

    public static void viewReports() {

        clearConsole();
        System.out.println("""
                               \t  _____                       _   
                               \t |  __ \\                     | |  
                               \t | |__) |___ _ __   ___  _ __| |_ 
                               \t |  _  // _ \\ '_ \\ / _ \\| '__| __|
                               \t | | \\ \\  __/ |_) | (_) | |  | |_ 
                               \t |_|  \\_\\___| .__/ \\___/|_|   \\__|
                               \t            | |                   
                               \t            |_|                """);
        System.out.println("\t_________________________________\n");

        System.out.println("\n\n");
        System.out.println("\t[1] Best in Customer\n\t[2] All Customer reports\n\t");
        System.out.print("\tEnter Option :");
        int option = input.nextInt();
        switch (option) {
            case 1:
                bestInCustomers();
                break;
            case 2:
                allCustomerReports();
                int answer1;
                do {
                    System.out.println("To access the main menu,please enter 0 : ");
                    answer1 = input.nextInt();
                    if (answer1 == 0) {
                        home();
                    }

                } while (answer1 != 0);

        }
    }

    public static void changeOrderDetails() {
        String orderId;
        do {
            System.out.println("\t   _____ _                               ____          _              _____ _        _              ");
            System.out.println("\t  / ____| |                             / __ \\        | |            / ____| |      | |             ");
            System.out.println("\t | |    | |__   __ _ _ __   __ _  ___  | |  | |_ __ __| | ___ _ __  | (___ | |_ __ _| |_ _   _ ___  ");
            System.out.println("\t | |    | '_ \\ / _` | '_ \\ / _` |/ _ \\ | |  | | '__/ _` |/ _ \\ '__|  \\___ \\| __/ _` | __| | | / __| ");
            System.out.println("\t | |____| | | | (_| | | | | (_| |  __/ | |__| | | | (_| |  __/ |     ____) | || (_| | |_| |_| \\__ \\ ");
            System.out.println("\t  \\_____|_| |_|\\__,_|_| |_|\\__, |\\___|  \\____/|_|  \\__,_|\\___|_|    |_____/ \\__\\__,_|\\__|\\__,_|___/ ");
            System.out.println("\t                            __/ |                                                                   ");
            System.out.println("\t                           |___/                                                                    ");
            System.out.println("\t\t____________________________________________________________________________________________________/n/n");
            System.out.print("\tEnter order ID : ");
            orderId = input.next();
            for (int i = 0; i < orderIds.length; i++) {
                if (orderIds[i].equals(orderId) && status[i].equals(statusArray[0])) {
                    System.out.println("\tPhone Number  : " + customerIds[i] + "\n");
                    System.out.println("\tSize          : " + sizes[i] + "\n");
                    System.out.println("\tQTY           : " + qtys[i] + "\n");
                    System.out.println("\tAmount        : " + amount[i] + "\n");
                    System.out.println("\tStatus        : " + status[i]);

                    System.out.println("");
                    System.out.print("Do you want to change this order status? (Y/N) : ");
                    String answer = input.next().toUpperCase();
                    if ("Y".equals(answer)) {
                        System.out.println("\t[1] Order Delivering \n \t[2] Order Delivered\n");
                        System.out.print("Enter option :");
                        int option = input.nextInt();
                        if (option == 1) {
                            status[i] = statusArray[1];
                            System.out.println(" Order Delivering ");
                            System.out.println("Do you want change another order ? (Y/N) : ");
                            String answer1 = input.next().toUpperCase();
                            if ("Y".equals(answer1)) {
                                changeOrderDetails();
                            } else {
                                home();
                            }

                        }
                        if (option == 2) {
                            status[i] = statusArray[2];
                            System.out.println("Order Delivered");
                            System.out.println("Do you want change another order ? (Y/N) : ");
                            String answer1 = input.next().toUpperCase();
                            if ("Y".equals(answer1)) {
                                changeOrderDetails();
                            } else {
                                home();
                            }

                        }

                    }
                }
            }
            break;

        } while (true);
        for (int i = 0; i < customerIds.length; i++) {
            if (!orderIds[i].equals(orderId)) {
                System.out.print("\t\tInvalid ID...\n\tDo you want to search another order? (Y/N) :");
                String answer = input.next().toUpperCase();
                if ("Y".equals(answer)) {
                    changeOrderDetails();
                } else {
                    home();
                }

            }

        }

    }
    
    public static void deleteOrder() {

        System.out.println(" \t\t _____       _      _          ____          _");
        System.out.println("\t\t|  __ \\     | |    | |        / __ \\        | |");
        System.out.println("\t\t| |  | | ___| | ___| |_ ___  | |  | |_ __ __| | ___ _ __");
        System.out.println("\t\t| |  | |/ _ \\ |/ _ \\ __/ _ \\ | |  | | '__/ _` |/ _ \\ '__|");
        System.out.println("\t\t| |__| |  __/ |  __/ ||  __/ | |__| | | | (_| |  __/ | ");
        System.out.println("\t\t|_____/ \\___|_|\\___|\\__\\___|  \\____/|_|  \\__,_|\\___|_| ");
        System.out.println("\t\t___________________________________________________________\n\n");

        System.out.println("\n ");
        L1:
        while (true) {
            System.out.print("\n\tEnter Order Id  :");
            String OrderId1 = input.next();

            boolean orderFound = false;
            for (int i = 0; i < orderIds.length; i++) {

                if (orderIds[i].equals(OrderId1) && status[i].equals(statusArray[0])) {
                    int index = i;
                    orderFound = true;

                    System.out.println("\tPhone Number  : " + customerIds[i] + "\n");
                    System.out.println("\tSize          : " + sizes[i] + "\n");
                    System.out.println("\tQTY           : " + qtys[i] + "\n");
                    System.out.println("\tAmount        : " + amount[i] + "\n");
                    System.out.println("\tStatus        : " + status[i]);

                    System.out.print("\n\tDo you want to delete this order  ?  ( Y/N )  : ");

                    char Delete = input.next().toUpperCase().charAt(0);

                    if (Delete == 'Y') {

                        String[] temporderIds = new String[orderIds.length - 1];
                        String[] tempcustIds = new String[orderIds.length - 1];
                        String[] tempsizes = new String[orderIds.length - 1];
                        int[] tempqtys = new int[qtys.length - 1];
                        String[] tempstatus = new String[orderIds.length - 1];
                        double[] tempAmount = new double[amount.length - 1];

                        for (int k = 0; k < orderIds.length - 1; k++) {

                            if (k < index) {
                                temporderIds[k] = orderIds[k];
                                tempcustIds[k] = customerIds[k];
                                tempsizes[k] = sizes[k];
                                tempqtys[k] = qtys[k];
                                tempstatus[k] = status[k];
                                tempAmount[k] = amount[k];

                            } else {
                                temporderIds[k] = orderIds[k + 1];
                                tempcustIds[k] = customerIds[k + 1];
                                tempsizes[k] = sizes[k + 1];
                                tempqtys[k] = qtys[k + 1];
                                tempstatus[k] = status[k + 1];
                                tempAmount[k] = amount[k + 1];

                            }
                        }

                        orderIds = temporderIds;
                        customerIds = tempcustIds;
                        sizes = tempsizes;
                        qtys = tempqtys;
                        status = tempstatus;
                        amount = tempAmount;

                        System.out.println("\n\t\tOrder Deleted...! ");

                        break;
                    }

                }
            }

            if (!orderFound) {

                System.out.println("\n\t\t *** Your Enter OrderID is not Valied... ! ");
            }

            System.out.print("Do you want to Delete another order ?  ( Y/N ) :");
            char againdelete2 = input.next().toUpperCase().charAt(0);

            if (againdelete2 == 'Y') {
                //clearConsole();
                continue;

            } else if (againdelete2 == 'N') {
                // clearConsole();
                home();

            }

            break;
        }
    }

    public static void home() {
        do {

            System.out.println(" /$$$$$$$$                 /$$       /$$                            /$$$$$$  /$$");
            System.out.println("| $$_____/                | $$      |__/                           /$$__  $$| $$");
            System.out.println("| $$    /$$$$$$   /$$$$$$$| $$$$$$$  /$$  /$$$$$$  /$$$$$$$       | $$  \\__/| $$$$$$$   /$$$$$$   /$$$$$$");
            System.out.println("| $$$$$|____  $$ /$$_____/| $$__  $$| $$ /$$__  $$| $$__  $$      |  $$$$$$ | $$__  $$ /$$__  $$ /$$__  $$");
            System.out.println("| $$__/ /$$$$$$$|  $$$$$$ | $$  \\ $$| $$| $$  \\ $$| $$  \\ $$       \\____  $$| $$  \\ $$| $$  \\ $$| $$  \\ $$");
            System.out.println("| $$   /$$__  $$ \\____  $$| $$  | $$| $$| $$  | $$| $$  | $$        /$$  \\ $$| $$  | $$| $$  | $$| $$  | $$");
            System.out.println("| $$  |  $$$$$$$ /$$$$$$$/| $$  | $$| $$|  $$$$$$/| $$  | $$       |  $$$$$$/| $$  | $$|  $$$$$$/| $$$$$$$/");
            System.out.println("|__/   \\_______/|_______/ |__/  |__/|__/ \\______/ |__/  |__/       \\______/ |__/  |__/ \\______/ | $$____/ ");
            System.out.println("                                                                                                | $$ ");
            System.out.println("                                                                                                | $$ ");
            System.out.println("                                                                                                |__/ ");
            System.out.println("\t_____________________________________________________________________________________________________________\n");
            System.out.printf("%s\t\t\t\t%s%n%s\t\t\t%s%n%s\t\t\t%s%n%n", "\t[1] place order", "[2] Search Customer", "\t[3] Search Order", "[4] View Reports", "\t[5] Change Order Status", "[6] Delete Order");
            System.out.print("\tInput Option : ");
            int option = input.nextInt();
            switch (option) {
                case 1: {
                    placeOrder();
                    break;
                }

                case 2: {
                    searchCustomer();
                    break;
                }
                case 3:
                    searchOrder();
                    break;
                case 4:
                    viewReports();
                    break;
                case 5:
                    changeOrderDetails();
                    break;
                case 6:
                    deleteOrder();
                    break;
            }
        } while (true);

    }

    public static void main(String[] args) {
        home();

    }

}
