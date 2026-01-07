package Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever retriever = new DataRetriever();
        // Test : récupération d'une équipe par ID
        Team team = retriever.findTeamById(1);

        if (team != null) {
            System.out.println("Team : " + team.getName() + " (" + team.getContinent() + ")");
            System.out.println("With : " + team.getPlayersCount() + " players");
            System.out.println("Players in the team:");

            for (Player p : team.getPlayers()) {
                System.out.println(" - " + p.getName() +
                        " | Position: " + p.getPlayerPosition() +
                        " | Team: " + p.getTeamName());
            }
        } else {
            System.out.println("Team not found with ID 1.");
        }

        // Test : pagination des joueurs
        System.out.println("\n=== Test page 1, size 10 ===");
        List<Player> playersPage1 = retriever.findPlayers(1, 10);  // <-- Correction ici
        afficherJoueurs(playersPage1);

        // Tu peux ajouter d'autres tests si tu veux
        System.out.println("\n=== Test page 2, size 5 ===");
        List<Player> playersPage2 = retriever.findPlayers(2, 5);

        afficherJoueurs(playersPage2);
    }
    private static void afficherJoueurs(List<Player> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("Aucun joueur trouvé.");
            return;
        }

        System.out.println("Nombre de joueurs retournés : " + list.size());
        System.out.println("--------------------------------------------------");
        for (Player p : list) {
            System.out.printf("ID: %3d | Nom: %-20s | Position: %s%n",
                    p.getId(),
                    p.getName(),
                    p.getPlayerPosition());
            // Tu peux ajouter d'autres infos si tu veux :
            // System.out.printf("... | Âge: %d | Équipe ID: %d%n", p.getAge(), p.getTeamId());
        }
        System.out.println("--------------------------------------------------");
    }

}




