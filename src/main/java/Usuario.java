public class Usuario {
    
    private String email;
    private int id_usuario;
    private String nome;
    private String senha;
    
    public Usuario(){}

    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }


    public void setSenha(String senha) {this.senha = senha;}
    public void setEmail(String email) {this.email = email;}
    public void setNome(String nome) {this.nome = nome;}
    public void setIdUsuario(int id_usuario) {this.id_usuario = id_usuario;}

    public String getNome() {return this.nome;}
    public String getSenha() {return this.senha;}
    public String getEmail() {return this.email;}
    public int getIdUsuario() {return this.id_usuario;}


}
