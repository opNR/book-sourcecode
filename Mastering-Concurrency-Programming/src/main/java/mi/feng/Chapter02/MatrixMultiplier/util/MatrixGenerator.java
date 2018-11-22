package mi.feng.Chapter02.MatrixMultiplier.util;

import java.util.Random;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/16 14:24
 * @Description:
 */
public class MatrixGenerator {

    public static double[][] generate(int rows, int columns){
        double[][] ret = new double[rows][columns];

        Random random = new Random();

        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                ret[i][j] = random.nextDouble()*10;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        double[][] demoArr1 ={{1,2,3},{4,5,6}};
        double[][] demoArr2 ={{1,4},{2,5},{3,6}};

//        double[][] arr = generate(2,3);
        double[][] arr = mult(demoArr1, demoArr2);

        for (int i = 0; i < arr.length; i++) {
            System.out.println("--------------------");
            for (int j = 0; j < arr[i].length; j++){
                System.out.println(arr[i][j]);
            }
        }

    }

    public static double[][] mult(double[][] matrix1, double[][] matrix2){
        int rows = matrix1.length;
        int columns1 = matrix1[0].length;

        int columns = matrix2[0].length;
        double[][] ret = new double[rows][columns];

        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                ret[i][j] = 0;
                for (int k=0; k<columns1; k++){
                    ret[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return ret;
    }

}
