/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;

/**
 *
 * @author Nomena
 */
@Named(value = "listeComptesBean")
@ViewScoped
public class ListeComptesBean implements Serializable {
    @Inject
    private GestionnaireCompte gestionnaireCompte;
    
     private List<CompteBancaire> compteBancaire;
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptesBean() {
        
    }
    
       public List<CompteBancaire> getAllComptes() {
        if (compteBancaire == null) {
            compteBancaire = gestionnaireCompte.getAllComptes();
        }
        return compteBancaire;
    }
    
}
