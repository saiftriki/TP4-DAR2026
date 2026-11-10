package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;

public class Server4_12 {

    // Liste des adresses des clients connus
    static ArrayList<SocketAddress> listeClients =
            new ArrayList<SocketAddress>();

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
            System.out.println("En attente des clients...");


            // 4. Boucle de réception et de diffusion

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


                // Adresse de l'expéditeur
                SocketAddress adresseExpediteur =
                        packetReception.getSocketAddress();


                // Enregistrement du client s'il n'est pas déjà connu
                if (!listeClients.contains(adresseExpediteur)) {

                    listeClients.add(adresseExpediteur);

                    System.out.println(
                            "Nouveau client : " + adresseExpediteur
                    );
                }


                // Conversion byte[] -> String
                String message =
                        new String(
                                packetReception.getData(),
                                0,
                                packetReception.getLength()
                        );


                // Affichage sur le serveur
                System.out.println();

                System.out.println(
                        "Message reçu de "
                                + packetReception.getAddress()
                                + ":"
                                + packetReception.getPort()
                );

                System.out.println(message);


                // Diffusion à tous les autres clients
                byte[] bufferEnvoi = message.getBytes();

                for (SocketAddress adresseClient : listeClients) {

                    // Ne pas renvoyer le message à l'expéditeur
                    if (adresseClient.equals(adresseExpediteur)) {

                        continue;
                    }


                    // Création du DatagramPacket
                    DatagramPacket packetEnvoi =
                            new DatagramPacket(
                                    bufferEnvoi,
                                    bufferEnvoi.length,
                                    adresseClient
                            );


                    // Envoi du message
                    socket.send(packetEnvoi);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}