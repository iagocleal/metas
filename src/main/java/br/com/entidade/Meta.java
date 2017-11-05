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

@Entity
@Table(name = "META")
@SequenceGenerator(allocationSize = 1, initialValue = 1, name = "seq_meta", sequenceName = "seq_meta")
public class Meta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_meta")
	@Column(name = "ID_META" )
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String descricao;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_META")
	private TipoMeta tipoMeta;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_META_RELACIONADA", nullable = true)
	private Meta metaRelacionada;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ORGAO")
	private Orgao orgao;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_SETOR")
	private Setor setor;
	
	public Meta()
	{
		
	}
	
	/**
	 * 
	 * @param idMeta
	 * @param descricao
	 * @param idOrgao
	 * @param descricaoOrgaoF
	 * @param idMetaRelacionada
	 * @param descricaoMetaRelacionada
	 * @param idTipoMeta
	 * @param descricaoTipoMeta
	 * @param idSetor
	 * @param descricaoSetor
	 */
	public Meta(Long idMeta, String descricao, 
			Long idOrgao, String descricaoOrgaoF,
			Long idMetaRelacionada, String descricaoMetaRelacionada,
			Long idTipoMeta, String descricaoTipoMeta,
			Long idSetor, String descricaoSetor)
	{
		this.id = idMeta;
		this.descricao = descricao;
		
		Orgao o = new Orgao();
		o.setId(idOrgao);
		o.setDescricao(descricaoOrgaoF);
		this.orgao = o;
		
		TipoMeta tipoMeta = new TipoMeta();
		tipoMeta.setId(idTipoMeta);
		tipoMeta.setDescricao(descricaoTipoMeta);
		this.tipoMeta = tipoMeta;
		
		Setor setor = new Setor();
		setor.setId(idSetor);
		setor.setDescricao(descricaoSetor);
		this.setor = setor;
		
		Meta metaRelacionada = new Meta();
		if(idMetaRelacionada != null){
			metaRelacionada.setId(idMetaRelacionada);
			metaRelacionada.setDescricao(descricaoMetaRelacionada);
		}
		else{
			metaRelacionada.setDescricao("...");
		}
		
		this.metaRelacionada = metaRelacionada;
	}

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

	public TipoMeta getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(TipoMeta tipoMeta) {
		this.tipoMeta = tipoMeta;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Meta getMetaRelacionada() {
		return metaRelacionada;
	}

	public void setMetaRelacionada(Meta metaRelacionada) {
		this.metaRelacionada = metaRelacionada;
	}
}
