/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.isia;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author galvez
 */
public class Matriz {
    private final int[][] datos;

    public Matriz(int filas, int columnas, boolean inicializarAleatorio) {
        datos = new int[columnas][];
        for (int i = 0; i < columnas; i++) {
            datos[i] = new int[filas];
            Random rnd = new Random();
            if (inicializarAleatorio)
                for (int j = 0; j < filas; j++)
                    datos[i][j] = rnd.nextInt(100);
        }
    }

    public Matriz(Dimension d, boolean inicializarAleatorio) {
        this(d.height, d.width, inicializarAleatorio);
    }

    public Matriz(int filas, int columnas) {
        this(filas, columnas, false);
    }

    public Matriz(List<int[]> lista) throws DimensionesIncompatibles {
        this(lista.size(), lista.get(0).length);
        if (lista.size()!=lista.get(0).length)
            throw new DimensionesIncompatibles("Este tipo de constructor es solo para matrices cuadradas");
        for (int i = 0; i < lista.size(); i++) {
            datos[i] = lista.get(i);
        }
    }

    public Dimension getDimension() {
        return new Dimension(datos.length, datos[0].length);
    }

    public static Matriz sumarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles {
        if (!a.getDimension().equals(b.getDimension()))
            throw new DimensionesIncompatibles("La suma de matrices requiere matrices de las mismas dimensiones");
        int i, j, filasA, columnasA;
        filasA = a.getDimension().height;
        columnasA = a.getDimension().width;
        Matriz matrizResultante = new Matriz(filasA, columnasA, false);
        for (j = 0; j < filasA; j++) {
            for (i = 0; i < columnasA; i++) {
                matrizResultante.datos[i][j] += a.datos[i][j] + b.datos[i][j];
            }
        }
        return matrizResultante;
    }


    public static Matriz multiplicarDosMatrices(Matriz a, Matriz b) throws DimensionesIncompatibles {

        int filasA = a.getDimension().height;
        int filasB = b.getDimension().height;
        int columnasA = a.getDimension().width;
        int columnasB = b.getDimension().width;

        if (columnasA != filasB || filasA != columnasB)
            throw new DimensionesIncompatibles("Las dimensiones no son válidas para la multiplicación");

        int dimension = Math.max(filasA, columnasA);
        Matriz matrizResultante = new Matriz(dimension, dimension, false);
        for (int i = 0; i < columnasA; i++)
            for (int j = 0; j < filasB; j++)
                for (int k = 0; k < filasA; k++)
                    matrizResultante.datos[i][j] += a.datos[i][k] * b.datos[k][j];
        return matrizResultante;
    }

    public static Matriz transponerMatriz(Matriz origen) {

        int filasA = origen.getDimension().height;
        int columnasA = origen.getDimension().width;
        Matriz matrizResultante = new Matriz(columnasA, filasA);

        for (int i = 0; i < columnasA; i++)
            for (int j = 0; j < filasA; j++)
                matrizResultante.datos[j][i] = origen.datos[i][j];

        return matrizResultante;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("[\n");
        for (int i = 0; i < getDimension().width; i++) {
            ret.append("(");
            for (int j = 0; j < getDimension().height; j++) {
                ret.append(String.format("%3d", datos[i][j]));
                if (j != getDimension().height - 1) ret.append(", ");
            }
            ret.append(")");
            if (i != getDimension().width - 1) ret.append(",");
            ret.append("\n");
        }
        ret.append("]\n");
        return ret.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matriz matriz = (Matriz) o;
        if (matriz.datos.length != datos.length || matriz.datos[0].length != datos[0].length) return false;

        for (int i = 0; i<datos.length; i++){
            if(!Arrays.equals(datos[i], matriz.datos[i]))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(datos);
    }
}
