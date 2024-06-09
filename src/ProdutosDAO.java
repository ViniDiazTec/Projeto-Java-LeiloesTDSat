/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<Produtos> listagem = new ArrayList<>();

    public void cadastrarProduto(Produtos produtos) {

        conn = new conectaDAO().connectDB();
        try {
            String query = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(query);
            prep.setString(1, produtos.getNome());
            prep.setInt(2, produtos.getValor());
            prep.setString(3, produtos.getStatus());

            prep.executeUpdate();

            // Fechar a conexão e o PreparedStatement
            prep.close();
            conn.close();

            // Exibir mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            // Tratar erros de SQL
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        }

    }

    public ArrayList<Produtos> listarProdutos() {

        return listagem;
    }

}
