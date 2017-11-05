package br.com.entidade;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORGAO")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "seq_orgao", sequenceName = "seq_orgao")
public class Orgao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orgao")
	@Column(name = "ID_ORGAO" )
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
