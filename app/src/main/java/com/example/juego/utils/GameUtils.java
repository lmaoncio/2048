package com.example.juego.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.example.juego.R;


public class GameUtils {
    public static void setCellListColors(TextView[][] cellList) {
        for (int i = 0; i < cellList.length; i++) {
            for (int j = 0; j < cellList[0].length; j++) {
                if (cellList[i][j].getText().equals("2")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ffcdd2"));
                } else if (cellList[i][j].getText().equals("4")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ef9a9a"));
                } else if (cellList[i][j].getText().equals("8")) {
                    cellList[i][j].setTextColor(Color.parseColor("#e57373"));
                } else if (cellList[i][j].getText().equals("16")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ef5350"));
                } else if (cellList[i][j].getText().equals("32")) {
                    cellList[i][j].setTextColor(Color.parseColor("#f06292"));
                } else if (cellList[i][j].getText().equals("64")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ec407a"));
                } else if (cellList[i][j].getText().equals("128")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ffe082"));
                } else if (cellList[i][j].getText().equals("256")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ffe082"));
                } else if (cellList[i][j].getText().equals("512")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ffe082"));
                } else if (cellList[i][j].getText().equals("1024")) {
                    cellList[i][j].setTextColor(Color.parseColor("#ffe082"));
                } else if (cellList[i][j].getText().equals("2048")) {
                    cellList[i][j].setTextColor(Color.parseColor("#7986cc"));
                }
            }
        }
    }

    public static void generateNumber(TextView[][] cellList) {

        int randomNumberY;
        int randomNumberX;

        do {
            randomNumberY = (int) Math.floor(Math.random() * 4);
            randomNumberX = (int) Math.floor(Math.random() * 4);
        } while (!cellList[randomNumberY][randomNumberX].getText().equals(""));

        cellList[randomNumberY][randomNumberX].setText("2");
        startAnimation(cellList[randomNumberY][randomNumberX]);
        setCellListColors(cellList);

    }

    public static void startAnimation(TextView textView) {
        Animation rotate = AnimationUtils.loadAnimation(textView.getContext(), R.anim.rotate_animation);
        textView.startAnimation(rotate);
    }

}
