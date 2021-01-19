package db.inicial.util;

public class PrintUtil {
    public static void drawSign(String signMessage, String symbol) {
        drawLine(signMessage.length(), symbol);
        System.out.println(signMessage.toUpperCase());
        drawLine(signMessage.length(), symbol);
        System.out.println();
    }

    public static void drawLine(int longitud, String simbolo) {
        for (int i = 0; i < longitud; i++) {
            System.out.print(simbolo);

        }
        System.out.println();

    }

    public static void printMessage(String message){
        System.out.println(message);
        System.out.println();

    }
}
