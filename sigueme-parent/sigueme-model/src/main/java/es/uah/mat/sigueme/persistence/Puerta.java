package es.uah.mat.sigueme.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table (name="puerta")
public class Puerta {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private String id;
	@NotNull
	private String nombre;
	@NotNull
	@ManyToOne
	@JoinColumn (name="idzona1", nullable=false, referencedColumnName="id")
	private Zona zona1;
	@NotNull
	@ManyToOne
	@JoinColumn (name="idzona2", nullable=false, referencedColumnName="id")
	private Zona zona2;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Zona getZona1() {
		return zona1;
	}
	public void setZona1(Zona zona1) {
		this.zona1 = zona1;
	}
	public Zona getZona2() {
		return zona2;
	}
	public void setZona2(Zona zona2) {
		this.zona2 = zona2;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombre);
		hcb.append(zona1);
		hcb.append(zona2);
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
		Puerta other = (Puerta) obj;
		
		equalsBuilder.append(nombre, other.nombre);
		equalsBuilder.append(zona1, other.zona1);
		equalsBuilder.append(zona2, other.zona2);
		return equalsBuilder.isEquals();
	}
}
