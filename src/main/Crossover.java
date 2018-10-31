/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.*;

/**
 *
 * @author ZdrytchX
 */
public class Crossover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //DEMO: REMOVE BEFORE FLIGHT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //long sample for practical case
        int DemoLibrary[] = {1,2,3,4,15,6,7,8,9,10,11,12,13,14,5,16,17,18,19,20};
        int DemoChrome1[] = {1,2,4,5,3,8,9,6,16,7,17,18,19,20,14,15,13,11,12,10};
        int DemoChrome2[] = {5,1,12,13,7,6,9,11,16,17,8,4,3,18,19,20,14,2,15,10};
        //small sample for visibility, you need to modify SETTINGS to accomadate small sample size
        /*
        int DemoLibrary[] = {1,2,3,4,5};
        int DemoChrome1[] = {1,2,4,5,3};
        int DemoChrome2[] = {3,2,5,1,4};
        */
        System.out.println("Parent Chrome 1:");
        for (int i=0; i < DemoChrome1.length; i++)
        {
            int j = DemoChrome1[i];
            System.out.println(j);
        }
        /*
        System.out.println("Parent Chrome 2:");        
        for (int i=0; i < DemoChrome2.length; i++)
        {
            int j = DemoChrome2[i];
            System.out.println(j);
        }
        System.out.println("");*/
        //DEMO: REMOVE BEFORE FLIGHT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        
        
                //==============//
                //MAIN CROSSOVER//
                //==============//

        //SETTINGS
        //Substitute these values as needed ->->->->->->->->->->->->->->->->->->
        int genePool[] =    DemoLibrary;
        //child is parent so that get modified and is da child
        int child1[] =     DemoChrome1;//parent genes
        int child2[] =     DemoChrome2;
            
        //Does not wrap around e.g. swapping gene slot 0 and last slot
        double swapWidthMax = 0.2;//0.1     Must be < 1.0
        double swapWidthMin = 0.1;//0.05   Must be <= swapWidthMax
        
        //Probability of swapping a random gene
        double mutationThreshold = 0.005;//typical value 0.001 - 0.01
        
        //Mutation PROBABILITY condition is executed X times per parent/child cycle
        //typical value = 1(?) but doesn't scale with size. Assign it to chrome
        //size and you get per-gene equivalent-ish probability of mutation, even 
        //though it can mutate the same gene twice
        int mutationAttempts = (int)(genePool.length * 1.0);//scale on size, per-gene probability
        //int mutationAttempts = 1;//per-cycle single-gene probability
        
        int numSwaps = 1;//Minimum swaps per parent/child cycle
        double extraSwaps = 1.5;//Random number of extra swaps per cycle, >1.0 to do anything due to rounding
                //e.g. value 1.5 = 33.3% chance of adding 1 extra swap
                //can act as extra mutation
        numSwaps += (int)(extraSwaps * Math.random() );
        
        //END OF SETTINGS
        //<-<-<-<-<-<-<-<-<-<-Do not modify any numbers below here unless necessary
        //"demo: remove before flight" print statements are not necessary
        
        int chromeSize = genePool.length;//cut down on processing time
        
        //Swap function only swaps once, repeat times for multiple segment swaps in different locations
        for(int i=0; i < numSwaps; i++)
        {
            swapGenes(child1, child2, swapWidthMin, swapWidthMax, chromeSize);
        }
        
        
        //Random Mutation
        mutateGene(child1, genePool, mutationThreshold, mutationAttempts, chromeSize);
        mutateGene(child2, genePool, mutationThreshold, mutationAttempts, chromeSize);
        
        
        //checkGenes fixes duplicate genes
        //This may also be used to fix other chromosomes in contexts as well
        checkGenes(child1, genePool, chromeSize);
        checkGenes(child2, genePool, chromeSize);
        
        
        //DEMO: REMOVE BEFORE FLIGHT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        System.out.println("Child1 after fix:");
        for (int i=0; i < child1.length; i++)
        {
            int j = child1[i];
            System.out.println(j);
        }
        //DEMO: REMOVE BEFORE FLIGHT <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    }
    
    //works only on adjacent genes e.g. index 2 and 3 but not 2 and 5.
    public static void swapGenes(int[] chromeA, int[] chromeB, double swapWidthMin, double swapWidthMax, int chromeSize)
    {
        //This ensures swapGenes() remains within the array size. 
        int randomNumChanges;
        randomNumChanges = (int)( chromeSize * (
                (swapWidthMax - swapWidthMin) * Math.random()  + swapWidthMin ) );
        //Remainder is used to randomly pick an index to swap
        int randomIndex = (int)( Math.random() * (chromeSize - randomNumChanges) );
        
        //DEMO: REMOVE BEFORE FLIGHT - Indicator for multiple swaps
        System.out.println("Swap @ index "+randomIndex+", "+randomNumChanges+" changes");
        
        for(int i = 0; i < randomNumChanges; i++)
        {
            int j = randomIndex + i;//alternatively you could use a random function for increased spacing
            int SwapeeVar = chromeA[j];
            chromeA[j] = chromeB[j];
            chromeB[j] = SwapeeVar;
        }
    }
        
    //changes a single random gene of a random index
    public static void mutateGene(int[] chrome, int[] genePool, double mutationThreshold, int mutationAttempts, int chromeSize)
    {
        //Calculate how many mutations to do based on threshold probability for this cycle
        int numMutations = 0;
        for(int i = 0; i < mutationAttempts; i++)
            if( Math.random() < mutationThreshold)
            {
                numMutations++;
            }
            
        for(int i = 0; i < numMutations; i++)
        {
            int randomIndex = (int)( Math.random() * chromeSize );
            int randomGene = (int)( Math.random() * chromeSize );
            chrome[randomIndex] = genePool[randomGene];
            System.out.println("MUTATED @ Index "+randomIndex);//DEMO: REMOVE BEFORE FLIGHT
        }
    }
    
    //Finds duplicate genes and fixes them in a single chromosome
    public static void checkGenes(int[] chrome, int[] genesAvail, int chromeSize)
    {       
        int[] duplicatesCheck = new int[chromeSize];
        
    //Counts duplicate Genes in a chromosome
        for(int i = 0;i < chromeSize; i++)
        {
            int gene = genesAvail[i];
            int count = 0;
            //Compare the gene from the gene library (genesAvail) to the chrome genes
            for(int k = 0;k < chromeSize; k++)
            {
                if(chrome[k] == gene)
                    count++;
            }
            duplicatesCheck[i] = count;
        }
        
    //Corrects duplicated chromosomes
        for(int i=0;i<chromeSize;i++)
        {
            int gene = chrome[i];
            
            //compare against gene library, j is used to ID which gene to replace duplicate
            for(int j = 0; j < chromeSize; j++)
                //Only deal with duplicate genes
                if(gene == genesAvail[j] && duplicatesCheck[j] > 1)
                {
                    //missingGene list is flexible - needed for custom array size for optimisation
                    ArrayList<Integer> missingGene = new ArrayList<>();
                    
                    for(int k = 0; k < chromeSize;k++)
                    {
                        if(duplicatesCheck[k] < 1)
                            missingGene.add(k);
                    }
                    
                    //Variation ensured by picking random missing gene.
                    int pickAGene = (int)(Math.random() * missingGene.size());
                    int useGene = missingGene.get(pickAGene);
                    
                    chrome[i] = genesAvail[useGene];//Substitute duplicate with missing gene
                    duplicatesCheck[j]--;//We fixed one, make sure to remove it
                }
        }
    }
}