package com.glhf.bomberball.gameobject;

import com.glhf.bomberball.Constants;
import com.glhf.bomberball.Graphics;
import com.glhf.bomberball.maze.Cell;
import com.glhf.bomberball.maze.Maze;

import java.util.ArrayList;

public class Bomb extends GameObject {
    // attributes
    private int damage;
    private int range;

    public Bomb()
    {

    }

    /**
     * constructor
     * @param position_x x axis position of the bomb
     * @param position_y y axis position of the bomb
     * @param range number of squares reached by the bomb in the four directions ( north, east, sout, west)
     * @return Bomb
     */
    public Bomb(int position_x, int position_y, int range) {
        super(position_x, position_y);
        // initially, bomb inflict 1 getDamage
        this.damage=Constants.config_file.getIntAttribute("bomb_damage");
        this.range=range;
        this.life = 1;
        sprite = Graphics.Sprites.get("bomb");
    }

    /**
     * set damages to gameObjects on the reachable squares
     * @param map the map where the bomb is
     */
    public void explode(Maze map){
        int i;
        Cell cell;
        //UP
        for(i=1;i<=range;i++){
            cell=map.getCellAt(position_x,position_y-i);
            if(cell==null)continue;
            if(cell.hasGameObjectInstanceOf(Wall.class)){
               i=range;
            }
            cell.getDamage(damage);
        }
        //DOWN
        for(i=1;i<=range;i++){
            cell=map.getCellAt(position_x,position_y+i);
            if(cell==null)continue;
            if(cell.hasGameObjectInstanceOf(Wall.class)){
                i=range;
            }
            cell.getDamage(damage);
        }
        //RIGHT
        for(i=1;i<=range;i++){
            cell=map.getCellAt(position_x+1,position_y);
            if(cell==null)continue;
            if(cell.hasGameObjectInstanceOf(Wall.class)){
                i=range;
            }
            cell.getDamage(damage);
        }
        //LEFT
        for(i=1;i<=range;i++){
            cell=map.getCellAt(position_x-1,position_y);
            if(cell==null)continue;
            if(cell.hasGameObjectInstanceOf(Wall.class)){
                i=range;
            }
            cell.getDamage(damage);
        }
        //version avec Coord (un Vector2 avec des int)
        /*
        for(Coord dir : Coords.directions){//Coords.directions est une variable statique constante avec les 4 vecteurs (0,1),(1,0),(1,0),(1,1)
            for(int i=i; i<=range && !(object instanceof Wall); i++){
                object=map.getGameObjectsAt(position.add(dir));
                object.getDamage(getDamage);
            }
        }
        */
        //map.handleDestruction();
    }
}
