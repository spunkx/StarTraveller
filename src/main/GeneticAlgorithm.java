/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.*;
import javax.swing.SwingUtilities;
/**
 *
 * @author tspinks
 */
public class GeneticAlgorithm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //declare variables used through program
        int minDistance = 1;
        int[] chromosome = new int[0];
        int[][] starMap = new int[0][0];
        ArrayList<Integer> allFitness; 
        int[][] multipleChromsomes = new int[][]{};      
        ArrayList<Integer> maxFitnessValue = new ArrayList<>();
        ArrayList<Double> averageFitnessValue = new ArrayList<>();
        
        System.out.println("Welcome to Star Traveller\n\n");
        
        System.out.println("***INFO ABOUT CURRENT VERSION***\n-This is the non-debug version, debug version avaliable on github\n-Currently no input validation present, will break on incorrect values\n-No handling for very large integer values or negative\n-Minimal statistical information"
                + "\n-Optimum chromosomes are currently not outputted\n-Mutation and cross over values must be changed in code\n");
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter a maximum star distance");
        int maxDistance = sc.nextInt();
        
        System.out.println("Enter the amount of Stars in the Universe you want");
        int universeStars = sc.nextInt();

        System.out.println("Enter the chromosome population size");
        int populationSize = sc.nextInt();
        
        System.out.println("Enter the amount of iterations you want to do");
        int iterationsGenAlgo = sc.nextInt();
        
        //star map instance
        SimpleMap newStarMap = new SimpleMap(minDistance, maxDistance, starMap, universeStars);
       
        int curruniverseStars = newStarMap.getUniverseStars();
      
        
        starMap = newStarMap.getStarMap();
        newStarMap.printMap();
        
        //generate chromosomes
        ChromosomePopulation newPopulation = new ChromosomePopulation(populationSize, multipleChromsomes, chromosome);
       
        int currPopulationSize = newPopulation.getPopulationSize();
        
        multipleChromsomes = newPopulation.getChromosomes(starMap, curruniverseStars, currPopulationSize);
        
      
        //iterate for use specified iterations
        for(int m=0; m < iterationsGenAlgo; m++){
            allFitness = newPopulation.getFitness(starMap, multipleChromsomes);
            int totalFitness = newPopulation.getTotalPopfitness(allFitness);
            ArrayList<Double> relativeFitness = newPopulation.getRelativeFitness(allFitness,totalFitness);

            System.out.println("Got the total fitness!" + totalFitness);
            System.out.println("All Relative Fitneses!" + relativeFitness);
            
                    //GRAPHS 4 ALL
            //temporary interation value for X-axis
            maxFitnessValue.add(newPopulation.getMax(allFitness));
            averageFitnessValue.add(newPopulation.getAverage(totalFitness, multipleChromsomes.length)); 
          

            //while loop
            int[] parentPairs;
            int[][] multipleNewChromsomes = new int[populationSize][curruniverseStars];
     
            /*
            for(int l = 0; l<currPopulationSize;l++){
                System.out.println("Chromosome #"+l+": is     "+Arrays.toString(multipleChromsomes[l]));
            }*/

            for(int i = 0; i < currPopulationSize; i +=2){
                //do the follow for the length of curr population Size 
                TournamentSelection newSelection = new TournamentSelection(multipleChromsomes, totalFitness, relativeFitness);

                parentPairs = newSelection.getParentPairs(multipleChromsomes,totalFitness,relativeFitness,populationSize);
                
               //get the parent parents and assignment them to two know variables called parent1 and parent2
                
                int[] parent1 = multipleChromsomes[parentPairs[0]];
                int[] parent2 = multipleChromsomes[parentPairs[1]];

                CrossOverMutation newCrossoverMutation = new CrossOverMutation(parent1,parent2);

                int[][] children;

                children = newCrossoverMutation.getCrossoverReults(parent1, parent2);
                
               
                multipleNewChromsomes[i] = children[0];
                //modulo for when the current population size is even or odd
                if (currPopulationSize % 2 == 0 || ( currPopulationSize % 2 == 1 && i+1 < currPopulationSize ) ){
                    multipleNewChromsomes[i+1] = children[1];
                }

                //System.out.println(Arrays.toString(children[0])+"\n"+Arrays.toString(children[1]));
            }

            //copy back to original array
            for(int i = 0;i< populationSize;i++){
                System.arraycopy(multipleNewChromsomes[i],0,multipleChromsomes[i],0,curruniverseStars);
            }

            /*
            for(int l = 0; l<currPopulationSize;l++){
                System.out.println("Chromosome #"+l+": is now "+Arrays.toString(multipleChromsomes[l]));
            }*/
        }
        
        SwingUtilities.invokeLater(() -> {
                NuGraph ex = new NuGraph(maxFitnessValue, averageFitnessValue);
                ex.setVisible(true);
            });
        
        //send to selection sort
        
    }
}
