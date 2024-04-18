/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;
import mg.itu.tpbanquenomenafinoana.util.Util;

/**
 *
 * @author Nomena
 */
@Named
@ViewScoped
public class CompteDetailsBean implements Serializable {

    private Long idCompte;
    private CompteBancaire cb;
    private CompteBancaire lastCompte;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getIdCompte() {
        return idCompte;
    }
    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }
    /**
     * Action handler - met à jour dans la base de données les données du client
     * contenu dans la variable d'instance customer.
     *
     * @return la prochaine page à afficher, celle qui affiche la liste des
     * clients.
     */
    public String update() {        
        cb = gestionnaireCompte.update(cb);
        
        
        if(!lastCompte.getNom().equals(cb.getNom())) {
            Util.addFlashInfoMessage("Nom " + lastCompte.getNom() + " changé en "+cb.getNom()+" ");
        }
        
        if(lastCompte.getSolde()!= cb.getSolde()) {
            Util.addFlashInfoMessage("Solde " + lastCompte.getSolde() + " changé en "+cb.getSolde()+" ");
        }
        
        
        return "listeComptes?faces-redirect=true";
    }

    public void loadCompte() {
        this.cb = gestionnaireCompte.findById(idCompte);
        this.lastCompte = gestionnaireCompte.findById(idCompte);
    }
    public CompteBancaire getCompteBancaire() {
        return cb;
    }
    /**
     * Creates a new instance of CompteDetails
     */
    public CompteDetailsBean() {
    }

}
