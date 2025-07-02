package com.startjwtproject.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuario")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id_user;

	@Column(name = "name", unique = true)
	private String name;

	@Email
	@Column(name = "email")
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "pass")
	private String pass;

	@Column(name = "since")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime since;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "token")
	private String token;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_client", insertable = true, updatable = true)
	@Cascade(CascadeType.SAVE_UPDATE)
	@Fetch(FetchMode.JOIN)
	@JsonIgnore
	private Client client;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(UUID id_user, String name, @Email String email, String pass, LocalDateTime since, String token,
			Client client) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.since = since;
		this.token = token;
		this.client = client;
	}

	public UUID getId_user() {
		return id_user;
	}

	public void setId_user(UUID id_user) {
		this.id_user = id_user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDateTime getSince() {
		return since;
	}

	public void setSince(LocalDateTime since) {
		this.since = LocalDateTime.now();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id_user, other.id_user);
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", name=" + name + ", email=" + email + ", pass=" + pass
				+ ", since=" + since + ", token=" + token + ", client=" + client + "]";
	}

}
