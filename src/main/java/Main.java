import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int demanda; //Variábel para escolha da demanda dos funcionários
        int opcao; //Variável para escolher entre login ou cadastro
        int id_solicitacao; // Variável para recuperar o id da solicitação
        //int id_funcionario;
        int tipo_user;  // Variável que registra o tipo de funcionário (consumidor ou funcionário)
        int out_sis = 2; //Variável para sair do sistema
        String email; // Guarda email dos usuários
        String nome;  //Guarda o nome dos usuários
        String senha; //Guarda a senha dos usuários
        ConsumidorDAO consumidorDAO = new ConsumidorDAO();  //Variável que interage com o SGBD
        Usuario usuario = new Usuario();    //Variável para guardar usuário
        Funcionario funcionario = new Funcionario(); //Variável para guardar funcionário
        String parecer; // Variável que guarda o conteúdo de parecer

        System.out.println("\t\tOlá!\nEste é o sistema gerenciador de solicitações.");
        System.out.println("1 - Realizar login\t2 - Cadastrar novo usuário");
        opcao = scanner.nextInt(); //Lê qual a operação requisitada pelo usuário
        System.out.println("Qual o seu tipo de usuário? \n1 - Consumidor\t2 - Funcionário");
        tipo_user = scanner.nextInt(); //Lê se o usuário é um consumidor ou um funcionário
        scanner.nextLine();

        //Cadastro de usuários
        if(opcao == 2) {

            //Registra um usuário. Se for um consumidor, para aqui
            System.out.println("Qual o seu nome?"); 
            nome = scanner.nextLine();
            System.out.println("Qual o seu email?");
            email = scanner.nextLine();
            System.out.println("Qual a sua senha?");
            senha = scanner.nextLine();
            usuario.setEmail(email); 
            usuario.setNome(nome);
            usuario.setSenha(senha); 
            consumidorDAO.criaUsuario(usuario); // É criado um cadastro para o usuário
            
            int id_usuario = consumidorDAO.idUsuario(usuario); //Recupera o id do usuário criado pelo SGBD
            usuario.setIdUsuario(id_usuario); //

            if (tipo_user == 2) { //Se o usuário for um funcionário
                System.out.println("Qual o seu cargo?");
                String cargo = scanner.nextLine();
               
                System.out.println("Qual o seu departamento?");
                String departamento = scanner.nextLine();

                funcionario.setCargo(cargo);
                funcionario.setDepartamento(departamento);

                funcionario.setIdUsuario(id_usuario); // Coloca o id do usuário daquele funcionário
                consumidorDAO.createFuncionario(funcionario); // Cria um funcionário
            }
        }
        else if (opcao == 1){   //Opção de login
            System.out.println("Digite o seu email");
            email = scanner.nextLine();
            System.out.println("Digite a sua senha");
            senha = scanner.nextLine();

            //Recupera dados do usuario
            usuario = consumidorDAO.loginUsuario(email, senha);
            
            
            if (usuario == null) {
                System.out.println("Usuário não cadastrado");
            }
        }

        //Aqui o sistema começa a operar
        while (out_sis != 1) {
            if (tipo_user == 1) {   //A única função permitida para um consumidor é criar uma solicitação
                System.out.println("Qual a sua solicitação?");
                String conteudo = scanner.nextLine();
                //O status inicial da solicitação é não atendida
                Solicitacao solicitacao = new Solicitacao(conteudo, usuario.getIdUsuario(), "Não atendida");
                consumidorDAO.createSolicitacao(solicitacao);
    
            }
            else if (tipo_user == 2) { // O usuário é um funcionário
                
                //Nesta opção, foi feito um login, então os dados do funcionário são recuperados
                if (opcao == 1) {
                    funcionario = consumidorDAO.recuperaFuncionario(usuario);
                    
                }
                //Lista as solicitações
                System.out.println("Abaixo estão as solicitações");
                consumidorDAO.readSolicitacao();

                //Se for atendente
                if (funcionario.getCargo().equals("atendente")) {
                    System.out.println("Deseja analisar uma solicitação?\n1 - Sim\t2 - Não");
                    int sol = scanner.nextInt();
    
                    if (sol == 1) {
                        System.out.println("Qual solicitação você deseja encaminhar?");
                        int id_sol = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Para qual departamento você deseja direcionar esta solicitação?");
                        String departamento = scanner.nextLine();
                        System.out.println("Qual o tipo desta solicitação?\n1 - Reclamação\t2 - Reavaliação de metas");
                        int tipo_sol = scanner.nextInt();
                        //O atendente lê as solicitações disponíveis e as encaminha para o departamento responsável
                        //O status da solicitação muda para "Encaminhada"
                        consumidorDAO.direcionarSolicitacao(id_sol, funcionario.getIdFuncionario(), departamento,tipo_sol, "Encaminhada");
                    }

                } //Se for um avaliador
                else if (funcionario.getCargo().equals("avaliador")) {
                    System.out.println("Digite o número correspondente à sua demanda\n" +
                    "1 - Emitir parecer\t2 - Consultar cadastro de metas\t3 - Encaminhar solicitação\t4 - Atualizar cadastro de metas");
                    demanda = scanner.nextInt();

                    if(demanda == 1) {  // O avaliador analisa as solicitações do dpt de relação com o cliente e emite um parecer

                        System.out.println("Para qual solicitação você deseja emitir um parecer?"); 
                        id_solicitacao = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Qual o seu parecer?");
                        parecer = scanner.nextLine();
                        Parecer parecer2 = new Parecer(parecer, id_solicitacao, funcionario.getIdFuncionario()); //Um parecer é emitido e o status da solicitação muda para "Parecer emitido"
                        consumidorDAO.createParecer(parecer2);
                    }
                    else if (demanda == 2) {System.out.println("Cadastro de metas consultado...");}
                    else if (demanda == 3) {System.out.format("Solicitação encaminhada...");}
                    else if (demanda == 4) {System.out.println("Cadastro de metas atualizado...");}
                }
                else if (funcionario.getCargo().equals("gerente")) {    //O funcionário é um gerente
                    System.out.println("Digite o número correspondente à sua demanda\n" +
                    "1 - Estudar solicitação\t2 - Gerar resultado\t3 - Responder consumidor");
                    demanda = scanner.nextInt();

                    System.out.println("Qual solicitação deseja atender?");
                    id_solicitacao = scanner.nextInt();
                    scanner.nextLine();
                    if (demanda == 1) {
                        
                        System.out.println("Estes são os pareceres anexados a esta solicitação");
                        consumidorDAO.readPareceres(id_solicitacao); //Lista os pareceres anexados àquela solicitação
                        consumidorDAO.estudaSolicitacao(id_solicitacao); //Quando o gerente estuda uma solicitação, seu status muda para "Aguardando resposta"
                    }
                    else if(demanda == 2){ //Gerente emite um resposta
                        System.out.println("Qual a sua resposta?");  
                        String resposta = scanner.nextLine();
                        System.out.println("Você aprova esta solicitação?\n1 - Sim\t2 - Não");
                        int aprovado = scanner.nextInt(); 
                        scanner.nextLine();
                        consumidorDAO.gerarResposta(resposta, id_solicitacao, aprovado); //Solicitação muda de status para "Aprovada" ou "Reprovada"
                    }
                    else if(demanda == 3) {System.out.println("Resposta enviada ao consumidor.");}
                }
        }
            
            System.out.println("Deseja sair do sistema?\n1 - Sim\t2 - Não");
            out_sis = scanner.nextInt();
        }
      scanner.close(); 
    }
}
