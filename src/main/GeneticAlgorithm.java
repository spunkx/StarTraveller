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
        int[] chromosome = new int[0];
        int[][] starMap = new int[0][0];
        ArrayList<Integer> allFitness = new ArrayList<>(); 
        int[][] allChomosomes = new int[][]{};
        
        System.out.println("Welcome to Star Traveller\n\n");
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter a maximum star distance");
        int maxDistance = sc.nextInt();
        
        System.out.println("Enter the amount of Stars in the Universe you want");
        int universeStars = sc.nextInt();

        System.out.println("Enter the chromosome population size");
        int populationSize = sc.nextInt();
        
        //star map instance
        SimpleMap newStarMap = new SimpleMap(minDistance, maxDistance, starMap, universeStars);
       
        int curruniverseStars = newStarMap.getUniverseStars();
      
        
        starMap = newStarMap.getStarMap();
        newStarMap.printMap();
        
        //generate chromosomes
        

        ChromosomePopulation newPopulation = new ChromosomePopulation(populationSize, allChomosomes, chromosome);
       
        int currPopulationSize = newPopulation.getPopulationSize();

        allChomosomes = newPopulation.getChromosomes(starMap, curruniverseStars, currPopulationSize);
        allFitness = newPopulation.getFitness(starMap, allChomosomes);
        int totalFitness = newPopulation.getTotalPopfitness(allFitness);
        ArrayList<Double> relativeFitness = newPopulation.getRelativeFitness(allFitness,totalFitness);
        
        System.out.println("This is the total fitness of all Chromosome population: " + totalFitness);
        System.out.println("This is the all relative fitnesses per the chromsomes: " + relativeFitness);
        
        //send to selection sort
        
        /*
        System.out.println("this is outside the class");
        for(int j = 0; j < populationSize; j++){ 
            for (int i = 0; i < universeStars; i++){
                System.out.println(allChomosomes[j][i]);
            }
                System.out.println("\n");
        }
        */
        
    }
}
