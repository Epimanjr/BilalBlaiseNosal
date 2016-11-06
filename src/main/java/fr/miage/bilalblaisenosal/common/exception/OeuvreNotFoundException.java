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
public class OeuvreNotFoundException extends Exception {
    
    public OeuvreNotFoundException() {
        super();
    }

    public OeuvreNotFoundException(String message) {
        System.out.println(message);
    }
}
