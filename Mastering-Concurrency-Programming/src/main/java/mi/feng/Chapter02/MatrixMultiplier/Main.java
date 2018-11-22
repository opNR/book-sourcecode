package mi.feng.Chapter02.MatrixMultiplier;

import mi.feng.Chapter02.MatrixMultiplier.parallel.group.ParallelGroupMultiplier;
import mi.feng.Chapter02.MatrixMultiplier.parallel.individual.ParallelIndividualMultiplier;
import mi.feng.Chapter02.MatrixMultiplier.parallel.row.ParallelRowMultiplier;
import mi.feng.Chapter02.MatrixMultiplier.serial.SerialMultiplier;
import mi.feng.Chapter02.MatrixMultiplier.util.MatrixGenerator;

import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/16 15:00
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];

        Date start = new Date();

        // 串行版本     1000:10177, 2000:139771
        SerialMultiplier.multiply(matrix1, matrix2, result);

        //并行版本1: 每个元素一个线程   1000:74922, 2000:312695
        ParallelIndividualMultiplier.multiply(matrix1, matrix2, result);

        //并行版本2: 每行一个线程     1000:3557, 2000:40759
        ParallelRowMultiplier.multiply(matrix1, matrix2, result);

        //并行版本3: 线程数量有处理器决定     1000:2796, 2000:35737
        ParallelGroupMultiplier.multiply(matrix1, matrix2, result);

        Date end = new Date();
        System.out.printf("Serial: %d%n", end.getTime() - start.getTime());
    }
}
