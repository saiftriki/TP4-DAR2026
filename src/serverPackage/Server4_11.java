package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Server4_11 {

    public static void main(String[] args) {

        try {

            // 1. Création du socket UDP du serveur

            DatagramSocket socket = new DatagramSocket(null);


            // 2. Adresse et port du serveur

            InetSocketAddress adresseServeur =
                    new InetSocketAddress("0.0.0.0", 1234);


            // 3. Liaison du socket à l'adresse

            socket.bind(adresseServeur);

            System.out.println("Serveur UDP démarré sur le port 1234...");
            System.out.println("En attente des messages du client...");


            // 4. Boucle de réception (unidirectionnel)

            while (true) {

                // Tableau d'octets pour recevoir le message
                byte[] bufferReception = new byte[1024];


                // Création du DatagramPacket
                DatagramPacket packetReception =
                        new DatagramPacket(
                                bufferReception,
                                bufferReception.length
                        );


                // Attente d'un message
                socket.receive(packetReception);


                // Conversion byte[] -> String
                String message =
                        new String(
                                packetReception.getData(),
                                0,
                                packetReception.getLength()
                        );


                // Affichage de l'adresse de l'expéditeur et du message
                System.out.println();

                System.out.println(
                        "Message reçu de "
                                + packetReception.getAddress()
                                + ":"
                                + packetReception.getPort()
                );

                System.out.println(message);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
