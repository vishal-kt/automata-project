/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */
 import javax.swing.*;
 import javax.swing.JPanel.*;
 import java.awt.event.*;
 import java.awt.*;
 import java.applet.*;
public class nfapanel extends javax.swing.JPanel {
 
    DFA dfa=new DFA();
	DFA dfa1=new DFA();
	RE re = new RE();
	NFA nfa = new NFA();
	arrowfinding af= new arrowfinding();
    NEWDFA newdfa;
	project pr = new project();
	JFrame frm = new JFrame();
	int i,j,k,ind,kl,tt;
	 int f1,g1,f2,g2,xmid,ymid;
	 int first[]=new int[50];
	int second[]=new int[50];
	String sig[]= new String[50];
	String enterstring;
   
    public nfapanel() {
        initComponents();
    }

   /**
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}**/
  public void initComponents()
		{
			 bevpanel = new javax.swing.JPanel();
			 //edges = new javax.swing.JLabel();
			  //jd = new javax.swing.JDialog();
			//sigma = new javax.swing.JLabel();
			//add = new javax.swing.JButton();
			//enterstr = new javax.swing.JButton();
			//edgecombo = new javax.swing.JComboBox();
			//sigmacombo = new javax.swing.JComboBox();
			setBackground(new java.awt.Color(255, 255, 204));
			setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 3));
			addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //formMouseClicked(evt);
            }
			public void mousePressed(java.awt.event.MouseEvent evt) {
                //formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                //formMouseReleased(evt);
            }
			});
			  bevpanel.setBackground(new java.awt.Color(153, 255, 204));
			bevpanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

      /*  edges.setText("EDGES");

        sigma.setText("SIGMA");
		enterstr.setText("ENTERSTRING");
		 add.setText("ADD");
		 edgecombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgecomboActionPerformed(evt);
            }
        });
		 sigmacombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigmacomboActionPerformed(evt);
            }
        });
		 add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
		 enterstr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterstrActionPerformed(evt);
            }
        });*/

        javax.swing.GroupLayout bevpanelLayout = new javax.swing.GroupLayout(bevpanel);
        bevpanel.setLayout(bevpanelLayout);
        bevpanelLayout.setHorizontalGroup(
            bevpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 818, Short.MAX_VALUE)
        );
        bevpanelLayout.setVerticalGroup(
            bevpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bevpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(579, Short.MAX_VALUE)
                .addComponent(bevpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>

	 public void showdialog(NFA nfa1,String enne)
    	{	
			 Object enterre;
			 boolean result;
			if(enne=="nfainput")
			{nfa=nfa1;}
				
			enterre=JOptionPane.showInputDialog(frm, "Please enter the STRING", "ENTER STRING",JOptionPane.QUESTION_MESSAGE, null, null, null);
			enterstring=(String)enterre;
			if(enterstring!=null)
				 {
					result=nfa.process(enterstring);
					if(result==true)
					 {
						JOptionPane.showMessageDialog(frm, "HOORAY! STRING ACCEPTED","RESULT",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					 }
					 else if(result==false)
						{
							JOptionPane.showMessageDialog(frm, "SORRY! STRING NOT ACCEPTED","RESULT",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}
				 }
		}
		public void secondinput(arrowfinding af1,NFA nfa1)
			{
				af=af1;
				nfa=nfa1;
				int kkk=1;
				for(i=0;i<=kkk;i++ )
					{
						System.out.println(af.linex1[i]+" , "+af.liney1[i]+"-------> "+af.linex2[i]+" , "+af.liney2[i]);
					}
				//af.statesdone();//for dfa input
				repaint();
			}

	public void enterstring(String enterreg)
	{
		if(enterreg!=null)
				 {
					//nfaflag=1;dfaflag=1;dfaminflag=1;
					System.out.println("expression is "+ enterreg);
					 nfa=re.retofa(enterreg);
					// af.statesinitial(nfa.count_states);
						nfafromre(nfa);
				 }        
	}
	public void nfafromre(NFA nfa1)
		{
			nfa=nfa1;
			af.statesinitial(nfa.count_states);
			pr.printnfa(nfa);
			/* for(i=0;i<nfa.count_states;i++)
                {
                       for(j=0;j<nfa.count_sigma;j++)
                        {
						    if(nfa.tr_table[i][j].no_state!=0)
                                {
										
                                        for(k=0;k<nfa.tr_table[i][j].no_state;k++)
                                        {
                                              // System.out.print("q"+nfa.tr_table[i][j].index[k]+" ");
											  first[tt]=i;
											   second[tt]=ind=nfa.tr_table[i][j].index[k];
											   tt++;
												   af.makelinenfa(i,ind);
                                        }

                                        //System.out.print(" \t");
                                }
						}
				}*/
			af.nfaprinting(nfa);
			
		

				/*for (i=0;i<af.ii;i++ )
						{
							System.out.println(af.linex1[i]+" , "+af.liney1[i]+"-------> "+af.linex2[i]+" , "+af.liney2[i]);
						}*/
				repaint();
		}

	public void paint(Graphics g)
			{
				
				super.paint(g);
				g.setColor(Color.red);
				for(j=0;j<nfa.count_states;j++)
					{
						g.setColor(Color.red);
						g.fillOval(af.statex[j],af.statey[j],50,50);
						if(nfa.init_state==j)
						{
							g.setColor(Color.blue);
							g.fillOval(af.statex[j],af.statey[j],50,50);
						}
						for(k=0;k<nfa.count_fstates;k++)
						{
							if(nfa.fstates[k]==j)
							{
							g.setColor(Color.black);
							g.fillOval(af.statex[j],af.statey[j],50,50);
							}
							
						}
						
						g.drawString("q"+j,af.statex[j]+23,af.statey[j]+60);
					}

				for (j=0;j<=af.kkk;j++)
						{
							if(af.same[j]==0)
							{
								g.setColor(Color.red);
								g.drawLine(af.linex1[j],af.liney1[j],af.linex2[j],af.liney2[j]);
								f1=af.arrowf1(af.linex1[j],af.liney1[j],af.linex2[j],af.liney2[j]);
								g1=af.arrowg1(af.linex1[j],af.liney1[j],af.linex2[j],af.liney2[j]);
								f2=af.arrowf2(af.linex1[j],af.liney1[j],af.linex2[j],af.liney2[j]);
								g2=af.arrowg2(af.linex1[j],af.liney1[j],af.linex2[j],af.liney2[j]);
					   			xmid=(af.linex1[j]+af.linex2[j])/2;
       							ymid=(af.liney1[j]+af.liney2[j])/2;
								g.setColor(Color.blue);
								g.drawLine(f1,g1,xmid,ymid);
    							g.drawLine(f2,g2,xmid,ymid);
								g.setColor(Color.black);
								g.drawString(" "+af.sig[j],xmid-4,ymid-4);
							}
							else if(af.same[j]==1)
							{
								g.setColor(Color.red);
								g.drawArc(af.linex1[j],af.liney1[j]-25,50,70,0,180);
								g.setColor(Color.black);
								g.drawString(" "+af.sig[j],af.linex1[j]+25,af.liney1[j]-22);
								
							}
						}

				
			}
	

 public javax.swing.JPanel bevpanel;
}
