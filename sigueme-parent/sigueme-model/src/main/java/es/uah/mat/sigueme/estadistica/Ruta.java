package es.uah.mat.sigueme.estadistica;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.apache.commons.lang.builder.*;

@Entity
@Table (name="recorrido")
public class Ruta {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column (name="direccionlarga")
	private String direccionLarga;
	
	@NotNull
	@Temporal (TemporalType.TIMESTAMP)
	private Date inicio;
	
	@NotNull
	@Temporal (TemporalType.TIMESTAMP)
	private Date fin;
	@NotNull
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getDireccionLarga() {
		return direccionLarga;
	}
	public void setDireccionLarga(String direccionLarga) {
		this.direccionLarga = direccionLarga;
	}
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}	
	
	public long getDuracion(){
		return fin.getTime() - inicio.getTime();
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(inicio);
		hcb.append(direccionLarga);
		hcb.append(fin);
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
		Ruta other = (Ruta) obj;
		
		equalsBuilder.append(inicio, other.inicio);
		equalsBuilder.append(direccionLarga, other.direccionLarga);
		equalsBuilder.append(fin, other.fin);
		return equalsBuilder.isEquals();
	}

}
