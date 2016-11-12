/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naeemiqbal.passport;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author naeem
 */
public class Node {

    private String value;
    private Node parent;
    private LinkedList<Node> children;
    private NodeTypeEnum type;
    private int id;
    private static int idgen = 0;

    public Node(NodeTypeEnum type, String name) {
        this.id = getNextID();
        this.type = type;
        this.value = name;
    }

    private static int getNextID() {
        return ++idgen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public LinkedList<Node> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }

}
