package ss.week7.challenge.chatbox;

public class ServerGUI extends JFrame implements ActionListener, MessageUI {
	private JButton bConnect;
	private JTextField tfPort;
	private JTextArea taMessages;
	private Server server;

	/** Constructs a ServerGUI object. */
	public ServerGUI() {
		super("ServerGUI");

		buildGUI();
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/** Builds the GUI. */
	public void buildGUI() {
		setSize(400, 400);

		// Panel p1 - Listen

		JPanel p1 = new JPanel(new FlowLayout());
		JPanel pp = new JPanel(new GridLayout(2, 2));

		JLabel lbAddress = new JLabel("Address: ");
		JTextField tfAddress = new JTextField(getHostAddress(), 12);
		tfAddress.setEditable(false);

		JLabel lbPort = new JLabel("Port:");
		tfPort = new JTextField("2727", 5);

		pp.add(lbAddress);
		pp.add(tfAddress);
		pp.add(lbPort);
		pp.add(tfPort);

		bConnect = new JButton("Start Listening");
		bConnect.addActionListener(this);

		p1.add(pp, BorderLayout.WEST);
		p1.add(bConnect, BorderLayout.EAST);

		// Panel p2 - Messages

		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());

		JLabel lbMessages = new JLabel("Messages:");
		taMessages = new JTextArea("", 15, 50);
		taMessages.setEditable(false);
		p2.add(lbMessages);
		p2.add(taMessages, BorderLayout.SOUTH);

		Container cc = getContentPane();
		cc.setLayout(new FlowLayout());
		cc.add(p1);
		cc.add(p2);
	}

	/** returns the Internetadress of this computer */
	private String getHostAddress() {
		try {
			InetAddress iaddr = InetAddress.getLocalHost();
			return iaddr.getHostAddress();
		} catch (UnknownHostException e) {
			return "?unknown?";
		}
	}

	/**
	 * listener for the "Start Listening" button
	 */
	public void actionPerformed(ActionEvent ev) {
		Object src = ev.getSource();
		if (src == bConnect) {
			startListening();
		}
	}

	/**
	 * Construct a Server-object, which is waiting for clients. 
	 * The port field and button should be disabled
	 */
	private void startListening() {
		int port = 0;

		try {
			port = Integer.parseInt(tfPort.getText());
		} catch (NumberFormatException e) {
			addMessage("ERROR: not a valid portnumber!");
			return;
		}

		tfPort.setEditable(false);
		bConnect.setEnabled(false);

		server = new Server(port, this);
		server.start();

		addMessage("Started listening on port " + port + "...");
	}

	/** add a message to the textarea  */
	public void addMessage(String msg) {
		taMessages.append(msg + "\n");
	}

	/** Start a ServerGUI application */
	public static void main(String[] args) {
		new ServerGUI();
	}
}
