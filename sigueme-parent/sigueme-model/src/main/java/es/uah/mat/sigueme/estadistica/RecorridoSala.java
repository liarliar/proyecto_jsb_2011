package es.uah.mat.sigueme.estadistica;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.apache.commons.lang.builder.*;

import es.uah.mat.sigueme.persistence.*;

@Entity
@Table (name="recorrido_sala")
public class RecorridoSala {
	@Id 
	@Column (name="recorridosalaid")
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@Column (name="recorridoid")
	private Integer recorridoId;

	@NotNull
	@Column (name="fechaentrada")
	@Temporal (TemporalType.TIMESTAMP)
	private Date fechaEntrada;
	
	@NotNull
	@Column (name="fechasalidaid")
	@Temporal (TemporalType.TIMESTAMP)
	private Date fechaSalida;
	
	@NotNull
	@ManyToOne
	@JoinColumn (name="salaid", nullable=false, referencedColumnName="id" )
	private Zona zona;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechaEntrada);
		hcb.append(fechaSalida);
		hcb.append(zona);
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
		RecorridoSala other = (RecorridoSala) obj;
		
		equalsBuilder.append(fechaEntrada, other.fechaSalida);
		equalsBuilder.append(fechaSalida, other.fechaSalida);
		equalsBuilder.append(zona, other.zona);
		return equalsBuilder.isEquals();
	}

	public Integer getRecorridoId() {
		return recorridoId;
	}

	public void setRecorridoId(Integer recorridoId) {
		this.recorridoId = recorridoId;
	}
}
