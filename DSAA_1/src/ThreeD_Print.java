import java.util.Scanner;

public class ThreeD_Print {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[][] abc = new int[T][3];
        for (int i = 0; i < T; i++) {
            abc[i][0] = in.nextInt();
            abc[i][1] = in.nextInt();
            abc[i][2] = in.nextInt();
//            int a=abc[i][0];
//            int b=abc[i][1];
//            int c=abc[i][2];
        }
        int[][][] graphs = new int[T][][];
        for (int i = 0; i < T; i++) {
            graphs[i] = new int[2 * abc[i][1] + 2 * abc[i][2] + 1][2 * abc[i][0] + 2 * abc[i][1] + 1];

            for (int j = 0; j < 2 * abc[i][1]; j++) {//打印上面
                for (int k = 0; k < 2 * abc[i][0] + 2 * abc[i][1] + 1 - j; k++) {
                    if (j + k < 2 * abc[i][1]) {
                        graphs[i][j][k] = 5;
                    } else {
                        if (j % 2 == 0) {
                            if (k % 2 == 0)
                                graphs[i][j][k] = 1;
                            else graphs[i][j][k] = 2;
                        } else {
                            if (k % 2 == 0)
                                graphs[i][j][k] = 5;
                            else graphs[i][j][k] = 3;
                        }
                    }
                }
            }

            for (int j = 2 * abc[i][1]; j < 2 * abc[i][1] + 2 * abc[i][2] + 1; j++) {//打印正面
                for (int k = 0; k < 2 * abc[i][0] + 1; k++) {
                    if (j % 2 == 0) {
                        if (k % 2 == 0)
                            graphs[i][j][k] = 1;
                        else graphs[i][j][k] = 2;
                    } else {
                        if (k % 2 == 0)
                            graphs[i][j][k] = 4;
                        else graphs[i][j][k] = 5;
                    }
                }
            }

            for (int j = 0; j < 2 * abc[i][1] + 2 * abc[i][2] + 1; j++) {//打印侧面
                for (int k = 2 * abc[i][0] + 1; k < 2 * abc[i][0] + 2 * abc[i][1] + 1; k++) {
                    if (graphs[i][j][k] ==0){
                        if (j+k>2 * abc[i][0] + 2 * abc[i][2] + 2*abc[i][1])
                            graphs[i][j][k] = 5;
                        else {
                            if (j%2==0){
                                if (k%2==0)
                                    graphs[i][j][k] = 1;
                                else graphs[i][j][k] = 5;
                            }else {
                                if (k%2==0)
                                    graphs[i][j][k] = 4;
                                else graphs[i][j][k] = 3;
                            }
                        }
                    }
                }
            }
        }

            for (int i = 0; i < T; i++) {
                for (int j = 0; j < 2 * abc[i][1] + 2 * abc[i][2] + 1; j++) {
                    for (int k = 0; k < 2 * abc[i][0] + 2 * abc[i][1] + 1; k++) {
                        if (graphs[i][j][k] == 5)
                            System.out.print(".");
                        if (graphs[i][j][k] == 1)
                            System.out.print("+");
                        if (graphs[i][j][k] == 2)
                            System.out.print("-");
                        if (graphs[i][j][k] == 3)
                            System.out.print("/");
                        if (graphs[i][j][k] == 4)
                            System.out.print("|");
                    }
                    System.out.println();
                }
            }
        }
    }
