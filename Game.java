import java.util.*;

public class Game
{
    // '''
    // TO DO LIST:
    // ---------------------------------
    // 1-) ADD FULL FUNCTIONAL STAMINA SYSTEM || DONE!!!!
    // 2-) ADD INVENTORY
    // 3-) MAKE THE GOLD SYSTEM USABLE
    // 4-) ADD TRY CATCH FOR USER INPUTS
    // 5-) ADD MAP SYSTEM
    // 6-) ADD REST SYSTEM
    // 7-) ADD RANDOM EVENTS
    // 8-) ADD RESCUABLE COMPANIONS
    // 9-) IMPROVE PLAYER, ENEMY AND WEAPON SYSTEM
    // ---------------------------------
    // '''

    public static Scanner sc = new Scanner(System.in);

    public static Random rnd = new Random();

    //----------------  |
    //PLAYER FUNCTIONS  |
    //----------------  V

    public static ArrayList<Player> createCharacter(ArrayList<Player> character) //PLAYER CHARACTER CREATOR
    {
        character.add(new Player().getPlayer());  
        character.get(0).weapon = createWeapon(character.get(0));
        return character;
    }

    public static void attackPlayer(Character player, ArrayList<Enemy> enemies) //PLAYER ATTACK FUNCTION
    {
        System.out.println("\nYou chose to attack!");
        int choice;
        do
        {
            System.out.print("Choose your skill! " + player.weapon.skillNames);
            choice = sc.nextInt() - 1;
            if(Integer.valueOf(player.weapon.skill[choice][1]) > player.stamina) System.out.println("You don't have enough stamina to perform this skill!");
        }while(Integer.valueOf(player.weapon.skill[choice][1]) > player.stamina);
        int dice;
        
        if(player.weapon.skill[choice][0] == "AOE")
        {
            dice = rollDice();

            for(int i = 0; i < enemies.size(); i++)
            {
                double hit = player.weapon.chooseSkillPlayer(enemies.get(i), choice);
                hit = Math.round(Math.log10(dice) * hit) - enemies.get(i).defense;
                if(hit <= 0)
                {
                    System.out.println(enemies.get(i).type + " dodged your attack!");
                }
                else 
                {
                    enemies.get(i).health -= hit;
                    System.out.println("You hit " + hit + " to the " + enemies.get(i).type +"!");
                }
                
            }
        }

        else
        {
            System.out.println("Choose an enemy to attack!");
            showEnemies(enemies);
            int chooseEnemy = sc.nextInt() - 1;

            dice = rollDice();

            double hit = player.weapon.chooseSkillPlayer(enemies.get(chooseEnemy), choice);
            hit = Math.round(Math.log10(dice) * hit) - enemies.get(chooseEnemy).defense;

            if(hit <= 0) System.out.println(enemies.get(chooseEnemy).type + " dodged your attack!");

            else 
            {
                enemies.get(chooseEnemy).health -= hit;
                System.out.println("You hit " + hit + " to the " + enemies.get(chooseEnemy).type +"!");
            }
        }

        player.stamina -= Integer.valueOf(player.weapon.skill[choice][1]);

    }

    public static void defensePlayer(Character player) //PLAYER DEFENSE FUNCTION
    {
        System.out.println("You chose to defend yourself!");
        System.out.println("Press any key to throw the dice!");
        sc.next();
        int dice = rnd.nextInt(10);
        System.out.println("\nYou rolled " + dice + "!");
        double increment = Math.round(player.max_defense * (dice * 0.13 / (dice * 0.13 + 1)));
        System.out.println("Your increment increased by " + increment + "!");
        player.defense += increment;
    }

    public static void defenseArrangerPlayers(ArrayList<Player> players)
    {
        for(int i = 0; i < players.size(); i++) players.get(i).defense = players.get(i).max_defense;
    }

    public static void staminaArrangerPlayers(ArrayList<Player> players)
    {
        for(int i = 0; i < players.size(); i++) 
        {
            players.get(i).stamina += Math.round(players.get(i).max_stamina * 0.05);
            if(players.get(i).stamina > players.get(i).max_stamina) players.get(i).stamina -= (players.get(i).stamina - players.get(i).max_stamina);
        }
        
    }

    public static int turnPlayer() //PLAYER TURN FUNCTION
    {
        int choice;
        do
        {
            System.out.print("\n1-Attack | 2-Defense : ");
            choice = sc.nextInt();
        }while(choice != 1 && choice != 2);
        return choice;
    }

    public static boolean healthCheckerPlayers(ArrayList<Player> players) //PLAYER HEALTH CHECKER
    {
        int num = 0;
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).health <= 0) num++;
        }
        if(num == players.size()) return true;
        return false;
    }

    public static double goldArranger(double gold, ArrayList<Enemy> array) //FUNCTION THAT ADDS GOLD TO THE PLAYER AFTER KILLING AN ENEMY
    {   
        for(int i = 0; i < array.size(); i++)
        {
            if(array.get(i).health <= 0) 
            {
                System.out.println("YOU KILLED " + array.get(i).type + "!\nYOU EARNED " + array.get(i).bounty + " GOLD!");
                gold += array.get(i).bounty;
            }
        }
        return Math.round(gold);
    }

    public static int rollDice() //PLAYER DICE ROLLER
    {
        System.out.print("\nPress any key to throw the dice!");
        sc.next();

        System.out.println("\nDice is rolling");
        int dice = rnd.nextInt(1, 21);
        System.out.println("\nYou rolled " + dice + "!");
        return dice;
    }

    //---------------    |
    //ENEMY FUNCTIONS    |
    //----------------   V

    public static Character createEnemy(Character character) //ENEMY RANDOMIZER
    {
        return character = new Enemy().getEnemy();
    }
    
    public static void attackEnemy(ArrayList<Player> players, Character enemy) //ENEMY ATTACK FUNCTION
    {
        System.out.println("\nEnemy chose to attack!");
        int choice;
        do
        {
            choice = rnd.nextInt(enemy.weapon.skill.length);
        }while(Integer.valueOf(enemy.weapon.skill[choice][1]) > enemy.stamina);

        if(enemy.weapon.skill[choice][0] == "AOE")
        {
            System.out.println("\nEnemy rolling a dice");
            int dice = rnd.nextInt(1, 21);
            System.out.println("\nEnemy rolled " + dice + "!");

            for(int i = 0; i < players.size(); i++)
            {
                double hit = enemy.weapon.chooseSkillEnemy(players.get(i), choice);
                hit = Math.round(Math.log10(dice) * hit) - players.get(i).defense;
                if(hit <= 0) System.out.println(players.get(i).type + " dodged the attack of the " + enemy.type +"!");
                
                else 
                {
                    players.get(i).health -= hit;
                    System.out.println(enemy.type + " hit " + hit + " to the " + players.get(i).type +"!");
                }
            }
        }

        else
        {
            int chosePlayer = rnd.nextInt(players.size());
            System.out.println(enemy.type + " chose " + players.get(chosePlayer).type + " to attack");
            System.out.println("\nEnemy rolling a dice");
            int dice = rnd.nextInt(1, 21);
            System.out.println("\nEnemy rolled " + dice + "!");

            double hit = enemy.weapon.chooseSkillEnemy(players.get(chosePlayer), choice);
            hit = Math.round(Math.log10(dice) * hit) - players.get(chosePlayer).defense;

            if(hit <= 0) System.out.println( players.get(chosePlayer).type + " dodged your attack!");

            else 
            {
                players.get(chosePlayer).health -= hit;
                System.out.println(enemy.type + " hit " + hit + " to the " + players.get(chosePlayer).type +"!");
            }
        }

        enemy.stamina -= Integer.valueOf(enemy.weapon.skill[choice][1]);

    }

    public static void defenseEnemy(Character enemy) //ENEMY DEFENSE FUNCTION
    {
        System.out.println("\n"+ enemy.type + " chose to defend itself!");
        int dice = rnd.nextInt(10);
        System.out.println("\n\n"+ enemy.type + "  rolled " + dice + "!");
        double increment = Math.round(enemy.max_defense * (dice * 0.13 / (dice * 0.13 + 1)));
        System.out.println("\n\nDefense of "+ enemy.type +" increased by " + increment + "!");
        enemy.defense += increment;
    }

    public static int turnEnemy(Character enemy) //ENEMY TURN FUNCTION
    {
        int choice = rnd.nextInt(11);
        choice = (enemy.health < enemy.max_health * 0.2) ? (choice = (choice > 5) ? 1:2) : (choice = (choice > 3) ? 1:2);
        return choice;
    }

    public static void showEnemies(ArrayList<Enemy> enemies) //ENEMIES PRINT FUNCTION
    {
        System.out.println("--------------");
        for(int i = 0; i < enemies.size(); i++)
        {
            System.out.println((i+1) + "." + enemies.get(i).type + " : Health : " + enemies.get(i).health);
        }
        System.out.println("--------------");
    }

    public static ArrayList<Enemy> createEnemies(ArrayList<Enemy> array) //GROUP OF ENEMY CREATING FUNCTION
    {
        int enemyNum = rnd.nextInt(1,4);
        for(int i = 0; i < enemyNum; i++)
        {
            Character temp = new Enemy();
            temp = createEnemy(temp);
            temp.weapon = createWeapon(temp);
            array.add((Enemy) temp);
        }
        return array;
    }
    
    public static void checkHealthEnemies(ArrayList<Enemy> array) //CHECKING HEALTH OF ALL ENEMIES
    {
        ListIterator<Enemy> it = array.listIterator();

        for(int i = 0; i < array.size(); i++)
        {
            if(array.get(i).health <= 0) System.out.println(array.get(i).type + " has defeated!");
        }

        while(it.hasNext())
        {
            if(it.next().health <= 0) it.remove();
        }
    }

    public static void defenseArrangerEnemies(ArrayList<Enemy> array) //FUNCTION THAT ARRANGES ENEMIES DEFENSE AFTER CHOSING DEFENSE
    {
        for(int i = 0; i < array.size(); i++) array.get(i).defense = array.get(i).max_defense;
    }

    public static void staminaArrangerEnemies(ArrayList<Enemy> array) //FUNCTION THAT ARRANGES ENEMIES DEFENSE AFTER CHOSING DEFENSE
    {
        for(int i = 0; i < array.size(); i++)
        {
            array.get(i).stamina += Math.round(array.get(i).max_stamina * 0.05);
            if(array.get(i).stamina > array.get(i).max_stamina) array.get(i).stamina -= (array.get(i).stamina - array.get(i).max_stamina);
        }
        
    }

    public static void enemiesArranger(ArrayList<Enemy> array) //ARRANGING ENEMIES HEALTH AND DEFENSE SIMULTANEUOUSLY
    {
        checkHealthEnemies(array);
        defenseArrangerEnemies(array);
        staminaArrangerEnemies(array);
    }

    //----------------  |
    //OTHER FUNCTIONS   |
    //----------------  V

    public static Weapons createWeapon(Character character) //WEAPON CREATOR
    {
        return character.weapon = new Weapons().getWeapons(character);
    }

    public static void printFighters(ArrayList<Enemy> enemies, ArrayList<Player> heros) //FUNCTION THAT PRINTS ALL CHARACTER THAT FIGHTS
    {
        ListIterator<Enemy> enemyIt = enemies.listIterator();
        ListIterator<Player> playerIt = heros.listIterator();

        
        System.out.println("\n\n");
        while (playerIt.hasNext()) 
        {
            System.out.println(playerIt.next());
            System.out.println("--------------");
        }
        System.out.println("\nOpponents are : \n");
        System.out.println("--------------");
        while(enemyIt.hasNext())
        {
            System.out.println(enemyIt.next());
            System.out.println("--------------");
        }
    }

    public static void printPlayers(ArrayList<Player> players) //FUNCTION THAT PRINTS ALL PLAYERS AFTER AN EVENT
    {
        for(int i = 0; i < players.size(); i++)
        {
            System.out.println(players.get(i));
            System.out.println("--------------");
        }
    }

    //----------------         |
    //RANDOM EVENT FUNCIONS    |
    //----------------         V

    public static void fightRandomizer(ArrayList<Player> heros) //CREATING A RANDOM FIGHT
    {
        ArrayList<Enemy> enemies= new ArrayList<Enemy>();
        enemies = createEnemies(enemies);

        printFighters(enemies, heros);

        while(true)
        {
            for(int i = 0; i < heros.size(); i++)
            {
                if(heros.get(i).health > 0)
                {
                    switch (turnPlayer()) 
                    {
                        case 1:
                            if(Integer.valueOf(heros.get(i).weapon.skill[0][1]) > heros.get(i).stamina && Integer.valueOf(heros.get(i).weapon.skill[1][1]) > heros.get(i).stamina)
                            {
                                System.out.println("You don't have enough stamina to attack!");
                                defensePlayer(heros.get(i));
                            }
                            else attackPlayer(heros.get(i), enemies);
                            break;
                        case 2:
                            defensePlayer(heros.get(i));
                    }
                    Player.gold = goldArranger(Player.gold, enemies);
                }
                checkHealthEnemies(enemies);
            }
            
            enemiesArranger(enemies);
            if(enemies.size() == 0) break;

            for(int i = 0; i < enemies.size(); i++)
            {
                System.out.println("-----------------");
                switch (turnEnemy(enemies.get(i))) 
                {
                    case 1:
                        if(Integer.valueOf(enemies.get(i).weapon.skill[0][1]) > enemies.get(i).stamina && Integer.valueOf(enemies.get(i).weapon.skill[1][1]) > enemies.get(i).stamina)
                        {
                            System.out.println("Enemy doesn't have enough stamina to attack!");
                            defenseEnemy(enemies.get(i));
                        }
                        else attackEnemy(heros, enemies.get(i));
                        break;
                    case 2:
                        defenseEnemy(enemies.get(i));
                        break;
                }
            }

            defenseArrangerPlayers(heros);
            staminaArrangerPlayers(heros);
            if(healthCheckerPlayers(heros)) break;

            System.out.println("-----------------");
            printFighters(enemies, heros);
        }

        if(healthCheckerPlayers(heros)) System.out.println("\nYOU LOSE!!");
        else if(enemies.size() == 0) System.out.println("\nYOU WIN!!");
    }

    //----------------  |
    //MAIN FUNCTION     |
    //----------------  V

    public static void main(String[] args) 
    {
        ArrayList<Player> heros = new ArrayList<Player>();

        heros = createCharacter(heros);

        while(true)
        {
            fightRandomizer(heros);
            printPlayers(heros);
            if(healthCheckerPlayers(heros)) 
            {
                System.out.println("GAME IS OVER!");
                break;
            }
            System.out.println("\nPress any key to continue!!");
            sc.next();
        }
    }    

}