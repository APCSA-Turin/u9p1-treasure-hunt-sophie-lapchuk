package com.example.project;

public class Testing {
    public static void main(String[] args) {

        int size = 10;
        Grid grid = new Grid(size);
        Player player = new Player(0, 0);
        Enemy enemy = new Enemy(5, 5);
        Enemy enemy2 = new Enemy(7, 8);
        Treasure treasure = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(1, 7);
        Treasure trophy = new Trophy(9, 9);

        boolean allValidTestsPassed = true;

        System.out.println("Testing Player at initial position (0, 0) - Grid [9][0]:");
        boolean canMoveLeftFromOrigin = player.isValid(size, "a");
        System.out.println("Can move left: " + canMoveLeftFromOrigin + " (Expected: false)");
        if (canMoveLeftFromOrigin) {
            System.out.println("ERROR: Player should not be able to move left at [9][0]");
            allValidTestsPassed = false;
        }

        boolean canMoveUpFromOrigin = player.isValid(size, "w");
        System.out.println("Can move up: " + canMoveUpFromOrigin + " (Expected: true)");
        if (!canMoveUpFromOrigin) {
            System.out.println("ERROR: Player should be able to move up at [9][0]");
            allValidTestsPassed = false;
        }

        boolean canMoveDownFromOrigin = player.isValid(size, "s");
        System.out.println("Can move down: " + canMoveDownFromOrigin + " (Expected: false)");
        if (canMoveDownFromOrigin) {
            System.out.println("ERROR: Player should not be able to move down at [9][0]");
            allValidTestsPassed = false;
        }

        boolean canMoveRightFromOrigin = player.isValid(size, "d");
        System.out.println("Can move right: " + canMoveRightFromOrigin + " (Expected: true)");
        if (!canMoveRightFromOrigin) {
            System.out.println("ERROR: Player should be able to move right at [9][0]");
            allValidTestsPassed = false;
        }

        player.setX(9);
        player.setY(9);
        System.out.println("\nTesting Player at position (9, 9) - Grid [0][9]:");
        boolean canMoveLeftFromCorner = player.isValid(size, "a");
        System.out.println("Can move left: " + canMoveLeftFromCorner + " (Expected: true)");
        if (!canMoveLeftFromCorner) {
            System.out.println("ERROR: Player should be able to move left at [0][9]");
            allValidTestsPassed = false;
        }

        boolean canMoveUpFromCorner = player.isValid(size, "w");
        System.out.println("Can move up: " + canMoveUpFromCorner + " (Expected: false)");
        if (canMoveUpFromCorner) {
            System.out.println("ERROR: Player should not be able to move up at [0][9]");
            allValidTestsPassed = false;
        }

        boolean canMoveDownFromCorner = player.isValid(size, "s");
        System.out.println("Can move down: " + canMoveDownFromCorner + " (Expected: true)");
        if (!canMoveDownFromCorner) {
            System.out.println("ERROR: Player should be able to move down at [0][9]");
            allValidTestsPassed = false;
        }

        boolean canMoveRightFromCorner = player.isValid(size, "d");
        System.out.println("Can move right: " + canMoveRightFromCorner + " (Expected: false)");
        if (canMoveRightFromCorner) {
            System.out.println("ERROR: Player should not be able to move right at [0][9]");
            allValidTestsPassed = false;
        }

        System.out.println("\nAll isValid tests in main method completed.");
        if (allValidTestsPassed) {
            System.out.println("All isValid tests passed successfully.");
        } else {
            System.out.println("Some isValid tests failed. See 'ERROR' messages above.");
        }
    }


    
}