package entidades;

public class Tipo_Quarto {
	private int idPKTipo;	
	private String descricao;
	
	public int getPKIdTipo() {
		return idPKTipo;
	}
	public void setPKIdTipo(int idTipo) {
		this.idPKTipo = idTipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
