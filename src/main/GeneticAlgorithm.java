/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.*;
/**
 *
 * @author tspinks
 */
public class GeneticAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int minDistance = 1;
        int[][] multipleChromsomes = new int[][] {};
        int[] chromosome = new int[0];
        int[][][] starMap = new int[0][0][0];
        
        Random randomDistance;
        int randomSize;
       
        System.out.println("Welcome to Star Traveller\n\n");
        
        //THIS IS RANDOM!!!!!
        Scanner sc = new Scanner(System.in);
        //THIS IS RANDOM!!!!!
        
        System.out.println("Enter a maximum star distance");
        int maxDistance = sc.nextInt();
        

        System.out.println("Enter the chromosome population size");
        int populationSize = sc.nextInt();
        
        //star map instance
        SimpleMap newStarMap = new SimpleMap(minDistance, maxDistance, starMap);
       
        randomDistance = newStarMap.getRandomDistance();
        randomSize = newStarMap.getRandomSize();
      
        
        starMap = newStarMap.getStarMap();
        newStarMap.printMap();
        
        //generate chromosomes
        

        ChromosomePopulation newPopulation = new ChromosomePopulation(populationSize, multipleChromsomes, chromosome, randomSize);
       
        int currPopulationSize = newPopulation.getPopulationSize();
        int[] chromosomes = newPopulation.getChromosomes(starMap, currPopulationSize, randomSize);
        

        
        
        
    }
    
    /*public static int[][] generateMap(int maxNumberStars, int minDistance, int maxDistance) {
        Random randDistance = new Random();
        int randomSize = randDistance.nextInt(maxDistance);
        
        int[][] nodeTable = new int [randomSize][randomSize];
        
        for(int x=0; x < randomSize; x++){
            for(int y=0; y < randomSize; y++){
              
               //System.out.println(x+", "+y);
               
                if(x==y){
                    nodeTable[x][y]=0;
                }else if(y < x) {
                    nodeTable[x][y] = nodeTable[y][x];
                    
                }else{
                    nodeTable[x][y] = randDistance.nextInt(maxDistance + 1) +minDistance;
                }
            }
        }
        
        System.out.println("This is da map");
        for (int x = 0; x < randomSize; x++) { 
            for (int y = 0; y < randomSize; y++) { 
                System.out.print(nodeTable[x][y] + "\t"); 
            } 
            System.out.println(); 
        }
        
        return nodeTable;
    }*/

    
    /*
    public static int[][] createinitalChromosomes(int starMap[][], int multipleChromsomes[][], int populationSize){
        //use the starmap
        //for loop through the 2d array
        //grabs values at x and y cords of array
        //do this randomly
        //account for like values
        //avoid values with 0
        int chromosome[];
        int x = 0;
        
        
        while (populationSize != x) {
            x++;
            
            
            
            
        }
        
        
        
        
        return multipleChromsomes;
    }*/
    
}
