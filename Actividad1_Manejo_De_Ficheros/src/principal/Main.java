package principal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import clases.Articulo;
import clases.ArticuloImp;
import clases.FicherosUtils;

public class Main {
	static ArrayList<Articulo> articulos = new ArrayList<Articulo>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		System.out.println("CARGANDO FICHERO..... --------");
		System.out.println("********************************");
		// PRIMERO LEEMOS EL FICHERO Y GENERAMOS EN CASO DE NO EXISTIR
		FicherosUtils.lecturaFichero(articulos);

		int opcion;
		do {
			menu();
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				ArticuloImp.anadirArticulo(articulos, sc);
				TimeUnit.SECONDS.sleep(2);
				break;
			case 2:
				if (ArticuloImp.borrarArticulo(articulos, sc)) {
					System.out.println("El artículo a sido eliminado con éxito");
				} else {
					System.out.println("El id proporcionado no a sido encontrado");
				}
				TimeUnit.SECONDS.sleep(2);
				break;

			case 3:
				ArticuloImp.consultarArticulo(articulos, sc);
				TimeUnit.SECONDS.sleep(2);
				break;

			case 4:
				ArticuloImp.listarArticulos(articulos);
				TimeUnit.SECONDS.sleep(3);
				break;

			case 5:
				break;

			case 6:
				FicherosUtils.exportarArticulosACSV(articulos);
				TimeUnit.SECONDS.sleep(2);
				break;
			}
		} while (opcion != 5);
		
		System.out.println("Se cierra el programa.");
		TimeUnit.SECONDS.sleep(1);
		
		try(FileOutputStream file = new FileOutputStream("articulos.dat", false);
				ObjectOutputStream buffer = new ObjectOutputStream(file)){
			for(Articulo c : articulos) {
				buffer.writeObject(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("El objeto no a podido ser añadido");
		}
		
	}

	public static void menu() {
		System.out.println("Elige una opción:");
		System.out.println("Menu:");
		System.out.println("1 - Añadir nuevo articulo");
		System.out.println("2 - Borrar artículo por ID");
		System.out.println("3 - Consulta artículo por ID");
		System.out.println("4 - Listado de todos los artículos");
		System.out.println("5 - Terminar el programa");
		System.out.println("6 - Exportar datos a CSV");
	}
}
