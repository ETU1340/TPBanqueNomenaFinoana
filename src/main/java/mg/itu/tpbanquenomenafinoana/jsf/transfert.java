/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;

/**
 *
 * @author Nomena
 */
@Named(value = "transfert")
@ViewScoped
public class transfert implements Serializable {

    private Long idSource;
    private Long idReceveur;
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
        return gestionnaireCompte.findById(idSource) ;
    }
    public CompteBancaire getCbReceveur() {
        return gestionnaireCompte.findById(idReceveur) ;
    }


    /**
     * Creates a new instance of transfert
     */
    public transfert() {
    }

    public String transfert() {
    CompteBancaire cbSource = getCbSource();
    CompteBancaire cbReceveur = getCbReceveur();
    gestionnaireCompte.transferer(cbSource, cbReceveur, montant);
    return "listeComptes?faces-redirect=true";

    
}

}
