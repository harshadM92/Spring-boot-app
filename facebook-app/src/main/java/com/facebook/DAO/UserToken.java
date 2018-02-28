package com.facebook.DAO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;


@Entity
@Table(name="user_token")
public class UserToken {
	
	@Id
	@Column(name="series",length=255)
	private String series;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="token_value")
	private String tokenValue;
	
	@Column(name="token_last_used")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenLastUsed;

	/**
	 * @param series
	 * @param userName
	 * @param tokenValue
	 * @param tokenLastUsed
	 */
	public UserToken(PersistentRememberMeToken persistentRememberMeToken) {
		
		super();
		this.series = persistentRememberMeToken.getSeries();
		this.userName = persistentRememberMeToken.getUsername();
		this.tokenValue = persistentRememberMeToken.getTokenValue();
		this.tokenLastUsed = persistentRememberMeToken.getDate();
	}

	/**
	 * @return the series
	 */
	public String getSeries() {
		return series;
	}

	/**
	 * @param series the series to set
	 */
	public void setSeries(String series) {
		this.series = series;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the tokenValue
	 */
	public String getTokenValue() {
		return tokenValue;
	}

	/**
	 * @param tokenValue the tokenValue to set
	 */
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	/**
	 * @return the tokenLastUsed
	 */
	public Date getTokenLastUsed() {
		return tokenLastUsed;
	}

	/**
	 * @param tokenLastUsed the tokenLastUsed to set
	 */
	public void setTokenLastUsed(Date tokenLastUsed) {
		this.tokenLastUsed = tokenLastUsed;
	}
}
