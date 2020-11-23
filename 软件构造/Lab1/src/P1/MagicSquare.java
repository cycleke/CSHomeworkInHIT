package P1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MagicSquare {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Part 1: test isLegalMagicSquare
		System.out.print("Part 1: Please input the txt directory(default src/P1/txt):");
		String tempString = scanner.nextLine();
		String txtPath = tempString.isEmpty() ? "src/P1/txt" : tempString;
		for (int i = 1; i <= 6; ++i) {
			String fileName = String.format("%s/%d.txt", txtPath, i);
			System.out.println("Testing file " + fileName + ": " + isLegalMagicSquare(fileName));
		}

		// Part 2: test generateMagicSquare
		System.out.println("Part 2: Please input the size of the square to generate:");
		int n = scanner.nextInt();
		if (generateMagicSquare(n)) {
			System.out.println("Square generated successfully, written to src/P1/txt/6.txt");
		} else {
			System.out.println("Fail to generate");
		}

		scanner.close();
	}

	/**
	 * Check whether a file contains a legal magic square
	 *
	 * @param fileName path to the file
	 * @return true if matrix is legal magic square
	 */
	public static boolean isLegalMagicSquare(String fileName) {
		try (InputStream inputStream = new FileInputStream(fileName); Scanner scanner = new Scanner(inputStream);) {

			List<int[]> matrix = new ArrayList<>();

			while (scanner.hasNext()) {
				String myLine = scanner.nextLine().trim(); // 读取每一行
				String[] numberStrings = myLine.split("\t"); // 通过制表符分割
				if (!matrix.isEmpty() && (matrix.get(0).length != numberStrings.length)) {
					// 错误：有两行的数字个数不同，读入并非矩阵
					System.err.println("!ERROR: The input file:" + fileName + " is not a matrix.");
					return false;
				}

				for (String numberString : numberStrings)
					for (int i = 0; i < numberString.length(); ++i)
						if (!Character.isDigit(numberString.charAt(i))) {
							// 错误：矩阵中的某些数字并非正整数、数字之间并非使用\t 分割
							System.err.println("!ERROR: The input file:" + fileName + " contains invalid charactor.");
							return false;
						}

				// 转换为int
				int[] numbers = new int[numberStrings.length];
				for (int i = 0; i < numberStrings.length; ++i)
					numbers[i] = Integer.parseInt(numberStrings[i]);
				matrix.add(numbers);
			}

			if (matrix.isEmpty()) {
				// 错误：读入文件为空矩阵
				System.err.println("!ERROR: The input file:" + fileName + " is empty.");
				return false;
			}

			int n = matrix.size();
			if (matrix.get(0).length != n) {
				// 错误：行列数不同
				System.err.println("!ERROR: In the input file:" + fileName + ", the matrix has different rows and columns.");
				return false;
			}

			// 判断数字是否为1~n*n
			int min_bound = 1, max_bound = n * n;
			boolean[] marked = new boolean[n * n];
			Arrays.fill(marked, false);

			for (int i = 0; i < n; ++i) {
				for (int x : matrix.get(i)) {
					if (x < min_bound || x > max_bound) {
						// 错误：有数字不在1~n*n
						System.err.println("!ERROR: In the input file:" + fileName + ", some number is out of range.");
						return false;
					}
					if (marked[x - 1]) {
						// 错误：数字重复
						System.err.println("!ERROR: In the input file:" + fileName + ", there are two same numbers.");
						return false;
					}
					marked[x - 1] = true;
				}
			}

			// 判断行、列、对角线的和是否相等
			int sum = 0;
			for (int x : matrix.get(0))
				sum += x;

			// 检查行之和
			for (int i = 1; i < n; ++i) {
				int row = 0;
				for (int x : matrix.get(i))
					row += x;
				if (row != sum)
					return false;
			}

			// 检查列之和
			int[] cols = new int[n];
			for (int i = 0; i < n; ++i) {
				int[] arr = matrix.get(i);
				for (int j = 0; j < n; ++j)
					cols[j] += arr[j];
			}
			for (int x : cols)
				if (x != sum)
					return false;

			int diagonal1 = 0, diagonal2 = 0;
			for (int i = 0; i < n; ++i) {
				diagonal1 += matrix.get(i)[i];
				diagonal2 += matrix.get(i)[n - 1 - i];
			}
			if (diagonal1 != sum || diagonal2 != sum)
				return false;

			return true;
		} catch (FileNotFoundException e) {
			System.err.println("!ERROR: The input file:" + fileName + " not found.");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Generate a magic square with order n
	 *
	 * @param n order of matrix, must be positive odd integer
	 * @return true if generate successfully
	 */
	public static boolean generateMagicSquare(int n) {
		if (n < 0) {
			// 特判n为负数情况
			System.err.println("!Error: n is negative.");
			return false;
		}
		if (n % 2 == 0) {
			// 特判n为偶数
			System.err.println("!Error: n is odd.");
			return false;
		}

		// 从首行行中间开始放数字
		int[][] magic = new int[n][n];
		int row = 0, col = n / 2, square = n * n;

		for (int i = 1; i <= square; ++i) {
			magic[row][col] = i; // 为magic赋值
			if (i % n == 0) {
				++row; // 右上方位置已经赋值，进入下一行
			} else {
				// 沿斜对角线往右上方走
				row = (row == 0) ? n - 1 : row - 1;
				col = (col == n - 1) ? 0 : col + 1;
			}
		}

		// 将生成的 Magic Square 写入 6.txt
		String filePath = "src/P1/txt/6.txt";
		try {
			// 创建文件
			File writename = new File(filePath);
			writename.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try (// 创建输出流
				 OutputStream outputStream = new FileOutputStream(filePath);
				 PrintWriter printWriter = new PrintWriter(outputStream);) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					printWriter.print(magic[i][j] + "\t");
				}
				printWriter.println();
			}
			printWriter.close();
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("Error: Unable to open txt/6.txt.");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
