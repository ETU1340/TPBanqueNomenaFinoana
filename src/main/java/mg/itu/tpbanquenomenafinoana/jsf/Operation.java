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
import mg.itu.tpbanquenomenafinoana.entity.OperationBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;

/**
 *
 * @author Nomena
 */
@Named(value = "operation")
@ViewScoped
public class Operation implements Serializable {

    private Long idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> operationBancaire;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Operation() {
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }

    public void setGestionnaireCompte(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        compte = gestionnaireCompte.findById(idCompte);
    }

    public List<OperationBancaire> getAllOperations() {
        if (operationBancaire == null) {
            operationBancaire = compte.getOperations();
        }
        return operationBancaire;
    }

}
