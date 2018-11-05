/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.*;

public class ChromosomePopulation {
    private int populationSize;
    private int[][] multipleChromsomes = new int[][]{};
    
    //constuctor
    public ChromosomePopulation(int newpopulationSize, int[][] newmultipleChromsomes, int[] newChromosomes){
        this.populationSize = newpopulationSize;
        this.multipleChromsomes = newmultipleChromsomes;
    }
    
    public int getPopulationSize(){
        
        return populationSize;
    }

    public int[][] getChromosomes(int[][] starMap, int universeStars, int populationSize){
        ArrayList<Integer> starBank = new ArrayList<>();
        //universeStar is the number of stars in the universe
        multipleChromsomes = new int [populationSize][universeStars+1];
        
        for(int j = 0; j < populationSize; j++){
            
            multipleChromsomes[j][0] = 0;
            multipleChromsomes[j][universeStars] = 0;
            
            int startingStar = multipleChromsomes[j][0];
            
           for(int i=0; i < universeStars; i++){
                starBank.add(i);
            }

           starBank.remove(startingStar);
           
           for (int i = 0; i < starMap.length -1; i++){
              int randomStarBankIndex = (int)(Math.random() * starBank.size());
              int randomValue = starBank.get(randomStarBankIndex);
              multipleChromsomes[j][i+1] = randomValue;
              starBank.remove(randomStarBankIndex);
             
           }
           
            //this is on one line
            /*
            for(int i = 0;i<starMap.length;i++)
                System.out.println(multipleChromsomes[j][i] + " this is a test " + starMap[multipleChromsomes[j][i]][multipleChromsomes[j][i+1]]);
            System.out.println("\n");
            */
            //starMap[chromeList[j][i]][chromeList[j][i+1]] prints the distance between two genes of a chromosome
            
        }

        //System.out.println("end of chrome generation\n");
        /*fresh print loop
        for(int j = 0; j < populationSize; j++){
            for (int i = 0; i < universeStars+1; i++){
                System.out.println(multipleChromsomes[j][i]);
            }
                System.out.println("\n");
        }*/
        
        return multipleChromsomes;
    }
    
    public ArrayList<Integer> getFitness(int[][] starMap, int[][] allChomosomes){
        ArrayList<Integer> allFitness = new ArrayList <>();
        ArrayList<Integer> tempFit = new ArrayList <>();
        int distanceValue = 0;
        int fitnessValue = 0;
        
        for(int x = 0; x < allChomosomes.length; x++){
            fitnessValue=0;
            tempFit.clear();
            for(int y = 0; y < allChomosomes[0].length-1; y++){
  
                //fitnesdsvalue isnt  a total yet (rem,inder()
                distanceValue = starMap[allChomosomes[x][y]][allChomosomes[x][y+1]];
                tempFit.add(y,distanceValue);
                
                //System.out.println("y: " + tempFit.get(y));
                //System.out.println("test " + tempFit);

                fitnessValue += tempFit.get(y);
                //System.out.println("Fitness values: " + fitnessValue);
            }
            allFitness.add(fitnessValue);
            //System.out.println("All distances of this chrosomome: " + tempFit);
            
        }
        //System.out.println("Distance totals per chromosome: " + allFitness);
        
        return allFitness;
    }
    
    
    public int getTotalPopfitness(ArrayList <Integer> allFitness){
        int totalFitness = 0;
        for(int i = 0; i < allFitness.size(); i++){
            totalFitness += allFitness.get(i);
        }
        
        return totalFitness;
    }
    
    public ArrayList<Double> getRelativeFitness(ArrayList<Integer> allFitness, int totalFitness){
        //gets the relative fitness, relative fitness is mostly used for roulette wheel selection
        ArrayList<Double> allChromosomeRelativeFitness = new ArrayList<>();
        double relFit;
        for(int i = 0; i < allFitness.size(); i++){
            double currFitness = allFitness.get(i);
            double dblTotalFitness = totalFitness;
            
            //(dblTotalFitness-currFitness)/dblTotalFitness converts it to a higher value is more fit
            relFit = (dblTotalFitness-currFitness)/dblTotalFitness;
            allChromosomeRelativeFitness.add(relFit);
            
        }
        
        //System.out.println(allChromosomeRelativeFitness);
        
        return allChromosomeRelativeFitness;
    }
    
    public double getAverage(int totalFitness, int NumberoOfChomosomes){
       double average = totalFitness / NumberoOfChomosomes;
       return average;
    }

    public int getMax(ArrayList<Integer> allFitness){
       int max = Collections.max(allFitness);
       return max;

    }
    
    public void setMutlipleChromosomes(int[][] newmultipleChromsomes){
        multipleChromsomes = newmultipleChromsomes;
    }
    
    public void setPopulationSize(int newpopulationSize){
        populationSize = newpopulationSize;

    }
   
    
   
}
