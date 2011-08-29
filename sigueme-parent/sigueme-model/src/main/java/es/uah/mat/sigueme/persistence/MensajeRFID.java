package es.uah.mat.sigueme.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table (name="mensajeRFID")
@NamedQuery (name="MensajeRFID.getMensajesRFID", query="from MensajeRFID")
public class MensajeRFID {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private Integer id;

	@Enumerated (EnumType.STRING)
	@NotNull
	private TipoMensaje tipo;
	@NotNull
	private String direccionCorta;
	@NotNull
	@Temporal (TemporalType.TIMESTAMP)
	private Date fecha;
	@NotNull
	private String direccionLarga;
	@NotNull
	@ManyToOne
	@JoinColumn (name="idPuerta", nullable=false, referencedColumnName="id" )
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
