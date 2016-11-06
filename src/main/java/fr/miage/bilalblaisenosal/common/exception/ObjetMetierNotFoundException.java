/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.bilalblaisenosal.common.exception;

/**
 *
 * @author Maxime BLAISE
 */
public class ObjetMetierNotFoundException extends Exception {
    
    public ObjetMetierNotFoundException() {
        super();
    }

    public ObjetMetierNotFoundException(String message) {
        System.out.println(message);
    }
}
