package 简易计算器;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

public class Calculator extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;// Java对象序列化中的版本标示
	// 定义两个字符串，KEYS用来存放数字和运算符，COMMAND用来存放功能键，且都用final修饰，不可以更改
	private final String[] KEYS = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "+", "=" };
	private final String[] COMMAND = { "Backspace", "Clear" };
	// 新建存放数字和运算符按钮的面板
	public JPanel calckeysPanel;
	public JMenuItem item1;

	// 新建计算器上的数字和运算符按钮
	private JButton keys[] = new JButton[KEYS.length];
	// 新建功能键按钮
	private JButton commands[] = new JButton[COMMAND.length];
	// 显示计算结果的文本框，默认显示0
	private JTextField resultText = new JTextField("0");
	// 判断用户输入用于运算的一个数是否结束
	private boolean firstDigit = true;
	private double resultNum = 0.0;
	private String operator = "=";
	// 判断运算是否合法
	private boolean operateValidFlag = true;
	// 产生随机色
	Random random = new Random();
	public int r1 = random.nextInt(255);
	public int g1 = random.nextInt(255);
	public int b1 = random.nextInt(255);
	public int r2 = random.nextInt(255);
	public int g2 = random.nextInt(255);
	public int b2 = random.nextInt(255);

	// 计算器构造器
	public Calculator() {
		super();
		// 初始化计算器
		init();
		// 设置标题
		this.setTitle("计算器");
		// 计算器出现的默认坐标
		this.setLocation(100, 100);
		// 计算器窗口大小不可更改
		this.setResizable(false);
		// 使容器正好容纳组件，设置JFrame的最优尺寸
		this.pack();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 实例化
		Calculator calculator1 = new Calculator();
		// 设置该窗口为可见
		calculator1.setVisible(true);
		// 调用java.lang.System.exit()方法退出程序
		calculator1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 初始化的方法
	private void init() {
		// 播放程序启动声音
		playStartSound();
		// 文本框中的文字采用右对齐
		resultText.setHorizontalAlignment(JTextField.RIGHT);
		// 文本框内容不可人为修改
		resultText.setEditable(false);
		// 设置文本框背景色
		resultText.setBackground(Color.white);
		// 设置文本框字体属性
		resultText.setFont(new Font("宋体", Font.BOLD, 30));

		JMenuBar bar = new JMenuBar();
		this.setJMenuBar(bar);
		JMenu jm1 = new JMenu("功能");
		bar.add(jm1);
		jm1.setPreferredSize(new Dimension(55, 20));
		JMenu jm2 = new JMenu("帮助");
		bar.add(jm2);

		Action exitAction = new AbstractAction(" 退出", new ImageIcon("F:\\java\\红色.jpg")) {

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
		JMenuItem item3 = new JMenuItem("一");
		JMenuItem item4 = new JMenuItem("二");
		JMenuItem item5 = new JMenuItem("三");
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

		Action aboutAction = new AbstractAction("关于") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null,
						"<html><body><h1><i>Calculater</i></h1><hr>By T.X.Zhang</body></html>", "关于",
						JOptionPane.INFORMATION_MESSAGE);

			}

		};

		JMenuItem item2 = jm2.add(aboutAction);
		jm2.add(item2);
		item2.setPreferredSize(new Dimension(80, 20));

		// 新建放置文本框的面板
		JPanel textPanel = new JPanel();
		// 面板中组件的布局方式为边界布局
		textPanel.setLayout(new BorderLayout());
		// 向容器中添加组件，放置在容器中央
		textPanel.add("Center", resultText);

		// 新建放置功能键的面板
		JPanel commandsPanel = new JPanel();
		// 布局方式为网格布局，一行两列，面板中组件横向和纵向间隔为3个像素
		commandsPanel.setLayout(new GridLayout(1, 2, 3, 3));
		// 设置功能键面板背景色
		commandsPanel.setBackground(new Color(r2, g2, b2));
		// 向面板中添加功能键按钮
		for (int i = 0; i < COMMAND.length; i++) {
			commands[i] = new JButton(COMMAND[i]);
			commandsPanel.add(commands[i]);
			// 设置前景色
			commands[i].setForeground(new Color(r2, g2, b2));
			// 设置功能键的字体属性，宋体、加粗、30号字
			commands[i].setFont(new Font("宋体", Font.BOLD, 30));
		}

		// 初始化
		calckeysPanel = new JPanel();
		// 面板中组件的布局方式为四行四列的网格布局，组件横向和纵向间隔都是3个像素
		calckeysPanel.setLayout(new GridLayout(4, 4, 3, 3));
		// 向面板中添加数字和运算符按钮
		for (int i = 0; i < KEYS.length; i++) {
			keys[i] = new JButton(KEYS[i]);
			calckeysPanel.add(keys[i]);
			// 设置前景色
			keys[i].setForeground(new Color(r1, g1, b1));
			// 设置字体属性为宋体、加粗、30号字
			keys[i].setFont(new Font("宋体", Font.BOLD, 30));
		}
		// 设置运算符按钮上的字体颜色
		keys[3].setForeground(new Color(r2, g2, b2));
		keys[7].setForeground(new Color(r2, g2, b2));
		keys[11].setForeground(new Color(r2, g2, b2));
		keys[14].setForeground(new Color(r2, g2, b2));
		keys[15].setForeground(new Color(r2, g2, b2));

		// 获得内容面板，布局方式为边界布局，各个组件横向间距为3个像素，纵向间距为5个像素
		getContentPane().setLayout(new BorderLayout(3, 5));
		// 向框架中添加之前新建的三个面板，分别放在上方，中央和下方
		getContentPane().add("North", textPanel);
		getContentPane().add("Center", commandsPanel);
		getContentPane().add("South", calckeysPanel);

		// 为各个按钮添加事件监听器
		for (int i = 0; i < KEYS.length; i++) {
			keys[i].addActionListener(this);
		}
		for (int i = 0; i < COMMAND.length; i++) {
			commands[i].addActionListener(this);
		}

	}

	@Override
	// 重写actionPerformed方法实现对按下按钮事件的监听
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获得按下的按键
		String label = e.getActionCommand();
		// 改变面板背景色
		changeBackgroundColor();

		// 按下了Backspace键
		if (label.equals(COMMAND[0])) {
			handleBackspace();
		} /**
			 * else if(label.equals(item1)){ System.exit(0); }
			 */
		// 按下了Clear键
		else if (label.equals(COMMAND[1])) {
			handleC();
			//playClickSound();
			Sound s=new Sound();
			s.start();
		}
		// 按下了数字键和小数点
		else if ("0123456789.".indexOf(label) >= 0) {
			handleNumber(label);
		}
		// 按下了运算符键
		else {
			handleOperator(label);
		}

	}

	private void changeBackgroundColor() {
		// TODO Auto-generated method stub
		// 设置数字键面板背景色
		random = new Random();
		int r3 = random.nextInt(255);
		int g3 = random.nextInt(255);
		int b3 = random.nextInt(255);
		calckeysPanel.setBackground(new Color(r3, g3, b3));
	}

	// 退格方法
	private void handleBackspace() {
		// 将结果存放在字符串text中
		String text = resultText.getText();
		// 变量i记录文本的长度
		int i = text.length();
		// 如果有文本
		if (i > 0) {
			// 截取长度从第一位到倒数第一位，相当于退格
			text = text.substring(0, i - 1);
			// 当删除到最后一位时，更改为初始化状态
			if (text.length() == 0) {
				resultText.setText("0");
				firstDigit = true;
				operator = "=";
			}
			// 没有删到最后一位时就将新截取的字符串显示到结果框中
			else {
				resultText.setText(text);
			}
		}

	}

	// 清零方法，更改为初始状态
	private void handleC() {
		resultText.setText("0");
		firstDigit = true;
		operator = "=";
	}

	// 输入数字和小数点的方法
	private void handleNumber(String key) {
		if (firstDigit) {
			// 输入第一个数字
			resultText.setText(key);
		} else if (key.equals(".") && resultText.getText().indexOf(".") < 0) {
			// 如果按下小数点且之前没有按过小数点按键，就将小数点输入进去
			resultText.setText(resultText.getText() + ".");
		} else if (!key.equals(".")) {
			// 如果没有按小数点，就将按下的其他数字键对应的数字输入进去，实现多位数的输入
			resultText.setText(resultText.getText() + key);
		}
		// 第一个数输入完成
		firstDigit = false;
	}

	// 处理按下运算符键的方法
	private void handleOperator(String key) {
		// 按下“/”键
		if (operator.equals("/")) {
			// 如果文本框中数字为零
			if (getNumberFromText() == 0.0) {
				// 非法操作
				operateValidFlag = false;
				resultText.setText("除数不能为零!");
			} else {
				// 除法运算
				resultNum /= getNumberFromText();
			}
		}
		// 按下“+”键
		else if (operator.equals("+")) {
			// 加法运算
			resultNum += getNumberFromText();
		}
		// 按下“-”键
		else if (operator.equals("-")) {
			// 减法运算
			resultNum -= getNumberFromText();
		}
		// 按下“*”键
		else if (operator.equals("*")) {
			// 乘法运算
			resultNum *= getNumberFromText();
		}
		// 按下“=”键
		else if (operator.equals("=")) {
			// 将文本框中的数字赋值给结果
			resultNum = getNumberFromText();
		}
		// 双精度浮点数运算
		if (operateValidFlag) {
			long t1;
			double t2;
			t1 = (long) resultNum;
			t2 = resultNum - t1;
			if (t2 == 0) {
				// 转化为字符串输入到结果文本框中
				resultText.setText(String.valueOf(t1));
			} else {
				// 同上
				resultText.setText(String.valueOf(resultNum));
			}
		}
		operator = key;
		firstDigit = true;
		operateValidFlag = true;
	}

	// 从文本框中获得数字
	private double getNumberFromText() {
		double result = 0;
		// 异常处理
		try {
			result = Double.valueOf(resultText.getText()).doubleValue();
		} catch (NumberFormatException e) {

		}
		return result;
	}

	private void playClickSound() {
		Play("F:\\java\\提示音.wav");
	}

	private void playStartSound() {
		Play("F:\\java\\登录音.wav");
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
