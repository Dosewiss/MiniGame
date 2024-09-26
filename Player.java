import java.util.*;

public class Player extends Character
{

    Scanner sc = new Scanner(System.in);
    Random rnd = new Random();

    public static double gold = 100;

    public class Warrior extends Player
    {
        Warrior(Character character)
        {
            this.weapon = new Weapons();
            this.type = "Warrior";
            this.attack = 25;
            this.max_health = 300; 
            this.health = max_health;
            this.max_defense = 40;
            this.defense = max_defense;
            this.max_stamina = 150;
            this.stamina = max_stamina;
        }
    }
    
    public class Archer extends Player
    {
        Archer(Character character)
        {
            this.weapon = new Weapons();
            this.type = "Archer";
            this.attack = 40;
            this.max_defense = 25;
            this.max_health = 200;
            this.max_stamina = 120;
            this.stamina = max_stamina;
            this.health = max_health;
            this.defense = max_defense;
        }
    }

    public Player getPlayer()
    {
        int choice = 0;
        Player hero = new Player();
        while(choice != 1 && choice != 2)
        {
            System.out.print("\nChoose a hero class! \n1-Warrior   2-Archer : ");
            choice = sc.nextInt();
            if(choice == 1)
            {
                hero = new Player.Warrior(hero);
            }
            else if(choice == 2)
            {
                hero = new Player.Archer(hero);
            }
            else System.out.println("\nInvalid choice");
        }
        return hero;
    }

    @Override
    public String toString() 
    {
        return "Class: " + type + "\nHealth: " + health + "\nDefense: " + defense + "\nStamina: " + stamina + "\nAttack: " + weapon.attack + "\nWeapon: " + weapon.type;
    }
}
