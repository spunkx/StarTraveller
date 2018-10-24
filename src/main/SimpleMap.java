package main;

import java.util.*;

public class SimpleMap {
    private int minDistance;
    private int maxDistance;
    private static Random randomDistance;
    private static int randomSize;
    private int[][][] starMap;

    public SimpleMap(int newminDistance, int newmaxDistance, int newStarMap[][][]){
        
        this.minDistance = newminDistance;
        this.maxDistance = newmaxDistance;
        this.starMap = newStarMap;
    }
    
    public int[][][] getStarMap(){
        starMap = new int [randomSize][randomSize][2];
        int index = 0;
        for(int x=0; x < randomSize; x++){
            for(int y=0; y < randomSize; y++){
                
                if(x==y){
                    starMap[x][y][0]=0;
                    starMap[x][y][1] = index;
                }else if(y < x) {
                    starMap[x][y][0] = starMap[y][x][0];
                    starMap[x][y][1] = index;
                    
                }else{
                    starMap[x][y][0] = randomDistance.nextInt(maxDistance) + minDistance;                  
                    starMap[x][y][1] = index;
                }
                index++;
            }
        }

        return starMap;
    }
    
    public Random getRandomDistance(){
        randomDistance = new Random();
        return randomDistance;
    }
    
    public int getRandomSize(){
        randomSize = randomDistance.nextInt(maxDistance);
        return randomSize;
    }
    
    public void setStarMap(int[][][] newStarMap){
        starMap = newStarMap;
    }
    
    public void setRandomDistance(Random newDistance) {
        randomDistance = newDistance;
    }
    
    public void setRandomSize(int newSize) {
        randomSize = newSize;
    }
    
    public void setMinDistance(int newMin) {
        //if(newMin == 1) 
        minDistance = newMin;
    }
    
    public void setMaxDistance(int newMax) {
        maxDistance = newMax;
    }
    
    public void printMap(){
        System.out.println("This is da map");
        for (int x = 0; x < randomSize; x++){
            for (int y = 0; y < randomSize; y++) {
                System.out.print("Index:" + starMap[x][y][1] + " ");
                System.out.print("Distance:" + starMap[x][y][0] + "\t");
            }
            System.out.println();
        }
    }
 
}
