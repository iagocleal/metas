package br.com.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_META")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "seq_tipo_meta", sequenceName = "seq_tipo_meta")
public class TipoMeta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_meta")
	@Column(name = "ID_TIPO_META" )
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;

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
}
