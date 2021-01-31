package com.example.juego;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView[][] cellList;
    TextView c1;
    TextView c2;
    TextView c3;
    TextView c4;
    TextView c5;
    TextView c6;
    TextView c7;
    TextView c8;
    TextView c9;
    TextView c10;
    TextView c11;
    TextView c12;
    TextView c13;
    TextView c14;
    TextView c15;
    TextView c16;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCells();

        GameUtils.firstTimeGeneration(cellList);

        GridLayout gridLayout = findViewById(R.id.gridLayout);


        gridLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                GameUtils.clearColors(cellList);

                int row;
                int column = 3;
                boolean stop = false;
                do {
                    for (row = 1; row < cellList.length; row++) {
                        if (column >= 0 && cellList[row][column].getText().equals("0")) {
                            if (row == cellList.length - 1) {
                                column--;
                            }
                            continue;
                        } else if (column >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int cellAbove = Integer.parseInt((String) cellList[row - 1][column].getText());
                            int aboveY = row - 1;

                            while (cellAbove == 0) {
                                cellList[row][column].setText("0");
                                cellList[aboveY][column].setText(String.valueOf(originalCell));
                                if (aboveY - 1 > 0) {
                                    cellAbove = Integer.parseInt((String) cellList[aboveY - 1][column].getText());
                                } else {
                                    cellAbove = Integer.parseInt((String) cellList[0][column].getText());
                                    cellList[row][column].setText("0");
                                }
                                row--;
                                aboveY--;
                            }
                        }
                        if (row == 3) {
                            column--;
                            if (column < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);
                stop = false;
                column = 3;
                do {
                    for (row = 1; row < cellList.length; row++) {
                        int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                        int cellAbove = Integer.parseInt((String) cellList[row - 1][column].getText());
                        int aboveY = row - 1;
                        if (originalCell == cellAbove) {
                            cellList[aboveY][column].setText(String.valueOf(originalCell + cellAbove));
                            cellList[row][column].setText("0");
                        }
                        if (row == 3) {
                            column--;
                            if (column < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                column = 3;
                stop = false;
                do {
                    for (row = 1; row < cellList.length; row++) {
                        if (column >= 0 && cellList[row][column].getText().equals("0")) {
                            if (row == cellList.length - 1) {
                                column--;
                            }
                            continue;
                        } else if (column >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int cellAbove = Integer.parseInt((String) cellList[row - 1][column].getText());
                            int aboveY = row - 1;
                            while (cellAbove == 0) {
                                cellList[row][column].setText("0");
                                cellList[aboveY][column].setText(String.valueOf(originalCell));
                                if (aboveY - 1 > 0) {
                                    cellAbove = Integer.parseInt((String) cellList[aboveY - 1][column].getText());
                                } else {
                                    cellAbove = Integer.parseInt((String) cellList[0][column].getText());
                                    cellList[row][column].setText("0");
                                }
                                row--;
                                aboveY--;
                            }
                        }

                        if (row == 3) {
                            column--;
                            if (column < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                GameUtils.generateNumber(cellList);
                Log.d("UP", "UP");
            }

            public void onSwipeRight() {
                GameUtils.clearColors(cellList);

                int column;
                int row = 3;
                boolean stop = false;
                do {
                    for (column = 2; column >= 0; column--) {
                        if (row >= 0 && cellList[row][column].getText().equals("0")) {
                            if (column == 0) {
                                row--;
                            }
                            continue;
                        } else if (row >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int rightCell = Integer.parseInt((String) cellList[row][column + 1].getText());
                            int rightX = column + 1;

                            while (rightCell == 0) {
                                cellList[row][column].setText("0");
                                cellList[row][rightX].setText(String.valueOf(originalCell));
                                if (rightX + 1 < 3) {
                                    rightCell = Integer.parseInt((String) cellList[row][rightX + 1].getText());
                                } else {
                                    rightCell = Integer.parseInt((String) cellList[row][3].getText());
                                    cellList[row][column].setText("0");
                                }
                                column++;
                                rightX++;
                            }
                        }
                        if (column == 0) {
                            row--;
                            if (row < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                stop = false;
                row = 3;
                do {
                    for (column = 2; column >= 0; column--) {
                        int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                        int rightCell = Integer.parseInt((String) cellList[row][column + 1].getText());
                        int rightX = column + 1;
                        if (originalCell == rightCell) {
                            cellList[row][rightX].setText(String.valueOf(originalCell + rightCell));
                            cellList[row][column].setText("0");
                        }
                        if (column == 0) {
                            row--;
                            if (row < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                stop = false;
                row = 3;
                do {
                    for (column = 2; column >= 0; column--) {
                        if (row >= 0 && cellList[row][column].getText().equals("0")) {
                            if (column == 0) {
                                row--;
                            }
                            continue;
                        } else if (row >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int rightCell = Integer.parseInt((String) cellList[row][column + 1].getText());
                            int rightX = column + 1;

                            while (rightCell == 0) {
                                cellList[row][column].setText("0");
                                cellList[row][rightX].setText(String.valueOf(originalCell));
                                if (rightX + 1 < 3) {
                                    rightCell = Integer.parseInt((String) cellList[row][rightX + 1].getText());
                                } else {
                                    rightCell = Integer.parseInt((String) cellList[row][3].getText());
                                    cellList[row][column].setText("0");
                                }
                                column++;
                                rightX++;
                            }
                        }
                        if (column == 0) {
                            row--;
                            if (row < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                GameUtils.generateNumber(cellList);
                Log.d("RIGHT", "RIGHT");
            }

            public void onSwipeLeft() {
                GameUtils.clearColors(cellList);

                int column;
                int row = 3;
                boolean stop = false;
                do {
                    for (column = 1; column <= 3; column++) {
                        if (row >= 0 && cellList[row][column].getText().equals("0")) {
                            if (column == 3) {
                                row--;
                            }
                            continue;
                        } else if (row >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int leftCell = Integer.parseInt((String) cellList[row][column - 1].getText());
                            int leftX = column - 1;

                            while (leftCell == 0) {
                                cellList[row][column].setText("0");
                                cellList[row][leftX].setText(String.valueOf(originalCell));
                                if (leftX - 1 > 0) {
                                    leftCell = Integer.parseInt((String) cellList[row][leftX - 1].getText());
                                } else {
                                    leftCell = Integer.parseInt((String) cellList[row][0].getText());
                                    cellList[row][column].setText("0");
                                }
                                column--;
                                leftX--;
                            }
                        }
                        if (column == 3) {
                            row--;
                            if (row < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                stop = false;
                row = 3;
                do {
                    for (column = 1; column <=3; column++) {
                        int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                        int leftCell = Integer.parseInt((String) cellList[row][column - 1].getText());
                        int leftX = column - 1;
                        if (originalCell == leftCell) {
                            cellList[row][leftX].setText(String.valueOf(originalCell + leftCell));
                            cellList[row][column].setText("0");
                        }
                        if (column == 3) {
                            row--;
                            if (row < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                do {
                    for (column = 1; column <= 3; column++) {
                        if (row >= 0 && cellList[row][column].getText().equals("0")) {
                            if (column == 3) {
                                row--;
                            }
                            continue;
                        } else if (row >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int leftCell = Integer.parseInt((String) cellList[row][column - 1].getText());
                            int leftX = column - 1;

                            while (leftCell == 0) {
                                cellList[row][column].setText("0");
                                cellList[row][leftX].setText(String.valueOf(originalCell));
                                if (leftX - 1 > 0) {
                                    leftCell = Integer.parseInt((String) cellList[row][leftX - 1].getText());
                                } else {
                                    leftCell = Integer.parseInt((String) cellList[row][0].getText());
                                    cellList[row][column].setText("0");
                                }
                                column--;
                                leftX--;
                            }
                        }
                        if (column == 3) {
                            row--;
                            if (row < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                GameUtils.generateNumber(cellList);
                Log.d("LEFT", "LEFT");
            }

            public void onSwipeBottom() {
                GameUtils.clearColors(cellList);

                int row;
                int column = 3;
                boolean stop = false;
                do {
                    for (row = 2; row >= 0; row--) {
                        if (column >= 0 && cellList[row][column].getText().equals("0")) {
                            if (row == 0) {
                                column--;
                            }
                            continue;
                        } else if (column >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int belowCell = Integer.parseInt((String) cellList[row + 1][column].getText());
                            int belowY = row + 1;

                            while (belowCell == 0) {
                                cellList[row][column].setText("0");
                                cellList[belowY][column].setText(String.valueOf(originalCell));
                                if (belowY + 1 < 3) {
                                    belowCell = Integer.parseInt((String) cellList[belowY + 1][column].getText());
                                } else {
                                    belowCell = Integer.parseInt((String) cellList[3][column].getText());
                                    cellList[row][column].setText("0");
                                }
                                row++;
                                belowY++;
                            }
                        }
                        if (row == 0) {
                            column--;
                            if (column < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                stop = false;
                column = 3;
                do {
                    for (row = 2; row >= 0; row--) {
                        int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                        int belowCell = Integer.parseInt((String) cellList[row + 1][column].getText());
                        int belowY = row + 1;
                        if (originalCell == belowCell) {
                            cellList[belowY][column].setText(String.valueOf(originalCell + belowCell));
                            cellList[row][column].setText("0");
                        }
                        if (row == 0) {
                            column--;
                            if (column < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                stop = false;
                column = 3;
                do {
                    for (row = 2; row >= 0; row--) {
                        if (column >= 0 && cellList[row][column].getText().equals("0")) {
                            if (row == 0) {
                                column--;
                            }
                            continue;
                        } else if (column >= 0) {
                            int originalCell = Integer.parseInt((String) cellList[row][column].getText());
                            int belowCell = Integer.parseInt((String) cellList[row + 1][column].getText());
                            int belowY = row + 1;

                            while (belowCell == 0) {
                                cellList[row][column].setText("0");
                                cellList[belowY][column].setText(String.valueOf(originalCell));
                                if (belowY + 1 < 3) {
                                    belowCell = Integer.parseInt((String) cellList[belowY + 1][column].getText());
                                } else {
                                    belowCell = Integer.parseInt((String) cellList[3][column].getText());
                                    cellList[row][column].setText("0");
                                }
                                row++;
                                belowY++;
                            }
                        }
                        if (row == 0) {
                            column--;
                            if (column < 0) {
                                stop = true;
                            }
                        }
                    }
                } while (!stop);

                GameUtils.generateNumber(cellList);
                Log.d("DOWN", "DOWN");
            }
        });
    }

    private void createCells() {
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);
        c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);
        c11 = findViewById(R.id.c11);
        c12 = findViewById(R.id.c12);
        c13 = findViewById(R.id.c13);
        c14 = findViewById(R.id.c14);
        c15 = findViewById(R.id.c15);
        c16 = findViewById(R.id.c16);

        cellList = new TextView[4][4];
        cellList[0][0] = c1;
        cellList[0][1] = c2;
        cellList[0][2] = c3;
        cellList[0][3] = c4;
        cellList[1][0] = c5;
        cellList[1][1] = c6;
        cellList[1][2] = c7;
        cellList[1][3] = c8;
        cellList[2][0] = c9;
        cellList[2][1] = c10;
        cellList[2][2] = c11;
        cellList[2][3] = c12;
        cellList[3][0] = c13;
        cellList[3][1] = c14;
        cellList[3][2] = c15;
        cellList[3][3] = c16;
    }

}