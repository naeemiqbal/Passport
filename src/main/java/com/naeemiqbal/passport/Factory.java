/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naeemiqbal.passport;

import java.util.LinkedList;

/**
 *
 * @author naeem
 */
public class Factory extends Node{
 
    int MAXNODES=15;
    public Factory(String name) {
        super(NodeTypeEnum.FACTORY, name);
    }

    private int lower = 0;
    private int upper = 100;

    public void generateChildren(Integer nodesCount) throws Exception{
        if (nodesCount == null || nodesCount < 1)
            throw new Exception("Number of nodes to add is not valid. Valid input 1 to " + MAXNODES);
        if (nodesCount > MAXNODES){
            throw new Exception("Cannot add more than " + MAXNODES + " nodes");
        }
        LinkedList<Node> children = new LinkedList<Node>();
        this.setChildren(children);        
        while (children.size() < nodesCount){
            Double val =  Math.random() * (upper -lower) + lower;
            Node child = new Node(NodeTypeEnum.LEAF, Integer.toString(val.intValue()));
            children.add(child);                  
        }
    }

    public int getLower() {
        return lower;
    }

    public void setLower(int lower) {
        this.lower = lower;
    }

    public int getUpper() {
        return upper;
    }

    public void setUpper(int upper) {
        this.upper = upper;
    }
    
}
