import java.util.*;

public class Enemy extends Character
{
    Random rnd = new Random();

    double bounty;

    public class Goblin extends Enemy
    {
        Goblin()
        {
            this.type = "Goblin";
            this.max_health = rnd.nextInt(100, 150);
            this.max_defense = rnd.nextInt(15, 25);
            this.attack = rnd.nextInt(20, 25);
            this.max_stamina = 80;
            this.stamina = max_stamina;
            this.health = max_health;
            this.defense = max_defense;
            this.bounty = Math.round((max_health * 0.15) + (max_defense * 0.15) + (attack * 0.15));
        }
    }

    public class Giant extends Enemy
    {
        Giant()
        {
            this.type = "Giant";
            this.max_health = rnd.nextInt(400, 500);
            this.max_defense = rnd.nextInt(35, 45);
            this.attack = rnd.nextInt(35, 45);
            this.max_stamina = 180;
            this.stamina = max_stamina;
            this.health = max_health;
            this.defense = max_defense;
            this.bounty = Math.round((max_health * 0.15) + (max_defense * 0.15) + (attack * 0.15));
        }
    }
    
    public class Wizard extends Enemy
    {
        Wizard()
        {
            this.type = "Wizard";
            this.max_health = rnd.nextInt(200, 250);
            this.max_defense = rnd.nextInt(15, 20);
            this.attack = rnd.nextInt(30, 45);
            this.max_stamina = 110;
            this.stamina = max_stamina;
            this.health = max_health;
            this.defense = max_defense;
            this.bounty = Math.round((max_health * 0.15) + (max_defense * 0.15) + (attack * 0.15));
        }
    }

    public Enemy getEnemy()
    {
        Enemy enemy = new Enemy();
        int choice = rnd.nextInt(10);
        if(choice < 5) enemy = new Goblin();
        else if(choice < 8) enemy = new Wizard();
        else enemy = new Giant();
        return enemy;
    }

    @Override
    public String toString() 
    {
        return "Class: " + type + "\nHealth: " + health + "\nDefense: " + defense + "\nStamina: " + stamina +  "\nAttack: " + weapon.attack + "\nWeapon: " + weapon.type;
    }

}
