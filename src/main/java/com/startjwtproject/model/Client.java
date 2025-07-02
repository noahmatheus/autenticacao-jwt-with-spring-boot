package com.startjwtproject.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id_client;

	@OneToMany(mappedBy = "client")
	@Fetch(FetchMode.SELECT)
	private List<User> users;

	public Client() {
		super();
	}

	public Client(UUID id_client, List<User> users) {
		super();
		this.id_client = id_client;
		this.users = users;
	}

	public UUID getId_client() {
		return id_client;
	}

	public void setId_client(UUID id_client) {
		this.id_client = id_client;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_client);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id_client, other.id_client);
	}

	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", users=" + users + "]";
	}

}
