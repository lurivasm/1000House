package windows;

import app.*;
import exception.NotGuest;
import exception.NotHost;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ProfileWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel tabReserve, tabHistory, tabHouses, tabUsers;
	private JPanel profilePanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JTable tableHouse, tableHistory, tableReserve, tableUser;
	private DefaultTableModel dataHouse, dataReserve, dataHistory, dataUser;
	private JTabbedPane tabbPanel = new JTabbedPane();
	private JSplitPane splitPanel;
	private JLabel titleLabel, nameLabel, surnameLabel, nifLabel, stateLabel, ccnumberLabel;
	private JLabel name, surname, nif, state, ccnumber;
	private JButton menubutton = new JButton("Main menu");
	private JButton changeProfile = new JButton("Change Profile");
	/**
	 * Constructor for the create offer window
	 * @param list of the houses of the connected host
	 */
	public 	ProfileWindow(Application app) {
		super("1000House");
		int i = 0;
		
		/*We need the logged user*/
		User user = app.getLog();
			
		/*Labels*/
		titleLabel = new JLabel("Profile Data");
		titleLabel.setFont(new Font("Georgia",20,20));
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.ITALIC ,titleLabel.getFont().getSize()));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameLabel = new JLabel(" Name : ");
		surnameLabel = new JLabel(" Surname : " );
		nifLabel = new JLabel(" NIF : ");	
		ccnumberLabel = new JLabel(" Credit Card :"); 
		stateLabel = new JLabel(" Current State : ");	
		name = new JLabel(user.getName());
		name.setFont(new Font(name.getFont().getName(), Font.ITALIC ,name.getFont().getSize()));
		surname = new JLabel(user.getSurname());
		surname.setFont(new Font(surname.getFont().getName(), Font.ITALIC ,surname.getFont().getSize()));
		nif = new JLabel(user.getNIF());
		nif.setFont(new Font(nif.getFont().getName(), Font.ITALIC ,nif.getFont().getSize()));
	    state = new JLabel(user.getState().toString());
	    state.setFont(new Font(state.getFont().getName(), Font.ITALIC ,state.getFont().getSize()));
		if(user.isGuest() == true) {
			ccnumber = new JLabel(user.getGuestProfile().getccNumber());
			ccnumber.setFont(new Font(ccnumber.getFont().getName(), Font.ITALIC ,ccnumber.getFont().getSize()));
		}
	    else if (user.isHost() == true) {
	    	ccnumber = new JLabel(user.getHostProfile().getccNumber());
	    	ccnumber.setFont(new Font(ccnumber.getFont().getName(), Font.ITALIC ,ccnumber.getFont().getSize()));
	    }
		
		/*Label Panel*/
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.Y_AXIS));
		labelPanel.add(nameLabel);
		labelPanel.add(new JPanel());
		labelPanel.add(surnameLabel);
		labelPanel.add(new JPanel());
		labelPanel.add(nifLabel);
		labelPanel.add(new JPanel());
		labelPanel.add(stateLabel);
		labelPanel.add(new JPanel());
		labelPanel.add(ccnumberLabel);
		
		JPanel thingsPanel = new JPanel();
		thingsPanel.setLayout(new BoxLayout(thingsPanel,BoxLayout.Y_AXIS));
		thingsPanel.add(name);
		thingsPanel.add(new JPanel());
		thingsPanel.add(surname);
		thingsPanel.add(new JPanel());
		thingsPanel.add(nif);
		thingsPanel.add(new JPanel());
		thingsPanel.add(state);
		thingsPanel.add(new JPanel());
		thingsPanel.add(ccnumber);
		
		JPanel dataPanel = new JPanel();
		dataPanel.add(labelPanel);
		dataPanel.add(thingsPanel);
		
		/*If the user is host we add House Table*/
		if(user.isHost() == true) {
			Object [] titlesHouse = {"Location", "Zip Code", "Nº Offers"};
			Object[][] contentsHouse = new Object[user.getHostProfile().getHouses().size()][3];
			for(House h : user.getHostProfile().getHouses()) {
				contentsHouse[i][0] = h.getLocation();
				contentsHouse[i][1] = h.getZipcode(); 
				contentsHouse[i][2] = h.getOffers().size();
				i++;
			}
			
			dataHouse = new DefaultTableModel(contentsHouse, titlesHouse);
			tableHouse = new JTable(dataHouse);
			JScrollPane scrollHouse = new JScrollPane();
			scrollHouse.setViewportView (tableHouse);
			tabbPanel.addTab("Houses", scrollHouse);
		}
		
		/*If the user is guest we add History and Reserves Table*/
		if(user.isGuest() == true) {
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
			JScrollPane scrollHistory = new JScrollPane();
			scrollHistory.setViewportView (tableHistory);
			tabbPanel.addTab("History", scrollHistory);
			
			Object [] titlesReserve = {"Location", "Zip Code", "Type", "Price", "Host"};
			Object [][] contentsReserve = new Object[user.getGuestProfile().getReserves().size()][5];
			i = 0;
			for(Reserve o : user.getGuestProfile().getReserves()) {
				contentsReserve[i][0] = o.getOffer().getHouse().getLocation();
				contentsReserve[i][1] = o.getOffer().getHouse().getZipcode();
				if(o.getOffer() instanceof LivingOffer) contentsReserve[i][2] = "Living";
				else if(o.getOffer() instanceof HolidaysOffer) contentsReserve[i][2] = "Holidays";
				contentsReserve[i][3] = o.getOffer().getPrice();
				contentsReserve[i][4] = o.getOffer().getHouse().getHost().getName();
				i++;
			}
			dataReserve = new DefaultTableModel(contentsReserve, titlesReserve);
			tableReserve = new JTable(dataReserve);
			JScrollPane scrollReserve = new JScrollPane();
			scrollReserve.setViewportView (tableReserve);
			tabbPanel.addTab("Reserves", scrollReserve);
		}
		
		
		/*If the user is admin we add a list of banned users*/
		if(user.isAdmin() == true) {
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
			JScrollPane scrollUser = new JScrollPane();
			scrollUser.setViewportView (tableUser);
			tabbPanel.addTab("Banned Users", scrollUser);
		}
		
		/*Tabbed Panel*/
		titleLabel.setHorizontalAlignment(JTextField.LEFT);
		changeProfile.setHorizontalAlignment(JTextField.CENTER);
		tabbPanel.setSelectedIndex(0);
		
		/*Menu Panel*/
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
		profilePanel.add(changeProfile);
		changeProfile.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		for(int j = 0 ; j< 16 ; j++) {
			profilePanel.add(new JPanel());
		}		
		
		profilePanel.setMinimumSize(new Dimension(0, 0));
		tabbPanel.setMinimumSize(new Dimension(0, 0));
		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, profilePanel, tabbPanel);
		splitPanel.setOneTouchExpandable(true);
	//	splitPanel.setToolTipText("SUUUU");
		splitPanel.setDividerLocation(260);

		
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


		
}