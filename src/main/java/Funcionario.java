public class Funcionario {
    
    private int id_usuario;
    private int id_funcionario;
    private String cargo;
    private String departamento; 

    public Funcionario(){}

    public Funcionario(int id_usuario, String cargo, String departamento) {
        this.id_usuario = id_usuario;
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public String getCargo() {return this.cargo;}
    public String getDepartamento() {return this.departamento;}
    public int getIdFuncionario() { return this.id_funcionario;}
    public int getIdUsuario () {return this.id_usuario;}

    public void setIdUsuario(int id_usuario) {this.id_usuario = id_usuario;}
    public void setCargo(String cargo) {this.cargo = cargo;}
    public void setDepartamento(String departamento) {this.departamento = departamento;}
    public void setIdFuncionario(int id_funcionario) {this.id_funcionario = id_funcionario;}
}
