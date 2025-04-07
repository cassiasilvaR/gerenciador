import java.util.List;

public class Solicitacao {
    
    private int id_solicitacao;
    private String conteudo; 
    private int id_consumidor;
    private int tipo;
    private int id_atendente;
    private String status; 
    private String resposta; 
    private String data;
    private String departamento;
    
    public Solicitacao(){}

    public Solicitacao(String conteudo, int id_consumidor, String status) {
         this.conteudo = conteudo;
         this.id_consumidor = id_consumidor;
         this.status = status;
    }

    public String getConteudo(){ return this.conteudo;}
    public String getStatus(){return this.status;}
    public int getIdConsumidor(){return this.id_consumidor;}
    public int getIdSolicitacao(){return this.id_solicitacao;}
    public String getData(){return this.data;}
    public String getDepartamento(){return this.departamento;}

    public void setIdSolicitacao(int id_solicitacao){this.id_solicitacao = id_solicitacao;}
    public void setIdAtendente(int id_atendente) {this.id_atendente = id_atendente;}
    public void setTipo(int tipo){this.tipo = tipo;}
    public void setResposta(String reposta){this.resposta = resposta;}
    public void setData(String data){this.data = data;}
    public void setDepartamento(String departamento){this.departamento = departamento;}

}
