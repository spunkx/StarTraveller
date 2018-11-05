package main;

import java.util.*;

public class SimpleMap {
    private int minDistance;
    private int maxDistance;
    private static Random randomDistance;
    private int universeStars;
    private int[][] starMap;

    public SimpleMap(int newMinDistance, int newMaxDistance, int newStarMap[][], int newUniverseStars){
        
        this.minDistance = newMinDistance;
        this.maxDistance = newMaxDistance;
        this.starMap = newStarMap;
        this.universeStars = newUniverseStars;
    }
    
    public int[][] getStarMap(){
        //creates a star map
        //set the size of the 2d array to user specified value
        starMap = new int [universeStars][universeStars];
        //create a new random
        randomDistance = new Random();
        for(int x=0; x < universeStars; x++){
            for(int y=0; y < universeStars; y++){
                
                if(x==y){
                    //set the diagonal 0s accross the star map for when a x1=y1
                    starMap[x][y]=0;
                    
                }else if(y < x) {
                    //remove repeating
                    starMap[x][y] = starMap[y][x];

                }else{
                    //Add the random values to the star map
                    starMap[x][y] = randomDistance.nextInt(maxDistance) + minDistance;   
                }

            }
        }

        return starMap;
    }

    public int getUniverseStars(){
        return universeStars;
    }
    
    public void setStarMap(int[][] newStarMap){
        starMap = newStarMap;
    }
    
    public void setRandomDistance(Random newDistance) {
        randomDistance = newDistance;
    }
    
    public void setUniverseStars(int newSize) {
        universeStars = newSize;
    }
    
    public void setMinDistance(int newMin) {
        minDistance = newMin;
    }
    
    public void setMaxDistance(int newMax) {
        maxDistance = newMax;
    }
    
    public void printMap(){
        System.out.println("This is da map");
        int i = 0;
        for (int x = 0; x < universeStars; x++){
            for (int y = 0; y < universeStars; y++) {
                System.out.print("(" + x + "," + y + ") ");
                System.out.print("Dist: " + starMap[x][y] + "\t");
            }
            System.out.println();
        }
    }
 
}
