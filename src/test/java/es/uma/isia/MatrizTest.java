package es.uma.isia;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrizTest {


    @Test
    void getDimensionTest() {
        Matriz m = new Matriz(3, 2, true);
        Assertions.assertAll(() -> assertEquals(2, m.getDimension().width),
                () -> assertEquals(3, m.getDimension().height));
    }

    @Test
    void sumarDosMatricesTest() {
        Matriz m1 = new Matriz(3, 3, false);
        Matriz m2 = new Matriz(3, 3, false);
    }

    @Test
    void multiplicarDosMatricesDimensionesIncompatiblesTest() {
        Matriz m1 = new Matriz(3, 2, false);
        Matriz m2 = new Matriz(3, 3, false);
        Exception exception = assertThrows(DimensionesIncompatibles.class, () -> Matriz.multiplicarDosMatrices(m1, m2));
        assertEquals("Las dimensiones no son válidas para la multiplicación", exception.getMessage());
    }

    @Test
    void multiplicarDosMatricesTest() {

        try {
            List<int[]> l1 = Arrays.asList(new int[]{1, -1, 1}, new int[]{2, 2, 3}, new int[]{-2, -3, -1});
            Matriz m1 = new Matriz(l1);
            List<int[]> l2 = Arrays.asList(new int[]{1, 0, 4}, new int[]{0, 2, 5}, new int[]{1, 3, 0});
            Matriz m2 = new Matriz(l2);
            List<int[]> l3 = Arrays.asList(new int[]{2, 1, -1}, new int[]{5, 13, 18}, new int[]{-3, -9, -23});
            Matriz m3 = new Matriz(l3);

            Matriz mTest = Matriz.multiplicarDosMatrices(m1, m2);
            assertEquals(m3, mTest);
        } catch (Exception exception) {
            assertTrue(exception.getMessage().equals("Las dimensiones no son válidas para la multiplicación") ||
                    exception.getMessage().equals("Este tipo de constructor es solo para matrices cuadradas"));
        }

    }

    @Test
    void transponerMatrizTest() {
        try {
            List<int[]> l1 = Arrays.asList(new int[]{1, 0, 4}, new int[]{0, 5, 0}, new int[]{6, 0, -9});
            Matriz m1 = new Matriz(l1);
            List<int[]> l2 = Arrays.asList(new int[]{1, 0, 6}, new int[]{0, 5, 0}, new int[]{4, 0, -9});
            Matriz transpuestaM1 = new Matriz(l2);

            Matriz mTranspuestaTest = Matriz.transponerMatriz(m1);
            assertEquals(transpuestaM1, mTranspuestaTest);
        } catch (Exception exception) {
            assertTrue(exception.getMessage().equals("Las dimensiones no son válidas para la multiplicación") ||
                    exception.getMessage().equals("Este tipo de constructor es solo para matrices cuadradas"));
        }
    }

    @Test
    void operacionCompuestaMatrizTranspuestaDeTranspuestaTest() {
        try {
            List<int[]> l1 = Arrays.asList(new int[]{1, 0, 4}, new int[]{0, 5, 0}, new int[]{6, 0, -9});
            Matriz m1 = new Matriz(l1);
            Matriz m1TranspuestaTranspuestaTest = Matriz.transponerMatriz(Matriz.transponerMatriz(m1));

            assertEquals(m1, m1TranspuestaTranspuestaTest);
        } catch (Exception exception) {
            assertTrue(exception.getMessage().equals("Las dimensiones no son válidas para la multiplicación") ||
                    exception.getMessage().equals("Este tipo de constructor es solo para matrices cuadradas"));
        }
    }

    @Test
    void operacionCompuestaProductoTranspuestasTest() {
        try {
            List<int[]> l1 = Arrays.asList(new int[]{1, -1, 1}, new int[]{2, 2, 3}, new int[]{-2, -3, -1});
            Matriz m1 = new Matriz(l1);
            List<int[]> l2 = Arrays.asList(new int[]{1, 0, 4}, new int[]{0, 2, 5}, new int[]{1, 3, 0});
            Matriz m2 = new Matriz(l2);

            Matriz mTranspuestaProducto = Matriz.transponerMatriz(Matriz.multiplicarDosMatrices(m1, m2));
            Matriz m1Transpuesta = Matriz.transponerMatriz(m1);
            Matriz m2Transpuesta = Matriz.transponerMatriz(m2);
            Matriz mProductoTranspuestas = Matriz.multiplicarDosMatrices(m2Transpuesta, m1Transpuesta);

            assertEquals(mTranspuestaProducto, mProductoTranspuestas);
        } catch (Exception exception) {
            assertTrue(exception.getMessage().equals("Las dimensiones no son válidas para la multiplicación") ||
                    exception.getMessage().equals("Este tipo de constructor es solo para matrices cuadradas"));
        }
    }
}