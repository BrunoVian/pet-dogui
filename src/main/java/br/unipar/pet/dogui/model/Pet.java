/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.model;

import br.unipar.pet.dogui.model.enums.GeneroEnum;
import br.unipar.pet.dogui.model.enums.PorteEnum;

/**
 *
 * @author andersonbosing
 */
public class Pet {
    
    private int id;
    private String nome;
    private Pessoa dono;
    private GeneroEnum genero;
    private double peso;
    private PorteEnum porte;
    
}
