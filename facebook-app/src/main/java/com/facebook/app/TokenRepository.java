package com.facebook.app;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.facebook.DAO.UserToken;

//@Repository("tokenRepository")
@Service("tokenRepository")
public class TokenRepository implements PersistentTokenRepository {

	private final String TRANSACTIONMANAGER = "transactionManager";
	
	@Override
	public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
		// TODO Auto-generated method stub
		
		final JpaTransactionManager jpaTransactionManager=(JpaTransactionManager)ApplicationContextProvider.getApplicationContext().getBean(TRANSACTIONMANAGER);
		final EntityManager entityManager=jpaTransactionManager.getEntityManagerFactory().createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(new UserToken(persistentRememberMeToken));
		entityManager.getTransaction().commit();
		
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String series) {
		// TODO Auto-generated method stub
		
		final JpaTransactionManager jpaTransactionManager=(JpaTransactionManager)ApplicationContextProvider.getApplicationContext().getBean(TRANSACTIONMANAGER);
		final EntityManager entityManager=jpaTransactionManager.getEntityManagerFactory().createEntityManager();
		
		final UserToken springToken=entityManager.find(UserToken.class,series);
		if(springToken==null){
			return null;
		}
		
		return new PersistentRememberMeToken(springToken.getUserName(), springToken.getSeries(), springToken.getTokenValue(), springToken.getTokenLastUsed());
	}

	@Override
	public void removeUserTokens(final String userName) {
		// TODO Auto-generated method stub
		final JpaTransactionManager jpaTransactionManager=(JpaTransactionManager)ApplicationContextProvider.getApplicationContext().getBean(TRANSACTIONMANAGER);
		final EntityManager entityManager=jpaTransactionManager.getEntityManagerFactory().createEntityManager();
		
		final Query query=entityManager.createQuery("FROM SpringToken springToken where springToken.userName=:userName");
		query.setParameter("userName", userName);
		
		final List list=query.getResultList();
		
		UserToken springToken=null;
		if(list.size()>0){
			springToken=(UserToken)list.get(0);
		}
		if(springToken!=null){
			entityManager.getTransaction().begin();
			entityManager.remove(springToken);
			entityManager.getTransaction().commit();
		}
	}

	@Override
	public void updateToken(final String series,final String tokenValue,final Date lastUsed) {
		// TODO Auto-generated method stub
		final JpaTransactionManager jpaTransactionManager=(JpaTransactionManager)ApplicationContextProvider.getApplicationContext().getBean(TRANSACTIONMANAGER);
		final EntityManager entityManager=jpaTransactionManager.getEntityManagerFactory().createEntityManager();
		
		final UserToken existingToken=entityManager.find(UserToken.class,series);
		
		existingToken.setTokenLastUsed(lastUsed);
		existingToken.setTokenValue(tokenValue);
		entityManager.getTransaction().begin();
		entityManager.merge(existingToken);
		entityManager.getTransaction().commit();
	}

	
	
}
