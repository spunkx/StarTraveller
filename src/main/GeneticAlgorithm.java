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
        int minDistance = 1;
        int[] chromosome = new int[0];
        int[][] starMap = new int[0][0];
        ArrayList<Integer> allFitness = new ArrayList<>(); 
        int[][] multipleChromsomes = new int[][]{};
        
        ArrayList<Integer> maxFitnessValue = new ArrayList<>();
        ArrayList<Double> averageFitnessValue = new ArrayList<>();
       
        
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
        
        
        ChromosomePopulation newPopulation = new ChromosomePopulation(populationSize, multipleChromsomes, chromosome);
       
        int currPopulationSize = newPopulation.getPopulationSize();
        
      
        //do shit for every iteration
        for(int opaf=0;opaf<100;opaf++){
            System.out.println("\n=========================\nA new world!\n=========================\n");
            multipleChromsomes = newPopulation.getChromosomes(starMap, curruniverseStars, currPopulationSize);
            allFitness = newPopulation.getFitness(starMap, multipleChromsomes);
            int totalFitness = newPopulation.getTotalPopfitness(allFitness);
            ArrayList<Double> relativeFitness = newPopulation.getRelativeFitness(allFitness,totalFitness);

            System.out.println("This is the total fitness of all Chromosome population: " + totalFitness);
            System.out.println("This is the all relative fitnesses per the chromsomes: " + relativeFitness);
            
                    //GRAPHS 4 ALL
            //temporary interation value for X-axis
            maxFitnessValue.add(newPopulation.getMax(allFitness));
            averageFitnessValue.add(newPopulation.getAverage(totalFitness, multipleChromsomes.length));
          

            //while loop
            int[] parentPairs;
            int[][] multipleNewChromsomes = new int[populationSize][curruniverseStars];

            for(int l = 0; l<currPopulationSize;l++){
                System.out.println("Chromosome #"+l+": is     "+Arrays.toString(multipleChromsomes[l]));
            }


            for(int i = 0; i < currPopulationSize; i +=2){

                TournamentSelection newSelection = new TournamentSelection(multipleChromsomes, totalFitness, relativeFitness);

                parentPairs = newSelection.getParentPairs(multipleChromsomes,totalFitness,relativeFitness,populationSize);

                int[] parent1 = multipleChromsomes[parentPairs[0]];
                int[] parent2 = multipleChromsomes[parentPairs[1]];

                CrossOverMutation newCrossoverMutation = new CrossOverMutation(parent1,parent2);

                int[][] children;

                children = newCrossoverMutation.getCrossoverReults(parent1, parent2);

                multipleNewChromsomes[i] = children[0];
                if (currPopulationSize % 2 == 0 || ( currPopulationSize % 2 == 1 && i+1 < currPopulationSize ) ){
                    multipleNewChromsomes[i+1] = children[1];
                }

                System.out.println(Arrays.toString(children[0])+"\n"+Arrays.toString(children[1]));
            }

            //copy back to original array
            for(int i = 0;i< populationSize;i++){
                System.arraycopy(multipleNewChromsomes[i],0,multipleChromsomes[i],0,curruniverseStars);
            }


            for(int l = 0; l<currPopulationSize;l++){
                System.out.println("Chromosome #"+l+": is now "+Arrays.toString(multipleChromsomes[l]));
            }
        }
        
        SwingUtilities.invokeLater(() -> {
                NuGraph ex = new NuGraph(maxFitnessValue, averageFitnessValue);
                ex.setVisible(true);
            });
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
