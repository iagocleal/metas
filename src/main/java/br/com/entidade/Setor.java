package br.com.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "SETOR")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "seq_setor", sequenceName = "seq_setor")
public class Setor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_setor")
	@Column(name = "ID_SETOR" )
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ORGAO")
	private Orgao orgao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}
	
}
