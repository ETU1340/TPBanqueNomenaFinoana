/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;
import mg.itu.tpbanquenomenafinoana.util.Util;

/**
 *
 * @author Nomena
 */
@Named(value = "transfert")
@RequestScoped
public class Transfert implements Serializable {

    private Long idSource;
    private Long idReceveur;
    @PositiveOrZero
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdReceveur() {
        return idReceveur;
    }

    public void setIdReceveur(Long idReceveur) {
        this.idReceveur = idReceveur;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public CompteBancaire getCbSource() {
        return gestionnaireCompte.findById(idSource);
    }

    public CompteBancaire getCbReceveur() {
        return gestionnaireCompte.findById(idReceveur);
    }

    /**
     * Creates a new instance of transfert
     */
    public Transfert() {
    }

    public String transfert() {
        CompteBancaire cbSource = getCbSource();
        CompteBancaire cbReceveur = getCbReceveur();

        boolean erreur = false;

        if (cbSource == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } 
        
        if (cbReceveur == null) {
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:receveur");
            erreur = true;
        }
        
        if (cbSource.getSolde() < montant) {
            Util.messageErreur("Solde insuffisant !", "Le montant indiqué depasse le solde de la source!", "form:montant");
            erreur = true;
        }

        if (erreur) { 
            return null;
        }
        gestionnaireCompte.transferer(cbSource, cbReceveur, montant);
        String messageSucces = "Une transfert de " + cbSource.getNom() + " d'une montant de " + montant + " vers " + cbReceveur.getNom() + " a bien été effectué";
        Util.addFlashInfoMessage(messageSucces);
        return "listeComptes?idSource=" + idSource + "&amp;idReceveur=" + idReceveur + "&amp;montant=" + montant + "&amp;faces-redirect=true ";

    }

}
