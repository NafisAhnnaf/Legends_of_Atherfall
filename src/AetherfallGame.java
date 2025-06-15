import java.util.Scanner;
public class AetherfallGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Character selection
        System.out.println("Choose a character: 1. Mage 2. Archer 3. Warrior");
        int heroChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Character hero = null;
        String heroType = "";
        
        switch(heroChoice) {
            case 1:
                System.out.println("You've chosen Mage");
                System.out.println("Enter your mage name");
                String mageName = scanner.nextLine();
                hero = new Mage(mageName);
                heroType = "Mage";
                break;
            case 2:
                System.out.println("You've chosen Archer");
                System.out.println("Enter your archer name");
                String archerName = scanner.nextLine();
                hero = new Archer(archerName);
                heroType = "Archer";
                break;
            case 3:
                System.out.println("You've chosen Warrior");
                System.out.println("Enter your warrior name");
                String warriorName = scanner.nextLine();
                hero = new Warrior(warriorName);
                heroType = "Warrior";
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(0);
        }
        
        // Villain selection
        System.out.println("Choose a villain to fight against:");
        System.out.println("1. Dark Overlord");
        System.out.println("2. Regional Warlord");
        System.out.println("3. Corrupted Mortal");
        int villainChoice = scanner.nextInt();
        
        Character villain = null;
        
        switch(villainChoice) {
            case 1:
                villain = new DarkOverlord();
                break;
            case 2:
                villain = new RegionalWarlord();
                break;
            case 3:
                villain = new CorruptedMortal();
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(0);
        }
        
        // Game start
        System.out.println(hero.Name + " the " + heroType + " enters the world of Aetherfall!");
        System.out.println(villain.Name + " rises to spread chaos!");
        System.out.println("Battle Start!");
        
        // Game loop
        while (hero.isAlive() && villain.isAlive()) {
            System.out.print("> ");
            String action = scanner.next().toLowerCase();
            
            switch(action) {
                case "attack":
                    hero.attack(villain);
                    break;
                case "special":
                    hero.spMove(villain);
                    break;
                default:
                    System.out.println("Invalid command! Use 'attack' or 'special'");
                    continue;
            }
            
            // Display stats after hero's action
            System.out.println("--- Game Stats---");
            hero.displayStats();
            villain.displayStats();
            
            // Villain's turn if still alive
            if (villain.isAlive()) {
                // Simple AI - randomly choose between attack and special (30% chance for special)
                if (Math.random() < 0.3 && villain.Power >= villain.spMoveCost()) {
                    villain.spMove(hero);
                } else {
                    villain.attack(hero);
                }
                
                System.out.println("--- Game Stats---");
                hero.displayStats();
                villain.displayStats();
            }
        }
        
        // Game over
        System.out.println("---Results---");
        if (hero.isAlive()) {
            System.out.println(hero.Name + " wins the battle!");
            System.out.println(villain.Name + " has been defeated!");
        } else {
            System.out.println(villain.Name + " wins the battle!");
            System.out.println(hero.Name + " the " + heroType + " has been defeated!");
        }
        
        scanner.close();
    }
}