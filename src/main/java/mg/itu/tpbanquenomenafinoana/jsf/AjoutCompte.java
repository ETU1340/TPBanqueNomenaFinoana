/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;
import mg.itu.tpbanquenomenafinoana.util.Util;

/**
 *
 * @author Nomena
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    private String nom;

    @PositiveOrZero
    private int solde;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String ajoutCompte() {
        CompteBancaire cb = new CompteBancaire(nom,solde);
        gestionnaireCompte.creerCompte(cb);
        Util.addFlashInfoMessage("Nouveau compte ajouté !");
        return "ajoutCompte?faces-redirect=true";
    }

}
