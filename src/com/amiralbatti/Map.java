package com.amiralbatti;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Map {
    // User input
    private Scanner input = new Scanner(System.in);

    // ArrayList Ship
    private ArrayList<Ship> ships = new ArrayList<>();


    // Constructor
    public Map() {
    }


    // Printing map with empty squares [ ]
    public void printEmptyMap(String[][] map) {

        // Prints the text and the line underneath the text
        System.out.println("                      OCEAN MAP                      ");

        for (int line = 1; line < map.length; line++) {
            System.out.print("_____");
        }

        // Initialize 2D matrix map
        for (int y = 1; y < map.length; y++) {
            for (int x = 1; x < map.length; x++) {
                map[x][y] = " ";
            }
        }

        // New line to structure the [ ] with the coordinates on the side
        System.out.println();

        // Initialises the empty squares as the game board [ ]
        for (int column = 1; column < map.length; column++) {
            String square = "";
            for (int row = 1; row < map.length; row++) {
                square += " [" + map[row][column] + "] ";
            }
            // Print the [ ]
            System.out.print(square);

            // Print Y-coordinates on the side (column)
            System.out.println("|" + column + "Y" + "|");
        }

        // Add line to separate the ocean map from the coordinates below
        for (int line = 1; line < map.length; line++) {
            System.out.print( "_____");
        }
        // Add new line to structure coordinates correctly below
        System.out.println();

        // Add this to center the coordinates with the [ ] in the map
        System.out.print(" ");

        // Adding X-coordinates
        for (int k = 1; k < map.length; k++) {
            System.out.print( "" + k + "X" + " | ");
        }

        System.out.println("\n" + "\n" +
                "S: SUBMARINE" + " " +
                "D: DESTROYER" + " " +
                "C: CRUISER" + " " +
                "B: BATTLESHIP" +  " " +
                "c: CARRIER"
        );
        // Adding new line after the map prints
        System.out.println("\n");
    }

    // Adding AI:s coordinates to the map
    private void AIRandomCoordinatesToMap(String map[][], int size, ShipType shipType) {

        // Object of random to use for AI
        Random random = new Random();

        try {
            // X and Y to be withing range of 1-10 (same as grid)
            int randomX = random.nextInt(10 - 1) + 1;
            int randomY = random.nextInt(10 - 1) + 1;

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (randomY < 1 || randomY > 8 && shipType.equals(ShipType.SUBMARINE)) {
                randomY = random.nextInt(10 - 1) + 1;
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (randomY < 1 || randomY > 9 && shipType.equals(ShipType.DESTROYER)) {
                randomY = random.nextInt(10 - 1) + 1;
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (randomY < 1 || randomY > 7 && shipType.equals(ShipType.CRUISER)) {
                randomY = random.nextInt(10 - 1) + 1;
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (randomY < 1 || randomY > 6 && shipType.equals(ShipType.BATTLESHIP)) {
                randomY = random.nextInt(10 - 1) + 1;
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (randomY < 1 || randomY > 8 && shipType.equals(ShipType.CARRIER)) {
                randomY = random.nextInt(10 - 1) + 1;
            }

            // Prevent ship to be placed on another ship
            while (!map[randomX][randomY].equals(" ")) {
                for (int i = 0; i < size; i++) {
                    for (int y = randomY; y <= randomY; y++) {
                        y += i;
                        for (int x = randomX; x <= randomX; x++) {
                            if (!map[randomX][randomY].equals(" ")) {
                                randomX = random.nextInt(10 - 1) + 1;
                                randomY = random.nextInt(10 - 1) + 1;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            // Loop through the size and place it on the grid
            for (int i = 0; i < size; i++) {
                for (int y = randomY; y <= randomY; y++) {
                    y += i;
                    for (int x = randomX; x <= randomX; x++) {

                        switch (shipType) {
                            case SUBMARINE:
                                map[x][y] =  ShipType.SUBMARINE.getShipType();
                                Submarine submarine = new Submarine(x, y);
                                ships.add(submarine);
                                break;

                            case DESTROYER:
                                map[x][y] =ShipType.DESTROYER.getShipType();
                                Destroyer destroyer = new Destroyer(x, y);
                                ships.add(destroyer);
                                break;

                            case CRUISER:
                                map[x][y] = ShipType.CRUISER.getShipType();
                                Cruiser cruiser = new Cruiser(x, y);
                                ships.add(cruiser);
                                break;

                            case BATTLESHIP:
                                map[x][y] = ShipType.BATTLESHIP.getShipType();
                                BattleShip battleShip = new BattleShip(x, y);
                                ships.add(battleShip);
                                break;

                            case CARRIER:
                                map[x][y] =ShipType.CARRIER.getShipType();
                                Carrier carrier1 = new Carrier(x, y);
                                ships.add(carrier1);
                                break;


                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                    }
                }
            }

        }
        catch(Exception e){
            System.out.println("Sorry something went wrong. ");
        }
    }


    // Add coordinates and ships to map for players
    private void addCoordinateToMap(String map[][], int size, ShipType shipType) {

        try {
            System.out.println("Add X-coordinate for your " + shipType + " with size " + size);
            int xInput = input.nextInt();

            System.out.println("Add Y-coordinate for your " + shipType + " with size " + size);
            int yInput = input.nextInt();


            // To not be able to add outside of the grid (coordinates 1 - 10)
            while (xInput < 1 || xInput > 10) {
                System.out.println("Please Enter Valid X- Coordinate");
                xInput = input.nextInt();
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (yInput < 1 || yInput > 8 && shipType.equals(ShipType.SUBMARINE)) {
                System.out.println("Please Enter Valid Y-Coordinate");
                yInput = input.nextInt();
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (yInput < 1 || yInput > 9 && shipType.equals(ShipType.DESTROYER)) {
                System.out.println("Please Enter Valid Y-Coordinate");
                yInput = input.nextInt();
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (yInput < 1 || yInput > 7 && shipType.equals(ShipType.CRUISER)) {
                System.out.println("Please Enter Valid Y-Coordinate");
                yInput = input.nextInt();
            }

            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (yInput < 1 || yInput > 6 && shipType.equals(ShipType.BATTLESHIP)) {
                System.out.println("Please Enter Valid Y-Coordinate");
                yInput = input.nextInt();
            }


            // To make sure the ship is not placed outside of the grid (calculated on its length)
            while (yInput < 1 || yInput > 8 && shipType.equals(ShipType.CARRIER)) {
                System.out.println("Please Enter Valid Y-Coordinate");
                yInput = input.nextInt();
            }


            // Prevent ship to be placed on another ship
            while (!map[xInput][yInput].equals(" ")) {
                for (int i = 0; i < size; i++){
                    for (int y = yInput; y <= yInput; y++) {
                        y += i;
                        for (int x = xInput; x <= xInput; x++) {
                            if (map[x][y] != " ") {
                                System.out.println("Oops, you can't place a boat on another boat, mate!");
                                System.out.println("Add X-coordinate for your " + shipType + " with size " + size);
                                xInput = input.nextInt();

                                System.out.println("Add Y-coordinate for your " + shipType + " with size " + size);
                                yInput = input.nextInt();
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            // Loop through ship size and place it on the grid
            for (int i = 0; i < size; i++) {
                for (int y = yInput; y <= yInput; y++) {
                    y += i;
                    for (int x = xInput; x <= xInput; x++) {

                        switch (shipType) {
                            case SUBMARINE:
                                map[x][y] =ShipType.SUBMARINE.getShipType();
                                Submarine submarinePlayer = new Submarine(x, y);
                                ships.add(submarinePlayer);
                                if(i==1){
                                    System.out.println(submarinePlayer.toString());
                                }
                                break;

                            case DESTROYER:
                                map[x][y] =ShipType.DESTROYER.getShipType();
                                Destroyer destroyerPlayer = new Destroyer(x, y);
                                ships.add(destroyerPlayer);
                                if(i==1){
                                    System.out.println(destroyerPlayer.toString());
                                }
                                break;

                            case CRUISER:
                                map[x][y] =ShipType.CRUISER.getShipType();
                                Cruiser cruiserPlayer = new Cruiser(x, y);
                                ships.add(cruiserPlayer);
                                if(i==1){
                                    System.out.println(cruiserPlayer.toString());
                                }
                                break;

                            case BATTLESHIP:
                                map[x][y] = ShipType.BATTLESHIP.getShipType();
                                BattleShip battleShipPlayer = new BattleShip(x, y);
                                ships.add(battleShipPlayer);
                                if(i==1){
                                    System.out.println(battleShipPlayer.toString());
                                }
                                break;

                            case CARRIER:
                                map[x][y] = ShipType.CARRIER.getShipType();
                                Carrier carrier1Player = new Carrier(x, y);
                                ships.add(carrier1Player);
                                if(i==1){
                                    System.out.println(carrier1Player.toString());
                                }
                                break;


                            default:
                                System.out.println("Invalid");
                                break;
                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            e.getMessage();
        }
    }


    public void printPlayerMapShips (String[][] map, int size, ShipType shipType){

        // Adding coordinates from user input
        addCoordinateToMap(map, size, shipType);

        // Prints the text and the line underneath the text
        System.out.println("                      OCEAN MAP                      ");

        for (int line = 1; line <= map.length; line++) {
            System.out.print("_____");
        }

        // New line to structure the [ ] with the coordinates on the side
        System.out.println();

        // Initialises the empty squares as the game board [ ]
        for (int column = 1; column < map.length; column++) {
            String square = "";
            for (int row = 1; row < map.length; row++) {
                square += " [" + map[row][column] + "] ";
            }
            // Print the [ ]
            System.out.print(square);

            // Print Y-coordinates on the side (column)
            System.out.println("|" + column + "Y" + "|");
        }

        // Add line to separate the ocean map from the coordinates below
        for (int line = 1; line <= map.length; line++) {
            System.out.print("_____");
        }
        // Add new line to structure coordinates correctly below
        System.out.println();

        // Add this to center the coordinates with the [ ] in the map
        System.out.print(" ");

        // Adding X-coordinates
        for (int k = 1; k < map.length; k++) {
            System.out.print("" + k + "X" + " | ");
        }

        System.out.println("\n" + "\n" +
                "S: SUBMARINE" + " " +
                 "D: DESTROYER" + " " +
                "C: CRUISER" + " " +
                "B: BATTLESHIP" +  " " +
                 "c: CARRIER"
        );

        // Adding new line after the map prints
        System.out.println("\n");
    }


    public void printAIMapShips (String map[][],int size, ShipType shipType){

        // AI adds random coordinates
        AIRandomCoordinatesToMap(map, size, shipType);

        // Prints the text and the line underneath the text
        System.out.println("                      OCEAN MAP                      ");

        for (int line = 1; line <= map.length; line++) {
            System.out.print("_____");
        }

        // New line to structure the [ ] with the coordinates on the side
        System.out.println();

        // Initialises the empty squares as the game board [ ]
        for (int column = 1; column < map.length; column++) {
            String square = "";
            for (int row = 1; row < map.length; row++) {
                square += " [" + map[row][column] + "] ";
            }
            // Print the [ ]
            System.out.print(square);

            // Print Y-coordinates on the side (column)
            System.out.println("|" + column + "Y" + "|");
        }

        // Add line to separate the ocean map from the coordinates below
        for (int line = 1; line <= map.length; line++) {
            System.out.print("_____");
        }
        // Add new line to structure coordinates correctly below
        System.out.println();

        // Add this to center the coordinates with the [ ] in the map
        System.out.print(" ");

            /*// Adding X-coordinates
            for (int k = 1; k < map.length; k++) {
                System.out.print(ANSI_RED + "" + k + "X" + " | " + ANSI_RESET);
            }*/

        System.out.println("\n" + "\n" +
               "S: SUBMARINE" +  " " +
                "D: DESTROYER" +  " " +
                "C: CRUISER" + " " +
                 "B: BATTLESHIP" +  " " +
                "c: CARRIER"
        );

        // Adding new line after the map prints
        System.out.println("\n");
    }


    public void printBattle (String[][] map, int x, int y, String battle){

        // Prints the text and the line underneath the text
        System.out.println("                      OCEAN MAP                      ");

        for (int line = 1; line <= map.length; line++) {
            System.out.print("_____");
        }

        // New line to structure the [ ] with the coordinates on the side
        System.out.println();

        // Initialises the empty squares as the game board [ ]
        for (int column = 1; column < map.length; column++) {
            String square = "";
            for (int row = 1; row < map.length; row++) {
                square += " [" + map[row][column] + "] ";
            }
            // Print the [ ]
            System.out.print(square);

            // Print Y-coordinates on the side (column)
            System.out.println("|" + column + "Y" + "|");
        }

        // Add line to separate the ocean map from the coordinates below
        for (int line = 1; line <= map.length; line++) {
            System.out.print("_____");
        }
        // Add new line to structure coordinates correctly below
        System.out.println();

        // Add this to center the coordinates with the [ ] in the map
        System.out.print(" ");

        // Adding X-coordinates
        for (int k = 1; k < map.length; k++) {
            System.out.print("" + k + "X" + " | ");
        }

        System.out.println("\n" + "\n" +
                "HIT (*) "+ " " +
               "MISS (X) "  + " "
        );
        // Adding new line after the map prints
        System.out.println("\n");
    }

}


