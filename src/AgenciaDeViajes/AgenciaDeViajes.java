/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenciaDeViajes;

import javax.swing.*;
import java.util.*;
import java.time.*;

/**
 *
 * @author villa
 */
public class AgenciaDeViajes {

    //declaracion de los array de reservas, hoteles, aerolineas y transportes
    private ArrayList<Reserva> reservaciones;
    private ArrayList<Hotel> hoteles;
    private ArrayList<Aerolinea> aerolineas;
    private ArrayList<EventoCultural> eventosCulturales;
    private ArrayList<TransporteCiudad> transportes;

    //declaración del area de texto
    JTextArea area;
    JScrollPane barras;

    /**
     * inicializa las listas y las areas de texto
     */
    AgenciaDeViajes() {
        reservaciones = new ArrayList<>();
        hoteles = new ArrayList<>();
        aerolineas = new ArrayList<>();
        eventosCulturales = new ArrayList();
        transportes = new ArrayList<>();

        area = new JTextArea();
        barras = new JScrollPane(area);
    }

    /**
     * permite ingresar informacion de alguno de las opciones disponibles
     */
    private void ingresarInformacion() {
        String opcion = "";
        do {

            opcion = JOptionPane.showInputDialog("Escoga el tipo de informacion que desea ingresar\n"
                    + "1. Hotel\n"
                    + "2. Aerolínea\n"
                    + "3. Medios de Transporte\n"
                    + "4. Eventos Culturales\n"
                    + "5. Regresar al Menú");

            switch (opcion) {
                case "1": //hotel
                    String nombreH = JOptionPane.showInputDialog("Ingrese nombre del hotel").trim().toUpperCase();
                    int estrellas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la calificacion de estrellas").trim());
                    String ciudadH = JOptionPane.showInputDialog("Ingrese la ciudad del hotel").trim().toUpperCase();
                    double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese costo del hotel por habitacion"));

                    hoteles.add(new Hotel(estrellas, nombreH, ciudadH, precio));

                    area.setText(
                            "Informacion del hotel ingresado con exito\n\n"
                            + nombreH + "\n" + estrellas + "\n" + ciudadH + "\n" + precio
                    );
                    JOptionPane.showMessageDialog(null, barras);
                    break;
                case "2": //aerolinea
                    //JOptionPane.showMessageDialog(null, "Opcion no disponible en el momento");
                    String nombreA = JOptionPane.showInputDialog("Ingrese el nombre de la aerolinea").trim().toUpperCase();
                    Aerolinea aerolinea = new Aerolinea(nombreA);
                    String opcionA = "";

                    do {
                        opcionA = JOptionPane.showInputDialog("Desea agregar vuelos a la aerolinea"
                                + "\n1. Agregar vuelo"
                                + "\n0. Salir");
                        switch (opcionA) {
                            case "0":
                                break;
                            case "1":
                                String origenA = JOptionPane.showInputDialog("Ingrese la ciudad de origen del vuelo").trim().toUpperCase();
                                String destinoA = JOptionPane.showInputDialog("Ingrese la ciudad de destino del vuelo").trim().toUpperCase();
                                double precioA = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del vuelo").trim());
                                aerolinea.agregarVuelo(origenA, destinoA, precioA);
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                        }
                    } while (!opcionA.equals("0"));
                    aerolineas.add(aerolinea);

                    area.setText("Informacion de la aerolina ingresado con exito:\n\n"
                            + nombreA);
                    JOptionPane.showMessageDialog(null, barras);
                    break;

                case "3": //medios de transporte
                    String ciudadT = JOptionPane.showInputDialog("Ingrese la ciudad").trim().toUpperCase();
                    double precioBus = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo del pasaje del bus").trim());
                    double precioChiva = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo del pasaje de chiva").trim());
                    double precioBicicleta = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo del alquiler de la bicicleta").trim());

                    transportes.add(new TransporteCiudad(ciudadT, precioBus, precioChiva, precioBicicleta));

                    area.setText("Informacion de los medios de transporte ingresado con exito:\n"
                            + "\nCiudad: " + ciudadT
                            + "\nPrecio pasaje Bus: " + precioBus
                            + "\nPrecio pasaje Chiva: " + precioChiva
                            + "\nPrecio alquiler Bicicleta: " + precioBicicleta);
                    JOptionPane.showMessageDialog(null, barras);
                    break;

                case "4": //eventos culturales
                    String nombreE = JOptionPane.showInputDialog("Ingrese el nombre del evento").trim().toUpperCase();
                    String ciudadE = JOptionPane.showInputDialog("Ingrese la ciudad").trim().toUpperCase();
                    double costo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el costo por persona").trim());
                    String horario = JOptionPane.showInputDialog("Ingrese el horario").trim();
                    String fecha = JOptionPane.showInputDialog("Ingrese la fecha del evento (yyyy-MM-dd)").trim();
                    String lugar = JOptionPane.showInputDialog("Ingrese el lugar del evento").trim().toUpperCase();

                    eventosCulturales.add(new EventoCultural(nombreE, ciudadE, costo, horario, fecha, lugar));

                    area.setText("Informacion del evento ingresado con exito\n"
                            + "\nEvento: " + nombreE
                            + "\nCiudad: " + ciudadE
                            + "\nCosto entrada: " + costo
                            + "\nHorario: " + horario
                            + "\nFecha evento: " + fecha
                            + "\nLugar evento: " + lugar);
                    JOptionPane.showMessageDialog(null, barras);
                    break;

                case "5":
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                    break;
            }
        } while (!opcion.equals("5"));
    }

    /**
     * lista las posibles opciones a escoger de acuerdo a los parametros
     * ingresados
     */
    private void catalogo() {
        String ciudadDestino = JOptionPane.showInputDialog("Ingrese la ciudad de destino").trim().toUpperCase();
        double valorMinimo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor minimo del viaje").trim());
        double valorMaximo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor maximo del viaje").trim());
        int diasViaje = Integer.parseInt(JOptionPane.showInputDialog("Digite los dias de viaje").trim());
        int cantidadPersonas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a viajar").trim());

        String datos = "Opciones disponibles de acuerdo a los parametros indicados\n"
                + "Ciudad destino: " + ciudadDestino
                + "\nRango de precio: " + valorMinimo + " - " + valorMaximo
                + "\nDias de viaje: " + diasViaje
                + "\nCantidad de personas a viajar: " + cantidadPersonas + "\n";
        int contador = 1;
        for (Hotel x : hoteles) {
            if (x.getCiudad().equals(ciudadDestino)) {
                double precioTotal = 0;
                if (x.getPrecio() * diasViaje * cantidadPersonas < valorMaximo) {
                    precioTotal = x.getPrecio() * diasViaje * cantidadPersonas;
                    datos += "\n\tOpcion " + contador
                            + "\nNombre Hotel: " + x.getNombre()
                            + "\nEstrellas: " + x.getEstrellas()
                            + "\nPrecio hotel: " + x.getPrecio()
                            + "\nPrecio Total (Hotel): " + precioTotal;
                    contador++;

                    datos += "\n\nPosibles eventos a asistir:";
                    for (EventoCultural e : eventosCulturales) {
                        if (e.getCiudad().equals(ciudadDestino)) {
                            datos += "\n" + e.getInformacion();

                            for (TransporteCiudad t : transportes) {
                                if (t.getCiudad().equals(ciudadDestino)) {
                                    datos += "\nPrecio Total en Bus (Hotel, Evento, Transporte): ";
                                    if (precioTotal + t.getPrecio("BUS") * cantidadPersonas * diasViaje < valorMaximo) {
                                        datos += precioTotal + t.getPrecio("BUS") * cantidadPersonas * diasViaje;
                                    } else {
                                        datos += "Excede el precio maximo\n";
                                    }

                                    datos += "\nPrecio Total en Chiva (Hotel, Evento, Transporte): ";
                                    if (precioTotal + t.getPrecio("CHIVA") * cantidadPersonas * diasViaje < valorMaximo) {
                                        datos += precioTotal + t.getPrecio("CHIVA") * cantidadPersonas * diasViaje;
                                    } else {
                                        datos += "Excede el precio maximo\n";
                                    }

                                    datos += "\nPrecio Total en Bicicleta (Hotel, Evento, Transporte): ";
                                    if (precioTotal + t.getPrecio("BICICLETA") * cantidadPersonas * diasViaje < valorMaximo) {
                                        datos += precioTotal + t.getPrecio("BICICLETA") * cantidadPersonas * diasViaje + "\n";
                                    } else {
                                        datos += "Excede el precio maximo\n";
                                    }
                                }
                            }

                        }
                    }

                } else {
                    datos += "No hay opciones disponibles de acuerdo a los parametros indicados";
                }
            }
        }
        area.setText(datos);
        JOptionPane.showMessageDialog(null, barras);
    }

    /**
     * lista todos los hoteles
     *
     * @return
     */
    private String listarHoteles() {
        if (!hoteles.isEmpty()) {
            String datos = "";
            for (Hotel x : hoteles) {
                datos += x.getInformacion() + "\n\n";
            }
            return datos;
        } else {
            return "No hay hoteles listados";
        }
    }

    /**
     * lista los hoteles de acuerdo a la ciudad solicitada
     *
     * @param ciudad
     * @return
     */
    private String listarHoteles(String ciudad) {
        if (!hoteles.isEmpty()) {
            String datos = "";
            for (Hotel x : hoteles) {
                if (x.getCiudad().equals(ciudad)) {
                    datos += x.getInformacion() + "\n";
                }
            }
            return datos;
        } else {
            return "No hay hoteles listados para esta ciudad";
        }
    }

    /**
     * lista los hoteles de acuerdo a la ciudad y cantidad de estrellas
     * solicitada
     *
     * @param ciudad
     * @param estrellas
     * @return
     */
    private String listarHoteles(String ciudad, int estrellas) {
        if (!hoteles.isEmpty()) {
            String datos = "";
            int enumerador = 1;
            boolean disponible = false;
            for (Hotel x : hoteles) {
                if (x.getCiudad().equals(ciudad) && x.getEstrellas() == estrellas) {
                    datos += enumerador + ". " + x.getInformacion() + "\n";
                    enumerador++;
                    disponible = true;
                }
            }
            if (disponible) {
                return datos;
            } else {
                return "No hay hoteles listados para esta ciudad\ny con la calificacion en estrellas deseada";
            }
        } else {
            return "No hay hoteles listados para esta ciudad\ny con la calificacion en estrellas deseada";
        }
    }

    /**
     * lista todos los eventos
     *
     * @return
     */
    private String listarEventosCulturales() {
        if (!eventosCulturales.isEmpty()) {
            String datos = "";
            for (EventoCultural x : eventosCulturales) {
                datos += x.getInformacion() + "\n\n";
            }
            return datos;
        } else {
            return "No hay eventos culturales listados";
        }
    }

    /**
     *
     * @param ciudad
     * @return
     */
    private String listarEventosCulturales(String ciudad, LocalDate fechaViaje, LocalDate fechaRegreso) {
        if (!eventosCulturales.isEmpty()) {
            String datos = "";
            for (EventoCultural x : eventosCulturales) {
                if (x.getCiudad().equals(ciudad) && x.getFecha().isAfter(fechaViaje) && x.getFecha().isBefore(fechaRegreso)) {
                    datos += x.getInformacion() + "\n";
                }
            }
            return datos;
        } else {
            return "No hay eventos culturales listados para esta ciudad";
        }
    }

    /**
     *
     * @param ciudad
     * @return
     */
    private String listarTransporteCiudad(String ciudad) {
        for (TransporteCiudad x : transportes) {
            if (x.getCiudad().equals(ciudad)) {
                return x.getInformacionPrecios();
            }
        }
        return "No hay medios de transporte listados para esta ciudad";
    }

    private String listarAerolineas(String origen, String destino) {
        String datos = "";
        for (Aerolinea x : aerolineas) {
            if (x.consultarVuelo(origen, destino)) {
                datos += x.getNombre() + "\nPrecio: " + x.precioVuelo(origen, destino) + "\n";
            }
        }
        return datos;
    }

    /**
     *
     */
    private void realizarReserva() {
        String ciudadDestinoReserva = JOptionPane.showInputDialog("Ingrese ciudad de destino").trim().toUpperCase();

        LocalDate fechaViaje = LocalDate.parse(JOptionPane.showInputDialog("Ingrese fecha de viaje (yyyy-MM-dd)").trim());
        int diasViaje = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los dias de viaje").trim());
        LocalDate fechaRegreso = fechaViaje.plusDays(diasViaje);

        /*pregunta acerca de las estrellas del hotel deseado y lista los hoteles disponibles,
        para seleccionar solo debe introducir el nombre del hotel
         */
        int estrellasHotelReserva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese las estrellas del hotel deseado"));
        String escogerHotel = "Introduzca el nombre del hotel deseado:\n" + listarHoteles(ciudadDestinoReserva, estrellasHotelReserva);
        String nombreHotel = JOptionPane.showInputDialog(escogerHotel).toUpperCase();
        double costoHotel = 0;
        for (Hotel x : hoteles) {
            if (x.getNombre().equals(nombreHotel)) {
                costoHotel = x.getPrecio();
                break;
            }
        }

        String escogerAerolinea = "Escoja la aerolinea deseada\n" + listarAerolineas("CALI", ciudadDestinoReserva);
        String nombreAerolinea = JOptionPane.showInputDialog(escogerAerolinea).trim().toUpperCase();
        for (Aerolinea x : aerolineas) {
            if (x.getNombre().equals(nombreAerolinea)) {
                x.precioVuelo("CALI", ciudadDestinoReserva);
                break;
            }
        }

        int viajeros = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas viajar").trim());

        String escogerMedioTransporte = "Introduza el tipo de medio de transporte deseado\n" + listarTransporteCiudad(ciudadDestinoReserva);
        String tipoTransporteDeseado = JOptionPane.showInputDialog(escogerMedioTransporte).trim().toUpperCase();
        double costoTransporte = 0;
        for (TransporteCiudad x : transportes) {
            if (x.getCiudad().equals(ciudadDestinoReserva)) {
                costoTransporte = x.getPrecio(tipoTransporteDeseado);
                break;
            }
        }

        String escogerEventoCultural = "Introduzca el nombre del evento cultural a visitar\n"
                + "Digite 0 para ninguno\n"
                + listarEventosCulturales(ciudadDestinoReserva, fechaViaje, fechaRegreso);
        String infoEventosCulturales = "";
        double costoEventos = 0;
        String opcion = "";
        do {
            opcion = JOptionPane.showInputDialog(escogerEventoCultural).trim().toUpperCase();
            switch (opcion) {
                case "0":
                    break;
                default:
                    for (EventoCultural x : eventosCulturales) {
                        if (x.getNombre().equals(opcion)) {
                            infoEventosCulturales += x.getInformacion() + "\n";
                            costoEventos += x.getCosto();
                        }
                    }
            }
        } while (!opcion.equals("0"));

        String idClienteReserva = JOptionPane.showInputDialog("Ingrese la cedula para guardar la reserva").trim();

        if (fechaViaje.getDayOfMonth() >= 25) {
            JOptionPane.showMessageDialog(null, "Aplicado descuento del 15% al valor total, por concepto de viaje al final de mes a cargo de la agencia");
        }

        reservaciones.add(new Reserva(idClienteReserva, fechaViaje, fechaRegreso, viajeros, ciudadDestinoReserva, diasViaje, nombreHotel, costoHotel, nombreAerolinea, 10000, tipoTransporteDeseado, costoTransporte, infoEventosCulturales, costoEventos));
    }

    /**
     * busca una reserva de acuerdo con el numero de identidad a la cual fue
     * guardada la reserva
     */
    private void consultarReserva() {
        String buscarReserva = JOptionPane.showInputDialog("Ingrese el documento de identidad del cliente").trim();
        if (!reservaciones.isEmpty()) {
            for (Reserva x : reservaciones) {
                if (x.getIdentCliente().equals(buscarReserva)) {
                    area.setText(x.getInformacion());
                    JOptionPane.showMessageDialog(null, barras);
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "No se ha encontrado reservas con los parametros ingresados");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado reservas con los parametros ingresados");
        }

    }

    /**
     * muestra la ciudad mas solicitada para viajar y los hoteles preferidos por
     * los usuarios
     */
    private void estadisticas() {
        JOptionPane.showMessageDialog(null, "Funcion no disponible en el momento");
        /*
      for(Reserva x: reservaciones)
      {
          x.getCiudad();
      }
      
         */
    }

    /**
     * agrega un hotel, una aerolinea, un evento y medios de transportes a las
     * listas iniciales
     */
    private void listadoDePrueba() {
        hoteles.add(new Hotel(3, "INTERCONTINENTAL", "BOGOTA", 60000));
        hoteles.add(new Hotel(5, "SUPREMO", "BOGOTA", 100000));
        hoteles.add(new Hotel(3, "AMERICANO", "MEDELLIN", 50000));
        hoteles.add(new Hotel(5, "PARAISO", "MEDELLIN", 80000));
        eventosCulturales.add(new EventoCultural("CONCIERTO SHAKIRA", "BOGOTA", 25000, "5 P.M - 10 P.M", "2020-10-12", "SOACHA"));
        eventosCulturales.add(new EventoCultural("DIA DE LA INDEPENDENCIA", "BOGOTA", 0, "5 P.M - 10 P.M", "2020-07-20", "PALACIO DE NARIÑO"));
        eventosCulturales.add(new EventoCultural("FERIA DE LAS FLORES", "MEDELLIN", 20000, "5 P.M - 10 P.M", "2020-07-01", "PRINCIPALES AUTOPISTAS"));
        eventosCulturales.add(new EventoCultural("FIESTA DE LA VIRGEN DEL CARMEN", "MEDELLIN", 4000, "5 P.M - 10 P.M", "2020-07-16", "MUNICIPIO DE FRONTINO"));
        transportes.add(new TransporteCiudad("BOGOTA", 2500, 2000, 1000));
        transportes.add(new TransporteCiudad("MEDELLIN", 2200, 1600, 600));
    }

    /**
     *
     */
    private void menu() {
        String opcion;
        boolean insertarListadoPrueba = false;
        do {
            opcion = JOptionPane.showInputDialog("Viajemos por Colombia\n\n"
                    + "Ciudad Actual: Cali\n"
                    + "" + LocalDate.now() + "\n\n"
                    + "1. Ingresar Informacion\n"
                    + "2. Consultar opciones disponibles\n"
                    + "3. Listar hoteles\n"
                    + "4. Listar eventos culturales\n"
                    + "5. Realizar reservacion\n"
                    + "6. Consultar reserva\n"
                    + "7. Estadisticas\n"
                    + "8. Insertar listados de prueba\n"
                    + "9. Salir");

            switch (opcion) {
                case "1": //ingresar informacion
                    ingresarInformacion();
                    break;
                case "2": // consultar todas las opciones disponibles
                    catalogo();
                    break;
                case "3": //listar hoteles
                    area.setText(listarHoteles());
                    JOptionPane.showMessageDialog(null, barras);
                    break;
                case "4": //listar eventos culturales
                    area.setText(listarEventosCulturales());
                    JOptionPane.showMessageDialog(null, barras);
                    break;
                case "5": //realizar reserva
                    realizarReserva();
                    break;
                case "6": //consultar reservaciones
                    consultarReserva();
                    break;
                case "7": //mostrar estadisticas
                    estadisticas();
                    break;
                case "8": //insertar listado de prueba, solo una vez
                    if (!insertarListadoPrueba) {
                        listadoDePrueba();
                        insertarListadoPrueba = true;
                    }
                    break;
                case "9": //salir
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                    break;
            }
        } while (!opcion.equals("9"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AgenciaDeViajes app = new AgenciaDeViajes();
        app.menu();
    }

}
