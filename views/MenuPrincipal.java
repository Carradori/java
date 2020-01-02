package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import styles.Estilos;

@SuppressWarnings({"unused", "serial"})
public class MenuPrincipal extends JFrame {

    private Container painel;
    private JMenuBar barra;
    private JMenu menuArquivo, menuCadastro, menuSobre, menuMostrar;
    private JMenuItem arqSair, arqSobre, cadasProduto, cadasCliente, cadasVenda, mostraProd, mostraCli, mostraVenda, principal;
    private JPanel menuInicio;
    private JLabel lblSair;
    private int xx, xy;

    public MenuPrincipal() {
        iniciarComponentes();
        iniciarEventos();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(192, 217, 217));
        this.setUndecorated(true);
    }

    private void iniciarComponentes() {
        setSize(600, 400);
        setTitle("Menu principal");
        painel = getContentPane();
        menuInicio = new JPanel();
        barra = new JMenuBar();
        barra.setBackground(new Color(188, 194, 209));
        barra.setOpaque(true);
        //ESTILO LABEL
            lblSair = new JLabel("X");
            Estilos.StyleLbl(lblSair);
            lblSair.setBounds(580, 5, 25, 25);
        menuArquivo = new JMenu("Arquivo");
        menuCadastro = new JMenu("Cadastro");
        menuMostrar = new JMenu("Mostrar");
        menuSobre = new JMenu("Sobre");
        arqSair = new JMenuItem("Sair");
        arqSobre = new JMenuItem("App");
        principal = new JMenuItem("Menu principal");
        cadasProduto = new JMenuItem("Cadastrar produto");
        cadasCliente = new JMenuItem("Cadastrar cliente");
        cadasVenda = new JMenuItem("Cadastrar venda");
        mostraProd = new JMenuItem("Mostrar produtos");
        mostraCli = new JMenuItem("Mostrar clientes");
        mostraVenda = new JMenuItem("Mostrar vendas");
        menuInicio.setBounds(0, 0, 600, 400);
        menuInicio.setBackground(new Color(135, 161, 204));
        //adicionando
        setJMenuBar(barra);
        barra.add(menuArquivo);
        barra.add(menuCadastro);
        barra.add(menuSobre);
        barra.add(menuMostrar);
        menuArquivo.add(arqSair);
        menuArquivo.add(principal);
        menuCadastro.add(cadasProduto);
        menuCadastro.add(cadasCliente);
        menuCadastro.add(cadasVenda);
        menuMostrar.add(mostraProd);
        menuMostrar.add(mostraCli);
        menuMostrar.add(mostraVenda);
        menuSobre.add(arqSobre);
        menuInicio.setLayout(null);
        add(menuInicio);
        menuInicio.add(lblSair);
    }

    private void iniciarEventos() {

        barra.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {}
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                xx = e.getX();
                xy = e.getY();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {}
        });

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

        barra.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                MenuPrincipal.this.setLocation(x - xx, y - xy);
            }
        });

        cadasProduto.addActionListener((ActionEvent e) -> {
            setTitle("Cadastro de produto");
            CadastroProduto cProduto = new CadastroProduto();
            painel.removeAll();
            painel.add(cProduto);
            painel.validate();
        });
        cadasCliente.addActionListener((ActionEvent e) -> {
            setTitle("Cadastro de cliente");
            CadastroCliente cCliente = new CadastroCliente();
            painel.removeAll();
            painel.add(cCliente);
            painel.validate();
        });
        cadasVenda.addActionListener((ActionEvent e) -> {
            setTitle("Cadastro de vendas");
            CadastroVenda cVenda = new CadastroVenda();
            painel.removeAll();
            painel.add(cVenda);
            painel.validate();
        });
        mostraProd.addActionListener((ActionEvent e) -> {
            setTitle("Todos os produtos");
            MostrarProdutos mp = new MostrarProdutos();
            painel.removeAll();
            painel.add(mp);
            painel.validate();
        });
        mostraCli.addActionListener((ActionEvent e) -> {
            setTitle("Todos os clientes");
            MostrarClientes mc = new MostrarClientes();
            painel.removeAll();
            painel.add(mc);
            painel.validate();
        });
        mostraVenda.addActionListener((ActionEvent e) -> {
            setTitle("Todas as vendas");
            MostraVendas mv = new MostraVendas();
            painel.removeAll();
            painel.add(mv);
            painel.validate();
        });
        principal.addActionListener((ActionEvent e) -> {
            MenuPrincipal mp = new MenuPrincipal();
            painel.removeAll();
            painel.add(mp.menuInicio);
            painel.validate();
        });
        arqSobre.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "Esse É um aplicativo onde vocÊ pode cadastrar\nprodutos, clientes e vendas\n\n\nDesenvolvido por - Felipe Carradori\n2_DSA");
        });
        arqSair.addActionListener((ActionEvent e) -> {
            int r = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (r == 1) {
            } else {
                System.exit(0);
            }
        });
    }
}