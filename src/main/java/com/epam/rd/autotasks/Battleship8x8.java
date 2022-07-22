package com.epam.rd.autotasks;

public class Battleship8x8 {



    private final long ships;

    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {

        long currentShot;

        String Position = "HGFEDCBA";

        if (shot.equals("A1")) {
            currentShot = Long.MIN_VALUE;
        }
        else {
            int horizontal = Position.indexOf(shot.substring(0, 1));
            int vertical = (8 - Integer.parseInt(shot.substring(1, 2))) * 8;
            currentShot = (long) Math.pow(2, horizontal + vertical);
        }

        shots^=currentShot;

        if (ships > 0)
            return (ships^currentShot) < ships && !shot.equals("A1");
        else
            return shot.equals("A1") || (ships^currentShot) < ships;
    }

    public String state() {

        StringBuilder state = new StringBuilder(72);

        char[] shipsArray = String.format("%64s", Long.toBinaryString(ships))
                .replace(' ','0').toCharArray();

        char[] shotsArray = String.format("%64s", Long.toBinaryString(shots))
                .replace(' ','0').toCharArray();

        for (int i = 0; i < 64; i++) {

            if (i%8==0)
                state.append("\n");

            if (shipsArray[i] == '0' && shotsArray[i] == '0')
                state.append(".");
            else if (shipsArray[i] == '0' && shotsArray[i] == '1')
                state.append("×");
            else if (shipsArray[i] == '1' && shotsArray[i] == '0')
                state.append("☐");
            else
                state.append("☒");
        }

        return state.toString();
    }
}
