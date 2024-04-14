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

/**
 *
 * @author Nomena
 */
@Named
@ViewScoped
public class CompteDetailsBean implements Serializable {

    private Long idCompte;
    private CompteBancaire cb;

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
        // Modifie la base de données.
        // Il faut affecter à customer (sera expliqué dans le cours).
        cb = gestionnaireCompte.update(cb);
        return "listeComptes";
    }

    public void loadCompte() {
        this.cb = gestionnaireCompte.findById(idCompte);
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
