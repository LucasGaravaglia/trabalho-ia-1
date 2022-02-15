package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.Controlador;
import model.Algoritmo;
import model.BuscaEmLargura;
import model.BuscaEmProfundidadeComRetrocesso;
import model.Vertice;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar barraDeMenus;
	private JMenu menuArquivo;
	private JMenuItem itemAbrir;

	private JPanel painelLateral;
	private JPanel painelGrafo;
	private JLabel rotuloTituloGrafo;
	private JScrollPane painelRolagemGrafo;
	private JLabel rotuloGrafo;

	private JLabel rotuloEscolha;
	private List<JRadioButton> seletoresAlgoritmos;
	private ButtonGroup grupoSeletoresAlgoritmos;

	private JComboBox<Vertice> seletorDeOrigem;
	private JComboBox<Vertice> seletorDeDestino;

	private JButton botaoExecutar;

	private JFileChooser seletorDeArquivo;

	private Controlador controlador;

	public TelaPrincipal(Controlador controlador) {
		// TODO Auto-generated constructor stub
		this.controlador = controlador;
		seletoresAlgoritmos = new ArrayList<>();
		grupoSeletoresAlgoritmos = new ButtonGroup();

		definirEstilo();

		JRadioButton jrb;
		for (Algoritmo algoritmo : controlador.getAlgoritmos()) {
			jrb = new JRadioButton(algoritmo.toString());
			jrb.setFont(jrb.getFont().deriveFont(18f));
			jrb.setBorder(new EmptyBorder(10, 5, 10, 5));
			grupoSeletoresAlgoritmos.add(jrb);
			seletoresAlgoritmos.add(jrb);
		}

		instanciarComponentes();
		definirOpcoesDaJanela();
		configurarComponentes();
		definirTratadoresDeEvento();
		posicionarComponentes();
	}

	private void definirEstilo() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void instanciarComponentes() {
		// TODO Auto-generated method stub
		seletorDeArquivo = new JFileChooser();

		barraDeMenus = new JMenuBar();
		menuArquivo = new JMenu("Arquivo");
		itemAbrir = new JMenuItem("Abrir arquivo");

		painelLateral = new JPanel();
		painelGrafo = new JPanel(new BorderLayout());
		rotuloTituloGrafo = new JLabel("Resultado");
		rotuloGrafo = new JLabel(new ImageIcon());
		painelRolagemGrafo = new JScrollPane(rotuloGrafo);

		rotuloEscolha = new JLabel("Escolha o algoritmo:");
		seletorDeOrigem = new JComboBox<>();
		seletorDeDestino = new JComboBox<>();
		botaoExecutar = new JButton("Executar");
	}

	private void definirOpcoesDaJanela() {
		// TODO Auto-generated method stub
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(barraDeMenus);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void configurarComponentes() {
		// TODO Auto-generated method stub
		itemAbrir.setIcon(new ImageIcon(this.getClass().getResource("/img/open-folder.png")));

		painelLateral.setPreferredSize(new Dimension(300, 500));
		painelLateral.setLayout(new BoxLayout(painelLateral, BoxLayout.Y_AXIS));
		painelLateral.setBorder(new EmptyBorder(20, 20, 20, 20));

		rotuloEscolha.setBorder(new EmptyBorder(10, 5, 10, 5));
		rotuloEscolha.setFont(rotuloEscolha.getFont().deriveFont(20f));

		seletorDeOrigem.setFont(rotuloEscolha.getFont().deriveFont(20f));
		seletorDeOrigem.setMaximumSize(new Dimension(600, 50));

		seletorDeDestino.setFont(rotuloEscolha.getFont().deriveFont(20f));
		seletorDeDestino.setMaximumSize(new Dimension(600, 50));

		botaoExecutar.setFont(botaoExecutar.getFont().deriveFont(20f));
		// botaoExecutar.setMaximumSize(new Dimension(260, 40));
		// botaoExecutar.setPreferredSize(new Dimension(260, 40));
		botaoExecutar.setMaximumSize(new Dimension(600, 50));

		rotuloTituloGrafo.setHorizontalAlignment(JLabel.CENTER);
		rotuloTituloGrafo.setFont(rotuloGrafo.getFont().deriveFont(20f).deriveFont(Font.BOLD));
		rotuloTituloGrafo.setBorder(new EmptyBorder(30, 30, 5, 5));

		rotuloGrafo.setOpaque(true);
		rotuloGrafo.setBackground(Color.WHITE);

		painelRolagemGrafo.setBackground(Color.WHITE);
	}

	private void definirTratadoresDeEvento() {
		// TODO Auto-generated method stub
		itemAbrir.addActionListener(l -> {
			int selecao = seletorDeArquivo.showOpenDialog(this);

			if (selecao == JFileChooser.APPROVE_OPTION) {
				controlador.lerArquivo(seletorDeArquivo.getSelectedFile());
			}
		});

		rotuloGrafo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (JRadioButton jrb : seletoresAlgoritmos)
					if (jrb.isSelected()) {
						controlador.avancar(jrb.getText());
						break;
					}
			}

		});

		botaoExecutar.addActionListener(l -> {
			for (JRadioButton jrb : seletoresAlgoritmos)
				if (jrb.isSelected()) {
					controlador.executar(jrb.getText(), (Vertice) seletorDeOrigem.getSelectedItem(),
							(Vertice) seletorDeDestino.getSelectedItem());
					break;
				}
		});
	}

	private void posicionarComponentes() {
		menuArquivo.add(itemAbrir);
		barraDeMenus.add(menuArquivo);

		// TODO Auto-generated method stub
		this.add(painelLateral, BorderLayout.WEST);
		painelLateral.add(rotuloEscolha);
		for (JRadioButton jrb : seletoresAlgoritmos)
			painelLateral.add(jrb);
		painelLateral.add(seletorDeOrigem);
		painelLateral.add(seletorDeDestino);
		painelLateral.add(botaoExecutar);

		painelGrafo.add(rotuloTituloGrafo, BorderLayout.NORTH);
		painelGrafo.add(painelRolagemGrafo);
		this.add(painelGrafo);
	}

	public void atualizarVertices(List<Vertice> vertices) {
		seletorDeOrigem.removeAllItems();
		seletorDeDestino.removeAllItems();
		for (Vertice v : vertices) {
			seletorDeOrigem.addItem(v);
			seletorDeDestino.addItem(v);
		}
		seletorDeOrigem.repaint();
		seletorDeDestino.repaint();
	}

	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}

	public void setImagem(BufferedImage imagem) {
		rotuloGrafo.setIcon(new ImageIcon(imagem));
	}

}
