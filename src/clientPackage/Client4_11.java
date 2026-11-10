package clientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client4_11 {

    public static void main(String[] args) {

        try {

            // 1. Création du socket UDP du client

            DatagramSocket socket =
                    new DatagramSocket();


            // 2. Adresse du serveur

            InetAddress adresseServeur =
                    InetAddress.getByName("localhost");

            int portServeur = 1234;


            // 3. Scanner pour la saisie clavier

            Scanner scanner = new Scanner(System.in);


            // 4. Saisie du nom utilisateur

            System.out.print(
                    "Entrez votre nom d'utilisateur : "
            );

            String nomUtilisateur =
                    scanner.nextLine();


            // 5. Boucle d'envoi (unidirectionnel)

            while (true) {

                System.out.print(
                        nomUtilisateur + " : "
                );


                // Lecture du message
                String message =
                        scanner.nextLine();


                // Ajouter le nom utilisateur
                String messageComplet =
                        "[" + nomUtilisateur + "] : "
                                + message;


                // Conversion String -> byte[]
                byte[] bufferEnvoi =
                        messageComplet.getBytes();


                // Création du DatagramPacket
                DatagramPacket packetEnvoi =
                        new DatagramPacket(
                                bufferEnvoi,
                                bufferEnvoi.length,
                                adresseServeur,
                                portServeur
                        );


                // Envoi du message
                socket.send(packetEnvoi);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}