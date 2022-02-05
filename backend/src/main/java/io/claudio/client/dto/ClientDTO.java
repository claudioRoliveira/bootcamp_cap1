package io.claudio.client.dto;

import java.io.Serializable;
import java.time.Instant;
import io.claudio.client.entities.Client;


public class ClientDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String cpf;
	private double income;
	private Instant birthDate;
	private int children;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(long id, String name, String cpf, double income, Instant birthDate, int children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		cpf = client.getCpf();
		income = client.getIncome();
		birthDate = client.getBirthDate();
		children = client.getChildren();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}
	
	
	
}
