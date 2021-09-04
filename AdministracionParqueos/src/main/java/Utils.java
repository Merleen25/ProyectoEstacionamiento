import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Utils: es donde se encuentran los mtodos para poder validar informacion y recibirla de la manera que se solicita.
 * @author Merleen Guardado y Jeison Roblero
 * @version 31-08-2021
 */

public class Utils {
    /**
     * obtnerEntero: valida que el valor ingresado sea un entero y no negativo ni caracter, si es string lo cambia a entero.
     * @param mensaje: recibe un String que se cambia a int.
     *  @param valorDefault: .
     * @return {descripcionDesalida}
     */

    public static int obtenerEntero(String mensaje, int valorDefault){
        int valor = 0;
        boolean valorInvalido = true;
        Scanner entrada;
        do{
            entrada = new Scanner(System.in);
            try {
                if (0 != valorDefault){
                    System.out.print(mensaje + "(" + valorDefault + "): ");
                    String valorInicial = entrada.nextLine();
                    if (valorInicial.equals("")){
                        valor = valorDefault;
                    } else {
                        valor = Integer.parseInt(valorInicial);
                    }
                } else {
                    System.out.print(mensaje + ": ");
                    valor = Integer.parseInt(entrada.nextLine());
                }
                valorInvalido = false;
            } catch(InputMismatchException | NumberFormatException e){
                valorInvalido = true;
                System.out.println("[ERROR] Valor invalido!, vuelve a intentarlo.");
            }
        } while(valorInvalido);
        return valor;
    }

    /**
     * obtenerDouble: valida que el valor ingresado sea un double o decimal y no negativo ni caracter, si es string lo cambia a decimal o double.
     * @param mensaje: recibe un String que se cambia a double
     * @param valorDefault
     * @return {descripcionDesalida}
     */
    public static double obtenerDouble(String mensaje, double valorDefault){
        double valor = 0;
        boolean valorInvalido = true;
        Scanner entrada;
        do{
            entrada = new Scanner(System.in);
            try {
                if (0 != valorDefault){
                    System.out.print(mensaje + "(" + valorDefault + "): ");
                    String valorInicial = entrada.nextLine();
                    if (valorInicial.equals("")){
                        valor = valorDefault;
                    } else {
                        valor = Double.parseDouble(valorInicial);
                    }
                } else {
                    System.out.print(mensaje + ": ");
                    valor = Double.parseDouble(entrada.nextLine());
                }
                valorInvalido = false;
            } catch(InputMismatchException | NumberFormatException e){
                valorInvalido = true;
                System.out.println("[ERROR] Valor invalido!, vuelve a intentarlo.");
            }
        } while(valorInvalido);
        return valor;
    }

    /**
     * obtenerString: valida que el valor ingresado sea un String o alfanumerico y no un numero entero o decimal, si es cualquiera de estos los convierte a String.
     * @param mensaje
     * @param valorDefault:
     * @return {descripcionDesalida}
     */
    public static String obtenerString(String mensaje, String valorDefault){
        String valor = "";
        do{
            Scanner entrada = new Scanner(System.in);
            entrada.useDelimiter("\n");
            if (null != valorDefault){
                System.out.print(mensaje + "(" + valorDefault + "): ");
            } else {
                System.out.print(mensaje + ": ");
            }
            valor = entrada.nextLine();
            if(valor.equals("")){
                if (null != valorDefault){
                    valor = valorDefault;
                } else {
                    System.out.println("[ERROR] Valor invalido!, intente de nuevo");
                }
            }
        }while(valor.equals(""));
        return valor;
    }

    /**
     * obtenerFecha: valida que la fecha este en el formato correcto (dd/mm/yyy)
     * @param mensaje
     * @param valorDefault
     * @return {descripcionDesalida}
     */
    public static String obtenerFecha(String mensaje, String valorDefault){
        String fecha = "";
        boolean fechaInvalida = true;
        int diasMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
        String mensajeError = "[ERROR] Fecha invalida!, vuelve a intentarlo. el formato requerido es (dd/mm/yyyy)";
        System.out.println("[WARNING] Formato de fecha (dd/mm/yyyy)");
        Scanner entrada;
        do{
            entrada = new Scanner(System.in);
            if (null != valorDefault){
                System.out.print(mensaje + "(" + valorDefault + "): ");
            } else {
                System.out.print(mensaje + ": ");
            }
            fecha = entrada.nextLine();
            if (fecha.equals("") && valorDefault != null){
                return valorDefault;
            }
            if (fecha.length() == 10){
                String[] fechaArray = fecha.split("/");
                if(fechaArray.length == 3) {
                    try{
                        int dia = Integer.parseInt(fechaArray[0]);
                        int mes = Integer.parseInt(fechaArray[1]);
                        int anio = Integer.parseInt(fechaArray[2]);
                        if(dia > 0 && mes > 0 && anio > 0
                                && mes <= 12 && dia <= diasMes[(mes - 1)]){
                            fechaInvalida = false;
                        } else {
                            System.out.println(mensajeError);
                        }
                    } catch(NumberFormatException e){
                        System.out.println(mensajeError);
                    }
                } else {
                    System.out.println(mensajeError);
                }
            } else {
                System.out.println(mensajeError);
            }
        } while(fechaInvalida);
        return fecha;
    }

    /**
     * obtenerPlaca: valida que la placa ingresada tenga el formato correcto, la cantidad de digitos de
     * letras y numeros.
     * @param mensaje
     * @param valorDefault
     * @return {descripcionDesalida}
     */
    public static String obtenerPlaca(String mensaje, String valorDefault){
        String valor = "";
        String regexPlaca = "(M|P|C)+[0-9]{3}+[BCDFGHJKLMNOPQRSTVWXYZ]{3}";
        do{
            Scanner entrada = new Scanner(System.in);
            entrada.useDelimiter("\n");
            if (null != valorDefault){
                System.out.print(mensaje + "(" + valorDefault + "): ");
            } else {
                System.out.print(mensaje + ": ");
            }
            valor = entrada.nextLine();
            if(valor.equals("")){
                if (null != valorDefault){
                    valor = valorDefault;
                } else {
                    System.out.println("[ERROR] No. Placa Invalido!, intente de nuevo");
                }
            }
            if(!valor.matches(regexPlaca)){
                System.out.println("[ERROR] No. Placa Invalido!, intente de nuevo");
            }
        }while(valor.equals("") || !valor.matches(regexPlaca));
        return valor;
    }

    /**
     * obtenerNit: valida que el Nit este ingresado correctamente, con sus digitos correspondientes
     * @param mensaje
     * @param valorDefault
     * @return {descripcionDesalida}
     */
    public static String obtenerNit(String mensaje, String valorDefault){
        String valor = "";
        String regexNit = "[0-9]{8}";
        do{
            Scanner entrada = new Scanner(System.in);
            entrada.useDelimiter("\n");
            if (null != valorDefault){
                System.out.print(mensaje + "(" + valorDefault + "): ");
            } else {
                System.out.print(mensaje + ": ");
            }
            valor = entrada.nextLine();
            if(valor.equals("")){
                if (null != valorDefault){
                    valor = valorDefault;
                } else {
                    System.out.println("[ERROR] Nit Invalido!, intente de nuevo");
                }
            }
            if(!valor.matches(regexNit)){
                System.out.println("[ERROR] Nit Invalido!, intente de nuevo");
            }
        }while(valor.equals("") || !valor.matches(regexNit));
        return valor;
    }

    /**
     * generarPdfIngreso: Genera un pdf con toda la informacion del ingreso del vehiculo, como lo es
     * la placa, el espacio de parqueo, la fecha y hora de ingreso, la tarifa y el tipo de vehiculo
     * @param placa
     * @param espacioParqueo
     * @param fechaHora
     * @param tarifa
     * @param tipoVehiculo
     */
    public static void generarPdfIngreso(String placa, String espacioParqueo, String fechaHora, double tarifa, String tipoVehiculo) {
        Document documento = new Document();
        try {
            String fileName = "PDF\\Ticket-" + placa + "_" + espacioParqueo + "_" + tipoVehiculo + "_" + fechaHora + ".pdf";
            FileOutputStream ficheroPdf = new FileOutputStream(fileName);

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

            documento.open();
            documento.add(new Paragraph("Ticket - Gestor de Parqueos",
                    FontFactory.getFont("arial",   // fuente
                            20,                        // tamaño
                            Font.ITALIC,                   // estilo
                            BaseColor.RED)));             // color
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("Fecha");
            tabla.addCell(fechaHora);
            tabla.addCell("Placa");
            tabla.addCell(placa);
            tabla.addCell("No. Estacionamiento");
            tabla.addCell(espacioParqueo);
            tabla.addCell("Tipo de Vehiculo");
            tabla.addCell(tipoVehiculo);
            tabla.addCell("Tarifa por Segundo");
            tabla.addCell("Q. " + tarifa);
            documento.add(tabla);
            documento.add(new Paragraph("Nota: Por favor no pierda este ticket."));
            documento.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * generarPdfEgreso: Genera un pdf el cual contiene toda la información de salida como lo es el
     * numero de placa, el espacio del parqueo, la fecha y hora, el monto a pagar, el tipo de vehiculo,
     * el tiempo total y el numero de nit de quien fue registrado.
     * @param placa
     * @param espacioParqueo
     * @param fechaHora
     * @param monto
     * @param tipoVehiculo
     * @param totalTiempo
     * @param nit
     */
    public static void generarPdfEgreso(String placa, String espacioParqueo, String fechaHora, double monto, String tipoVehiculo, long totalTiempo, String nit) {
        Document documento = new Document();
        try {
            String fileName = "PDF\\Factura-" + placa + "_" + espacioParqueo + "_" + tipoVehiculo + "_" + fechaHora + ".pdf";
            FileOutputStream ficheroPdf = new FileOutputStream(fileName);

            // Se asocia el documento al OutputStream y se indica que el espaciado entre
            // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
            PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

            documento.open();
            documento.add(new Paragraph("Factura - Gestor de Parqueos",
                    FontFactory.getFont("arial",   // fuente
                            20,                        // tamaño
                            Font.ITALIC,                   // estilo
                            BaseColor.BLUE)));             // color
            PdfPTable tabla = new PdfPTable(2);
            tabla.addCell("Fecha");
            tabla.addCell(fechaHora);
            tabla.addCell("NIT");
            tabla.addCell(nit);
            tabla.addCell("Placa");
            tabla.addCell(placa);
            tabla.addCell("No. Estacionamiento");
            tabla.addCell(espacioParqueo);
            tabla.addCell("Tipo de Vehiculo");
            tabla.addCell(tipoVehiculo);
            tabla.addCell("Tiempo");
            tabla.addCell(String.valueOf(totalTiempo));
            tabla.addCell("Total Pagado");
            tabla.addCell("Q. " + monto);
            documento.add(tabla);
            documento.add(new Paragraph("Gracias por visitarnos."));
            documento.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * obtenerMascara: le da el formato ### al numero, es decir, si ingresa '1' devolverá 0001, si ingresa '10' devolverá 0010
     * y así sucesivamente.
     * @param numero
     * @return {descripcionDesalida}
     */
    public static String obtenerMascara(int numero){
        if(numero < 10) {
            return "000" + numero;
        } else if (numero < 100) {
            return "00" + numero;
        } else if (numero < 1000) {
            return "0" + numero;
        } else {
            return "" + numero;
        }
    }
}
