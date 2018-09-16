package entidades;

public class Quarto {

	private int PKidQuarto;
	private int NumeroDoQuarto;
	private String descricao;
	private int FKtipo;
	private int capacidade;
	
	public int getPKIdQuarto() {
		return PKidQuarto;
	}
	public void setPKIdQuarto(int idQuarto) {
		this.PKidQuarto = idQuarto;
	}
	public int getNumeroDoQuarto() {
		return NumeroDoQuarto;
	}
	public void setNumeroDoQuarto(int numeroDoQuarto) {
		NumeroDoQuarto = numeroDoQuarto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getFKTipo() {
		return FKtipo;
	}
	public void setFKTipo(int tipo) {
		this.FKtipo = tipo;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
}
