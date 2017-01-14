package Zad2;

import Zad2Util.*;
import PersonEmployee.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PayrollGUI{
	private Payroll fullList; 			// wyświetlana lista pracowników 
	private String filePath; 			// ścieżka dostępu do pliku
	
	// deklaracja elementów interfejsu
	private JFrame payrollGUI = new JFrame("Lista płac");			// Główna ramka interfejsu
	private JPanel interfaceContent = new JPanel();					// Obiekt przechowujący interfejs
	
	// panel i przyciski menu głównego
	private JPanel mainMenu;
	private JButton newList;
	private JButton loadList;
	private JButton saveList;
	private JButton saveAsList;
	
	// panel i zakładki interfejsu obsługi pliku
	JTabbedPane payrollInterface;
	private JPanel actionPanel = new JPanel();
	private JScrollPane showPane = new JScrollPane();
	private JPanel showPanel = new JPanel();
	private JScrollPane searchPane = new JScrollPane();
	private JPanel searchPanel = new JPanel();
	private JScrollPane addPane = new JScrollPane();
	private JPanel addPanel = new JPanel();
	
	// przyciski i ikony panelu pokazującego pracowników
	private JButton[] removeEmp;
	private JButton[] showEmp;
	private Icon showEmpIcon = new ImageIcon("img/DetailIcon.png");
	private Icon removeEmpIcon = new ImageIcon("img/RemoveUserIcon.png");
	
	// przyciski i ikony panelu dodającego pracowników
	JPanel addCards;
	JButton birthDateButton;
	JTextField birthDateInput;
	JButton employmentDateButton;
	JTextField employmentDateInput;
	JTextField buildingInputAd;
	JTextField roomInputAd;
	JTextField buildingInputEd;
	JTextField roomInputEd;
	JComboBox<WeekDay> ohWeekdayInput;
	JTextField ohStartInput;
	JTextField ohEndInput;
	JComboBox<WeekDay> actWeekdayInput;
	JTextField actStartInput;
	JTextField actEndInput;
	JTextField actBuildingInput;
	JTextField actRoomInput;
	JButton addActButton;
	String[][] data = new String[0][0];
	String[] columnNames = {"dzień", "początek", "koniec", "budynek", "sala", "nazwa"};
	JTable activitiesTable;
	
	/*
	 * metoda programLoaded tworzy ramkę i wypełnia ją podstawowym interfejsem
	 * 		- przyciski nowy plik, otwórz, zapisz, zapisz jako, ostatnie dwa wstępnie nieaktywne
	 * 		- definiuje obszary okna, które następnie będą wykorzystywane przez menu, interfejs obsługi pliku i pasek informacyjny na dole 
	 */
	public void programLoaded(){
		// zdefiniowanie ramki
		payrollGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		payrollGUI.getContentPane().add(interfaceContent);
		
		// dodanie ikony do ramki
		payrollGUI.setIconImage(new ImageIcon("img/UsersIcon.png").getImage());
		
		// zdefiniowanie elementów składowych
		loadMenu();	
		
		// dodanie elementów do głównego interfejsu
		interfaceContent.setLayout(new BorderLayout());
		interfaceContent.add(mainMenu, BorderLayout.NORTH);
		interfaceContent.add(actionPanel, BorderLayout.CENTER);
						
		// wyświetlenie gotowego interfejsu
		payrollGUI.setSize(new Dimension(640, 400));
		payrollGUI.setVisible(true);
	}
	
	private void loadMenu(){
		mainMenu = new JPanel();
		mainMenu.setLayout(new FlowLayout(FlowLayout.LEFT));
		ActionListener menuListener = new MenuButonsListener();
		// new list button
		newList = new JButton(new ImageIcon("img/NewFileIcon.png"));
		newList.addActionListener(menuListener);
		newList.setPreferredSize(new Dimension(30,30));
		newList.setName("newFile");
		// load list button
		loadList = new JButton(new ImageIcon("img/OpenFileIcon.png"));
		loadList.addActionListener(menuListener);
		loadList.setPreferredSize(new Dimension(30,30));
		loadList.setName("openFile");
		//save list button
		saveList = new JButton(new ImageIcon("img/SaveIcon.png"));
		saveList.addActionListener(menuListener);
		saveList.setPreferredSize(new Dimension(30,30));
		saveList.setName("saveFile");
		saveList.setEnabled(false);
		// save list as button
		saveAsList = new JButton(new ImageIcon("img/SaveAsIcon.png"));
		saveAsList.addActionListener(menuListener);
		saveAsList.setPreferredSize(new Dimension(30,30));
		saveAsList.setName("saveFileAs");
		saveAsList.setEnabled(false);
		
		// dodanie przycisków
		mainMenu.add(newList);
		mainMenu.add(loadList);
		mainMenu.add(saveList);
		mainMenu.add(saveAsList);
	}
	
	/*
	 * metoda payrollLoaded wywołuje trzy metody wypełniające interfejs obsługi pliku:
	 * 		- payrollShow pokazującą listę pracowników pozwalająca na podgląd i suswanie pracowników
	 * 		- payrollAdd pokazującą formularz wprowadzania nowego pracownika
	 * 		- payrollSearch pokazująca formularz wyszukiwania pracownika o zadanych parametrach
	 */
	private void payrollLoaded(){
		// wyzerowanie interfejsu
		actionPanel.removeAll();
		actionPanel.revalidate();
		payrollGUI.repaint();
		
		// zdefiniowanie okien interfejsu
		payrollInterface = new JTabbedPane();
		payrollShow();
		payrollAdd();
		payrollSearch();
		
		// dodanie możliwości przewijania do zakładek
		showPane.setViewportView(showPanel);
		searchPane.setViewportView(searchPanel);
		addPane.setViewportView(addPanel);
		
		// dodanie okien interfejsu do zakładek
		payrollInterface.addTab("Lista pracowników ("+fullList.length()+")", new ImageIcon("img/UsersIcon.png"), showPane);
		payrollInterface.addTab("Wyszukaj...", new ImageIcon("img/SearchIcon.png"), searchPane);
		payrollInterface.addTab("Dodaj pracownika", new ImageIcon("img/AddUserIcon.png"), addPane);
		
		// wyświetlenie końcowego interfejsu
		actionPanel.setLayout(new BorderLayout());
		actionPanel.add(payrollInterface);
		payrollGUI.revalidate();
	}
	
	private void payrollShow(){
		// wyzerowanie karty
		showPanel.removeAll();
		showPanel.revalidate();
		showPanel.repaint();
		
		// zmiana etykiety zakładki o ile zakładka została zainicjalizowana
		if (payrollInterface.getTabCount()>0)payrollInterface.setTitleAt(0, "Lista pracowników ("+fullList.length()+")");
		
		// zdefiniowanie interfejsu
		showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
		
		// zdefiniowanie składowych
		JPanel[] employeeRecords  = new JPanel[fullList.length()];		// rekord
		JLabel[] empNumber = new JLabel[fullList.length()];				// liczba porządkowa
		JLabel[] nameLabels = new JLabel[fullList.length()];			// imię, nazwisko, tytuł
		JLabel[] peselNumbers = new JLabel[fullList.length()];			// numer pesel
		showEmp = new JButton[fullList.length()]; 						// podgląd szczegułów
		removeEmp = new JButton[fullList.length()]; 					// usuń pracownika z ewidencji
		
		// definiowanie wymiarów
		Dimension numberSize = new Dimension(30, 30);
		Dimension nameSize = new Dimension(240, 30);
		Dimension peselSize = new Dimension(120, 30);
		Dimension buttonSize = new Dimension(30, 30);
		
		// wygenerowanie treści
			// listener
		ManageEmpsListener showPanelListener = new ManageEmpsListener();
			// legenda
		JPanel legend = new JPanel();
		legend.setAlignmentX(Component.LEFT_ALIGNMENT);
		legend.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel numberLabel = new JLabel("l.p.");
		JLabel nameLabel = new JLabel("inię i nazwisko");
		JLabel peselLabel = new JLabel("PESEL");
		JLabel showLabel = new JLabel("<html><div style='text-align: center;'>szczegóły</div></html>");
		JLabel removeLabel = new JLabel("<html><div style='text-align: center'>usuń</div></html>");
		legend.add(numberLabel);
		legend.add(nameLabel);
		legend.add(peselLabel);
		legend.add(showLabel);
		legend.add(removeLabel);
		numberLabel.setPreferredSize(numberSize);
		nameLabel.setPreferredSize(nameSize);
		peselLabel.setPreferredSize(peselSize);
		showLabel.setPreferredSize(new Dimension(60, 30));
		removeLabel.setPreferredSize(new Dimension(60, 30));
		showPanel.add(legend);
			//treść tabeli
		for (int i=0; i<fullList.length(); i++){
			employeeRecords[i] = new JPanel();											// pojedynczy rekord tabeli pracowników
			employeeRecords[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			employeeRecords[i].setLayout(new FlowLayout(FlowLayout.LEFT));
			empNumber[i] = new JLabel((i+1)+".");										// liczba porządkowa w tabeli
			nameLabels[i] = new JLabel(fullList.getEmployee(i).getFullName());			// imię, nazwisko i tytuł pracownika
			peselNumbers[i] = new JLabel("("+fullList.getEmployee(i).getPesel()+")");	// pesel pracownika
			showEmp[i] = new JButton(showEmpIcon);										// przycisk "pokaż pracownika"
			showEmp[i].setName("show "+i);												// identyfikator przycisku
			showEmp[i].addActionListener(showPanelListener);
			removeEmp[i] = new JButton(removeEmpIcon);									// przycisk usuń pracownika
			removeEmp[i].setName("remove "+i);											// // identyfikator przycisku
			removeEmp[i].addActionListener(showPanelListener);
			
			// ustalanie rozmiaru
			empNumber[i].setPreferredSize(numberSize);
			nameLabels[i].setPreferredSize(nameSize);
			peselNumbers[i].setPreferredSize(peselSize);
			showEmp[i].setPreferredSize(buttonSize);
			removeEmp[i].setPreferredSize(buttonSize);
			
			// dodawanie składowych
			employeeRecords[i].add(empNumber[i]);
			employeeRecords[i].add(nameLabels[i]);
			employeeRecords[i].add(peselNumbers[i]);
			employeeRecords[i].add(Box.createHorizontalStrut(15));
			employeeRecords[i].add(showEmp[i]);
			employeeRecords[i].add(Box.createHorizontalStrut(30));
			employeeRecords[i].add(removeEmp[i]);
			showPanel.add(employeeRecords[i]);
		}
		
		// aktualizacja zawartości
		showPanel.revalidate();
	}
	
	private void payrollAdd(){
		// wyzerowanie karty
		addPanel.removeAll();
		addPanel.revalidate();
		addPanel.repaint();
		
		// zmienne wewnętrzne
		String [] titles = {"sz.p.", "inż.", "mgr", "mgr inż.", "lic.", "dr", "dr hab.", "doc.", "prof."};
		Gender[] genders = {new Gender("k"), new Gender("m")};
		String [] empType = {"<wybierz>","pracownik naukowo-dydaktyczny", "pracownik administracyjny", "pracownik techniczny", "pracownik ochrony"};
		WeekDay[] weekDays = {WeekDay.PN, WeekDay.WT, WeekDay.SR, WeekDay.CW, WeekDay.PT, WeekDay.SO, WeekDay.ND};
		
		// definicja panelu i kart składowych
			// layout i listener panelu dodawania pracownika
		FlowLayout innerLayout = new FlowLayout(FlowLayout.LEFT);
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		AddPanelListener addListener = new AddPanelListener(); 
			// główne panele składowe
		JPanel namePanel = new JPanel();
		JPanel genderBirthPanel = new JPanel();
		JPanel peselPanel = new JPanel();
		JPanel employeePanel = new JPanel();
		JPanel specializationPanel = new JPanel();
		
		// definicja składowych głównych dla klasy Person i Employee
			
			// imię, nazwisko, tytuł
		namePanel.setLayout(innerLayout);
				// imię
		JLabel nameLabel = new JLabel("Imię");
		JTextField nameInput = new JTextField();
		nameInput.setPreferredSize(new Dimension(90,30));
				// nazwisko
		JLabel surnameLabel = new JLabel("Nazwisko:");
		JTextField surnameInput = new JTextField();
		surnameInput.setPreferredSize(new Dimension(150,30));
				// tytuł
		JLabel titleLabel = new JLabel("tytuł:");
		JComboBox<String> titleInput = new JComboBox<String>(titles);
				// dodanie składowych
		namePanel.add(nameLabel);
		namePanel.add(nameInput);
		namePanel.add(surnameLabel);
		namePanel.add(surnameInput);
		namePanel.add(titleLabel);
		namePanel.add(titleInput);
			
			// płeć, dataUrodzenia
		genderBirthPanel.setLayout(innerLayout);
				//płeć
		JLabel genderLabel = new JLabel("Płeć:");
		JComboBox<Gender> genderInput = new JComboBox<Gender>(genders);
				// data urodzenia
		JLabel birthDateLabel = new JLabel("Data urodzenia:");
		birthDateInput = new JTextField(LocalDate.now().toString());
		birthDateInput.setPreferredSize(new Dimension(90,30));
		birthDateButton = new JButton(new ImageIcon("img/CalendarIcon.png"));
		birthDateButton.setPreferredSize(new Dimension(30,30));
		birthDateButton.setName("birthDate");
		birthDateButton.addActionListener(addListener);
				//dodanie składowych
		genderBirthPanel.add(genderLabel);
		genderBirthPanel.add(genderInput);
		genderBirthPanel.add(birthDateLabel);
		genderBirthPanel.add(birthDateInput);
		genderBirthPanel.add(birthDateButton);
			
			// pesel 
		peselPanel.setLayout(innerLayout);
		JLabel peselLabel = new JLabel("PESEL: ");
		JTextField peselInput = new JTextField();
		peselInput.setPreferredSize(new Dimension(90,30));
		peselPanel.add(peselLabel);
		peselPanel.add(peselInput);
			
			// data zatrudnienia i pensja
		employeePanel.setLayout(innerLayout);
				// data zatrudnienia
		JLabel employmentDateLabel = new JLabel("Data urodzenia:");
		employmentDateInput = new JTextField(LocalDate.now().toString());
		employmentDateInput.setPreferredSize(new Dimension(90,30));
		employmentDateButton = new JButton(new ImageIcon("img/CalendarIcon.png"));
		employmentDateButton.setPreferredSize(new Dimension(30,30));
		employmentDateButton.setName("employmentDate");
		employmentDateButton.addActionListener(addListener);
				// pensja
		JLabel salaryLabel = new JLabel("Pensja: ");
		JTextField salaryInput = new JTextField();
		salaryInput.setPreferredSize(new Dimension(90,30));
		JLabel salaryMoneyLabel = new JLabel("ZŁ");
				// dodanie składników
		employeePanel.add(employmentDateLabel);
		employeePanel.add(employmentDateInput);
		employeePanel.add(employmentDateButton);
		employeePanel.add(salaryLabel);
		employeePanel.add(salaryInput);
		employeePanel.add(salaryMoneyLabel);
		
		// dane specjalizacji
		JComboBox<String> empTypeChooser = new JComboBox<String>(empType);
		empTypeChooser.setSize(new Dimension(200, 30));
		empTypeChooser.addActionListener(addListener);
		JPanel typeChooserContainer = new JPanel();
		JLabel typeChooserLabel = new JLabel("Rodzaj stanowiska:");
		typeChooserContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
		typeChooserContainer.add(typeChooserLabel);
		typeChooserContainer.add(empTypeChooser);
		addCards = new JPanel(new CardLayout());
		JPanel edEmpPanel = new JPanel();
		JPanel adEmpPanel = new JPanel();
		JPanel mntEmpPanel = new JPanel();
		JPanel secEmpPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		edEmpPanel.setLayout(new BoxLayout(edEmpPanel, BoxLayout.Y_AXIS));
		adEmpPanel.setLayout(new BoxLayout(adEmpPanel, BoxLayout.Y_AXIS));
		secEmpPanel.setLayout(new BoxLayout(secEmpPanel, BoxLayout.Y_AXIS));
		mntEmpPanel.setLayout(new BoxLayout(mntEmpPanel, BoxLayout.Y_AXIS));
		
		addCards.add(emptyPanel, empType[0]);
		addCards.add(edEmpPanel, empType[1]);
		addCards.add(adEmpPanel, empType[2]);
		addCards.add(mntEmpPanel, empType[3]);
		addCards.add(secEmpPanel, empType[4]);
		
		specializationPanel.setLayout(new BoxLayout(specializationPanel, BoxLayout.Y_AXIS));
		specializationPanel.add(typeChooserContainer, BorderLayout.PAGE_START);
		specializationPanel.add(addCards);
		
		// panele specjalistyczne pracowników
			// definicja
		JPanel addressPanelAd = new JPanel();
		JPanel addressPanelEd = new JPanel();
		JPanel activityPanel = new JPanel();
		JPanel addedActivityPanel = new JPanel();
		JPanel officeHoursPanel = new JPanel();
		JPanel districtPanel = new JPanel();
		JPanel shiftPanel = new JPanel();
		addressPanelAd.setLayout(innerLayout);
		addressPanelEd.setLayout(innerLayout);
		activityPanel.setLayout(innerLayout);
		officeHoursPanel.setLayout(innerLayout);
		districtPanel.setLayout(innerLayout);
		shiftPanel.setLayout(innerLayout);

			// definicja składowych
		buildingInputAd = new JTextField();
		buildingInputAd.setPreferredSize(new Dimension(60,30));
		roomInputAd = new JTextField();
		roomInputAd.setPreferredSize(new Dimension(60,30));
		buildingInputEd = new JTextField();
		buildingInputEd.setPreferredSize(new Dimension(60,30));
		roomInputEd = new JTextField();
		roomInputEd.setPreferredSize(new Dimension(60,30));
		addressPanelAd.add(new JLabel("Budynek: "));
		addressPanelAd.add(buildingInputAd);
		addressPanelAd.add(new JLabel("Pokój: "));
		addressPanelAd.add(roomInputAd);
		addressPanelEd.add(new JLabel("Budynek: "));
		addressPanelEd.add(buildingInputEd);
		addressPanelEd.add(new JLabel("Pokój: "));
		addressPanelEd.add(roomInputEd);
		ohWeekdayInput = new JComboBox<WeekDay>(weekDays);
		ohStartInput = new JTextField();
		ohStartInput.setPreferredSize(new Dimension(60,30));
		ohEndInput = new JTextField();
		ohEndInput.setPreferredSize(new Dimension(60,30));
		officeHoursPanel.add(new JLabel("Konsultacje: "));
		officeHoursPanel.add(new JLabel("Dzień: "));
		officeHoursPanel.add(ohWeekdayInput);
		officeHoursPanel.add(new JLabel("Godziny: "));
		officeHoursPanel.add(ohStartInput);
		officeHoursPanel.add(new JLabel(" - "));
		officeHoursPanel.add(ohEndInput);
		activityPanel.add(new JLabel("Nowe zajęcia: "));
		actWeekdayInput = new JComboBox<WeekDay>(weekDays);
		actStartInput = new JTextField();
		actStartInput.setPreferredSize(new Dimension(60,30));
		actEndInput = new JTextField();
		actEndInput.setPreferredSize(new Dimension(60,30));
		actBuildingInput = new JTextField();
		actBuildingInput.setPreferredSize(new Dimension(60,30));
		actRoomInput = new JTextField();
		actRoomInput.setPreferredSize(new Dimension(60,30));
		JButton addActButton = new JButton("Dodaj");
		addActButton.setPreferredSize(new Dimension(80,30));
		addActButton.setName("addAct");
		addActButton.addActionListener(addListener);
		activityPanel.add(new JLabel("Dzień: "));
		activityPanel.add(actWeekdayInput);
		activityPanel.add(new JLabel("Godziny: "));
		activityPanel.add(actStartInput);
		activityPanel.add(new JLabel(" -  "));
		activityPanel.add(actEndInput);
		activityPanel.add(new JLabel("Budynek: "));
		activityPanel.add(actBuildingInput);
		activityPanel.add(new JLabel("Sala: "));
		activityPanel.add(actRoomInput);
		activityPanel.add(addActButton);
		
			// wypełnienie
		
			// dodanie
		edEmpPanel.add(addressPanelEd);
		edEmpPanel.add(officeHoursPanel);
		edEmpPanel.add(activityPanel);
		edEmpPanel.add(addedActivityPanel);
		adEmpPanel.add(addressPanelAd);
		mntEmpPanel.add(districtPanel);
		secEmpPanel.add(districtPanel);
		secEmpPanel.add(shiftPanel);
		
		// dodanie paneli do interfejsu nowego użytkownika
		addPanel.add(namePanel);
		addPanel.add(genderBirthPanel);
		addPanel.add(peselPanel);
		addPanel.add(employeePanel);
		addPanel.add(specializationPanel);
	}
	
	private void payrollSearch(){
		// wyświetlenie karty wyszukiwania
		searchPanel.setLayout(new CardLayout());
		JPanel findEmpType = new JPanel();
		JPanel findPersonalData = new JPanel();
		JPanel findSalary = new JPanel();
		JPanel findActivity = new JPanel();
		JPanel findBuilding = new JPanel();
		JPanel findShift = new JPanel();
	}

	// metody interfejsu
	private void showEmp(int index){
		JOptionPane.showMessageDialog(null, new JTextArea(fullList.getEmployee(index).toString()), fullList.getEmployee(index).getFullName(), JOptionPane.PLAIN_MESSAGE);
	}
	
	private boolean removeEmp(int index){
		if (index >= 0 && index < fullList.length() && areYouSure("Czy na pewno chcesz usunąć Poniższego pracownika z listy?"+"\n"+fullList.getEmployee(index).getFullName())){
			fullList.remove(index);
			payrollShow();
			return true;
		} else return false;
	}
	
	private boolean areYouSure(String question){
		int n = JOptionPane.showConfirmDialog(
	            null,
	            question,
	            "Pytanie",
	            JOptionPane.YES_NO_OPTION);
		if( n == JOptionPane.YES_OPTION) return true;
		else return false;
	}
	
	private void newFile(){
		if ((fullList!=null && areYouSure("Zmiany wprowadzone w otwartym pliku zostaną utracone. Kontynuować?"))||fullList==null) {
			fullList = new Payroll();
			filePath = null;
			payrollLoaded();
		}
	}
	
	private void openFile(){
		if ((fullList!=null && areYouSure("Zmiany wprowadzone w otwartym pliku zostaną utracone. Kontynuować?"))||fullList==null){
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
			fc.setDialogTitle("Wczytaj listę pracowników");
			fc.showSaveDialog(loadList);
			if(fc.getSelectedFile()!=null) {
				filePath = fc.getSelectedFile().getPath();
				fullList = Payroll.payrollIn(filePath);
				menuActivate();
				payrollLoaded();
			}
		}	
	}
		
	private void saveFile(){
		if (filePath == null) saveFileAs();
		else {
			fullList.payrollOut(filePath);
		}
	}
	
	private void saveFileAs(){
		if (fullList != null){
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new java.io.File("."));
			fc.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
			fc.setDialogTitle("Zapisz listę pracowników");
			fc.showSaveDialog(saveList);
			if(fc.getSelectedFile()!=null) {
				filePath = fc.getSelectedFile().getPath();
				fullList.payrollOut(filePath);
			}
		}
	}
	
	private void menuActivate(){
		saveList.setEnabled(true);
		saveAsList.setEnabled(true);
	}
	
	// obsługa zdarzeń
	private class ManageEmpsListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			String[] toDo = ((JButton)action.getSource()).getName().split(" ");
			switch (toDo[0]){
				case "show":
					showEmp(Integer.parseInt(toDo[1]));
					break;
				case "remove":
					removeEmp(Integer.parseInt(toDo[1]));
					break;
			}
		}
	}
	
	private class AddPanelListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			LocalDate inputDate;
			CalendarGUI calendarGui;
			if (action.getSource() instanceof JButton){
				switch (((JButton)action.getSource()).getName()){
					case "birthDate":
						try {
							inputDate = LocalDate.parse(birthDateInput.getText());
						} catch (Exception e){
							inputDate = LocalDate.now();
						}
						calendarGui = new CalendarGUI(payrollGUI, inputDate);
						calendarGui.setDate();
						birthDateInput.setText(calendarGui.getDate().toString());
						break;
					case "employmentDate":
						try {
							inputDate = LocalDate.parse(employmentDateInput.getText());
						} catch (Exception e){
							inputDate = LocalDate.now();
						}
						calendarGui = new CalendarGUI(payrollGUI, inputDate);
						calendarGui.setDate();
						employmentDateInput.setText(calendarGui.getDate().toString());
						break;
					case "addAct":
						String[][] result = new String[data.length+1][columnNames.length];
						for (int i=0; i<data.length; i++){
							for (int j=0; j<columnNames.length; i++){
								result[i][j] = data[i][j];
							}
						}
						
						break;
				}
			} // instance of JButton
			if (action.getSource() instanceof JComboBox){
				CardLayout c1 = (CardLayout)(addCards.getLayout());
				c1.show(addCards, ((JComboBox<String>)action.getSource()).getSelectedItem().toString());
			}
		}
	}
	
	private class MenuButonsListener implements ActionListener{
		public void actionPerformed(ActionEvent action) {
			switch (((JButton)action.getSource()).getName()){
				case "newFile":
					newFile();
					break;
				case "openFile":
					openFile();
					break;
				case "saveFile":
					saveFile();
					break;
				case "saveFileAs":
					saveFileAs();
					break;
			}
		}
	}
}