package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import produto.bean.Produto;
import produto.dao.ProdutoDAO;
import styles.Estilos;

public class CadastroProduto extends JPanel {

    private JLabel lblNome, lblPreco, lblDesc, lblSair;
    private JTextField txtNome, txtPreco, txtDesc;
    private JButton btnCadastrar;

    public CadastroProduto() {
        iniciarComponentes();
        iniciarEventos();
        this.setBackground(new Color(135, 161, 204));
    }

    private void iniciarComponentes() {
        setLayout(null);
        //ESTILO TODAS LABELS
            lblSair = new JLabel("X");
            Estilos.StyleLbl(lblSair);
            lblSair.setBounds(580, 5, 25, 25);
            lblNome = new JLabel("Nome");
            Estilos.StyleLblN(lblNome);
            lblNome.setBounds(10, 10, 50, 20);
            lblDesc = new JLabel("Categ");
            Estilos.StyleLblN(lblDesc);
            lblDesc.setBounds(10, 60, 80, 20);
            lblPreco = new JLabel("Preço");
            Estilos.StyleLblN(lblPreco);
            lblPreco.setBounds(10, 105, 50, 20);
        //estilos botao
            btnCadastrar = new JButton("Cadastrar");
            Estilos.StyleBtn(btnCadastrar);
            btnCadastrar.setBounds(180, 250, 200, 35);
        txtNome = new JTextField();
        txtNome.setBounds(10, 35, 300, 20);
        txtDesc = new JTextField();
        txtDesc.setBounds(10, 85, 300, 20);
        txtPreco = new JTextField();
        txtPreco.setBounds(10, 130, 80, 20);
        
        add(lblNome);
        add(txtNome);
        add(lblDesc);
        add(txtDesc);
        add(lblPreco);
        add(txtPreco);
        add(btnCadastrar);
        add(lblSair);
    }

    private void iniciarEventos() {

        lblSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {}
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.exit(0);
            }
        });

        btnCadastrar.addActionListener((ActionEvent e) -> {
            if (txtDesc.getText().isEmpty() || txtNome.getText().isEmpty() || txtPreco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
            } else {
                Produto produto = new Produto();
                ProdutoDAO pdao = new ProdutoDAO();
                produto.setCategoria_produto(txtDesc.getText());
                produto.setNome(txtNome.getText());
                produto.setPreco(Double.parseDouble(txtPreco.getText()));

                pdao.adicionar(produto);
                MostrarProdutos mp = new MostrarProdutos();
                mp.lerTabela();
                txtDesc.setText("");
                txtNome.setText("");
                txtPreco.setText("");
            }
        });

        btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnCadastrar);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnCadastrar);
            }
        });
    }
}