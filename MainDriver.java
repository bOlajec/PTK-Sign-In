import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDriver {

	public static void main(String[] args) {
		// TODO
		/*
		 * create GUI
		 * store sign ins
		 * validate M-Numbers
		 */
		
		MemberSignIns signIns = new MemberSignIns();
		
		// Create the frame
        JFrame frame = new JFrame("PTK Member Sign In");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLayout(new GridLayout(8, 2, 10, 10));

        // Create components
        JLabel mNumLabel = new JLabel("M-Number:");
        JTextField mNumText = new JTextField();

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameText = new JTextField();
        firstNameLabel.setVisible(false);
        firstNameText.setVisible(false);
        
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameText = new JTextField();
        lastNameLabel.setVisible(false);
        lastNameText.setVisible(false);
        
        JLabel emailLabel = new JLabel("Email ending in @montgomerycollege.edu:");
        JTextField emailText = new JTextField();
        emailLabel.setVisible(false);
        emailText.setVisible(false);
        
        JLabel majorLabel = new JLabel("Major:");
        JTextField majorText = new JTextField();
        majorLabel.setVisible(false);
        majorText.setVisible(false);

        JLabel checkLabel = new JLabel("Are you ok with being in pictures?:");
        JCheckBox checkBox = new JCheckBox();
        checkLabel.setVisible(false);
        checkBox.setVisible(false);
        
        JLabel roleLabel = new JLabel("Member Status:");
        String[] roles = {"Member", "Visiting", "Provisional"};
        JComboBox<String> roleDropdown = new JComboBox<>(roles);
        roleLabel.setVisible(false);
        roleDropdown.setVisible(false);
        
        JButton loginButton = new JButton("Sign In");

        // Add components to the frame
        frame.add(mNumLabel);
        frame.add(mNumText);

        frame.add(firstNameLabel);
        frame.add(firstNameText);

        frame.add(lastNameLabel);
        frame.add(lastNameText);
        
        frame.add(emailLabel);
        frame.add(emailText);
        
        frame.add(majorLabel);
        frame.add(majorText);
        
        frame.add(checkLabel);
        frame.add(checkBox);
        
        frame.add(roleLabel);
        frame.add(roleDropdown);

        frame.add(new JLabel()); // Empty space
        frame.add(loginButton);
        
        frame.setVisible(true);

        // Add action listener for login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String mNumber = "";
                if(!firstNameLabel.isVisible()) {
                
                	mNumber = mNumText.getText();

                    // Validate the inputs
                    if (mNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please enter an M-Number (M12345678)", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    else if(validMNumber(mNumber) == -1) {
                    	JOptionPane.showMessageDialog(frame, "Please make sure all character after the 'M' are digits", "Error", JOptionPane.ERROR_MESSAGE);
                    	mNumText.setText("");
                    }
                    else if(validMNumber(mNumber) == -2) {
                    	JOptionPane.showMessageDialog(frame, "Please enter an M-Number that starts with a capital M", "Error", JOptionPane.ERROR_MESSAGE);
                    	mNumText.setText("");
                    }
                    else if(validMNumber(mNumber) == 1) {
                    	if(signIns.existingMember(mNumber)) {
                    		String studentName = signIns.getFirstName(mNumber);
                    		JOptionPane.showMessageDialog(frame, "Welcome Back " + studentName, "Success", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    	else {
                    		showSignInFields();
                    		
                    	}
                    	
                    }
                }//end big if
            	
              else {
            	  mNumber = mNumText.getText();
            	  	if(!emptyFields() && validMNumber(mNumber) == 1) {
            	  		String firstName = firstNameText.getText();
            	  		String lastName = lastNameText.getText();
            	  		String email = emailText.getText();
            	  		String major = majorText.getText();
            	  		boolean cameraShy = checkBox.isSelected();
            	  		String status = (String) roleDropdown.getSelectedItem();
            	
            	  		Student newStudent = new Student(mNumber, firstName, lastName, email, major, cameraShy, status);
            	  		signIns.addNewMember(newStudent);
            	  		JOptionPane.showMessageDialog(frame, "Welcome " + firstName, "Success", JOptionPane.INFORMATION_MESSAGE);
            	  		hideSignInFields();
            	  		clearFields();
            	  	}
            	  	else {
            	  		if (validMNumber(mNumber) != 1) {
            	  			JOptionPane.showMessageDialog(frame, "Error: Invalid M-Number", "Error", JOptionPane.ERROR_MESSAGE);
            	  		}
            	  		else {
            	  			JOptionPane.showMessageDialog(frame, "Error: Missing fields", "Error", JOptionPane.ERROR_MESSAGE);
            	  		}
            	  		
            	  	}
            	  	
              }//end else 
            

            }//end actionPerformed
            
			private boolean emptyFields() {
				return firstNameText.getText().equals("") || lastNameText.getText().equals("") || emailText.getText().equals("") || majorText.getText().equals("");
			}

			private void clearFields() {
            	
            	mNumText.setText("");
            	firstNameText.setText("");
            	lastNameText.setText("");
            	emailText.setText("");
            	majorText.setText("");
            	checkBox.setSelected(false);
            	roleDropdown.setSelectedIndex(0);
			}
			
			private void hideSignInFields() {
            	firstNameLabel.setVisible(false);
		        firstNameText.setVisible(false);
		        
		        lastNameLabel.setVisible(false);
		        lastNameText.setVisible(false);
		        
		        emailLabel.setVisible(false);
		        emailText.setVisible(false);
		        
		        majorLabel.setVisible(false);
		        majorText.setVisible(false);

		        checkLabel.setVisible(false);
		        checkBox.setVisible(false);
		        
		        roleLabel.setVisible(false);
		        roleDropdown.setVisible(false);
				
			}
			
			private void showSignInFields() {
				firstNameLabel.setVisible(true);
		        firstNameText.setVisible(true);
		        
		        lastNameLabel.setVisible(true);
		        lastNameText.setVisible(true);
		        
		        emailLabel.setVisible(true);
		        emailText.setVisible(true);
		        
		        majorLabel.setVisible(true);
		        majorText.setVisible(true);

		        checkLabel.setVisible(true);
		        checkBox.setVisible(true);
		        
		        roleLabel.setVisible(true);
		        roleDropdown.setVisible(true);
            } //end showSignInFields
            
        });//end addActionListener
	}
	
	
	//validate M-Number
	//return codes: 1 = valid M-Number, -1 = char after M is non digit, -2 = string does not start with capital M
	public static int validMNumber(String mNumber) {
		if(mNumber.charAt(0) == 'M') {
			for(int i = 1; i < mNumber.length(); i++) {
				if(!(Character.isDigit(mNumber.charAt(i)))) {
					//non digit char
					return -1;
				}
			}
			// first char is capital M and the rest are digits
			return 1;
		}
		// first char is not capital M
		return -2;
	}
	
}
