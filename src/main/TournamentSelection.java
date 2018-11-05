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
public class TournamentSelection {
    private int[][] multipleChromsomes = new int[][]{};
    private double totalFitness;
    private ArrayList<Double> relativeFitness;
    
    public TournamentSelection(int[][] currMultipleChromsomes, int currTotalFitness, ArrayList<Double> currRelativeFitness){
        this.multipleChromsomes = currMultipleChromsomes;
        this.totalFitness = currTotalFitness;
        this.relativeFitness = currRelativeFitness;
    }
    
    public int[] getParentPairs(int[][] multipleChromsomes, int totalFitness, ArrayList<Double> relativeFitness, int populationSize){
        int random;
        int tournamentSize = (int)(multipleChromsomes.length * 0.2) + 2;
        double theChosenOne;
        double tournament[] = new double[tournamentSize];
        double highest = 0;
        ArrayList<Double> newPopulation = new ArrayList<>();
        int[] parentPairs = new int[2];
        
        System.out.println("\n");
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < tournamentSize; j++){
                random = (int)(Math.random()* populationSize);
                theChosenOne = relativeFitness.get(random);
                tournament[j] = theChosenOne;
            }

            //grab max value
            highest = tournament[0];
            for(int asdf=1; asdf < tournament.length;asdf++){
                if (tournament[asdf] > highest){
                    highest = tournament[asdf];
                }
            }
        
        System.out.println("\nhighest" + " in generation:"+(i+1) + " " + highest + " below is the population size");
        //System.out.println(highest);// prints hightest in the current generation
        newPopulation.add(highest);
        System.out.println(newPopulation.size());
        // adds the highest into the new poputation array
        
        }
        
        System.out.println("new selected parents: tournament selection");
        // foops through the new population array and prints the the fitnesses
        
        for(int i = 0; i < newPopulation.size(); i++){
            System.out.println("Index of " + newPopulation.get(i) + " has the overall population index of " + relativeFitness.indexOf(newPopulation.get(i)));
            
            parentPairs[i] = relativeFitness.indexOf(newPopulation.get(i));
            
        }
        
        return parentPairs;
    }
    
    
    public void setMultipleChrosomomes(int[][] currMultipleChromsomes){
        multipleChromsomes = currMultipleChromsomes;
    }
    public void setTotalFitness(int currTotalFitness){
        totalFitness = currTotalFitness;
    }
    public void setRelativeFitness(ArrayList<Double> currRelativeFitness){
        relativeFitness = currRelativeFitness;
    }
}
