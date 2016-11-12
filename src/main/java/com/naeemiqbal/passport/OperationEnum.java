/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naeemiqbal.passport;

/**
 *
 * @author naeem
 */
public enum OperationEnum {
    INSERT("I"), UPDATE("U"), DELETE("D");

    private String oper;

    OperationEnum(String oper) {
        this.oper = oper;
    }
    
   boolean isEqual(String pOper){
        return pOper ==null || pOper.isEmpty()? false : this.oper.equalsIgnoreCase(pOper);                  
    }
}
