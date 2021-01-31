package com.example.juego;

import android.graphics.Color;
import android.widget.TextView;

public class GameUtils {

    public static void clearColors(TextView[][] cellList) {
        for (int i = 0; i < cellList.length; i++) {
            for (int j = 0; j < cellList[0].length; j++) {
                cellList[i][j].setTextColor(Color.BLACK);
            }
        }
    }

    public static void generateNumber(TextView[][] cellList) {

        int randomNumberY;
        int randomNumberX;

        do {
            randomNumberY = (int) Math.floor(Math.random() * 4);
            randomNumberX = (int) Math.floor(Math.random() * 4);
        } while (!cellList[randomNumberY][randomNumberX].getText().equals("0"));

        cellList[randomNumberY][randomNumberX].setText("2");
        cellList[randomNumberY][randomNumberX].setTextColor(Color.BLUE);



    }

    public static void firstTimeGeneration(TextView[][] cellList) {

        int randomNumberY;
        int randomNumberX;

        for (int i = 0; i < cellList.length; i++) {
            for (int j = 0; j < cellList[0].length; j++) {
                cellList[i][j].setText("0");
            }
        }

        for (int i = 0; i < 2; i++) {
            do {
                randomNumberY = (int) Math.floor(Math.random() * 4);
                randomNumberX = (int) Math.floor(Math.random() * 4);
            } while (!cellList[randomNumberY][randomNumberX].getText().equals("0"));
            cellList[randomNumberY][randomNumberX].setText("2");
            cellList[randomNumberY][randomNumberX].setTextColor(Color.BLUE);
        }

        /*
        cellList[0][0].setText("2");
        cellList[0][1].setText("2");
        cellList[0][2].setText("2");
        cellList[0][3].setText("0");

        cellList[1][0].setText("0");
        cellList[1][1].setText("2");
        cellList[1][2].setText("2");
        cellList[1][3].setText("0");

        cellList[2][0].setText("2");
        cellList[2][1].setText("0");
        cellList[2][2].setText("0");
        cellList[2][3].setText("0");

        cellList[3][0].setText("2");
        cellList[3][1].setText("0");
        cellList[3][2].setText("0");
        cellList[3][3].setText("2");
         */
    }
}
