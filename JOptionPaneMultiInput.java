import javax.swing.*;

public class JOptionPaneMultiInput{
	public static void main(String[] args){
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("X: "));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15));
		myPanel.add(new JLabel("Y: "));
		myPanel.add(yField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please enter X and Y Values", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION){
			System.out.println("x = "+xField.getText());
			System.out.println("y = "+yField.getText());
		}
	}
}