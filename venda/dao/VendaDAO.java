package venda.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import connection.ConnectionFactory;
import venda.bean.Venda;

public class VendaDAO {

    public void adicionar(Venda v) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO tb_venda (quantidade_prod, id_cliente, id_produto) VALUES (?, ?, ?)");
            stmt.setInt(1, v.getQuantidade_prod());
            stmt.setInt(2, v.getId_cliente());
            stmt.setInt(3, v.getId_produto());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Venda> ler() {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("select c.nome_cliente, p.nome_produto, p.preco_produto,p.preco_produto*v.quantidade_prod, v.quantidade_prod from tb_cliente c join tb_venda v on c.id_cliente = v.id_cliente join tb_produto p on p.id_produto = v.id_produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setNome_cliente(rs.getString("nome_cliente"));
                venda.setNome_produto(rs.getString("nome_produto"));
                venda.setQuantidade_prod(rs.getInt("quantidade_prod"));
                venda.setPreco_produto(rs.getDouble("preco_produto"));
                venda.setTotal(rs.getDouble("p.preco_produto*v.quantidade_prod"));
                vendas.add(venda);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
        return vendas;
    }
    
    public void pesquisaIDCliente(int id) {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT nome_cliente FROM tb_cliente WHERE id_cliente LIKE ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString(1));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! Acho que houve um erro inesperado.");
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
    }
    
    public void pesquisaIDProduto(int id) {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT nome_produto FROM tb_produto WHERE id_produto LIKE ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                JOptionPane.showMessageDialog(null, rs.getString(1));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! Acho que houve um erro inesperado.");
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
    }
}