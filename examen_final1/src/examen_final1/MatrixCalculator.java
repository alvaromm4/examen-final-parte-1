package examen_final1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixCalculator {
    public static void main(String[] args) {
        try {
            int[][] matrizA = leerMatrizDesdeArchivo("matriz_a.txt");
            int[][] matrizC = leerMatrizDesdeArchivo("matrizc.txt");

            int[][] matrizB = calcularMatrizB(matrizA, matrizC);

            escribirMatrizEnArchivo(matrizB, "matrizb.txt");

            System.out.println("Matriz B calculada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al leer o escribir en archivos: " + e.getMessage());
        }
    }

    private static int[][] leerMatrizDesdeArchivo(String rutaArchivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int filas = 0;
            int columnas = 0;

            // Contar el número de filas y columnas en la matriz
            while ((linea = reader.readLine()) != null) {
                filas++;
                String[] valores = linea.split("\\s+");
                columnas = valores.length;
            }

            int[][] matriz = new int[filas][columnas];

            // Leer los valores de la matriz desde el archivo
            try (BufferedReader matrixReader = new BufferedReader(new FileReader(rutaArchivo))) {
                String lineaFila;
                int fila = 0;

                while ((lineaFila = matrixReader.readLine()) != null) {
                    String[] valores = lineaFila.split("\\s+");
                    for (int columna = 0; columna < columnas; columna++) {
                        matriz[fila][columna] = Integer.parseInt(valores[columna]);
                    }
                    fila++;
                }
            }

            return matriz;
        }
    }

    static int[][] calcularMatrizB(int[][] matrizA, int[][] matrizC) {
        int filas = matrizA.length;
        int columnas = matrizA[0].length;
        int[][] matrizB = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizB[i][j] = matrizC[i][j] - matrizA[i][j];
            }
        }

        return matrizB;
    }

    private static void escribirMatrizEnArchivo(int[][] matriz, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            int filas = matriz.length;
            int columnas = matriz[0].length;

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    writer.write(matriz[i][j] + " ");
                }
                writer.newLine();
            }
        }
    }
}
