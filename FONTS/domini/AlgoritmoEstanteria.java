package FONTS.domini;

import java.util.ArrayList;
import java.util.Vector;

public interface AlgoritmoEstanteria {

    public Vector<Producto> ejecutarAlgoritmo(ArrayList<Producto> productos, int filas, int columnas);

    public Integer getCostoMaximo();
}