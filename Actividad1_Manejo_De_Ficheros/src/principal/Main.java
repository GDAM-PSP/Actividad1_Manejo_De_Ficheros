package principal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import clases.Articulo;
import clases.ArticuloImp;
import clases.FicherosOps;

public class Main {
	static ArrayList<Articulo> articulos = new ArrayList<Articulo>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("CARGANDO FICHERO..... --------");
		System.out.println("********************************");
		// PRIMERO LEEMOS EL FICHERO Y GENERAMOS EN CASO DE NO EXISTIR
		articulos = FicherosOps.lecturaFichero();

		int opcion;
		do {
			menu();
			opcion = sc.nextInt();
			sc.nextLine();
			switch (opcion) {
			case 1:
				ArticuloImp.anadirArticulo(articulos);
				TimeUnit.SECONDS.sleep(2);
				break;
			case 2:
				if (ArticuloImp.borrarArticulo(articulos)) {
					System.out.println("El artículo a sido eliminado con éxito");
				} else {
					System.out.println("El id proporcionado no a sido encontrado");
				}
				TimeUnit.SECONDS.sleep(2);
				break;

			case 3:
				ArticuloImp.consultarArticulo(articulos);
				TimeUnit.SECONDS.sleep(2);
				break;

			case 4:
				ArticuloImp.listarArticulos(articulos);
				TimeUnit.SECONDS.sleep(3);
				break;

			case 5:
				break;

			case 6:
				FicherosOps.exportarArticulosACSV();
				TimeUnit.SECONDS.sleep(2);
				break;
			}
		} while (opcion != 5);

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
