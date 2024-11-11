/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import java.sql.SQLException;

/**
 *
 * @author Anuli
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        RequisitosBanco chamar = new RequisitosBanco();
        chamar.ObterConexao();
        chamar.setProduto("Armario");
        chamar.setPreco(258.12);
        chamar.Cadastrar();
        chamar.Consulta();
        chamar.DeletarPrincipal();
        // É provavel que quando vocês forem chamar Cadastrar possa dar uma linha vermelha.
        // Adicionem a Cláusula throws SQLException no public static void main.
    }
    
}
