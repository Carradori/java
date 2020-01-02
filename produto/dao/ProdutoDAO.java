package produto.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import produto.bean.Produto;

public class ProdutoDAO {

    public void adicionar(Produto p) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO tb_produto (nome_produto, categoria_produto, preco_produto) VALUES (?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria_produto());
            stmt.setDouble(3, p.getPreco());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops! Acho que não consegui cadastrar.");
        } finally {
            ConnectionFactory.fecharConexao(conexao, stmt);
        }
    }

    public void atualizar(Produto p) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE tb_produto SET nome_produto = ?, categoria_produto = ?, preco_produto = ? WHERE id_produto = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria_produto());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops! Acho que não consegui alterar!");
        } finally {
            ConnectionFactory.fecharConexao(conexao, stmt);
        }
    }

    public void remover(Produto p) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE FROM tb_produto WHERE id_produto = ?");
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Removido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante a exclus�o");
        } finally {
            ConnectionFactory.fecharConexao(conexao, stmt);
        }
    }

    public List<Produto> pesquisa(String nome) {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_produto WHERE nome_produto LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setCategoria_produto(rs.getString("categoria_produto"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setPreco(rs.getDouble("preco_produto"));

                produtos.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! Acho que houve um erro inesperado.");
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
        return produtos;
    }

    public List<Produto> pesquisaCateg(String categ) {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_produto WHERE categoria_produto LIKE ?");
            stmt.setString(1, "%" + categ + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setCategoria_produto(rs.getString("categoria_produto"));
                produto.setNome(rs.getString("nome_produto"));
                produto.setPreco(rs.getDouble("preco_produto"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! Acho que houve um erro inesperado.");
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
        return produtos;
    }

    public List<Produto> ler() {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        if (con == null) {
            return null;
        } else {
            try {
                stmt = con.prepareStatement("SELECT * FROM tb_produto");
                rs = stmt.executeQuery();

                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id_produto"));
                    produto.setCategoria_produto(rs.getString("categoria_produto"));
                    produto.setNome(rs.getString("nome_produto"));
                    produto.setPreco(rs.getDouble("preco_produto"));
                    produtos.add(produto);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ops! Acho que houve um erro inesperado.");
            } finally {
                ConnectionFactory.fecharConexao(con, stmt, rs);
            }
            return produtos;
        }
    }
}