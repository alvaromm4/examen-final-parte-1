package examen_final1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MatrixCalculatorTest {
    private final String matrizAFile = "matriz_a.txt";
    private final String matrizCFile = "matrizc.txt";
    private final String matrizBFile = "matrizb.txt";

    private final int[][] matrizA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    private final int[][] matrizC = { { 2, 4, 6 }, { 8, 10, 12 }, { 14, 16, 18 } };
    private final int[][] matrizBExpected = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    @Before
    public void setup() throws IOException {
        escribirMatrizEnArchivo(matrizA, matrizAFile);
        escribirMatrizEnArchivo(matrizC, matrizCFile);
    }

    @Test
    public void testCalcularMatrizB() throws IOException {
        MatrixCalculator.main(null);

        int[][] matrizBActual = leerMatrizDesdeArchivo(matrizBFile);

        assertArrayEquals(matrizBExpected, matrizBActual);
    }

    private int[][] leerMatrizDesdeArchivo(String rutaArchivo) throws IOException {
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

    private void escribirMatrizEnArchivo(int[][] matriz, String rutaArchivo) throws IOException {
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
