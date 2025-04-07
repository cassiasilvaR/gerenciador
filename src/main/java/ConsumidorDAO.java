import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumidorDAO {

    //Registrando um consumidor
    public void criaUsuario(Usuario usuario) { //Cria novo usuário
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?,?,?)"; 

        //Estabelece conexão com o banco de dados
        try (Connection conn = LoadDriver.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());

                stmt.executeUpdate();

        } catch (SQLException e) {
                throw new RuntimeException("Falha ao criar novo usuario", e);
        }
    } 
    //Recuperando o id do consumidor para inserir na solicitação
    public int idUsuario(Usuario usuario) {
        String sql = "SELECT id_usuario, email from usuario"; 
        int id_usuario = 0; 
        try (Connection conn = LoadDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //stmt.setString(1, consumidor.getNome());
            //List<Consumidor> consumidores = new ArrayList<>();
            ResultSet result = stmt.executeQuery(); 
            
            
            while (result.next()) {

                // Verifica se o email pertence a algum usuário do bd
                if(result.getString("email").equals(usuario.getEmail())) {
                    id_usuario = result.getInt("id_usuario");
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar id do usuario", e);
        }
       
        return id_usuario;
    }

    //Recuperando o id do atendente para colocar na solicitacao
    public Funcionario buscaFuncionario(int id_usuario) {
        String sql = "SELECT * funcionario where id_usuario = (?)"; 
        Funcionario funcionario = new Funcionario();

        try (Connection conn = LoadDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            //stmt.setString(1, consumidor.getNome());
            //List<Consumidor> consumidores = new ArrayList<>();
            ResultSet result = stmt.executeQuery(); 
                funcionario.setCargo(result.getString("cargo"));
                funcionario.setDepartamento(result.getString("departamento"));
                funcionario.setIdFuncionario(result.getInt("id_funcionario"));
                funcionario.setIdUsuario(id_usuario);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar id do consumidor", e);
        }
        return funcionario;
    }
    //Registrando solicitação
    public void createSolicitacao(Solicitacao solicitacao) { 
        String sql = "INSERT INTO solicitacao (conteudo, id_consumidor, status) VALUES (?, ?, ?)";
        
        try (Connection conn = LoadDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, solicitacao.getConteudo()); 
            stmt.setString(2, String.valueOf(solicitacao.getIdConsumidor()));
            stmt.setString(3,solicitacao.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar solicitação", e);
        }
    }

    //Registrando um funcionário
    public void createFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (id_usuario, cargo, departamento) VALUES (?, ?, ?)";
        
        try (Connection conn = LoadDriver.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
           
            stmt.setString(1, String.valueOf(funcionario.getIdUsuario())); 
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3,funcionario.getDepartamento());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar funcionário", e);
        }
    }

    // Busca e imprime todas as solicitações atuais
    public void readSolicitacao() {

        String sql = "SELECT * FROM solicitacao";
        List<Solicitacao> solicitacoes = new ArrayList<>();
        
        try (Connection conn = LoadDriver.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Solicitacao solicitacao = new Solicitacao(
                    rs.getString("conteudo"),
                    rs.getInt("id_consumidor"),
                    rs.getString("status")
                );
                solicitacao.setIdSolicitacao(rs.getInt("id_solicitacao"));
                solicitacao.setData(rs.getString("data"));
                solicitacoes.add(solicitacao);
            }

            for (Solicitacao s : solicitacoes) {
                System.out.println("Número da solicitação - " + s.getIdSolicitacao() + " \n" 
                + "Número do consumidor - " + s.getIdConsumidor() + " \n" +
                "Solicitação - " + s.getConteudo()+ "\n" + "Data da solicitação - " + s.getData() + "\n" 
                +"Status da solicitação - " + s.getStatus());
    
                System.out.println("\n");
            }

        }catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações", e);
        }
        
    }

    //Atualizando a tabela de solicitações quando uma solicitação é atendida
    public void direcionarSolicitacao(int id_solicitacao, int id_atendente, String departamento, int tipo_sol, String status){
        String sql = "UPDATE solicitacao SET departamento = (?), id_atendente = (?), tipo_sol = (?), status = (?) WHERE id_solicitacao = (?)";
        
        try (Connection conn = LoadDriver.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {

        stmt.setString(1, departamento); 
        stmt.setString(2, String.valueOf(id_atendente));
        stmt.setString(3, String.valueOf(tipo_sol));
        stmt.setString(4, status);
        stmt.setString(5, String.valueOf(id_solicitacao));

        stmt.executeUpdate();

        } catch (SQLException e) { throw new RuntimeException("Erro ao atualizar solicitação", e);}
    }

    //emitindo um parecer
    public void createParecer(Parecer parecer) {
        String sql = "INSERT INTO parecer (id_solicitacao, conteudo, id_funcionario) VALUES (?, ?, ?)";
        String sql2 = "UPDATE solicitacao SET status = (?) WHERE id_solicitacao = (?)";

        try (Connection conn = LoadDriver.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        PreparedStatement stms2 = conn.prepareStatement(sql2)) {

            stmt.setString(1, String.valueOf(parecer.getIdSolicitacao()));
            stmt.setString(2, parecer.getConteudo());
            stmt.setString(3, String.valueOf(parecer.getIdFuncionario()));

            stmt.executeUpdate();

            stms2.setString(1, "Parecer emitido");
            stms2.setString(2, String.valueOf(parecer.getIdSolicitacao()));
            stms2.executeUpdate();

        } catch (SQLException e) { throw new RuntimeException("Erro ao atualizar solicitação", e); }
    }

    //Registra a resposta do gerente e muda o status da solicitação
    public void gerarResposta(String resposta, int id_solicitacao, int aprovado){
        String sql = "UPDATE solicitacao SET resposta = (?), status = (?) WHERE id_solicitacao = (?)";

        try (Connection conn = LoadDriver.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
                stmt.setString(1, resposta);
                if (aprovado == 1){stmt.setString(2, "Aprovada");} 
                else {stmt.setString(2, "Reprovada");}
                stmt.setString(3, String.valueOf(id_solicitacao));

                stmt.executeUpdate();

        } catch (SQLException e) { throw new RuntimeException("Falha ao gerar uma resposta", e);}
    }

    //Muda o status da solicitação para aguardando resposta
    public void estudaSolicitacao(int id_solicitacao) {
        String sql = "UPDATE solicitacao SET status = (?) where id_solicitacao = (?)";

        try (Connection conn = LoadDriver.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, "Aguardando resposta");
                stmt.setString(2, String.valueOf(id_solicitacao));

                stmt.executeUpdate();

        } catch (SQLException e) { throw new RuntimeException("Falha ao atualizar status da solicitação", e);}
    }

    // Faz o login de um usuário
    public Usuario loginUsuario(String email, String senha) {
        String sql = "SELECT * from usuario";
        //List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        try (Connection conn = LoadDriver.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
           
            while (rs.next()) {
                
                if(rs.getString("email").equals(email) && 
                rs.getString("senha").equals(senha)) {
                    usuario.setEmail(rs.getString("email"));
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                }
                         
            }

        } catch (SQLException e) {
            throw new RuntimeException("Falha ao fazer login", e);
        }

        return usuario;
    }

    //Se o login for de um funcionário
    public Funcionario recuperaFuncionario(Usuario usuario) {
        String sql = "SELECT * from funcionario";
        Funcionario funcionario = new Funcionario();

        try (Connection conn = LoadDriver.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery()) {
                    
                while (result.next()) {
                    
                    if(usuario.getIdUsuario() == result.getInt("id_usuario")) {
                        funcionario.setCargo(result.getString("cargo"));
                        funcionario.setDepartamento(result.getString("departamento"));
                        funcionario.setIdFuncionario(result.getInt("id_funcionario"));
                        funcionario.setIdUsuario(result.getInt("id_usuario"));
                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException("Falha ao acessar funcionário", e);
        }
        return funcionario;
    }

    //Busca e imprime os pareceres de uma solicitação
    public void readPareceres(int id_solicitacao) {
        String sql = "SELECT * FROM parecer where id_solicitacao = (?)";
        List<Parecer> pareceres = new ArrayList<>();
        
        try (Connection conn = LoadDriver.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, String.valueOf(id_solicitacao));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Parecer parecer = new Parecer(
                    rs.getString("conteudo"),
                    rs.getInt("id_solicitacao"),
                    rs.getInt("id_funcionario")
                );
                
                pareceres.add(parecer);
            }

            for (Parecer p : pareceres) {
                System.out.println("Número da solicitação - " + p.getIdSolicitacao() + " \n" 
                + "Número do funcionário - " + p.getIdFuncionario() + " \n" +
                "Parecer - " + p.getConteudo());
    
                System.out.println("\n");
            }

        }catch (SQLException e) {
            throw new RuntimeException("Erro ao listar solicitações", e);
        }
    }
}
