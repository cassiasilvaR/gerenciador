public class Parecer {
    
    private String conteudo;
    private int id_parecer;
    private int id_solicitacao;
    private int id_funcionario;

    public Parecer(String conteudo, int id_solicitacao, int id_funcionario) {
        this.conteudo = conteudo;
        this.id_solicitacao = id_solicitacao;
        this.id_funcionario = id_funcionario;
    }

    public void setIdFuncionario(int id_funcionario){this.id_funcionario = id_funcionario;}
    public void setIdParecer(int id_parecer) {this.id_parecer = id_parecer;}

    public int getIdSolicitacao() {return this.id_solicitacao;}
    public String getConteudo() {return this.conteudo;}
    public int getIdFuncionario() {return this.id_funcionario;}

} 
