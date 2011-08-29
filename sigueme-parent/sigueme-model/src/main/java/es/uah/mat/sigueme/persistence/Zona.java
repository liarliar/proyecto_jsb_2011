package es.uah.mat.sigueme.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table (name="zona")
public class Zona {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String nombre;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombre);
		
		return hcb.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		Zona other = (Zona) obj;
		
		equalsBuilder.append(nombre, other.nombre);
		return equalsBuilder.isEquals();
	}
}
