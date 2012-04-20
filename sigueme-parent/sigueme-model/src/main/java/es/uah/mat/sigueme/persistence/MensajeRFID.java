package es.uah.mat.sigueme.persistence;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.apache.commons.lang.builder.*;


@Entity
@Table (name="mensajerfid")
public class MensajeRFID {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;

	@Enumerated (EnumType.STRING)
	@NotNull
	private TipoMensaje tipo;
	@NotNull
	@Column (name="direccioncorta")
	private String direccionCorta;
	@NotNull
	@Temporal (TemporalType.TIMESTAMP)
	private Date fecha;
	@NotNull
	@Column (name="direccionlarga")
	private String direccionLarga;
	@NotNull
	@ManyToOne
	@JoinColumn (name="idpuerta", nullable=false, referencedColumnName="id" )
	private Puerta puerta;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoMensaje getTipo() {
		return tipo;
	}
	public void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}
	public String getDireccionCorta() {
		return direccionCorta;
	}
	public void setDireccionCorta(String direccionCorta) {
		this.direccionCorta = direccionCorta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDireccionLarga() {
		return direccionLarga;
	}
	public void setDireccionLarga(String direccionLarga) {
		this.direccionLarga = direccionLarga;
	}
	public Puerta getPuerta() {
		return puerta;
	}
	public void setPuerta(Puerta puerta) {
		this.puerta = puerta;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fecha);
		hcb.append(direccionLarga);
		hcb.append(puerta);
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
		MensajeRFID other = (MensajeRFID) obj;
		
		equalsBuilder.append(fecha, other.fecha);
		equalsBuilder.append(direccionLarga, other.direccionLarga);
		equalsBuilder.append(puerta, other.puerta);
		return equalsBuilder.isEquals();
	}	
	
}
