import jdk.jshell.execution.Util;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * GestionParqueos
 *
 * Esta es la clase donde se encuentra el menú en la cual usuario puede interactuar,
 * e ingresar los datos requeridos, para el funcionamiento del parqueo.
 * @author Merleen Guaradado y Jeison Roblero
 * @version 08-31-2021
 */
public class GestionParqueos {
    static List<Automovil> listaAutomovil = new ArrayList<>();
    static List<Camion> listaCamion = new ArrayList<>();
    static List<Motocicleta> listaMotocicleta = new ArrayList<>();

    static List<Integer> listaEspaciosA = new ArrayList<>();
    static List<Integer> listaEspaciosM = new ArrayList<>();
    static List<Integer> listaEspaciosC = new ArrayList<>();
    static String strDateFormat = "dd-MM-yyyy hh-mm-ss";
    static SimpleDateFormat formatter = new SimpleDateFormat(strDateFormat);

    public static void main(String[] args){
        menuPrincipal();
    }

    /**
     * menuPrincipal: Menu inicial donde el usuario configurar precios, ingresar o egresar vehiculos.
     */
    public static void menuPrincipal(){
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */

        int opt = 0;
        do {
            System.out.println("********* Gestion de Parqueos 0.1 *********");
            System.out.println("  1. Configuraciones");
            System.out.println("  2. Ingreso de Vehiculos");
            System.out.println("  3. Egreso de Vehiculos");
            System.out.println("  4. Salir");
            opt = Utils.obtenerEntero("Ingresa una opcion", 0);
            switch (opt) {
                case 1:
                    subMenuConfiguraciones();
                    break;
                case 2:
                    if(TipoVehiculos.AUTOMOVIL.getEspacios() > 0 && TipoVehiculos.AUTOMOVIL.getPrecio()> 0 && TipoVehiculos.AUTOMOVIL.getTiempoTarifa() > 0
                            && TipoVehiculos.MOTOCICLETA.getEspacios() > 0 && TipoVehiculos.MOTOCICLETA.getPrecio() > 0 && TipoVehiculos.MOTOCICLETA.getTiempoTarifa() > 0
                            && TipoVehiculos.CAMION.getEspacios() > 0 && TipoVehiculos.CAMION.getPrecio()> 0 && TipoVehiculos.CAMION.getTiempoTarifa() > 0){
                        subMenuIngresarVehiculo();
                    } else {
                        System.out.println("[ERROR] Debe completar la configuracion de los espacios y tarifas para utilizar esta opcion.");
                    }
                    break;
                case 3:
                    if(TipoVehiculos.AUTOMOVIL.getEspacios() > 0 && TipoVehiculos.AUTOMOVIL.getPrecio()> 0 && TipoVehiculos.AUTOMOVIL.getTiempoTarifa() > 0
                            && TipoVehiculos.MOTOCICLETA.getEspacios() > 0 && TipoVehiculos.MOTOCICLETA.getPrecio() > 0 && TipoVehiculos.MOTOCICLETA.getTiempoTarifa() > 0
                            && TipoVehiculos.CAMION.getEspacios() > 0 && TipoVehiculos.CAMION.getPrecio()> 0 && TipoVehiculos.CAMION.getTiempoTarifa() > 0){
                        subMenuEgresoVehiculos();
                    } else {
                        System.out.println("[ERROR] Debe completar la configuracion de los espacios y tarifas para utilizar esta opcion.");
                    }
                    System.out.println("la opcion de egreso esta pendiente de completa");
                    break;
                case 4:
                    System.out.println("Gracias hasta pronto...");
                    break;
                default:
                    System.out.println("[WARNING] UUPS! Opcion invalida.");
            }
        } while (opt != 4);
    }
    /** subMenuConfiguraciones: menu para las configuraciones desde especio, precios hasta periodos de tiempo
     * por tarifa.
     */

    public static void subMenuConfiguraciones() {
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */
        int opt = 0;
        System.out.println("++++++++++ (1) Configuraciones ++++++++++");
        System.out.println("  1. Configurar Espacios");
        System.out.println("  2. Configurar Precios");
        System.out.println("  3. Configurar Tiempo de Tarifa");
        System.out.println("  4. Regresar");
        opt = Utils.obtenerEntero("Ingresa una opcion", 0);
        switch (opt) {
            case 1:
                subMenuConfEspacios();
                break;
            case 2:
                subMenuConfPrecios();
                break;
            case 3:
                subMenuConfTiempoTarifa();
                break;
            case 4:
                break;
            default:
                System.out.println("[WARNING] UUPS! Opcion invalida.");
        }
    }
    /** subMenuConfPrecios: una vez el usuario escoga decida configurar preciols este menu le ayudara a escoger primero
     * que precio tendra cada espacio dependiendo el tamano "automovil, moto, camion".
     */
    public static void subMenuConfPrecios() {
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */
        int opt = 0;
        System.out.println("++++++++++ (1 > 2) Configurar Precios ++++++++++");
        mostrarTiposEnumerados(1);
        System.out.println("  4. Regresar");
        opt = Utils.obtenerEntero("Ingresa una opcion", 0);
        double precio = 0.00;
        switch (opt) {
            case 1:
                System.out.println("++++++ (1 > 2 > 1) Actualizar Precio Automovil ++++++");
                precio = Utils.obtenerDouble("Ingresar el Precio por Segundo", TipoVehiculos.AUTOMOVIL.getPrecio());
                TipoVehiculos.AUTOMOVIL.setPrecio(precio);
                break;
            case 2:
                System.out.println("++++++ (1 > 2 > 2) Actualizar Precio Motocicleta ++++++");
                precio = Utils.obtenerDouble("Ingresar el Precio por Segundo", TipoVehiculos.MOTOCICLETA.getPrecio());
                TipoVehiculos.MOTOCICLETA.setPrecio(precio);
                break;
            case 3:
                System.out.println("++++++ (1 > 2 > 3) Actualizar Precio Camion ++++++");
                precio = Utils.obtenerDouble("Ingresar el Precio por Segundo", TipoVehiculos.CAMION.getPrecio());
                TipoVehiculos.CAMION.setPrecio(precio);
                break;
            case 4:
                break;
            default:
                System.out.println("[WARNING] UUPS! Opcion invalida.");

        }
    }
    /** subMenuConfEspacios: este nos sirve para setear la cantidasd de espacios que tendremos disponibles para cada
     * tamano de vehiculos.
     */
    public static void subMenuConfEspacios() {
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */
        int opt = 0;
        System.out.println("++++++++++ (1 > 1) Configurar Espacios ++++++++++");
        mostrarTiposEnumerados(2);
        System.out.println("  4. Regresar");
        opt = Utils.obtenerEntero("Ingresa una opcion", 0);
        int espacios = 0;
        switch (opt) {
            case 1:
                System.out.println("++++++ (1 > 1 > 1) Actualizar Espacios Automovil ++++++");
                espacios = Utils.obtenerEntero("Ingresar el Numero de Espacios", TipoVehiculos.AUTOMOVIL.getEspacios());
                TipoVehiculos.AUTOMOVIL.setEspacios(espacios);
                generarEespacios(espacios, listaEspaciosA);
                break;
            case 2:
                System.out.println("++++++ (1 > 1 > 2) Actualizar Espacios Motocicleta ++++++");
                espacios = Utils.obtenerEntero("Ingresar el Numero de Espacios", TipoVehiculos.MOTOCICLETA.getEspacios());
                TipoVehiculos.MOTOCICLETA.setEspacios(espacios);
                generarEespacios(espacios, listaEspaciosM);
                break;
            case 3:
                System.out.println("++++++ (1 > 1 > 3) Actualizar Espacios Camion ++++++");
                espacios = Utils.obtenerEntero("Ingresar el Numero de Espacios", TipoVehiculos.CAMION.getEspacios());
                TipoVehiculos.CAMION.setEspacios(espacios);
                generarEespacios(espacios, listaEspaciosC);
                break;
            case 4:
                break;
            default:
                System.out.println("[WARNING] UUPS! Opcion invalida.");

        }
    }
    /** subMenuConfTiempoTarifa: este submenu nos ayuda a darle un tiempo a cada period que se usara para el cobro
     * del parqueo.
     */
    public static void subMenuConfTiempoTarifa() {
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */
        int opt = 0;
        System.out.println("++++++++++ (1 > 3) Configurar Tiempos Tarifa ++++++++++");
        mostrarTiposEnumerados(3);
        System.out.println("  4. Regresar");
        opt = Utils.obtenerEntero("Ingresa una opcion", 0);
        int tiempo = 0;
        switch (opt) {
            case 1:
                System.out.println("++++++ (1 > 3 > 1) Actualizar Tiempo de Tarifa Automovil ++++++");
                tiempo = Utils.obtenerEntero("Ingresar el Numero de Espacios", TipoVehiculos.AUTOMOVIL.getTiempoTarifa());
                TipoVehiculos.AUTOMOVIL.setTiempoTarifa(tiempo);
                break;
            case 2:
                System.out.println("++++++ (1 > 3 > 2) Actualizar Tiempo de Tarifa Motocicleta ++++++");
                tiempo = Utils.obtenerEntero("Ingresar el Numero de Espacios", TipoVehiculos.MOTOCICLETA.getTiempoTarifa());
                TipoVehiculos.MOTOCICLETA.setTiempoTarifa(tiempo);
                break;
            case 3:
                System.out.println("++++++ (1 > 2 > 3) Actualizar Tiempo de Tarifa Camion ++++++");
                tiempo = Utils.obtenerEntero("Ingresar el Numero de Espacios", TipoVehiculos.CAMION.getTiempoTarifa());
                TipoVehiculos.CAMION.setTiempoTarifa(tiempo);
                break;
            case 4:
                break;
            default:
                System.out.println("[WARNING] UUPS! Opcion invalida.");

        }
    }
    /** subMenuIngresarVehiculo: en este submenu se ingresan los vehiculos de maneara aleatoria.
     */
    public static void subMenuIngresarVehiculo(){
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */
        int opt = 0;
        /**
         * placa: placa que luego nos ayudara a buscar el dueno del vehiculo.
         */
        String placa;
        /**
         * entero que obtiene la cantidad de espacios disponiles que se encuentran en la listaEspaciadora
         * y este nos da la cantidad de espacios disponibles con los que contamos.
         */
        int cantidadEspaciosDisponibles;
        /**
         * a este entero es el indice que se le asigna el valor aleatoreo que se genera dependiendo la cantidad de espacios disponibles
         * en la lista camtodadEspaciosDisponibles.
         */
        int indexRandom;
        /**
         * ya con el valor agregado aleatoriamente a la listaEspacios se le asigna el valor a esta variable.
         */
        int espacioTomar;
        System.out.println("++++++++++ (2) Ingreso de Vehiculos ++++++++++");
        mostrarTiposEnumerados(0);
        System.out.println("  4. Regresar");
        opt = Utils.obtenerEntero("Seleccionar el tipo de Vehiculo", 0);
        switch (opt) {
            case 1:
                if (listaEspaciosA.size() > 0) {
                    cantidadEspaciosDisponibles = listaEspaciosA.size();
                    ThreadLocalRandom tlr = ThreadLocalRandom.current();
                    indexRandom = tlr.nextInt(0, cantidadEspaciosDisponibles);
                    espacioTomar = listaEspaciosA.get(indexRandom);
                    placa = Utils.obtenerPlaca("Ingresar numero de placa", null);
                    Date fecha = new Date();
                    Automovil auto = new Automovil(placa, espacioTomar, fecha);
                    Utils.generarPdfIngreso(placa, Utils.obtenerMascara(espacioTomar), formatter.format(fecha), TipoVehiculos.AUTOMOVIL.getPrecio(), "Automovil");
                    listaEspaciosA.remove(indexRandom);
                    listaAutomovil.add(auto);
                    System.out.println("Su automovil con placas "+ placa + " fue ingresado correctamente en el espacio " + Utils.obtenerMascara(espacioTomar) +
                                       " a la fecha "+ formatter.format(fecha));
                } else {
                    System.out.println("[WARNING] Ya no hay espacios disponibles para automobiles");
                }
                break;
            case 2:
                if (listaEspaciosM.size() > 0) {
                    cantidadEspaciosDisponibles = listaEspaciosM.size();
                    ThreadLocalRandom tlr = ThreadLocalRandom.current();
                    indexRandom = tlr.nextInt(0, cantidadEspaciosDisponibles);
                    espacioTomar = listaEspaciosM.get(indexRandom);
                    placa = Utils.obtenerPlaca("Ingresar numero de placa", null);
                    Date fecha = new Date();
                    Motocicleta moto = new Motocicleta(placa, espacioTomar, fecha);
                    Utils.generarPdfIngreso(placa, Utils.obtenerMascara(espacioTomar), formatter.format(fecha), TipoVehiculos.MOTOCICLETA.getPrecio(), "Motocicleta");
                    listaEspaciosM.remove(indexRandom);
                    listaMotocicleta.add(moto);
                    System.out.println("Su motocicleta con placas "+ placa + " fue ingresado correctamente en el espacio " + Utils.obtenerMascara(espacioTomar) +
                            " a la fecha "+ formatter.format(fecha));
                } else {
                    System.out.println("[WARNING] Ya no hay espacios disponibles para motocicletas");
                }
                break;
            case 3:
                if (listaEspaciosC.size() > 0) {
                    cantidadEspaciosDisponibles = listaEspaciosC.size();
                    ThreadLocalRandom tlr = ThreadLocalRandom.current();
                    indexRandom = tlr.nextInt(0, cantidadEspaciosDisponibles);
                    espacioTomar = listaEspaciosC.get(indexRandom);
                    placa = Utils.obtenerPlaca("Ingresar numero de placa", null);
                    Date fecha = new Date();
                    Camion camion = new Camion(placa, espacioTomar, fecha);
                    Utils.generarPdfIngreso(placa, Utils.obtenerMascara(espacioTomar), formatter.format(fecha), TipoVehiculos.CAMION.getPrecio(), "Motocicleta");
                    listaEspaciosC.remove(indexRandom);
                    listaCamion.add(camion);
                    System.out.println("Su camion con placas "+ placa + " fue ingresado correctamente en el espacio " + Utils.obtenerMascara(espacioTomar) +
                            " a la fecha "+ formatter.format(fecha));
                } else {
                    System.out.println("[WARNING] Ya no hay espacios disponibles para camiones");
                }
                break;
            case 4:
                break;
            default:
                System.out.println("[WARNING] UUPS! Opcion invalida.");
        }
    }
    /** subMenuEgresoVehiculos: Haqui egresamos los vehiculos de la lista de espacios y se calcula el tiempo o
     * cantidad de periodos que uso el estacionamiento.
     */
    public static void subMenuEgresoVehiculos() {
        /**
         * opt: es um entero que nos sirve para interactuar con el usuario, el valor que el usuario asigne sera la
         * ocion escogida.
         */
        int opt = 0;
        System.out.println("++++++++++ (3) Egreso de Vehiculos ++++++++++");
        mostrarTiposEnumerados(0);
        System.out.println("  4. Regresar");
        opt = Utils.obtenerEntero("Seleccionar el tipo de Vehiculo", 0);
        /**
         * placa: es un String que recibe el numero de la placa con un reguex o exprecion regular.
         */
        String placa = Utils.obtenerPlaca("Ingresar numero de placa", null);
        Date fecha = new Date();
        System.out.println("***** GRACIAS POR VISITARNOS *****");
        switch (opt) {
            case 1:
                Automovil auto = buscarAutomovil(placa);
                if (null != auto) {
                    long tiempoTranscurridoMili = fecha.getTime() - auto.getFechaIngreso().getTime();
                    long tiempoTranscurrido = tiempoTranscurridoMili / 1000;
                    double periodos = tiempoTranscurrido / TipoVehiculos.AUTOMOVIL.getTiempoTarifa();
                    double redondeado = Math.floor(periodos);
                    System.out.println("Usted estuvo un total de " + tiempoTranscurrido + " segundos");
                    System.out.println("Que equivale a " + redondeado + " periodos");
                    double totalMonto = auto.calcularMonto(TipoVehiculos.AUTOMOVIL.getTiempoTarifa(), TipoVehiculos.AUTOMOVIL.getPrecio());
                    System.out.println("El total a pagar es de Q." + totalMonto);
                    String nit = Utils.obtenerNit("Ingresar Nit:", "C/F");
                    listaEspaciosA.add(auto.getNoEspacio());
                    listaAutomovil.remove(auto);
                    Utils.generarPdfEgreso(placa, Utils.obtenerMascara(auto.getNoEspacio()), formatter.format(fecha),
                            totalMonto, TipoVehiculos.AUTOMOVIL.toString(), tiempoTranscurrido, nit);
                    System.out.println("Su vehiculo con placas " + placa + " fue egresado exitosamente.");
                } else {
                    System.out.println("[ERROR] No se encontro el automovil con la palaca " + placa);
                }
                break;
            case 2:
                Motocicleta moto = buscarMotocicleta(placa);
                if (null != moto) {
                    long tiempoTranscurridoMili = fecha.getTime() - moto.getFechaIngreso().getTime();
                    long tiempoTranscurrido = tiempoTranscurridoMili / 1000;
                    double periodos = tiempoTranscurrido / TipoVehiculos.MOTOCICLETA.getTiempoTarifa();
                    double redondeado = Math.floor(periodos);
                    System.out.println("Usted estuvo un total de " + tiempoTranscurrido + " segundos");
                    System.out.println("Que equivale a " + redondeado + " periodos");
                    double totalMonto = moto.calcularMonto(TipoVehiculos.MOTOCICLETA.getTiempoTarifa(),
                            TipoVehiculos.MOTOCICLETA.getPrecio());
                    System.out.println("El total a pagar es de Q." + totalMonto);
                    String nit = Utils.obtenerNit("Ingresar Nit:", "C/F");
                    listaEspaciosM.add(moto.getNoEspacio());
                    listaMotocicleta.remove(moto);
                    Utils.generarPdfEgreso(placa, Utils.obtenerMascara(moto.getNoEspacio()), formatter.format(fecha),
                            totalMonto, TipoVehiculos.MOTOCICLETA.toString(), tiempoTranscurrido, nit);
                    System.out.println("Su vehiculo con placas " + placa + " fue egresado exitosamente.");
                } else {
                    System.out.println("[ERROR] No se encontro el motocicleta con la palaca " + placa);
                }
                break;
            case 3:
                Camion camion = buscarCamion(placa);
                if (null != camion) {
                    long tiempoTranscurridoMili = fecha.getTime() - camion.getFechaIngreso().getTime();
                    long tiempoTranscurrido = tiempoTranscurridoMili / 1000;
                    double periodos = tiempoTranscurrido / TipoVehiculos.CAMION.getTiempoTarifa();
                    double redondeado = Math.floor(periodos);
                    System.out.println("Usted estuvo un total de " + tiempoTranscurrido + " segundos");
                    System.out.println("Que equivale a " + redondeado + " periodos");
                    double totalMonto = camion.calcularMonto(TipoVehiculos.CAMION.getTiempoTarifa(),
                            TipoVehiculos.CAMION.getPrecio());
                    System.out.println("El total a pagar es de Q." + totalMonto);
                    String nit = Utils.obtenerNit("Ingresar Nit:", "C/F");
                    listaEspaciosC.add(camion.getNoEspacio());
                    listaCamion.remove(camion);
                    Utils.generarPdfEgreso(placa, Utils.obtenerMascara(camion.getNoEspacio()), formatter.format(fecha),
                            totalMonto, TipoVehiculos.CAMION.toString(), tiempoTranscurrido, nit);
                    System.out.println("Su vehiculo con placas " + placa + " fue egresado exitosamente.");
                } else {
                    System.out.println("[ERROR] No se encontro el camion con la palaca " + placa);
                }
                break;
            case 4:
                break;
            default:
                System.out.println("[WARNING] UUPS! Opcion invalida.");

        }
    }

    /** mostrarTiposEnumerados: Muestra el listado de tipos de vehiculos agregados al enum con su precio, espacios, tiempo de tarifa o vacio.
     *  * @param precioEspacios: 0 vacio ,1 precios, 2 espacios, 3 tiempo
     */
    public static void mostrarTiposEnumerados(int precioEspacios){
        int i = 1;
        for(TipoVehiculos tpVehiculo : TipoVehiculos.values()) {
            System.out.println("  " + i + "."
                    + (precioEspacios == 1 ? (" (" + tpVehiculo.getPrecio() + ") ") : " ")
                    + (precioEspacios == 2 ? (" (" + tpVehiculo.getEspacios() + ") ") : " ")
                    + (precioEspacios == 3 ? (" (" + tpVehiculo.getTiempoTarifa() + ") ") : " ")
                    + tpVehiculo );
            i++;
        }
    }

    public static Automovil buscarAutomovil(String placa){
        Automovil automovil = null;
        for (Automovil auto : listaAutomovil){
            if(auto.getPlaca().equals(placa)){
                automovil = auto;
                break;
            }
        }
        return automovil;
    }

    public static Motocicleta buscarMotocicleta(String placa){
        Motocicleta motocicleta = null;
        for (Motocicleta moto : listaMotocicleta){
            if(moto.getPlaca().equals(placa)){
                motocicleta = moto;
                break;
            }
        }
        return motocicleta;
    }

    public static Camion buscarCamion(String placa){
        Camion camionB = null;
        for (Camion camion : listaCamion){
            if(camion.getPlaca().equals(placa)){
                camionB = camion;
                break;
            }
        }
        return camionB;
    }

    public static void generarEespacios(int cantidadEspacios, List<Integer> lista){
        lista.clear();
        for (int i = 1; i <= cantidadEspacios; i++){
            lista.add(i);
        }
    }

}