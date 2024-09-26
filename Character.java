public class Character
{

    double max_health;
    double max_defense;
    double max_stamina;

    double attack;

    double health;
    double defense;
    double stamina;

    Weapons weapon;
    String type;

    public String showHealth()
    {
        return type + " Health: " + health;
    }
}