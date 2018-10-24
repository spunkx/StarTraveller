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
public class ChromosomePopulation {
    private int populationSize;
    private int[][] multipleChromsomes = new int[][] {};
    private int[] chromosomes;
    private int chromosomeSize;
    
    public ChromosomePopulation(int newpopulationSize, int[][] newmultipleChromsomes, int[] newChromosomes, int newChromosomeSize){
        this.populationSize = newpopulationSize;
        this.multipleChromsomes = newmultipleChromsomes;
        this.chromosomes = newChromosomes;
        this.chromosomeSize = newChromosomeSize;
    }
    
    public int getPopulationSize(){
        
        return populationSize;
    }
   
    
    public int[] getChromosomes(int[][][] starMap, int populationSize, int randomSize){
        int index = 0;
        
        chromosomes = new int [randomSize];
        
        ArrayList<Integer> geneBank = new ArrayList<>();
        
        int x = 0;
        for(int d = 0; d < randomSize; d++){
            geneBank.add(d);
        }
        
        geneBank.remove(x);
        
        for (int i=0; i < randomSize-1; i++){
            int randomIndex = (int)(Math.random() * geneBank.size());
            int y = geneBank.get(randomIndex);
         
            int gene = starMap[x][y][1];
           
            
            chromosomes[i] = gene;
            
            System.out.println("gene " + chromosomes[i]);
            System.out.println("geneBank before " + geneBank.size());
            if(randomIndex > 0)
                geneBank.remove(randomIndex);
            System.out.println("geneBank aft " + geneBank.size());
            
            System.out.println("test: " + starMap[x][y][1] + "\n\n");
            
            
            x=y;
        }
        //add the remainder 
        chromosomes[randomSize-1] = starMap[0][geneBank.get(0)][1];
        System.out.println("ggtgtgt " + geneBank.get(0) + "\n\n");
        
        for(int i=0; i < chromosomes.length; i++){
            int j = chromosomes[i];
            System.out.println(j);
        }
        
        
        /*
        for(int x=0; x < randomSize; x++){
            Random test = new Random();
            int Low = 0;
            int High = randomSize;
            int test2 = test.nextInt(High-Low) + Low;
            
            for(int y=0; y < randomSize; y++){
                Random test3 = new Random();
                int Low1 = 0;
                int High1 = randomSize;
                int test4 = test3.nextInt(High1-Low1) + Low1;
                
                System.out.println("Index: " + starMap[test2][test4][1]);
                System.out.println("Distance: " + starMap[test2][test4][0]);
                
                chromosomes 
            }*/
        
   
        
     
  
        return chromosomes;
    }
   
    
    public int[][] getMultipleChromosomes(int[] chromosomes){
        
        return multipleChromsomes;
    }
    
    public void setChromosomes(int[] newChromosomes){
        chromosomes = newChromosomes;
    }
    
    public void setMutlipleChromosomes(int[][] newmultipleChromsomes){
        multipleChromsomes = newmultipleChromsomes;
    }
    
    public void setPopulationSize(int newpopulationSize){
        populationSize = newpopulationSize;

    }
    
    public void setChromosomeSize(int newChromosomeSize){
        
        chromosomeSize = newChromosomeSize;
    }
    
   
}
