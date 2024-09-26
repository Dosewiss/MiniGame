import java.util.*;

public class Weapons
{

    double attack;
    double penetration;
    String type;
    String skill[][] = new String[2][2]; //SKILL NAMES AND STAMINA VALUES
    String skillNames;

    Random rnd = new Random();
    Scanner sc = new Scanner(System.in);

    public class Fist extends Weapons
    {
        Fist()
        {
            System.out.println("This character hadn't been chosen for any weapon so fist has been given to this character!");
            this.type = "Fist";
            this.attack = 0;
        }
        Fist(Character character)
        {
            this.type = "Fist";
            this.attack = character.attack;
            this.penetration = 5;
            this.skill[0][0] = "Single";
            this.skill[1][0] = "AOE";
            this.skill[0][1] = "15";
            this.skill[1][1] = "30";
            this.skillNames = "(1-Punch | 2-Slam Ground) : ";
        }

        public double chooseSkillPlayer(Character enemy, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.80 + (enemy.defense * (penetration * 0.06 / penetration * 0.06 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.40 + (enemy.defense * (penetration * 0.08 / penetration * 0.08 + 1)));
            else return 0;
        }

        @Override
        public double chooseSkillEnemy(Character player, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.75 + (player.defense * (penetration * 0.13 / penetration * 0.13 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.55 + (player.defense * (penetration * 0.02 / penetration * 0.02 + 1)));
            return 0;
        }
    }

    public class Bow extends Weapons
    {
        Bow(Character character)
        {
            this.type = "Bow";
            this.attack = character.attack;
            this.penetration = 15;
            this.skill[0][0] = "Single";
            this.skill[1][0] = "AOE";
            this.skill[0][1] = "20";
            this.skill[1][1] = "30";
            this.skillNames = "(1-Penetrating Shot | 2-Arrow Rain) : ";
        }

        @Override
        public double chooseSkillPlayer(Character enemy, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.75 + (enemy.defense * (penetration * 0.13 / penetration * 0.13 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.55 + (enemy.defense * (penetration * 0.03 / penetration * 0.03 + 1)));
            else return 0;
        }

        @Override
        public double chooseSkillEnemy(Character player, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.75 + (player.defense * (penetration * 0.13 / penetration * 0.13 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.55 + (player.defense * (penetration * 0.03 / penetration * 0.03 + 1)));
            return 0;
        }
    }

    public class Sword extends Weapons
    {
        Sword(Character character)
        {
            this.type = "Sword";
            this.attack = character.attack;
            this.penetration = 10;
            this.skill[0][0] = "Single";
            this.skill[1][0] = "Single";
            this.skill[0][1] = "15";
            this.skill[1][1] = "20";
            this.skillNames = "(1-Pierce | 2-Slice) : ";
        }

        public double chooseSkillPlayer(Character enemy, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.65 + (enemy.defense * (penetration * 0.1 / penetration * 0.1 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.85 + (enemy.defense * (penetration * 0.05 / penetration * 0.05 + 1)));
            else return 0;
        }

        @Override
        public double chooseSkillEnemy(Character player, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.65 + (player.defense * (penetration * 0.1 / penetration * 0.1 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.85 + (player.defense * (penetration * 0.05 / penetration * 0.05 + 1)));
            return 0;
        }
    }
    
    public class Spear extends Weapons
    {
        Spear(Character character)
        {
            this.type = "Spear";
            this.attack = character.attack;
            this.penetration = 20;
            this.skill[0][0] = "Single";
            this.skill[1][0] = "Single";
            this.skill[0][1] = "15";
            this.skill[1][1] = "25";
            this.skillNames = "(1-Stab | 2-Swing) : ";
        }

        public double chooseSkillPlayer(Character enemy, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.45 + (enemy.defense * (penetration * 0.18 / penetration * 0.18 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.75 + (enemy.defense * (penetration * 0.04 / penetration * 0.04 + 1)));
            else return 0;
        }

        @Override
        public double chooseSkillEnemy(Character player, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.45 + (player.defense * (penetration * 0.18 / penetration * 0.18 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.75 + (player.defense * (penetration * 0.04 / penetration * 0.04 + 1)));
            return 0;
        }
    }

    public class Staff extends Weapons
    {
        Staff(Character character)
        {
            this.type = "Staff";
            this.attack = Math.round(character.attack * 0.95);
            this.penetration = 5;
            this.skill[0][0] = "Single";
            this.skill[1][0] = "AOE";
            this.skill[0][1] = "10";
            this.skill[1][1] = "20";
            this.skillNames = "(1-Fireball | 2-Beam of Light) : ";
        }

        @Override
        public double chooseSkillPlayer(Character enemy, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.90 + (enemy.defense * (penetration * 0.03 / penetration * 0.03 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.45 + (enemy.defense * (penetration * 0.20 / penetration * 0.20 + 1)));
            else return 0;
        }

        @Override
        public double chooseSkillEnemy(Character player, int choice)
        {
            double attackPower = attack;
            if(choice == 0) return Math.round(attackPower * 0.90 + (player.defense * (penetration * 0.03 / penetration * 0.03 + 1)));
            else if(choice == 1) return Math.round(attackPower * 0.45 + (player.defense * (penetration * 0.20 / penetration * 0.20 + 1)));
            return 0;
        }
    }

    public Weapons getWeapons(Character character)
    {
        Weapons weapon = new Weapons();
        if(character.type == "Archer") weapon = new Bow(character);
        else if(character.type == "Warrior") weapon = new Sword(character);
        else if(character.type == "Giant") weapon = new Fist(character);
        else if(character.type == "Wizard") weapon = new Staff(character);
        else if(character.type == "Goblin")
        {
            int choice = rnd.nextInt(3);
            if(choice == 0) weapon = new Sword(character);
            else if(choice == 1) weapon = new Bow(character);
            else if(choice == 2) weapon = new Spear(character);
        }
        else weapon = new Fist();
        return weapon;
    }

    public double chooseSkillPlayer(Character enemy, int choice)
    {
        return 0;
    }

    public double chooseSkillEnemy(Character player, int choice)
    {
        return 0;
    }

}