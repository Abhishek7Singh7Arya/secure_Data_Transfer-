                        package Receiver;

                        import javax.swing.*;
                        import java.awt.*;
                        import java.awt.event.ActionEvent;
                        import java.awt.event.ActionListener;
                        import java.io.DataInputStream;
                        import java.io.IOException;
                        import java.net.ServerSocket;
                        import java.net.Socket;
                        import java.util.Base64;


                        public class Receiver_Main  implements ActionListener {
                            JFrame frame;
                            JLabel txthead;
                            JTextArea txtAreaReceivedmsg;
                            JPanel panelContainer, panelbtnHolder;
                            GridBagConstraints gbc;
                            GridBagLayout gl;
                            JButton btnOpen;
                            JButton btnDecode;
                            String key;
                            String  str;

                            JTextArea txtAreaDecodedmsg;


                            public Receiver_Main() {

                                /**
                                 *                   JFrame
                                 */

                                frame = new JFrame("RECEIVER");
                                frame.getContentPane().setLayout(new BorderLayout());
                                frame.setSize(600, 500);
                                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


                                /**
                                 *               received textArea
                                 */
                                txtAreaReceivedmsg = new JTextArea("received message                                     ");
                                txtAreaReceivedmsg.setEditable(false);
                                txtAreaReceivedmsg.setLineWrap(true);

                                
                                
                               
                                frame.getContentPane().add(txtAreaReceivedmsg, BorderLayout.WEST);
                                

                                /**
                                 *               Jlabel heading
                                 */
                                txthead = new JLabel("RECEIVE DATA");
                                txthead.setFont(txthead.getFont().deriveFont(34.0f));
                                txthead.setHorizontalAlignment(SwingConstants.CENTER);
                                frame.add(txthead, BorderLayout.NORTH);


                                /**
                                 *    parent Jpanel to add the subpanel and button
                                 */
                                panelContainer = new JPanel();
                                panelContainer.setLayout(new BorderLayout());

                                /**
                                 *   JButtons
                                 */
                                btnOpen = new JButton("OPEN");
                                btnDecode = new JButton("DECODE");
                                /**
                                 *    set Action listener
                                 */
                                btnDecode.addActionListener(this);
                                btnOpen.addActionListener(this);


                                /**
                                 *      subJpanel to add buttons
                                 */
                                panelbtnHolder = new JPanel();
                                gbc = new GridBagConstraints();
                                gl = new GridBagLayout();
                                panelbtnHolder.setLayout(gl);


                                gbc.fill = GridBagConstraints.HORIZONTAL; // Grid layout all work
                                gbc.gridx = 0;
                                gbc.gridy = 0;
                                panelbtnHolder.add(btnOpen, gbc);
                                gbc.gridy = 1;
                                panelbtnHolder.add(btnDecode, gbc);
                                panelContainer.add(panelbtnHolder, BorderLayout.WEST);

                                /**
                                 *          JTextArea
                                 */
                                txtAreaDecodedmsg = new JTextArea("Decoded message here");
                                txtAreaDecodedmsg.setEditable(false);
                                txtAreaDecodedmsg.setLineWrap(true);
                                panelContainer.add(txtAreaDecodedmsg, BorderLayout.CENTER);


                                /**
                                 *              Decoded msg JPanel
                                 */
                                JScrollPane sc = new JScrollPane(txtAreaDecodedmsg);
                                sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                                panelContainer.add(sc);
                                frame.getContentPane().add(panelContainer, BorderLayout.CENTER);
                                frame.setVisible(true);
                                
                                /**
                                 *      Scrollbar
                                 */
                          

                            }

                            public static void main(String[] args) {
                               Receiver_Main rm = new Receiver_Main();
                                try {
									rm.receiveMsg();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									
									JOptionPane.showMessageDialog(null, e.getMessage());
								}

                            }

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (e.getSource() == btnOpen) {

                                   
                                    	   txtAreaReceivedmsg.setText(str);
                                 
                                }

                                if (e.getSource() == btnDecode) {

                                   String key2  = JOptionPane.showInputDialog(frame,"Enter The unlock key ");
                                  
                                   if (key.compareTo(key2)==0) {
                                       try {
                                           byte[] actualByte = Base64.getDecoder()
                                                   .decode(txtAreaReceivedmsg.getText());

                                           String actualString = new String(actualByte);

                                           txtAreaDecodedmsg.setText(actualString);
                                       }
                                       catch (Exception ex)
                                       {
                                           JOptionPane.showMessageDialog(frame,ex.getMessage());
                                       }
                                   }
                                   else{
                                       JOptionPane.showMessageDialog(frame,"Enter the correct Key");

                                   }

                                }

                            }



                            public void receiveMsg() throws IOException {

                                ServerSocket ss = new ServerSocket(4000);
                                Socket s=ss.accept();//establishes connection
                               
                                DataInputStream dis=new DataInputStream(s.getInputStream());
                                 str=(String)dis.readUTF();
                                key =String.valueOf(dis.readUTF());
                                JOptionPane.showMessageDialog(frame,"message received");
                               
                                // System.out.println("message= "+str);
                                ss.close();
                            }
                        }
