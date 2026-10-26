package serverPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Server4_1 {
    static int PORT = 1234;
    static DatagramSocket socket =null;
    static byte[] buffer = new byte[1024];

    public static void main(String[] args) throws IOException {
        try {
            socket = new DatagramSocket(null);
            socket.bind(new InetSocketAddress(PORT));
            System.out.println("[SERVEUR] en écoute sur le port "+ PORT + "...");
            while (true) {
                // Le serveur attend un paquet UDP
                DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
                socket.receive(paquet);

                int taille = paquet.getLength();
                String donnees = new String(paquet.getData(), 0, taille);
                System.out.println("Donnees reçues = " + donnees);

            }
        } catch (IOException e) {
            System.out.println("Erreur: "+e.getMessage());
        }
    }

}
