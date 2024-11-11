/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Anuli
 */
public class RequisitosBanco {
    public Connection ObterConexao(){
        try{
            // Tente
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Class For name verifica se o Driver está presente antes de tudo.
            String url = "jdbc:mysql://localhost:3306/Banco_de_Dados";
            // Url utilizada para o caminho do Banco de Dados.
            String user = "root";
            String password = "";
            // Estabeler conexão
            System.out.println("Conectado ao Banco de Dados.");
            return DriverManager.getConnection(url, user, password);
            
            
        }catch (ClassNotFoundException | SQLException erro){
            //Tratamento de excessão, ou seja vai tratar como erro, para parar a Conexão.
            System.out.println("Erro de Conexão" + erro);
            return null;
        }
    }
    
    int id;
    String Produto;
    double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String Produto) {
        this.Produto = Produto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public void Cadastrar() throws SQLException{
        // Metodo Cadastrar lança a Excessão SQL, para tratamento de erro.
        Connection chamar = ObterConexao();
        // Chamo meu método ObterConexão através de Connection passando chamar em Seguida.
        PreparedStatement comando = chamar.prepareStatement("Insert into Tela_Com_Dados (Produto,Preco) Values (?,?)");
        // Declaração preparada do Comando do Banco de Dados Padrão de Inserção.
        System.out.println("Cadastrado com Sucesso!");
        comando.setString(1, Produto);
        comando.setDouble(2, preco);
        // Definindo a posição de cada item como parâmetro, usando o setString e o setDouble sendo (1,Variavel) e (2,Variavel)
        comando.execute();
        comando.close();
    }
    
    public RequisitosBanco ConsultarPeloId() throws ClassNotFoundException, SQLException{
        Connection chamar = ObterConexao();
        // Mesmo procedimento, chamar recebe ObterConexão.
        PreparedStatement comando = chamar.prepareStatement("select * from Tela_com_Dados where id = ?");
        // Comando de Consulta ("Select * from (Sua tabela)")
        comando.setInt(1, id);
        // ResultSet só pode ser chamado se for importado a biblioteca import java.sql.*;
        ResultSet consulta = comando.executeQuery();
        // Execução de consulta
        RequisitosBanco obter = new RequisitosBanco();
        // Aqui você chama a sua Classe.
        if (consulta.next()){
            // Minha classe banco obtem o id na consulta do Banco de Dados.
            obter.setId(consulta.getInt("id"));
            obter.setProduto(consulta.getString("Produto"));
            obter.setPreco(consulta.getDouble("Preco"));
        }
        
        System.out.println("Consultado!");
        chamar.close();
        return obter;        
       
    }
    public RequisitosBanco Consulta() throws ClassNotFoundException, SQLException{
        RequisitosBanco obter = new RequisitosBanco();
        obter.setId(1);
        try{
            obter = obter.ConsultarPeloId();
            // Chamando a classe anterior para consulta
            if (obter.getProduto() != null){
                // Se for diferente de Vazio.
                System.out.println("id...........:"+obter.getId());
                System.out.println("Produto....:"+obter.getProduto());
                System.out.println("Preco........:"+obter.getPreco());
            }else{
                // Se for falso
                System.out.println("Produto não encontrado");
            }
        }catch (ClassNotFoundException | SQLException erro){
            // Erro de Mensagem.
            System.out.println("Erro" + erro.getMessage());
        }
        return null;
    }
    
    public void Deletar() throws ClassNotFoundException, SQLException{
        Connection conectando = ObterConexao();
        PreparedStatement comando = conectando.prepareStatement("Delete from Tela_com_Dados where id = ?");
        comando.setInt(1, id);
        comando.execute();
        System.out.println("Deletado");
        conectando.close();
        
    }
    
    public void DeletarPrincipal(){
        RequisitosBanco chamar = new RequisitosBanco();
        chamar.setId(3);
        try{
            chamar.Deletar();
            System.out.println("Deletado com Sucesso!");
        }catch (ClassNotFoundException | SQLException erro){
            System.out.println(erro.getMessage() + "Erro: ");
        }
}
}
    
    
    

