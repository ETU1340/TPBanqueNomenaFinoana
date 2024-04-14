/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquenomenafinoana.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.itu.tpbanquenomenafinoana.entity.CompteBancaire;

/**
 *
 * @author Nomena
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3406,
        user = "root",
        password = "mdpprom10",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)

@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    /**
     *
     * @param cb
     * @return
     */
    @Transactional
    public CompteBancaire update(CompteBancaire cb) {
        return em.merge(cb);
    }

    @Transactional
    public void creerCompte(CompteBancaire cb) {
        em.persist(cb);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createQuery("SELECT cb FROM CompteBancaire cb", CompteBancaire.class);
        return query.getResultList();

    }

    public long nbComptes() {
        Query query = em.createQuery("SELECT COUNT(cb) FROM CompteBancaire cb");
        return (long) query.getSingleResult();
    }

    public CompteBancaire findById(Long idCompte) {
        return em.find(CompteBancaire.class, idCompte);
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
        
    }
    
        /**
     * Dépôt d'argent sur un compte bancaire.
     * @param compteBancaire
     * @param montant 
     */
    @Transactional
    public void deposer(CompteBancaire compteBancaire, int montant) {
      compteBancaire.deposer(montant);
      update(compteBancaire);
    }
    
    /**
     * Retrait d'argent sur un compte bancaire.
     * @param compteBancaire
     * @param montant 
     */
    @Transactional
    public void retirer(CompteBancaire compteBancaire, int montant) {
      compteBancaire.retirer(montant);
      update(compteBancaire);
    }


}
