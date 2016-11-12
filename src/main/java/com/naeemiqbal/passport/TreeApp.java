/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naeemiqbal.passport;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naeem
 */
public class TreeApp {

    private static TreeApp instance;

    public static TreeApp getInstance() {
        if (instance == null) {
            instance = new TreeApp();
        }
        return instance;
    }

    Node root = new Node(NodeTypeEnum.ROOT, "Root");

    private TreeApp() {
        if (root.getChildren() == null) {
            root.setChildren(new LinkedList<Node>());
        }
        try {
            initFactory("Naeem");
            initFactory("Iqbal");
        } catch (Exception ex) {
            Logger.getLogger(TreeApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initFactory(String name) throws Exception {
        Factory fact = new Factory(name);
        fact.generateChildren(3);
        root.getChildren().add(fact);
    }

    public void addFactory(String name, int lower, int upper, int nodeCount) throws Exception {
        Factory fact = new Factory(name);
        fact.setLower(lower);
        fact.setUpper(upper);
        fact.generateChildren(nodeCount);
        root.getChildren().add(fact);
    }

    public void deleteFactory(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Node ID is missing.");
        }
        root.getChildren().remove(getFactory(id));
    }

    public boolean updateFactory(Integer id, String name, Integer lower, Integer upper, Integer nodeCount) throws Exception {
        if (id == null) {
            throw new Exception("Node ID is missing.");
        }
        Factory fact = (Factory) getFactory(id);
        if (isChanged(fact.getChildren().size(), nodeCount)) {
            fact.generateChildren(nodeCount);
        }
        return true;
    }

    boolean isChanged(int currentVal, Integer newVal) {
        return newVal == null || newVal != currentVal ? true : false;
    }

    Node getFactory(Integer id) {
        for (Node fact : root.getChildren()) {

            if (fact.getId() == id) {
                return fact;
            }
        }
        return null;
    }

}
