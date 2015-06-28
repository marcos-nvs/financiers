/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ln.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Fabrica de conexão
 * @author Marcos Naves
 */
public class SessionFactoryDbName implements Serializable{
    
    static Map<String, SessionFactory> mapSessionFactory = new HashMap<>(2);
    final static Logger logger = Logger.getLogger(SessionFactoryDbName.class);
    
    public static Session getCurrentSessionByName(String strDbName){
        return getSessionFactoryByName(strDbName).openSession();
    }
    
    private static SessionFactory getSessionFactoryByName(String strDbName){
        if (mapSessionFactory.containsKey(strDbName)){
            return mapSessionFactory.get(strDbName);
        } else{
            SessionFactory sessionFactory = buildSessionFactoryByDbName(strDbName);
            mapSessionFactory.put(strDbName, sessionFactory);
            return sessionFactory;
        }
    }

    private static SessionFactory buildSessionFactoryByDbName(String strDbName) {
        
        SessionFactory sessionFactory = null;
        try {
            System.out.println("Buscando Session no banco dados : " + strDbName);
            
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            cfg.setProperty("hibernate.connection.datasource", "jdbc/Financiers");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            cfg.setProperty("hibernate.default_schema", strDbName);

           sessionFactory = cfg.configure().buildSessionFactory();
        } catch (HibernateException ex) {
            logger.error(ex.getMessage());
            mapSessionFactory.clear();
        }
        return sessionFactory;
    }
}
