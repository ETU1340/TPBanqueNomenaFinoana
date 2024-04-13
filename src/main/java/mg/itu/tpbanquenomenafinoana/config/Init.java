/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;
import mg.itu.tpbanquenomenafinoana.service.GestionnaireCompte;

/**
 *
 * @author Nomena
 */
@ApplicationScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaire;

    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context
    ) {
        if (gestionnaire.nbComptes() < 1) {

            gestionnaire.creerCompte(new CompteBancaire("John Lennon", 150000));
            gestionnaire.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            gestionnaire.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            gestionnaire.creerCompte(new CompteBancaire("Georges Harrisson", 100000));

        }

    }

}
