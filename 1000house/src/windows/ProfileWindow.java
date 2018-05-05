package windows;

import app.*;
import controllers.*;
import exception.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ProfileWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel thingsPanel = new JPanel();
	private JPanel labelPanel = new JPanel();
	private JPanel profilePanel = new JPanel();
	private JPanel reservePanel = new JPanel();
	private JPanel changeOfferPanel = new JPanel();
	private JComboBox<String> combobox, comboboxWO, comboboxCO, comboboxThing;
	private JTable tableWaitoffer, tableHouse, tableHistory, tableReserve, tableUser, tableBanUser, tableOffer;
	private DefaultTableModel dataWaitoffer, dataHouse, dataReserve, dataHistory, dataOffer, dataUser, dataBanUser;
	private JScrollPane scrollWaitoffer, scrollHistory, scrollHouse, scrollReserve, scrollUser, scrollBanUser, scrollOffer;
	private JTabbedPane tabbPanel = new JTabbedPane();
	private JSplitPane splitPanel;
	private JLabel titleLabel, nameLabel, surnameLabel, nifLabel, stateLabel, ccnumberLabel;
	private JLabel name, surname, nif, state, ccnumber;
	private JButton menubutton = new JButton("Main menu");
	private JButton changeProfile = new JButton("Change Profile");
	private JButton changeCCButton = new JButton("Apply");
	private JButton accept = new JButton("Accept");
	private JButton deny = new JButton("Deny");
	private JButton ask = new JButton("Ask for Changes");
	private JButton see = new JButton("See");
	private JButton paybutton = new JButton(" Pay ");
	private JButton cancelbutton = new JButton("Cancel");
	private JButton changeoffer = new JButton("Change Offer");
	private JTextField textfield, oldccnumber, newccnumber;
	
	/**
	 * Constructor for the create offer window
	 * @param the application
	 */
	public 	ProfileWindow(Application app) {
		super("1000House");
		int i = 0;
		
	
		/*We need the logged user*/
		User user = app.getLog();
		
		/*State And Ttile Label*/
		titleLabel = new JLabel("Profile Data");
		titleLabel.setFont(new Font("Georgia",20,20));
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC ,titleLabel.getFont().getSize()));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);	
		stateLabel = new JLabel(" Current State : ");
		state = new JLabel(user.getState().toString());
	    state.setFont(new Font(state.getFont().getName(), Font.ITALIC ,state.getFont().getSize()));
		
		/*The admin does not have profile*/
		if(user.isGuest() == true || user.isHost() == true) {
			
			/*Labels*/
			nameLabel = new JLabel(" Name : ");
			surnameLabel = new JLabel(" Surname : " );
			nifLabel = new JLabel(" NIF : ");	
			ccnumberLabel = new JLabel(" Credit Card :"); 	
			name = new JLabel(user.getName());
			name.setFont(new Font(name.getFont().getName(), Font.ITALIC ,name.getFont().getSize()));
			surname = new JLabel(user.getSurname());
			surname.setFont(new Font(surname.getFont().getName(), Font.ITALIC ,surname.getFont().getSize()));
			nif = new JLabel(user.getNIF());
			nif.setFont(new Font(nif.getFont().getName(), Font.ITALIC ,nif.getFont().getSize()));
		    if(user.isGuest() == true) {
				ccnumber = new JLabel(user.getGuestProfile().getccNumber());
				ccnumber.setFont(new Font(ccnumber.getFont().getName(), Font.ITALIC ,ccnumber.getFont().getSize()));
			}
		    else if (user.isHost() == true) {
		    	ccnumber = new JLabel(user.getHostProfile().getccNumber());
		    	ccnumber.setFont(new Font(ccnumber.getFont().getName(), Font.ITALIC ,ccnumber.getFont().getSize()));
		    }
		    			
			
			/*Profile Panel*/
			labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.Y_AXIS));
			labelPanel.add(nameLabel);
			labelPanel.add(new JPanel());
			labelPanel.add(surnameLabel);
			labelPanel.add(new JPanel());
			labelPanel.add(nifLabel);
			labelPanel.add(new JPanel());
			labelPanel.add(ccnumberLabel);
			labelPanel.add(new JPanel());
			thingsPanel.setLayout(new BoxLayout(thingsPanel,BoxLayout.Y_AXIS));
			thingsPanel.add(name);
			thingsPanel.add(new JPanel());
			thingsPanel.add(surname);
			thingsPanel.add(new JPanel());
			thingsPanel.add(nif);
			thingsPanel.add(new JPanel());
			thingsPanel.add(ccnumber);
			thingsPanel.add(new JPanel());
		}
		
		/*The admin does have state*/
		labelPanel.add(stateLabel);
		thingsPanel.add(state);
		JPanel dataPanel = new JPanel();
		dataPanel.add(labelPanel);
		dataPanel.add(thingsPanel);
		
		/*If the user is guest we add History and Reserves Table*/
		if(user.isGuest() == true) {
			/*History Panel*/
			Object [] titlesHistory = {"Location", "Zip Code", "Type", "Price", "Host"};
			Object [][] contentsHistory = new Object[user.getGuestProfile().getOffers().size()][5];
			i = 0;
			for(Offer o : user.getGuestProfile().getOffers()) {
				contentsHistory[i][0] = o.getHouse().getLocation();
				contentsHistory[i][1] = o.getHouse().getZipcode();
				if(o instanceof LivingOffer) contentsHistory[i][2] = "Living";
				else if(o instanceof HolidaysOffer) contentsHistory[i][2] = "Holidays";
				contentsHistory[i][3] = o.getPrice();
				contentsHistory[i][4] = o.getHouse().getHost().getName();
				i++;
			}
			dataHistory = new DefaultTableModel(contentsHistory, titlesHistory);
			tableHistory = new JTable(dataHistory);
			scrollHistory = new JScrollPane();
			scrollHistory.setViewportView (tableHistory);
			
			/*Reserve Panel*/
			Object [] titlesReserve = {"Location", "Zip Code", "Type", "Price", "IniDate"};
			Object [][] contentsReserve = new Object[user.getGuestProfile().getReserves().size()][5];
			i = 0;
			for(Reserve o : user.getGuestProfile().getReserves()) {
				contentsReserve[i][0] = o.getOffer().getHouse().getLocation();
				contentsReserve[i][1] = o.getOffer().getHouse().getZipcode();
				if(o.getOffer() instanceof LivingOffer) contentsReserve[i][2] = "Living";
				else if(o.getOffer() instanceof HolidaysOffer) contentsReserve[i][2] = "Holidays";
				contentsReserve[i][3] = o.getOffer().getPrice();
				contentsReserve[i][4] = o.getOffer().getIniDate().toString();
				i++;
			}
			dataReserve = new DefaultTableModel(contentsReserve, titlesReserve);
			tableReserve = new JTable(dataReserve);
			scrollReserve = new JScrollPane();
			scrollReserve.setViewportView (tableReserve);
			
			
			/*Pay and Cancel*/
			JPanel payPanel = new JPanel();
			payPanel.add(paybutton);
			payPanel.add(cancelbutton);
			
			String r[] = new String[user.getGuestProfile().getReserves().size()];
			i = 0;
			for(Reserve a : user.getGuestProfile().getReserves()) {
				r[i] = a.getOffer().getHouse().getLocation() +"-"+ a.getOffer().getHouse().getZipcode();
				i++;
			}
			combobox = new JComboBox<String>(r);
			JPanel aux = new JPanel();
			aux.add(combobox);
			reservePanel.setLayout(new BoxLayout(reservePanel,BoxLayout.Y_AXIS));
			reservePanel.add(scrollReserve);
			reservePanel.add(aux);
			reservePanel.add(payPanel);
			
			/*Add to the tabb Panel*/
			tabbPanel.addTab("Reserves", reservePanel);
			tabbPanel.addTab("History", scrollHistory);
		}
		
		
		/*If the user is host we add House Table*/
		if(user.isHost() == true) {
			/*House Panel*/
			Object [] titlesHouse = {"Location", "Zip Code", "Nº Offers"};
			Object[][] contentsHouse = new Object[user.getHostProfile().getHouses().size()][3];
			i = 0;
			for(House h : user.getHostProfile().getHouses()) {
				contentsHouse[i][0] = h.getLocation();
				contentsHouse[i][1] = h.getZipcode(); 
				contentsHouse[i][2] = h.getOffers().size();
				i++;
			}
			
			dataHouse = new DefaultTableModel(contentsHouse, titlesHouse);
			tableHouse = new JTable(dataHouse);
			scrollHouse = new JScrollPane();
			scrollHouse.setViewportView (tableHouse);
			tabbPanel.addTab("Houses", scrollHouse);
			
			/*Offer Panel*/
			Object [] titlesOffer = {"Location", "Type", "Price", "State", "Rate", "Number"};
			int size = 0;
			for(House h : user.getHostProfile().getHouses()) size += h.getOffers().size();
			Object[][] contentsOffer = new Object[size][6];
			i = 0;
			int a;
			String co[] = new String[app.getwaitoffers().size()];
			for(House h : user.getHostProfile().getHouses()) {
				if(h.getOffers().isEmpty() == true) continue;
				for(Offer o : h.getOffers()) {
					contentsOffer[i][0] = h.getLocation(); 
					if(o instanceof LivingOffer) contentsOffer[i][1] = "Living";
					else if(o instanceof HolidaysOffer) contentsOffer[i][1] = "Holidays";
					contentsOffer[i][2] = o.getPrice();
					contentsOffer[i][3] = o.getState();
					contentsOffer[i][4] = o.getAverageRate();
					a = i+1;
					co[i] = ""+a;
					contentsOffer[i][5] = ""+a;
					i++;
				}
			}
			
			dataOffer = new DefaultTableModel(contentsOffer, titlesOffer);
			tableOffer = new JTable(dataOffer);
			scrollOffer = new JScrollPane();
			scrollOffer.setViewportView (tableOffer);
			
			String thing[] = {"Price", "Deposit", "Initial Date", "End Date", "Duration"};
			comboboxThing = new JComboBox<String>(thing);
			comboboxCO = new JComboBox<String>(co);
			
			/*The panel for changes*/
			JPanel choose1 = new JPanel();
			JLabel l1 = new JLabel("Nº offer : ");
			JLabel l2 = new JLabel("Change: ");

			textfield = new JTextField(9);
			choose1.add(l1);
			choose1.add(comboboxCO);
			choose1.add(changeoffer);
			JPanel choose2 = new JPanel();
			choose2.add(l2);
			choose2.add(comboboxThing);
			choose2.add(textfield);
	
			JPanel text = new JPanel();
			text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
			text.add(choose1);
			text.add(choose2);
			
			changeOfferPanel = new JPanel();
			changeOfferPanel.setLayout(new BoxLayout(changeOfferPanel,BoxLayout.Y_AXIS));
			changeOfferPanel.add(scrollOffer);
			changeOfferPanel.add(text);
			tabbPanel.addTab("Offers", changeOfferPanel);
						
			
			/*For users who are guest and host we set invisible the other state*/
			if(user.getState().equals(UserStates.CONNECTED_GUEST) == true) {
				tabbPanel.remove(2);
				tabbPanel.remove(2);
			}
			if(user.getState().equals(UserStates.CONNECTED_HOST) == true &&user.isGuest() == true) {
				tabbPanel.remove(0);
				tabbPanel.remove(0);
			}
		}
		
		
		/*If the user is admin we add a list of banned users and a list of waiting offers*/
		if(user.isAdmin() == true) {
			/*User Panel*/
			Object [] titlesUser = {"Name", "Surname", "NIF", "State", "Debt"};
			Object [][] contentsUser = new Object[app.getUsers().size()][5];
			i = 0;
			for(User o : app.getUsers()) {
				contentsUser[i][0] = o.getName();
				contentsUser[i][1] = o.getSurname();
				contentsUser[i][2] = o.getNIF();
				contentsUser[i][3] = o.getState();
				contentsUser[i][4] = o.getDebt();
				i++;
			}
			dataUser = new DefaultTableModel(contentsUser, titlesUser);
			tableUser = new JTable(dataUser);
			scrollUser = new JScrollPane();
			scrollUser.setViewportView (tableUser);
			tabbPanel.addTab("Users", scrollUser);
			
			/*Banned User Panel*/
			Object [] titlesBanUser = {"Name", "Surname", "NIF", "State", "Debt"};
			Object [][] contentsBanUser = new Object[app.getBanUsers().size()][5];
			i = 0;
			for(User o : app.getBanUsers()) {
				contentsBanUser[i][0] = o.getName();
				contentsBanUser[i][1] = o.getSurname();
				contentsBanUser[i][2] = o.getNIF();
				contentsBanUser[i][3] = o.getState();
				contentsBanUser[i][4] = o.getDebt();
				i++;	
			}
			JLabel newlabel = new JLabel("Introduce the new CCNUmber:");
			JLabel oldlabel = new JLabel("Introduce the old CCNumber:");
			newccnumber = new JTextField(16);
			oldccnumber = new JTextField(16);
			
			dataBanUser = new DefaultTableModel(contentsBanUser, titlesBanUser);
			tableBanUser = new JTable(dataBanUser);
			scrollBanUser = new JScrollPane();
			scrollBanUser.setViewportView (tableBanUser);
			JPanel oldpanel = new JPanel();
			oldpanel.add(oldccnumber);
			JPanel newpanel = new JPanel();
			newpanel.add(newccnumber);
			JPanel butpanel = new JPanel();
			butpanel.add(changeCCButton);
			
			JPanel banPanel = new JPanel();
			banPanel.setLayout(new BoxLayout(banPanel,BoxLayout.Y_AXIS));
			banPanel.add(scrollBanUser);
			banPanel.add(oldlabel);
			banPanel.add(oldpanel);
			banPanel.add(new JPanel());
			banPanel.add(newlabel);
			banPanel.add(newpanel);
			banPanel.add(butpanel);
			tabbPanel.addTab("Banned Users", banPanel);
			
			/*Waiting Offers Panel*/
			Object [] titlesWaitoffer = {"Location", "Zip Code", "Type", "Price", "Host", "Number"};
			Object [][] contentsWaitoffer = new Object[app.getwaitoffers().size()][6];
			i = 0;
			int a;
			for(Offer o : app.getwaitoffers()) {
				contentsWaitoffer[i][0] = o.getHouse().getLocation();
				contentsWaitoffer[i][1] = o.getHouse().getZipcode();
				if(o instanceof LivingOffer) contentsWaitoffer[i][2] = "Living";
				else if(o instanceof HolidaysOffer) contentsWaitoffer[i][2] = "Holidays";
				contentsWaitoffer[i][3] = o.getPrice();
				contentsWaitoffer[i][4] = o.getHouse().getHost().getName();
				a = i+1;
				contentsWaitoffer[i][5] = ""+a;
				i++;	
			}
			dataWaitoffer = new DefaultTableModel(contentsWaitoffer, titlesWaitoffer);
			tableWaitoffer = new JTable(dataWaitoffer);
			scrollWaitoffer = new JScrollPane();
			scrollWaitoffer.setViewportView (tableWaitoffer);
			
			String wo[] = new String[app.getwaitoffers().size()];
			for(i = 0; i < app.getwaitoffers().size(); i++) {
				a = i+1;
				wo[i] = ""+a;
			}
			comboboxWO = new JComboBox<String>(wo);
			
			JPanel tpanel = new JPanel();
			tpanel.add(see);
			tpanel.add(accept);
			tpanel.add(deny);
			JPanel askpanel = new JPanel();
			askpanel.add(comboboxWO);
			askpanel.add(ask);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
			buttonPanel.add(askpanel);
			buttonPanel.add(tpanel);
						
			
			JPanel waitPanel = new JPanel();
			waitPanel.setLayout(new BoxLayout(waitPanel,BoxLayout.Y_AXIS));
			waitPanel.add(scrollWaitoffer);
			waitPanel.add(buttonPanel);
			
			tabbPanel.addTab("Waiting Offers", waitPanel);
			
		}
		
		/*Tabbed Panel*/
		titleLabel.setHorizontalAlignment(JTextField.LEFT);
		changeProfile.setHorizontalAlignment(JTextField.CENTER);
		tabbPanel.setSelectedIndex(0);
		
		/*Data Profile Panel*/
		BoxLayout menuLayout = new BoxLayout(profilePanel,BoxLayout.Y_AXIS);
		profilePanel.setLayout(menuLayout);	
		profilePanel.add(new JPanel());
		profilePanel.add(titleLabel);
		profilePanel.add(new JPanel());
		profilePanel.add(dataPanel);
		profilePanel.add(new JPanel());
		profilePanel.add(menubutton);
		menubutton.setAlignmentX(Component.CENTER_ALIGNMENT);
		profilePanel.add(new JPanel());
		if(user.isGuest() == true && user.isHost() == true) {
			profilePanel.add(changeProfile);
			changeProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		for(int j = 0 ; j< 16 ; j++) profilePanel.add(new JPanel());		
		profilePanel.setMinimumSize(new Dimension(0, 0));
		tabbPanel.setMinimumSize(new Dimension(0, 0));
		
		/*Final split panel*/
		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, profilePanel, tabbPanel);
		splitPanel.setOneTouchExpandable(true);
	//	splitPanel.setToolTipText("SUUUU");
		splitPanel.setDividerLocation(270);
		if(app.getLog().isAdmin() == true) splitPanel.setDividerLocation(200);
		
		/*The container*/
		Container cont = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		cont.setLayout(layout);
		cont.add(splitPanel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Getter of the old ccnumber jtextfield
	 * @return oldccnumber
	 */
	public String getOlcCCNumber() {
		return this.oldccnumber.getText();
	}
	
	/**
	 * Getter of the new ccnumber jtextfield
	 * @return newccnumber
	 */
	public String getNewCCNUmber() {
		return this.newccnumber.getText();
	}
	
	/**
	 * Getter of the changes jtextfield
	 * @return changes
	 */
	public String getChanges() {
		return this.textfield.getText();
	}
	
	
	/**
	 * Getter of the house table
	 * @return the house scroll panel
	 */
	public JScrollPane getHouseScroll() {
		return this.scrollHouse;
	}
	
	/**
	 * Getter of the offer table
	 * @return the offer scroll panel
	 */
	public JScrollPane getOfferScroll() {
		return this.scrollOffer;
	}
	
	/**
	 * Getter of the history table
	 * @return the history scroll panel
	 */
	public JScrollPane getHistoryScroll() {
		return this.scrollHistory;
	}
	
	/**
	 * Getter of the reserve table
	 * @return the house reserve panel
	 */
	public JScrollPane getReserveScroll() {
		return this.scrollReserve;
	}
	
	/**
	 * Getter of the tabbed panel
	 * @return the tabbed panel
	 */
	public JTabbedPane getTabbedPane() {
		return this.tabbPanel;
	}
	
	/**
	 * Setter of the state label when changing from host to guest or viceversa
	 * @param the state to set
	 */
	public void setStateLabel(UserStates s) {
		this.state.setText(s.toString());
	}
	
	/**
	 * Getter of the combobox reserve panel
	 * @return thecombobox reserve panel
	 */
	public JComboBox<String> getReserveComboBox() {
		return this.combobox;
	}
	
	/**
	 * Getter of the combobox waiting offers panel
	 * @return thecombobox waiting offers panel
	 */
	public JComboBox<String> getWOComboBox() {
		return this.comboboxWO;
	}
	
	/**
	 * Getter of the combobox changed offers panel
	 * @return thecombobox changed offers panel
	 */
	public JComboBox<String> getCOComboBox() {
		return this.comboboxCO;
	}
	
	/**
	 * Getter of the combobox number changed offers panel
	 * @return thecombobox number changed offers panel
	 */
	public JComboBox<String> getNCOComboBox() {
		return this.comboboxThing;
	}
	
	/**
	 * Getter of the reserve table
	 * @return the reserve tabbed panel
	 */
	public JPanel getReservePanel() {
		return this.reservePanel;
	}
	
	/**
	 * Getter of the change offer panel
	 * @return the change offer tabbed panel
	 */
	public JPanel getChangeOfferPanel() {
		return this.changeOfferPanel;
	}
	

	/**
	 * Set the action for the changeProfile button
	 * @param c the actionListener for the changeProfile button
	 */
	public void setProfileController(ActionListener c) {
		changeProfile.addActionListener(c);	
		paybutton.addActionListener(c);
		cancelbutton.addActionListener(c);
		changeoffer.addActionListener(c);
	}
	
	/**
	 * Set the action for the admin's button
	 * @param c the actionListener for the changeProfile button
	 */
	public void setAdminController(ActionListener c) {
		changeCCButton.addActionListener(c);	
		accept.addActionListener(c);
		ask.addActionListener(c);
		deny.addActionListener(c);
		see.addActionListener(c);
	}
	
	/**
	 * Set the action for the menu button
	 * @param c the actionListener for the buttons
	 */
	public void setMenuController(ActionListener c) {
		menubutton.addActionListener(c);
	}

		
}