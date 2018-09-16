package entidades;
import java.util.Date;
public class Reserva {
	private int codReserva;
	private int visitante;
	private int idQuarto;
	private Date data_entrada;
	private Date data_saida;
	private String pagamento;
	private String status;
	
	public int getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}
	public int getVisitante() {
		return visitante;
	}
	public void setVisitante(int visitante) {
		this.visitante = visitante;
	}
	public int getIdQuarto() {
		return idQuarto;
	}
	public void setIdQuarto(int idQuarto) {
		this.idQuarto = idQuarto;
	}
	public Date getData_entrada() {
		return data_entrada;
	}
	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}
	public Date getData_saida() {
		return data_saida;
	}
	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
