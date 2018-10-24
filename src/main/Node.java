/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author tspinks
 */
public class Node {
    //node number
    public int distance;
    public int filled = 0;
    public int index;
    
    
    public Node (int newDistance) {
        //sets nodeID on creation
        distance = newDistance;
    }
    
   public int getNodeID() {
        //returns nodeID as neccesary
        return distance;
    }
   public int getFilled() {
        //returns nodeID as neccesary
        return filled;
    }
   public int getIndex() {
       return index;
   }
}
