/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.model;

import java.util.Date;

/**
 *
 * @author andersonbosing
 */
public class ErroValidacao {
    
    private Date dtOcorrencia = new Date();
    private String dsOcorrencia;

    public ErroValidacao(String dsOcorrencia) {
        this.dsOcorrencia = dsOcorrencia;
    }
    
    public Date getDtOcorrencia() {
        return dtOcorrencia;
    }

    public void setDtOcorrencia(Date dtOcorrencia) {
        this.dtOcorrencia = dtOcorrencia;
    }

    public String getDsOcorrencia() {
        return dsOcorrencia;
    }

    public void setDsOcorrencia(String dsOcorrencia) {
        this.dsOcorrencia = dsOcorrencia;
    }
    
    
    
}
