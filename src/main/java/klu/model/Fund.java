package klu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fund {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@Column(name="name")
	String name;
	@Column(name="description")
	String description;
	@Column(name="amounttoberaised")
	double amounttoberaised;
	@Column(name="amountraised")
	double amountraised;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Fund [id=" + id + ", name=" + name + ", description=" + description + ", amounttoberaised="
				+ amounttoberaised + ", amountraised=" + amountraised + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmounttoberaised() {
		return amounttoberaised;
	}
	public void setAmounttoberaised(double amounttoberaised) {
		this.amounttoberaised = amounttoberaised;
	}
	public double getAmountraised() {
		return amountraised;
	}
	public void setAmountraised(double amountraised) {
		this.amountraised = amountraised;
	}
}
