package ���׼�����;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class Calculator extends JFrame implements ActionListener {

	/**
	 * hhh
	 */
	private static final long serialVersionUID = 1L;// Java�������л��еİ汾��ʾ
	// ���������ַ�����KEYS����������ֺ��������COMMAND������Ź��ܼ����Ҷ���final���Σ������Ը���
	private final String[] KEYS = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+", "=" };
	private final String[] COMMAND = { "Backspace", "Clear" };
	// �½�������ֺ��������ť�����
	public JPanel calckeysPanel;
	public JMenuItem item1;

	// �½��������ϵ����ֺ��������ť
	private JButton keys[] = new JButton[KEYS.length];
	// �½����ܼ���ť
	private JButton commands[] = new JButton[COMMAND.length];
	// ��ʾ���������ı���Ĭ����ʾ0
	private JTextField resultText = new JTextField("0");
	// �ж��û��������������һ�����Ƿ����
	private boolean firstDigit = true;
	private double resultNum = 0.0;
	private String operator = "=";
	// �ж������Ƿ�Ϸ�
	private boolean operateValidFlag = true;
	// �������ɫ
	Random random = new Random();
	public int r1 = random.nextInt(255);
	public int g1 = random.nextInt(255);
	public int b1 = random.nextInt(255);
	public int r2 = random.nextInt(255);
	public int g2 = random.nextInt(255);
	public int b2 = random.nextInt(255);

	// ������������
	public Calculator() {
		super();
		// ��ʼ��������
		init();
		// ���ñ���
		this.setTitle("������");
		// ���������ֵ�Ĭ������
		this.setLocation(100, 100);
		// ���������ڴ�С���ɸ���
		this.setResizable(false);
		// ʹ���������������������JFrame�����ųߴ�
		this.pack();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ʵ����
		Calculator calculator1 = new Calculator();
		// ���øô���Ϊ�ɼ�
		calculator1.setVisible(true);
		// ����java.lang.System.exit()�����˳�����
		calculator1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// ��ʼ���ķ���
	private void init() {
		// ���ų�����������
		playStartSound();
		// �ı����е����ֲ����Ҷ���
		resultText.setHorizontalAlignment(JTextField.RIGHT);
		// �ı������ݲ�����Ϊ�޸�
		resultText.setEditable(false);
		// �����ı��򱳾�ɫ
		resultText.setBackground(Color.white);
		// �����ı�����������
		resultText.setFont(new Font("����", Font.BOLD, 30));

		JMenuBar bar = new JMenuBar();
		this.setJMenuBar(bar);
		JMenu jm1 = new JMenu("����");
		bar.add(jm1);
		jm1.setPreferredSize(new Dimension(55, 20));
		JMenu jm2 = new JMenu("����");
		bar.add(jm2);

		Action exitAction = new AbstractAction(" �˳�", new ImageIcon("F:\\java\\��ɫ.jpg")) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		};
		JMenuItem item3 = new JMenuItem("һ");
		JMenuItem item4 = new JMenuItem("��");
		JMenuItem item5 = new JMenuItem("��");
		jm1.add(item3);
		jm1.add(item4);
		jm1.add(item5);
		item3.setPreferredSize(new Dimension(80, 20));
		item4.setPreferredSize(new Dimension(80, 20));
		item5.setPreferredSize(new Dimension(80, 20));
		jm1.addSeparator();
		JMenuItem item1 = jm1.add(exitAction);
		jm1.add(item1);
		item1.setPreferredSize(new Dimension(80, 20));

		Action aboutAction = new AbstractAction("����") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"<html><body><h1><i>Calculater</i></h1><hr>By T.X.Zhang</body></html>", "����",
						JOptionPane.INFORMATION_MESSAGE);

			}

		};

		JMenuItem item2 = jm2.add(aboutAction);
		jm2.add(item2);
		item2.setPreferredSize(new Dimension(80, 20));

		// �½������ı�������
		JPanel textPanel = new JPanel();
		// ���������Ĳ��ַ�ʽΪ�߽粼��
		textPanel.setLayout(new BorderLayout());
		// ������������������������������
		textPanel.add("Center", resultText);

		// �½����ù��ܼ������
		JPanel commandsPanel = new JPanel();
		// ���ַ�ʽΪ���񲼾֣�һ�����У��������������������Ϊ3������
		commandsPanel.setLayout(new GridLayout(1, 2, 3, 3));
		// ���ù��ܼ���屳��ɫ
		commandsPanel.setBackground(new Color(r2, g2, b2));
		// ���������ӹ��ܼ���ť
		for (int i = 0; i < COMMAND.length; i++) {
			commands[i] = new JButton(COMMAND[i]);
			commandsPanel.add(commands[i]);
			// ����ǰ��ɫ
			commands[i].setForeground(new Color(r2, g2, b2));
			// ���ù��ܼ����������ԣ����塢�Ӵ֡�30����
			commands[i].setFont(new Font("����", Font.BOLD, 30));
		}

		// ��ʼ��
		calckeysPanel = new JPanel();
		// ���������Ĳ��ַ�ʽΪ�������е����񲼾֣�������������������3������
		calckeysPanel.setLayout(new GridLayout(4, 4, 3, 3));
		// �������������ֺ��������ť
		for (int i = 0; i < KEYS.length; i++) {
			keys[i] = new JButton(KEYS[i]);
			calckeysPanel.add(keys[i]);
			// ����ǰ��ɫ
			keys[i].setForeground(new Color(r1, g1, b1));
			// ������������Ϊ���塢�Ӵ֡�30����
			keys[i].setFont(new Font("����", Font.BOLD, 30));
		}
		// �����������ť�ϵ�������ɫ
		keys[3].setForeground(new Color(r2, g2, b2));
		keys[7].setForeground(new Color(r2, g2, b2));
		keys[11].setForeground(new Color(r2, g2, b2));
		keys[14].setForeground(new Color(r2, g2, b2));
		keys[15].setForeground(new Color(r2, g2, b2));

		// ���������壬���ַ�ʽΪ�߽粼�֣��������������Ϊ3�����أ�������Ϊ5������
		getContentPane().setLayout(new BorderLayout(3, 5));
		// ���������֮ǰ�½���������壬�ֱ�����Ϸ���������·�
		getContentPane().add("North", textPanel);
		getContentPane().add("Center", commandsPanel);
		getContentPane().add("South", calckeysPanel);

		// Ϊ������ť����¼�������
		for (int i = 0; i < KEYS.length; i++) {
			keys[i].addActionListener(this);
		}
		for (int i = 0; i < COMMAND.length; i++) {
			commands[i].addActionListener(this);
		}

	}

	@Override
	// ��дactionPerformed����ʵ�ֶ԰��°�ť�¼��ļ���
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ��ð��µİ���
		String label = e.getActionCommand();
		// �ı���屳��ɫ
		changeBackgroundColor();

		// ������Backspace��
		if (label.equals(COMMAND[0])) {
			handleBackspace();
		} /**
			 * else if(label.equals(item1)){ System.exit(0); }
			 */
		// ������Clear��
		else if (label.equals(COMMAND[1])) {
			handleC();
			//playClickSound();
			Sound s=new Sound();
			s.start();
		}
		// ���������ּ���С����
		else if ("0123456789.".indexOf(label) >= 0) {
			handleNumber(label);
		}
		// �������������
		else {
			handleOperator(label);
		}

	}

	private void changeBackgroundColor() {
		// TODO Auto-generated method stub
		// �������ּ���屳��ɫ
		random = new Random();
		int r3 = random.nextInt(255);
		int g3 = random.nextInt(255);
		int b3 = random.nextInt(255);
		calckeysPanel.setBackground(new Color(r3, g3, b3));
	}

	// �˸񷽷�
	private void handleBackspace() {
		// �����������ַ���text��
		String text = resultText.getText();
		// ����i��¼�ı��ĳ���
		int i = text.length();
		// ������ı�
		if (i > 0) {
			// ��ȡ���ȴӵ�һλ��������һλ���൱���˸�
			text = text.substring(0, i - 1);
			// ��ɾ�������һλʱ������Ϊ��ʼ��״̬
			if (text.length() == 0) {
				resultText.setText("0");
				firstDigit = true;
				operator = "=";
			}
			// û��ɾ�����һλʱ�ͽ��½�ȡ���ַ�����ʾ���������
			else {
				resultText.setText(text);
			}
		}

	}

	// ���㷽��������Ϊ��ʼ״̬
	private void handleC() {
		resultText.setText("0");
		firstDigit = true;
		operator = "=";
	}

	// �������ֺ�С����ķ���
	private void handleNumber(String key) {
		if (firstDigit) {
			// �����һ������
			resultText.setText(key);
		} else if (key.equals(".") && resultText.getText().indexOf(".") < 0) {
			// �������С������֮ǰû�а���С���㰴�����ͽ�С���������ȥ
			resultText.setText(resultText.getText() + ".");
		} else if (!key.equals(".")) {
			// ���û�а�С���㣬�ͽ����µ��������ּ���Ӧ�����������ȥ��ʵ�ֶ�λ��������
			resultText.setText(resultText.getText() + key);
		}
		// ��һ�����������
		firstDigit = false;
	}

	// ��������������ķ���
	private void handleOperator(String key) {
		// ���¡�/����
		if (operator.equals("/")) {
			// ����ı���������Ϊ��
			if (getNumberFromText() == 0.0) {
				// �Ƿ�����
				operateValidFlag = false;
				resultText.setText("��������Ϊ��!");
			} else {
				// ��������
				resultNum /= getNumberFromText();
			}
		}
		// ���¡�+����
		else if (operator.equals("+")) {
			// �ӷ�����
			resultNum += getNumberFromText();
		}
		// ���¡�-����
		else if (operator.equals("-")) {
			// ��������
			resultNum -= getNumberFromText();
		}
		// ���¡�*����
		else if (operator.equals("*")) {
			// �˷�����
			resultNum *= getNumberFromText();
		}
		// ���¡�=����
		else if (operator.equals("=")) {
			// ���ı����е����ָ�ֵ�����
			resultNum = getNumberFromText();
		}
		// ˫���ȸ���������
		if (operateValidFlag) {
			long t1;
			double t2;
			t1 = (long) resultNum;
			t2 = resultNum - t1;
			if (t2 == 0) {
				// ת��Ϊ�ַ������뵽����ı�����
				resultText.setText(String.valueOf(t1));
			} else {
				// ͬ��
				resultText.setText(String.valueOf(resultNum));
			}
		}
		operator = key;
		firstDigit = true;
		operateValidFlag = true;
	}

	// ���ı����л������
	private double getNumberFromText() {
		double result = 0;
		// �쳣����
		try {
			result = Double.valueOf(resultText.getText()).doubleValue();
		} catch (NumberFormatException e) {

		}
		return result;
	}

	private void playClickSound() {
		Play("F:\\java\\��ʾ��.wav");
	}

	private void playStartSound() {
		Play("F:\\java\\��¼��.wav");
	}

	private void Play(String fileurl) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));
			AudioFormat aif = ais.getFormat();
			SourceDataLine sdl = null;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			int nByte = 0;
			byte[] buffer = new byte[128];
			while (nByte != -1) {
				nByte = ais.read(buffer, 0, 128);
				if (nByte >= 0) {
					sdl.write(buffer, 0, nByte);
				}
			}
			sdl.stop();
		} catch (Exception e) {
		}
	}

}
