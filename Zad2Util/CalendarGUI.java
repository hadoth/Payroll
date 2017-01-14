package Zad2Util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Insets;

public class CalendarGUI extends JDialog {
	private CalendarGUI thisFrame;
	private LocalDate resultDate;
	private LocalDate tempDate;
	private LocalDate inputDate;
	private JPanel monthPanel;
	private CalendarListener eventHandler = new CalendarListener();
	private MyButton confirmButton = MyButton.bigButton("OK", "confirm", eventHandler);
	private MyButton cancelButton = MyButton.bigButton("Anuluj", "cancel", eventHandler);
	JLabel dateLabel = new JLabel();

	
	public CalendarGUI(JFrame parentFrame, LocalDate date){
		// definowanie okna i ustalenie właściwości
		super(new JFrame(), true);
		thisFrame = this;
		if (date == null){
			date = LocalDate.now();
		}
		inputDate = date;
		tempDate = date;
		resultDate = date;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setUndecorated(true);
		this.setFocusable(true);
		
		
		// definicja wyglądu i głównych paneli
		JPanel borderPanel = new JPanel();
		JPanel contentPanel = new JPanel();
		JPanel calendarPanel = new JPanel();
		JPanel confirmationPanel = new JPanel();
		borderPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.getContentPane().add(borderPanel);
		borderPanel.add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(calendarPanel, BorderLayout.NORTH);
		contentPanel.add(confirmationPanel, BorderLayout.SOUTH);
		
		// panel kalendarza
		calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
			// panel nawigacji
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
		MyButton leftYear = MyButton.smallButton(new ImageIcon("img/LeftLeftIcon.png"), "yearLeft", eventHandler);
		MyButton leftMonth = MyButton.smallButton(new ImageIcon("img/LeftIcon.png"), "monthLeft", eventHandler);
		MyButton rightMonth = MyButton.smallButton(new ImageIcon("img/RightIcon.png"), "monthRight", eventHandler);
		MyButton rightYear = MyButton.smallButton(new ImageIcon("img/RightRightIcon.png"), "yearRight", eventHandler);
		
		menuPanel.add(leftYear);
		menuPanel.add(leftMonth);
		menuPanel.add(Box.createHorizontalStrut(10));
		menuPanel.add(dateLabel);
		menuPanel.add(Box.createHorizontalStrut(10));
		menuPanel.add(rightMonth);
		menuPanel.add(rightYear);
		
		calendarPanel.add(menuPanel);
			// panel miesiąca
		monthPanel = new JPanel();
		monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
		monthPanel.setPreferredSize(new Dimension(7*30, 6*30));
		showDate();
		
		calendarPanel.add(monthPanel);
			// panel potwierdzenia
		confirmationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		confirmationPanel.add(confirmButton);
		confirmationPanel.add(cancelButton);
		
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public LocalDate getDate(){
		return this.resultDate;
	}
	
	public void setDate(){
		this.setVisible(true);
	}
	
	private void cancel(){
		this.resultDate = this.inputDate;
		this.setVisible(false);
	}
	
	private void confirm(){
		this.resultDate = this.tempDate;
		this.setVisible(false);
	}
	
	private void showDate(){
		dateLabel.setText(tempDate.toString());
		dateLabel.revalidate();
		if (monthPanel != null){
			monthPanel.removeAll();
			monthPanel.revalidate();
		}
		int dayNumber = tempDate.lengthOfMonth();
		int firstDay = (tempDate.minusDays(tempDate.getDayOfMonth()-1)).getDayOfWeek().getValue();
		
		
		FlowLayout weekLayout = new FlowLayout(FlowLayout.CENTER);
		weekLayout.setHgap(0);
		weekLayout.setVgap(0);
		InnerListener innerListener = new InnerListener();
		
		int fullCounter=0;
		int weekCounter=0;
		JPanel[] weeks = new JPanel[100];
		while (fullCounter<dayNumber){
			weeks[weekCounter] = new JPanel();
			weeks[weekCounter].setLayout(weekLayout);
			for (int i=0; i<7; i++) {
				if (firstDay-->1 || fullCounter>=dayNumber) weeks[weekCounter].add(MyButton.smallButton(null, null, null, false));
				else {
					weeks[weekCounter].add(MyButton.smallButton((fullCounter+1)+"", "day "+(fullCounter+1), innerListener, true));
					fullCounter++;
				}
			}
			monthPanel.add(weeks[weekCounter]);
			weekCounter++;
		}
	}
	
	private class InnerListener implements ActionListener{
		public void actionPerformed(ActionEvent action){
			int dayOfMonth = Integer.parseInt((((MyButton)action.getSource()).getName().split(" "))[1]);
			tempDate = LocalDate.of(tempDate.getYear(), tempDate.getMonthValue(), dayOfMonth);
			dateLabel.setText(tempDate.toString());
			dateLabel.revalidate();
		}
	}
	
	private class CalendarListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			switch (((JButton)action.getSource()).getName()){
				case "monthLeft": 
					tempDate=tempDate.minusMonths(1);
					thisFrame.showDate();
					break;
				case "yearLeft":
					tempDate=tempDate.minusYears(1);
					thisFrame.showDate();
					break;
				case "monthRight":
					tempDate=tempDate.plusMonths(1);
					thisFrame.showDate();
					break;
				case "yearRight":
					tempDate=tempDate.plusYears(1);
					thisFrame.showDate();
					break;
				case "cancel":
					thisFrame.cancel();
					break;
				case "confirm":
					thisFrame.confirm();
					break;
			}
		}
	}
	
	private static class MyButton extends JButton{
		
		private MyButton(){
			super();
		}
		
		public static MyButton smallButton(Icon icon, String name, CalendarListener listener){
			MyButton result = new MyButton();
			result.setName(name);
			result.setIcon(icon);
			result.setPreferredSize(new Dimension(30,30));
			result.setMargin(new Insets(2, 2, 2, 2));
			result.addActionListener(listener);
			return result;
		}
		
		public static MyButton smallButton(String label, String name, InnerListener listener, boolean isEnabled){
			MyButton result = new MyButton();
			result.setName(name);
			result.setText(label);
			result.setPreferredSize(new Dimension(30,30));
			result.setMargin(new Insets(2, 2, 2, 2));
			result.addActionListener(listener);
			result.setEnabled(isEnabled);
			result.setBackground(Color.white);
			result.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			return result;
		}
		
		public static MyButton bigButton(String label, String name, CalendarListener listener){
			MyButton result = new MyButton();
			result.setText(label);
			result.setName(name);
			result.setPreferredSize(new Dimension(80, 30));
			result.addActionListener(listener);
			return result;
		}
	}
}