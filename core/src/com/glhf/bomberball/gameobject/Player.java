package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.maze.cell.Cell;

import java.util.ArrayList;
import java.util.Observer;

import com.glhf.bomberball.Directions;

public class Player extends Character {

    private ArrayList<Observer> observers;
    private boolean active;

    private int initial_bomb_number;
    private int initial_bomb_range;

    private transient int bombs_remaining;

    public transient int bonus_bomb_number = 0;
    public transient int bonus_bomb_range = 0;
    public transient int bonus_moves = 0;

    public Player(String player_skin,
                  int life,
                  int initial_moves,
                  int initial_bomb_number,
                  int initial_bomb_range)
    {
        super(player_skin, life, initial_moves);
        this.observers = new ArrayList<Observer>();
        this.active = false;
        this.initial_bomb_number = initial_bomb_number;
        this.initial_bomb_range = initial_bomb_range;
        initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    /**
     * initiate the turn of the player, initialize the number of bombs and the number of moves
     */
    @Override
    public void initiateTurn() {
        active = true;
        moves_remaining = initial_moves + bonus_moves;
        bombs_remaining = initial_bomb_number + bonus_bomb_number;
        this.notifyObservers();
    }

    @Override
    public boolean move(Directions dir)
    {
        if (moves_remaining > 0 && super.move(dir)) {
            moves_remaining--;
            this.notifyObservers();
            return true;
        }
        return false;
    }

    /**
     * The player create a bomb and put it on the square given
     * @param dir
     * @return a new Bomb
     */
    public void dropBomb(Directions dir) {
        if (bombs_remaining > 0) {
            Cell dest_cell = cell.getAdjacentCell(dir);
            if (dest_cell != null && dest_cell.isWalkable()) {
                bombs_remaining--;
                Bomb bomb = new Bomb(1, initial_bomb_range + bonus_bomb_range);
                dest_cell.addGameObject(bomb);
                this.notifyObservers();
            }
        }
    }

    public void interactWithCell(Cell cell) {
        for (Bonus bonus : cell.getInstancesOf(Bonus.class)) {
            bonus.dispose();
            bonus.applyEffect(this);
        }
    }

    public int getNumberBombRemaining() {
        return bombs_remaining;
    }

    public int getNumberMoveRemaining() {
        return moves_remaining;
    }

    public int getBombRange() {
        return bonus_bomb_range+initial_bomb_range;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean b) {
        this.active = b;
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(null, this);
        }
    }
}

