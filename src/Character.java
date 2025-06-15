import java.util.Scanner;

abstract class Character {
    protected String Name;
    protected int Health;
    protected int Power;
    protected boolean isAlive;  
    protected double Weakened = 1.0;

    public Character(String name, int health, int power) {
        this.Name = name;
        this.Health = health;
        this.Power = power;
        this.isAlive = true; 
    }

    public boolean isAlive() {
        return this.Health > 0;
    }
    
    public abstract void spMove(Character enemy);
    public abstract int StdAtkDmg();
    public abstract int StdAtkCost();
    public abstract int spMoveCost();

    public void displayStats() {
        System.out.println(this.Name + " - Health: " + this.Health + ", Power: " + this.Power);
    }

    public void attack(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        if (this.Power >= this.StdAtkCost()) {
            int damage = (int)(this.StdAtkDmg() * this.Weakened);
            enemy.takeDamage(damage);
            this.Power -= this.StdAtkCost();
            System.out.println(this.Name + " used Attack on " + enemy.Name);
            System.out.println(this.Name + " caused " + damage + " damage");
            this.Weakened = 1.0;
        } else {
            System.out.println(this.Name + " does not have enough power!");
        }
    }

    public void takeDamage(int damage) {
        this.Health -= damage;
        if (this.Health <= 0) {
            this.Health = 0;
            this.isAlive = false;
        }
    }
}

class Mage extends Character {
    public Mage(String name) {
        super(name, 80, 100);
    }
    
    @Override
    public int StdAtkDmg() {
        return 20;
    }
    
    @Override
    public int StdAtkCost() {
        return 10;
    }
    
    @Override
    public int spMoveCost() {
        return 30;
    }
    
    @Override
    public void spMove(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        if (this.Power >= spMoveCost()) {
            enemy.takeDamage(40);
            this.Power -= spMoveCost();
            System.out.println(this.Name + " used castSpell on " + enemy.Name + ".");
            System.out.println(this.Name + " caused 40 damage.");
        } else {
            System.out.println(this.Name + " doesn't have enough power for special move!");
        }
    }
}

class Archer extends Character {
    public Archer(String name) {
        super(name, 100, 100);
    }
    
    @Override
    public int StdAtkDmg() {
        return 15;
    }
    
    @Override
    public int StdAtkCost() {
        return 8;
    }
    
    @Override
    public int spMoveCost() {
        return 25; // Cost for quickShot, multiShot has different cost
    }
    
    @Override
    public void spMove(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose special move: 1. quickShot (25 power) 2. multiShot (50 power)");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            if (this.Power >= 25) {
                enemy.takeDamage(30);
                this.Power -= 25;
                System.out.println(this.Name + " used quickShot on " + enemy.Name + ".");
                System.out.println(this.Name + " caused 30 damage.");
            } else {
                System.out.println(this.Name + " doesn't have enough power for quickShot!");
            }
        } else if (choice == 2) {
            if (this.Power >= 50) {
                enemy.takeDamage(60);
                this.Power -= 50;
                System.out.println(this.Name + " used multiShot on " + enemy.Name + ".");
                System.out.println(this.Name + " caused 60 damage.");
            } else {
                System.out.println(this.Name + " doesn't have enough power for multiShot!");
            }
        }
    }
}

class Warrior extends Character {
    public Warrior(String name) {
        super(name, 120, 100);
    }
    
    @Override
    public int StdAtkDmg() {
        return 12;
    }
    
    @Override
    public int StdAtkCost() {
        return 5;
    }
    
    @Override
    public int spMoveCost() {
        return 35;
    }
    
    @Override
    public void spMove(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        if (this.Power >= spMoveCost()) {
            if (enemy.Health < this.Health) {
                System.out.println(this.Name + " used Shield - attack blocked!");
            } else {
                enemy.takeDamage(35);
                System.out.println(this.Name + " used mightyStrike on " + enemy.Name + ".");
                System.out.println(this.Name + " caused 35 damage.");
            }
            this.Power -= spMoveCost();
        } else {
            System.out.println(this.Name + " doesn't have enough power for special move!");
        }
    }
}

class DarkOverlord extends Character {
    public DarkOverlord() {
        super("DarkOverlord", 200, 150);
    }
    
    @Override
    public int StdAtkDmg() {
        return 25;
    }
    
    @Override
    public int StdAtkCost() {
        return 17;
    }
    
    @Override
    public int spMoveCost() {
        return 40;
    }
    
    @Override
    public void spMove(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        if (this.Power >= spMoveCost()) {
            enemy.takeDamage(50);
            enemy.Weakened = 0.5;
            this.Power -= spMoveCost();
            System.out.println(this.Name + " used summonDarkness on " + enemy.Name + ".");
            System.out.println(this.Name + " caused 50 damage and weakened next attack.");
        } else {
            System.out.println(this.Name + " doesn't have enough power for special move!");
        }
    }
}

class RegionalWarlord extends Character {
    public RegionalWarlord() {
        super("RegionalWarlord", 130, 110);
    }
    
    @Override
    public int StdAtkDmg() {
        return 20;
    }
    
    @Override
    public int StdAtkCost() {
        return 10;
    }
    
    @Override
    public int spMoveCost() {
        return 35;
    }
    
    @Override
    public void spMove(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        if (this.Power >= spMoveCost()) {
            enemy.takeDamage(30);
            enemy.Weakened = 0; // Block next attack
            this.Power -= spMoveCost();
            System.out.println(this.Name + " used toxicAura on " + enemy.Name + ".");
            System.out.println(this.Name + " caused 30 damage and blocked next attack.");
        } else {
            System.out.println(this.Name + " doesn't have enough power for special move!");
        }
    }
}

class CorruptedMortal extends Character {
    public CorruptedMortal() {
        super("CorruptedMortal", 90, 90);
    }
    
    @Override
    public int StdAtkDmg() {
        return 18;
    }
    
    @Override
    public int StdAtkCost() {
        return 9;
    }
    
    @Override
    public int spMoveCost() {
        return 20;
    }
    
    @Override
    public void spMove(Character enemy) {
        if (!this.isAlive || !enemy.isAlive()) return;
        
        if (this.Power >= spMoveCost()) {
            enemy.takeDamage(25);
            this.Power -= spMoveCost();
            System.out.println(this.Name + " used curse on " + enemy.Name + ".");
            System.out.println(this.Name + " caused 25 damage.");
        } else {
            System.out.println(this.Name + " doesn't have enough power for special move!");
        }
    }
}

