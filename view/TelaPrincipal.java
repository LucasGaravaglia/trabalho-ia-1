package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.Controlador;
import model.Algoritmo;
import model.Vertice;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar barraDeMenus;
	private JMenu menuArquivo;
	private JMenuItem itemAbrir;
	private JMenuItem itemSair;

	private JPanel painelLateral;
	private JPanel painelGrafo;
	private JLabel rotuloTituloGrafo;
	private JScrollPane painelRolagemGrafo;
	private JLabel rotuloGrafo;

	private JLabel rotuloEscolha;
	private List<JRadioButton> seletoresAlgoritmos;
	private ButtonGroup grupoSeletoresAlgoritmos;

	private JLabel rotuloOrigem;
	private JComboBox<Vertice> seletorDeOrigem;
	private JLabel rotuloDestino;
	private JComboBox<Vertice> seletorDeDestino;

	private JButton botaoExecutar;

	private JTextArea rotuloResultado;

	private JCheckBox seletorPassoAPasso;

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
			jrb.setToolTipText(algoritmo.toString());
			jrb.setFont(jrb.getFont().deriveFont(15f));
			//jrb.setBorder(new EmptyBorder(10, 5, 10, 5));
			jrb.setPreferredSize(new Dimension(250, 40));
			jrb.setPreferredSize(new Dimension(250, 40));
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
		itemSair = new JMenuItem("Sair");

		painelLateral = new JPanel();
		painelGrafo = new JPanel(new BorderLayout());
		rotuloTituloGrafo = new JLabel("Resultado");
		rotuloGrafo = new JLabel(new ImageIcon());
		painelRolagemGrafo = new JScrollPane(rotuloGrafo);

		rotuloEscolha = new JLabel("Escolha o algoritmo:");
		rotuloOrigem = new JLabel("Origem:");
		seletorDeOrigem = new JComboBox<>();
		rotuloDestino = new JLabel("Destino:");
		seletorDeDestino = new JComboBox<>();
		botaoExecutar = new JButton("Executar");
		seletorPassoAPasso = new JCheckBox("Passo a passo");
		rotuloResultado = new JTextArea(20, 15);

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
		//itemAbrir.setIcon(new ImageIcon(this.getClass().getResource("/img/open-folder.png")));
		menuArquivo.setFont(rotuloEscolha.getFont().deriveFont(16f));
		itemAbrir.setFont(rotuloEscolha.getFont().deriveFont(16f));
		itemSair.setFont(rotuloEscolha.getFont().deriveFont(16f));
		
		painelLateral.setPreferredSize(new Dimension(300, 500));
		painelLateral.setLayout(new FlowLayout(FlowLayout.LEADING));
		painelLateral.setBorder(new EmptyBorder(20, 20, 20, 20));

		// rotuloEscolha.setBorder(new EmptyBorder(10, 5, 10, 5));
		rotuloEscolha.setFont(rotuloEscolha.getFont().deriveFont(20f));
		rotuloEscolha.setPreferredSize(new Dimension(250, 40));
		rotuloEscolha.setMaximumSize(new Dimension(250, 40));

		rotuloOrigem.setFont(rotuloEscolha.getFont().deriveFont(20f));
		rotuloOrigem.setPreferredSize(new Dimension(250, 40));
		rotuloOrigem.setMaximumSize(new Dimension(250, 40));

		seletorDeOrigem.setFont(rotuloEscolha.getFont().deriveFont(20f));
		seletorDeOrigem.setPreferredSize(new Dimension(250, 40));
		seletorDeOrigem.setPreferredSize(new Dimension(250, 40));

		rotuloDestino.setFont(rotuloEscolha.getFont().deriveFont(20f));

		seletorDeDestino.setFont(rotuloEscolha.getFont().deriveFont(20f));
		seletorDeDestino.setPreferredSize(new Dimension(250, 40));
		seletorDeDestino.setMaximumSize(new Dimension(250, 40));

		seletorPassoAPasso.setFont(rotuloGrafo.getFont().deriveFont(20f));
		//seletorPassoAPasso.setBorder(new EmptyBorder(20, 15, 15, 15));
		seletorPassoAPasso.setPreferredSize(new Dimension(250, 40));
		seletorPassoAPasso.setMaximumSize(new Dimension(250, 40));

		botaoExecutar.setFont(botaoExecutar.getFont().deriveFont(20f));
		// botaoExecutar.setBorder(new EmptyBorder(20, -20, 20, 20));
		// botaoExecutar.setMaximumSize(new Dimension(260, 40));
		botaoExecutar.setPreferredSize(new Dimension(250, 50));
		botaoExecutar.setMaximumSize(new Dimension(250, 50));

		rotuloResultado.setEditable(false);
		rotuloResultado.setBackground(new Color(225, 225, 225));
		rotuloResultado.setBorder(new EmptyBorder(0, 0, 0 ,0));
		rotuloResultado.setFont(botaoExecutar.getFont().deriveFont(16f));
		rotuloResultado.setPreferredSize(new Dimension(250, 40));
		rotuloResultado.setMaximumSize(new Dimension(250, 40));

		rotuloTituloGrafo.setHorizontalAlignment(JLabel.CENTER);
		rotuloTituloGrafo.setFont(rotuloTituloGrafo.getFont().deriveFont(20f).deriveFont(Font.BOLD));
		rotuloTituloGrafo.setBorder(new EmptyBorder(30, 30, 5, 5));

		rotuloGrafo.setOpaque(true);
		rotuloGrafo.setFont(rotuloGrafo.getFont().deriveFont(20f).deriveFont(Font.BOLD));
		rotuloGrafo.setHorizontalAlignment(SwingConstants.CENTER);
		rotuloGrafo.setVerticalAlignment(SwingConstants.CENTER);
		rotuloGrafo.setBackground(Color.WHITE);

		painelRolagemGrafo.setBackground(Color.WHITE);
	}

	private void definirTratadoresDeEvento() {
		// TODO Auto-generated method stub
		itemAbrir.addActionListener(l -> {
			int selecao = seletorDeArquivo.showOpenDialog(this);

			if (selecao == JFileChooser.APPROVE_OPTION) {
				rotuloGrafo.setIcon(null);
				rotuloGrafo.setText("Carregando...");
				controlador.lerArquivo(seletorDeArquivo.getSelectedFile());
				rotuloGrafo.setText("");
			}
		});

		itemSair.addActionListener(l -> {
			int selecao = JOptionPane.showConfirmDialog(TelaPrincipal.this, "Deseja realmente sair?", "Sair",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (selecao == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});

		/*rotuloGrafo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (JRadioButton jrb : seletoresAlgoritmos)
					if (jrb.isSelected()) {
						controlador.avancar(jrb.getText());
						break;
					}
			}

		});*/

		botaoExecutar.addActionListener(l -> {
			if (botaoExecutar.getText().equals("Executar")) {
				for (JRadioButton jrb : seletoresAlgoritmos)
					if (jrb.isSelected()) {
						controlador.executar(jrb.getText(), (Vertice) seletorDeOrigem.getSelectedItem(),
								(Vertice) seletorDeDestino.getSelectedItem(), seletorPassoAPasso.isSelected());
						return;
					}
				JOptionPane.showMessageDialog(this, "Nenhum algoritmo foi selecionado.", "Mensagem", JOptionPane.WARNING_MESSAGE);
			} else {
				for (JRadioButton jrb : seletoresAlgoritmos)
					if (jrb.isSelected()) {
						controlador.avancar(jrb.getText());
						break;
					}
			}

		});

	}

	private void posicionarComponentes() {

		menuArquivo.add(itemAbrir);
		menuArquivo.add(itemSair);
		barraDeMenus.add(menuArquivo);

		// TODO Auto-generated method stub
		this.add(painelLateral, BorderLayout.WEST);
		painelLateral.add(rotuloEscolha);
		for (JRadioButton jrb : seletoresAlgoritmos)
			painelLateral.add(jrb);
		painelLateral.add(rotuloOrigem);
		JPanel jp = new JPanel();
		jp.setMaximumSize(new Dimension(600, 70));
		jp.add(Box.createHorizontalStrut(25));
		painelLateral.add(seletorDeOrigem);
		// painelLateral.add(jp);
		painelLateral.add(rotuloDestino);
		jp = new JPanel();
		jp.setMaximumSize(new Dimension(600, 70));
		jp.add(Box.createHorizontalStrut(25));
		painelLateral.add(seletorDeDestino);
		// painelLateral.add(jp);
		painelLateral.add(seletorPassoAPasso);
		painelLateral.add(botaoExecutar);
		painelLateral.add(rotuloResultado);

		painelGrafo.add(rotuloTituloGrafo, BorderLayout.NORTH);
		painelGrafo.add(painelRolagemGrafo);
		this.add(painelGrafo);
	}

	/** Atualiza os vértices de seleção */
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

	/** Ativa ou desativa os botões dependendo se o algoritmo está ou não em execução*/
	public void habilitarBotoes(boolean b) {
		// botaoExecutar.setEnabled(b);
		if (b)
		{
			botaoExecutar.setEnabled(true);
			botaoExecutar.setText("Executar");
		}
		else if(seletorPassoAPasso.isSelected())
			botaoExecutar.setText("Avançar");
		else
			botaoExecutar.setEnabled(false);
		seletorPassoAPasso.setEnabled(b);
		for (JRadioButton jrb : seletoresAlgoritmos)
			jrb.setEnabled(b);
		seletorDeOrigem.setEnabled(b);
		seletorDeDestino.setEnabled(b);
	}

	/** Atualiza a área de teto de resultado*/
	public void atualizarTextoResultado(String texto) {
		rotuloResultado.setText(texto);
	}

}
