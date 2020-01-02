package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import produto.bean.Produto;
import produto.dao.ProdutoDAO;
import styles.Estilos;

public class MostrarProdutos extends JPanel {

    private JScrollPane jpane;
    private JTable table;
    private JLabel lblNome, lblCateg, lblPreco, lblSair;
    private JTextField txtNome, txtCateg, txtPreco;
    private JButton btnAlterar, btnPesquisa, btnExclui;

    public MostrarProdutos() {
        iniComponents();
        iniEvents();
        this.setBackground(new Color(135, 161, 204));
        lerTabela();
    }

    private void iniComponents() {
        setLayout(null);
        //ESTILO TODAS AS LABELS
            lblSair = new JLabel("X");
            Estilos.StyleLbl(lblSair);
            lblSair.setBounds(580, 5, 25, 25);
            lblCateg = new JLabel("Categ");
            Estilos.StyleLblN(lblCateg);
            lblCateg.setBounds(150, 10, 50, 20);
            lblPreco = new JLabel("Valor");
            Estilos.StyleLblN(lblPreco);
            lblPreco.setBounds(290, 10, 100, 20);
            lblNome = new JLabel("Nome");
            Estilos.StyleLblN(lblNome);
            lblNome.setBounds(10, 10, 50, 20);
        //ESTILO DA JTABLE
            table = new JTable();
            Estilos.StyleTable(table);
        //ESTILO DE TODOS OS BUTTONS
            btnAlterar = new JButton("Alterar");
            Estilos.StyleBtn(btnAlterar);
            btnAlterar.setBounds(420, 30, 100, 20);
            btnPesquisa = new JButton("Pesquisar");
            Estilos.StyleBtn(btnPesquisa);
            btnPesquisa.setBounds(420, 55, 100, 20);
            btnExclui = new JButton("Excluir");
            Estilos.StyleBtn(btnExclui);
            btnExclui.setBounds(420, 80, 100, 20);
        jpane = new JScrollPane();
        jpane.setViewportView(table);
        jpane.setBounds(0, 175, 600, 200);
        txtCateg = new JTextField();
        txtCateg.setBounds(150, 30, 100, 20);
        txtPreco = new JTextField();
        txtPreco.setBounds(290, 30, 105, 20);
        txtNome = new JTextField();
        txtNome.setBounds(10, 30, 100, 20);

        add(jpane);
        add(lblNome);
        add(txtNome);
        add(lblCateg);
        add(txtCateg);
        add(lblPreco);
        add(txtPreco);
        add(btnAlterar);
        add(btnPesquisa);
        add(btnExclui);
        add(lblSair);
    }

    private void iniEvents() {

        lblSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnAlterar);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnAlterar);
            }
        });

        btnPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnPesquisa);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnPesquisa);
            }
        });

        btnExclui.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnExclui);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnExclui);
            }
        });

        btnPesquisa.addActionListener((ActionEvent e) -> {
            if (!txtNome.getText().isEmpty()) {
                lerTabelaPesq(txtNome.getText());
                txtNome.setText("");
            }else {
                lerTabelaCateg(txtCateg.getText());
                txtCateg.setText("");
            }
        });
        btnExclui.addActionListener((ActionEvent e) -> {
            if (table.getSelectedRow() != -1) {
                Produto p = new Produto();
                ProdutoDAO pdao = new ProdutoDAO();
                p.setId((int) table.getValueAt(table.getSelectedRow(), 0));
                pdao.remover(p);
                txtCateg.setText("");
                txtNome.setText("");
                txtPreco.setText("");
                lerTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            if (table.getSelectedRow() != -1) {
                Produto produto = new Produto();
                ProdutoDAO pdao = new ProdutoDAO();
                produto.setNome(txtNome.getText());
                produto.setCategoria_produto(txtCateg.getText());
                produto.setPreco(Double.parseDouble(txtPreco.getText()));
                produto.setId((int) table.getValueAt(table.getSelectedRow(), 0));
                pdao.atualizar(produto);
                lerTabela();
                txtCateg.setText("");
                txtNome.setText("");
                txtPreco.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtCateg.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtPreco.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                }
            }
        });
    }

    public void lerTabela() {
        String[] colunas = {"Id", "Nome", "Categ", "Valor"};
        String[][] objetos = {};
        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setModel(modelo);
        modelo.setNumRows(0);
        ProdutoDAO pdao = new ProdutoDAO();

        pdao.ler().stream().forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getCategoria_produto(),
                p.getPreco()
            });
        });
    }

    public void lerTabelaPesq(String nome) {
        String[] colunas = {"Id", "Nome", "Categ", "Valor"};
        String[][] objetos = {};
        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setModel(modelo);
        modelo.setNumRows(0);
        ProdutoDAO pdao = new ProdutoDAO();

        pdao.pesquisa(nome).stream().forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getCategoria_produto(),
                p.getPreco()
            });
        });
    }

    public void lerTabelaCateg(String categ) {
        String[] colunas = {"Id", "Nome", "Categ", "Valor"};
        String[][] objetos = {};
        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setModel(modelo);
        modelo.setNumRows(0);
        ProdutoDAO pdao = new ProdutoDAO();

        pdao.pesquisaCateg(categ).stream().forEach((p) -> {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getCategoria_produto(),
                p.getPreco()
            });
        });
    }
}