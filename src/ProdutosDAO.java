
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

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

    public static List<Produtos> listarProdutos() {
        List<Produtos> lista = new ArrayList<>();

        try {
            // Conectar ao banco
            Connection conexao = new conectaDAO().connectDB();

            // Instrução SQL
            String sql = "SELECT * FROM produtos";
            PreparedStatement consulta = conexao.prepareStatement(sql);

            // Executar a instrução SQL e obter os resultados
            ResultSet resposta = consulta.executeQuery();

            // Iterar sobre os resultados e adicionar os produtos à lista
            while (resposta.next()) {
                Produtos produto = new Produtos();
                produto.setId(resposta.getInt("id"));
                produto.setNome(resposta.getString("nome"));
                produto.setValor(resposta.getInt("valor")); // Definir o valor como double
                produto.setStatus(resposta.getString("status"));
                lista.add(produto);
            }

            // Fechar a conexão
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar os produtos: " + e.getMessage());
        }

        return lista;
    }

    public void venderProduto(int produtoId) {
        try {
            // Conectar ao banco
            Connection conexao = new conectaDAO().connectDB();

            // Instrução SQL para atualizar o status do produto
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, produtoId);

            // Executar a instrução SQL
            pstmt.executeUpdate();

            // Fechar a conexão
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao vender o produto: " + e.getMessage());
        }
    }

    public List<Produtos> listarProdutosVendidos() {
    List<Produtos> vendidos = new ArrayList<>();

    try {
        // Conectar ao banco
        Connection conexao = new conectaDAO().connectDB();

        // Instrução SQL
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        PreparedStatement consulta = conexao.prepareStatement(sql);

        // Executar a instrução SQL e obter os resultados
        ResultSet resposta = consulta.executeQuery();

        // Iterar sobre os resultados e adicionar os produtos vendidos à lista
        while (resposta.next()) {
            Produtos produto = new Produtos();
            produto.setId(resposta.getInt("id"));
            produto.setNome(resposta.getString("nome"));
            produto.setValor(resposta.getInt("valor"));
            produto.setStatus(resposta.getString("status"));
            vendidos.add(produto);
        }

        // Fechar a conexão
        conexao.close();
    } catch (SQLException e) {
        System.out.println("Erro ao listar os produtos vendidos: " + e.getMessage());
    }

    return vendidos;
}
    
    
    
}
