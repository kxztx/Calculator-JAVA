package 简易计算器;

import java.awt.BorderLayout;

import javax.swing.*;

public class AboutDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutDialog(AboutDialog aboutDialog) {
		super(aboutDialog, "About Dialog", true);

	}

	public void AboutDialogAppear() {
		JDialog dialog = new AboutDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(100, 100);
		add(new JLabel("<html><h1><i>Calculater</i></h1><hr>By T.X.Zhang</html>"), BorderLayout.CENTER);
		JButton ok = new JButton("确认");
		ok.addActionListener(event -> setVisible(false));
		JPanel panel = new JPanel();
		panel.add(ok);
		add(panel, BorderLayout.SOUTH);
		pack();
		
	}
}
