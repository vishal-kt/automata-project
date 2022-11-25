 import javax.swing.*;
 import javax.swing.JPanel.*;
 import java.awt.event.*;
 import java.awt.*;
 import java.applet.*;
 public class automata extends javax.swing.JFrame 
{
		auto2 sec = new auto2() ;
		DFA dfa=new DFA();
		RE re = new RE();
	    NFA nfa = new NFA();
		grammerpanel gp=new  grammerpanel();
		pdapanel pp=new  pdapanel();
		GRAMMAR gr=new GRAMMAR();
		GRAMMAR grnull=new GRAMMAR();
		GRAMMAR grunit=new GRAMMAR();
		GRAMMAR gruseless=new GRAMMAR();
		PDA pda=new PDA();
        NEWDFA newdfa;
		JFrame frml = new JFrame();
		nfapanel nf = new nfapanel();
		dfapanel df = new dfapanel();
		project pr = new project();
		arrowfinding af= new arrowfinding();
		retofa rf= new retofa();
		String enterreg,enne,symbol,vertex;
		String jbb,temp;
		int mn=0,n=0,a5=0,b5=0,c5=0,d5=0,len,k,count=0,tomp;
		int t=0,x3=0,y3=0,x4=0,y4=0,i=0,j=0,index,ft,jt,ij;
		int grammerflag=0,kk,result,cunt=0,cunt2=1;//for grammerinput function
		int x[]=new int[40];
		int y[]=new int[40];
		
		public automata(int flage,String ennet,String enn,arrowfinding af1,DFA dfa1,NFA nfa1)//1 for nfa , 2 for dfa
		{
		   initComponents();
		   retofa rf= new retofa();
		   arrowfinding af= new arrowfinding();
		   DFA dfa=new DFA();
		   NFA nfa=new NFA();
		   if((flage==1)&&(ennet!="zb"))
			{ 
			   enterreg=ennet;
			   enne=enn;
			   nfa=nfa1;
			   nfainputActionPerformed(af,nfa);
			   
			}
			else if((flage==2)&&(ennet!="zb"))
			{ 
			   enterreg=ennet;
			   enne=enn;
			   af=af1;
			   dfa=dfa1;
			   dfashowing(af,dfa);
			   
			}
		}

   
        @SuppressWarnings("unchecked")
   
        public void initComponents()
		{

			jt1 = new javax.swing.JToolBar();
			jt2 = new javax.swing.JToolBar();
			jb1 = new javax.swing.JButton();
			jb11 = new javax.swing.JButton();
			jbvertex = new javax.swing.JButton();
			jbsigma = new javax.swing.JButton();
			jbstart = new javax.swing.JButton();
			jbremovenull = new javax.swing.JButton();       jbs = new javax.swing.JButton();
			jbdone = new javax.swing.JButton();             jbremoveunit = new javax.swing.JButton();
			jb11pr = new javax.swing.JButton();				jbis = new javax.swing.JButton();
			jb111 = new javax.swing.JButton();				jbiss = new javax.swing.JButton();
			nfabutton = new javax.swing.JButton();			jbss = new javax.swing.JButton();
			dfabutton= new javax.swing.JButton();			jbfs = new javax.swing.JButton();
			dfaminbutton = new javax.swing.JButton();
			finalbutton = new javax.swing.JButton();
			edge = new javax.swing.JButton();
			initial = new javax.swing.JButton();
			jPanel1 = new javax.swing.JPanel();
			jPanel2 = new javax.swing.JPanel();
			jPanel3 = new javax.swing.JPanel();
			jMenuBar1 = new javax.swing.JMenuBar();
			jMenu1 = new javax.swing.JMenu();
			jMenu3 = new javax.swing.JMenu();
			jMenu4 = new javax.swing.JMenu();
			jMenu2 = new javax.swing.JMenu();
			window = new javax.swing.JMenu();
			label = new javax.swing.JMenuItem();
			show = new javax.swing.JButton();
			show1 = new javax.swing.JButton();
			input1 = new javax.swing.JMenuItem();
			input2 = new javax.swing.JMenuItem();
			nfainput = new javax.swing.JMenuItem();               mealyinput = new javax.swing.JMenuItem();
			grammerinput = new javax.swing.JMenuItem();           mooreinput = new javax.swing.JMenuItem();
			contextgrammerinput = new javax.swing.JMenuItem();    pdainput = new javax.swing.JMenuItem();
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

			jt1.setBackground(new java.awt.Color(204, 255, 204));
			jt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
			jt1.setRollover(true);

			jb1.setBackground(new java.awt.Color(255, 153, 153));
			jb1.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jb1.setForeground(new java.awt.Color(51, 51, 51));
			jb1.setText("States");
			jb1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jb1ActionPerformed(evt);
				}
			});
			jt1.add(jb1);
		
			edge.setBackground(new java.awt.Color(255, 153, 153));
			edge.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			edge.setForeground(new java.awt.Color(51, 51, 51));
			edge.setText("Edges");
			edge.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					edgeActionPerformed(evt);
				}
			});
			jt1.add(edge);

			initial.setBackground(new java.awt.Color(255, 153, 153));
			initial.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			initial.setForeground(new java.awt.Color(51, 51, 51));
			initial.setText("Initial");
			initial.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					initialActionPerformed(evt);
				}
			});
			jt1.add(initial);

			finalbutton.setBackground(new java.awt.Color(255, 153, 153));
			finalbutton.setFont(new java.awt.Font("Bookman Old Style", 2, 14)); // NOI18N
			finalbutton.setText("Final");
			finalbutton.setFocusable(false);
			finalbutton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			finalbutton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			finalbutton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					finalbuttonActionPerformed(evt);
				}
			});
			jt1.add(finalbutton);

			 show.setText("Label");
			show.setFocusable(false);
			show.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			show.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			 show.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					showActionPerformed(evt);
				}
			});
			jt1.add(show);
			
			 show1.setText("DONE");// this button for done
			show1.setFocusable(false);
			show1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
			show1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
			 show1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					show1ActionPerformed(evt);
				}
			});
			jt1.add(show1);
		
		
			jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
		   
			javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			jPanel1.setLayout(jPanel1Layout);
			jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 1000, Short.MAX_VALUE)
			//.addComponent(sec)
			);
			jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 630, Short.MAX_VALUE)
			//.addComponent(sec)
			);

		  jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
		//.addComponent(rf)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		//.addComponent(rf)
        );

		  jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
		//.addComponent(rf)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		//.addComponent(rf)
        );

        jMenu1.setText("File");

        jMenu3.setText("New");

        input1.setText("DFA INPUT");
        input1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //input1ActionPerformed(evt);
				sec.nfaflag=0;
				input1ActionPerformed();
            }
        });
        jMenu3.add(input1);

        input2.setText("Regular Expression Conversion");
        input2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input2ActionPerformed(evt);
            }
        });
        jMenu3.add(input2);

		 nfainput.setText("NFA INPUT");
        nfainput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
			{
               
			   input1ActionPerformed();
			   sec.nfaflag=1;
				//nfainputActionPerformed();
            }
        });
        jMenu3.add(nfainput);

		 grammerinput.setText("Construct Right Hand GRAMMER");
        grammerinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
			{
               
			   grammerinputActionPerformed(1);
			   //sec.nfaflag=1;
				//nfainputActionPerformed();
            }
        });
        jMenu3.add(grammerinput);

		 contextgrammerinput.setText("Construct Context Free GRAMMER");
        contextgrammerinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
			{
               
			   //grammerinputActionPerformed(1);
			   //sec.nfaflag=1;
				//nfainputActionPerformed();
            }
        });
        jMenu3.add(contextgrammerinput);

		 mealyinput.setText("Construct Mealy Machine");
        mealyinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
			{
               
			   //grammerinputActionPerformed(1);
			   //sec.nfaflag=1;
				//nfainputActionPerformed();
            }
        });
        jMenu3.add(mealyinput);

		 mooreinput.setText("Construct Moore Machine");
        mooreinput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
			{
               
			   //grammerinputActionPerformed(1);
			   //sec.nfaflag=1;
				//nfainputActionPerformed();
            }
        });
        jMenu3.add(mooreinput);

		 pdainput.setText("Construct Push Down Automation");
        pdainput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt)
			{
               
			   pdainputActionPerformed();
			   //sec.nfaflag=1;
				//nfainputActionPerformed();
            }
        });
        jMenu3.add(pdainput);

        jMenu4.setText("Save");
		jMenu1.add(jMenu3);
        jMenu1.add(jMenu4);
	
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);
	
         window.setText("Window");

        label.setText("Label");
        label.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelActionPerformed(evt);
            }
        });
        window.add(label);

        jMenuBar1.add(window);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            //.addComponent(jt1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
               // .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			
        );

        pack();
    }// </editor-fold>

 public void jb1ActionPerformed(java.awt.event.ActionEvent evt) 
 {
	 sec.t=1;
	 sec.flagedges=0;
	 sec.flag2=0;
	 sec.flag3=0;
 } 

	public void buttoninitial()
	{
		if(rf.nfaflag==1)
		{
			nfabutton.setBackground(new java.awt.Color(255, 153, 153));
			nfabutton.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			nfabutton.setForeground(new java.awt.Color(51, 51, 51));
			nfabutton.setText("NFA DISPLAY");
			nfabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nfabuttonActionPerformed(evt);
            }
			});
			jt2.add(nfabutton);
		}

		/*if(rf.dfaflag==1)
		{
			dfabutton.setBackground(new java.awt.Color(255, 153, 153));
			dfabutton.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			dfabutton.setForeground(new java.awt.Color(51, 51, 51));
			dfabutton.setText("DFA DISPLAY");
			dfabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dfabuttonActionPerformed(evt);
            }
			});
			jt2.add(dfabutton);
		}

		if(rf.dfaminflag==1)
		{
			dfaminbutton.setBackground(new java.awt.Color(255, 153, 153));
			dfaminbutton.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			dfaminbutton.setForeground(new java.awt.Color(51, 51, 51));
			dfaminbutton.setText("MINIMIZED DFA DISPLAY");
			dfaminbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dfaminbuttonActionPerformed(evt);
            }
			});
			jt2.add(dfaminbutton);
		}*/
	}
	/*public void reframe()
	{	   
		java.awt.EventQueue.invokeLater(new Runnable() 
		{
			    public void run() 
			{
				//dispose();
                new automata(1,rf.enterreg).setVisible(true);
			}
        });
	}*/	

  public void jb11ActionPerformed(java.awt.event.ActionEvent evt) 
 {
	 
	  
		{
			 rf.flag1=1;
			rf.showdialog();
			buttoninitial();
		}
	
 } 

   public void nfabuttonActionPerformed(java.awt.event.ActionEvent evt) 
 {
	java.awt.EventQueue.invokeLater(new Runnable() 
		{
			    public void run() 
			{
				dispose();
                new automata(1,rf.enterreg,"zb",af,dfa,nfa).setVisible(true);
			}
        });
	 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	 
	 	
 } 
   public void dfabuttonActionPerformed(java.awt.event.ActionEvent evt) 
 {
	 rf.dfadisflag=1;
	 rf.dfadisplay();
 } 
   public void dfaminbuttonActionPerformed(java.awt.event.ActionEvent evt) 
 {
	  
	
 } 
 
  public void edgeActionPerformed(java.awt.event.ActionEvent evt)   
  {  
	sec.t=0;
	sec.flagedges=1;
	sec.flag2=0;
	sec.label=1;
	sec.funct();

   }

 public void initialActionPerformed(java.awt.event.ActionEvent evt) 
  {
	  if(sec.count<=0)
		  {
			JOptionPane.showMessageDialog(frm, "FIRST DRAW THE STATES AND EDGES","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
		  }
	 else
	  {
		sec.flag2=1;
		sec.flag3=0;
		sec.t=0;
		sec.flagedges=0;
	  }
  }
	public void labelActionPerformed(java.awt.event.ActionEvent evt)
 {
	
	
 }
 public void finalbuttonActionPerformed(java.awt.event.ActionEvent evt)
	 {
		 if(sec.count<=0)
		  {
			JOptionPane.showMessageDialog(frm, "FIRST DRAW THE STATES AND EDGES","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
		  }
	 else
		 {
			sec.flag3=1;
			sec.t=0;
			sec.flagedges=0;
			sec.flag2=0;
			//sec.label=1;		
		 }
	}
	public void showActionPerformed(java.awt.event.ActionEvent evt)
		{
			sec.funct2();
			sec.showsigma();
		}

	public void show1ActionPerformed(java.awt.event.ActionEvent evt)
		{
			String str,strt;
			int x,count,k,l,t;
			if(sec.nfaflag==0)
			{
				   if(sec.f!=sec.n1)
					  {
						JOptionPane.showMessageDialog(frm, "FIRST LABEL ALL THE EDGES","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					  }
				   else
					{	
						dfa.count_states=sec.count;
						//System.out.println("dfa.count :"+dfa.count_states);
						dfa.states= new int[dfa.count_states];
						dfa.init_state=sec.init_state;
						for (int i=0;i<dfa.count_states ;i++ )
							{
								dfa.states[i]=i;
							}
						dfa.count_fstates=sec.ini;
						dfa.fstates= new int[dfa.count_fstates];
						for (int i=0;i<dfa.count_fstates ;i++)
							{
								dfa.fstates[i]=sec.finalstates[i];
							}
							dfa.count_sigma=sec.entersig.length();
							//System.out.println("dfa.count_states,dfa.count_sigma :     "+dfa.count_states+" " + dfa.count_sigma);
							dfa.sigma = new char[dfa.count_sigma];
							System.out.println("dfa.count_states,dfa.count_sigma :     "+dfa.count_states+" " + dfa.count_sigma);
							//dfa.tr_table= new int[dfa.count_states][dfa.count_sigma]; 
							dfa.sigma=sec.entersig.toCharArray();
						dfa.tr_table = new int[dfa.count_states][dfa.count_sigma];
						for(ft=0;ft<dfa.count_states;ft++)
						 {
							for(jt=0;jt<dfa.count_sigma;jt++)
							 {
								dfa.tr_table[ft][jt]=-1;
							 }
						 }
						 for(ft=0;ft<sec.n1;ft++)
						 {
							 for(j=1;j<sec.sigmalabel1[ft].length();j++)
							 {
								str=sec.sigmalabel1[ft];
								if(str.charAt(j)!='+')
								 {
									index =sec.findindex(dfa.sigma,dfa.count_sigma,str.charAt(j));
								 }
								ij=sec.third[ft];
								dfa.tr_table[ij][index]=sec.fourth[ft];
							 }
						 }
						 for(ft=0;ft<dfa.count_states;ft++)
						 {
							for(jt=0;jt<dfa.count_sigma;jt++)
							 {
								System.out.print("   "+dfa.tr_table[ft][jt]);
							 }
							 System.out.println();
						 }


					
					}
					for(j=0;j<sec.i;j++)
					{
						af.statex[j]=sec.x[j];
						af.statey[j]=sec.y[j];
					}
					af.kkk=sec.n;
					for (j=0;j<sec.n;j++)
					{
						af.linex1[j]=sec.a5[j];
						af.liney1[j]=sec.b5[j];
						af.linex2[j]=sec.c5[j];
						af.liney2[j]=sec.d5[j];
						if((af.linex1[j]==af.linex2[j])&&(af.liney1[j]==af.liney2[j]))
						{af.same[j]=1;}
						else 
						{af.same[j]=0;}
						af.sig[j]=sec.sigmalabel1[j];
					}
						
					java.awt.EventQueue.invokeLater(new Runnable() 
						{
						public void run() 
							{
								dispose();
								new automata(2,"zbb","dfainput",af,dfa,nfa).setVisible(true);
							}
						});
			}

			else if (sec.nfaflag==1)
			{
					nfa.count_states=sec.count;
				    nfa.count_sigma=sec.entersig.length();
					nfa.sigma=new char[nfa.count_sigma]; 
					nfa.sigma=sec.entersig.toCharArray();
					//nfa.sigma[nfa.count_sigma]='\\';
					nfa.init_state=sec.init_state;
					nfa.count_fstates=sec.ini;
					nfa.fstates=new int[nfa.count_fstates]; 
					for (int i=0;i<nfa.count_fstates ;i++)
							{
								nfa.fstates[i]=sec.finalstates[i];
							}
					nfa.tr_table=new nfastate[nfa.count_states][nfa.count_sigma];
					for(i=0;i<nfa.count_states;i++)
					{
							for(j=0;j<nfa.count_sigma;j++)
							{
									nfa.tr_table[i][j]=new nfastate();
							}
					}
					 for(i=0;i<nfa.count_states;i++)
					{
							k=i;
							for(j=0;j<nfa.count_sigma;j++)
							{		
									count=0;
									strt=" "+nfa.sigma[j];
									for (t=0;t<sec.n1;t++)
									{
										
										if (sec.third[t]==k && sec.sigmalabel1[t]==strt)
										{
											count++;
										}

									}
									x=nfa.tr_table[i][j].no_state=count;
									nfa.tr_table[i][j].index=new int[x];
									for(l=0;l<x;l++)
									{
										for (t=0;t<sec.n1;t++)
										{
											if (sec.third[t]==k && sec.sigmalabel1[t]==strt)
											{
												nfa.tr_table[i][j].index[l]=sec.fourth[t];
											}

										}
										
									}
							}
					}
					/* for(ft=0;ft<sec.n1;ft++)
						 {
							 for(j=1;j<sec.sigmalabel1[ft].length();j++)
							 {
								str=sec.sigmalabel1[ft];
								index =sec.findindex(dfa.sigma,dfa.count_sigma,str.charAt(j));
								ij=sec.third[ft];
								dfa.tr_table[ij][index].index=sec.fourth[ft];
							 }
						 }*/
					  for(i=0;i<nfa.count_states;i++)
					 {
                        System.out.print(" q"+i+"\t"); 
                        for(j=0;j<nfa.count_sigma;j++)
                        {
                                if(nfa.tr_table[i][j].no_state==0)
                                        System.out.print("-\t");
                                else
                                {
                                        for(k=0;k<nfa.tr_table[i][j].no_state;k++)
                                        {
                                               System.out.print("q"+nfa.tr_table[i][j].index[k]+" ");
                                        }
                                        System.out.print(" \t");
                                }
                        }
                        System.out.println();
						}
					
					pr.printnfa(nfa);
					for(j=0;j<sec.i;j++)
					{
						af.statex[j]=sec.x[j];
						af.statey[j]=sec.y[j];
					}
					af.kkk=sec.n;
					for (j=0;j<sec.n;j++)
					{
						af.linex1[j]=sec.a5[j];
						af.liney1[j]=sec.b5[j];
						af.linex2[j]=sec.c5[j];
						af.liney2[j]=sec.d5[j];
						if((af.linex1[j]==af.linex2[j])&&(af.liney1[j]==af.liney2[j]))
						{af.same[j]=1;}
						else 
						{af.same[j]=0;}
						af.sig[j]=sec.sigmalabel1[j];
					}
						
					java.awt.EventQueue.invokeLater(new Runnable() 
						{
						public void run() 
							{
								dispose();
								new automata(1,"zbb","nfainput",af,dfa,nfa).setVisible(true);
							}
						});
				
			}


				
		
		}

	public void input1ActionPerformed()
		{	
			 jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			 jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
			 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			 jPanel1.setLayout(jPanel1Layout);
			 jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 1000, Short.MAX_VALUE)
		     .addComponent(sec)
              );
             jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 630, Short.MAX_VALUE)
		     .addComponent(sec)
              );


			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			 getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
			 );
			 layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
             .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			
			);


		}

	public void input2ActionPerformed(java.awt.event.ActionEvent evt)
		{	
			 jt2.setBackground(new java.awt.Color(204, 255, 204));
			jt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
			jt2.setRollover(true);

			jb11.setBackground(new java.awt.Color(255, 153, 153));
			jb11.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jb11.setForeground(new java.awt.Color(51, 51, 51));
			jb11.setText("Enter R.E");
			jb11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jb11ActionPerformed(evt);
				}
			});
			jt2.add(jb11);

			 jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			 jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
			 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			 jPanel1.setLayout(jPanel1Layout);
			 jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 1000, Short.MAX_VALUE)
		    .addComponent(rf)
              );
             jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		    .addComponent(rf)
              );

			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			 getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
			 );
			 layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
             .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			
			);


		}

        public void pdainputActionPerformed()//buttons are 11,s,ss,is,iss,fs
		{	
			 jt2.setBackground(new java.awt.Color(204, 255, 204));
			jt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
			jt2.setRollover(true);
			
			jb11.setBackground(new java.awt.Color(255, 153, 153));
			jb11.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jb11.setForeground(new java.awt.Color(51, 51, 51));
			jb11.setText("Enter Number Of States");
			jb11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					Object vert;
				    grammerflag=1;
					vert=JOptionPane.showInputDialog(frm, "Please enter the Number of states not greater than 20", "ENTER VERTEX",JOptionPane.QUESTION_MESSAGE, null, null, null);
		        	enterreg=(String)vert;
					if(enterreg!=null)
					{
						pda.count_states=Integer.parseInt(enterreg);
						pda.states=new int[pda.count_states];
						for(i=0;i<pda.count_states;i++)
						  {
							  pda.states[i]=i;
						  }
					}
					else
					{
						JOptionPane.showMessageDialog(frm, "Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
				}
			});
			jt2.add(jb11);
			
			jbs.setBackground(new java.awt.Color(255, 153, 153));
			jbs.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbs.setForeground(new java.awt.Color(51, 51, 51));
			jbs.setText("Enter Sigma");
			jbs.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
						 Object vert;
						 vert=JOptionPane.showInputDialog(frm, "Please enter the Sigma variables", "ENTER SIGMA",JOptionPane.QUESTION_MESSAGE, null, null, null);
						 enterreg=(String)vert;
						 symbol=enterreg;
						 if(enterreg!=null)
						  {
							len=enterreg.length();
							
							if (gr.isterminal(enterreg)  &&  gr.isdistinct(enterreg))
							{
								count++;
								pda.count_sigma=len+1;
								pda.sigma=new char[pda.count_sigma];
								for (i=0;i<pda.count_sigma;i++)
								   {
									  pda.sigma[i]=enterreg.charAt(i);   
								   }
								pda.sigma[i]='\\';
							}
							else
							{
								JOptionPane.showMessageDialog(frm, "Sigma symbol can not be block letter or Escape Character and No Repition ","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
							}

						}
						else
						{
							JOptionPane.showMessageDialog(frm, "Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}
					
				
				}
			});
			jt2.add(jbs);
			
			jbss.setBackground(new java.awt.Color(255, 153, 153));
			jbss.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbss.setForeground(new java.awt.Color(51, 51, 51));
			jbss.setText("Enter Stack Symbol");
			jbss.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
						Object vert;
						 vert=JOptionPane.showInputDialog(frm, "Please enter the Sigma variables", "ENTER SIGMA",JOptionPane.QUESTION_MESSAGE, null, null, null);
						 enterreg=(String)vert;
						 symbol=enterreg;
						 if(enterreg!=null)
						  {
							len=enterreg.length();
							
							if (gr.isterminal(enterreg)  &&  gr.isdistinct(enterreg))
							{
								count++;
								pda.count_stacksymbol=len;
								pda.stacksymbol=new char[pda.count_stacksymbol];
								for (i=0;i<pda.count_stacksymbol;i++)
								   {
									  pda.stacksymbol[i]=enterreg.charAt(i);   
								   }
							}
							else
							{
								JOptionPane.showMessageDialog(frm, "Sigma symbol can not be block letter or Escape Character and No Repition ","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
							}

						}
						else
						{
							JOptionPane.showMessageDialog(frm, "Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}
					
				
				}
			});
			jt2.add(jbss);

			jbis.setBackground(new java.awt.Color(255, 153, 153));
			jbis.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbis.setForeground(new java.awt.Color(51, 51, 51));
			jbis.setText("Initial State");
			jbis.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					Object vert;
				   	vert=JOptionPane.showInputDialog(frm, "Please enter the initial state in integer format", "ENTER VERTEX",JOptionPane.QUESTION_MESSAGE, null, null, null);
		        	enterreg=(String)vert;
					if(enterreg!=null)
					{
						tomp=Integer.parseInt(enterreg);
						if(tomp>=0 && tomp<pda.count_states)
						{
							pda.init_state=tomp;
						}
						else 
						JOptionPane.showMessageDialog(frm, "Enter Correct Initial State","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
					else
					{
						JOptionPane.showMessageDialog(frm,"Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
				}
			});
			jt2.add(jbis);

			jbiss.setBackground(new java.awt.Color(255, 153, 153));
			jbiss.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbiss.setForeground(new java.awt.Color(51, 51, 51));
			jbiss.setText("Initial Stack Symbol");
			jbiss.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					/*Object vert;
				   	vert=JOptionPane.showInputDialog(frm, "Please enter the initial state in integer format", "ENTER VERTEX",JOptionPane.QUESTION_MESSAGE, null, null, null);
		        	enterreg=(String)vert;
					if(enterreg!=null)
					{
						tomp=(char)enterreg.charAt(0);
						if(tomp>=0 && tomp<pda.count_states)
						{
							pda.init_state=tomp;
						}
						else 
						JOptionPane.showMessageDialog(frm, "Enter Correct Initial State","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
					else
					{
						JOptionPane.showMessageDialog(frm,"Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}*/					
				}
			});
			jt2.add(jbiss);

			jbfs.setBackground(new java.awt.Color(255, 153, 153));
			jbfs.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbfs.setForeground(new java.awt.Color(51, 51, 51));
			jbfs.setText("Enter Final States");
			jbfs.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
				}
			});
			jt2.add(jbfs);

			 jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			 jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
			 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			 jPanel1.setLayout(jPanel1Layout);
			 jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 1000, Short.MAX_VALUE)
		    .addComponent(pp)
              );
             jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		    .addComponent(pp)
              );

			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			 getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
			 );
			 layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
             .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			 
			);
		}
			
		
	public void grammerinputActionPerformed(int grammerf)
		{	
			grammerflag=grammerf;
			 jt2.setBackground(new java.awt.Color(204, 255, 204));
			jt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
			jt2.setRollover(true);

			jbvertex.setBackground(new java.awt.Color(255, 153, 153));
			jbvertex.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbvertex.setForeground(new java.awt.Color(51, 51, 51));
			jbvertex.setText("Enter Vertices");
			jbvertex.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
				   Object vert;
				   grammerflag=1;
					vert=JOptionPane.showInputDialog(frm, "Please enter the Vertices", "ENTER VERTEX",JOptionPane.QUESTION_MESSAGE, null, null, null);
		        	enterreg=(String)vert;
					vertex=enterreg;
			       if(enterreg!=null)
				    {
						len=enterreg.length();
						if (gr.isvertex(enterreg)  &&  gr.isdistinct(enterreg))
						{
							count++;
							gr.count_vertex=len;
                             gr.vertex=new String[gr.count_vertex];
							for (i=0;i<gr.count_vertex;i++)
							   {
								  gr.vertex[i]=""+enterreg.charAt(i);   
							   }
						}
						else
						{
							JOptionPane.showMessageDialog(frm, "Vertex should be Capital And Distinct Letters","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}

					}
					else
					{
						JOptionPane.showMessageDialog(frm, "Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
				}
			});
			

			jbsigma.setBackground(new java.awt.Color(255, 153, 153));
			jbsigma.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbsigma.setForeground(new java.awt.Color(51, 51, 51));
			jbsigma.setText("Enter Sigma");
			jbsigma.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{
					 Object vert;
					// grammerflag=1;
					 vert=JOptionPane.showInputDialog(frm, "Please enter the Sigma variables", "ENTER SIGMA",JOptionPane.QUESTION_MESSAGE, null, null, null);
		        	 enterreg=(String)vert;
					 symbol=enterreg;
			         if(enterreg!=null)
				      {
						len=enterreg.length();
						
						if (gr.isterminal(enterreg)  &&  gr.isdistinct(enterreg))
						{
							count++;
							gr.count_sigma=len;
                            gr.sigma=new char[gr.count_sigma];
							for (i=0;i<gr.count_sigma;i++)
							   {
								  gr.sigma[i]=enterreg.charAt(i);   
							   }
						}
						else
						{
							JOptionPane.showMessageDialog(frm, "Terminal symbol can not be block letter or Escape Character and No Repition ","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}

					}
					else
					{
						JOptionPane.showMessageDialog(frm, "Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
				
				}
			});
			

			jbstart.setBackground(new java.awt.Color(255, 153, 153));
			jbstart.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbstart.setForeground(new java.awt.Color(51, 51, 51));
			jbstart.setText("Start Symbol");
			jbstart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				  {
					 Object vert;
					 // grammerflag=1;
					 vert=JOptionPane.showInputDialog(frm, "Please enter the Start Symbol From Vertex Entered", "ENTER SIGMA",JOptionPane.QUESTION_MESSAGE, null, null, null);
		        	 enterreg=(String)vert;
				    if(vert!=null)
				      {
						 gr.start=enterreg;
					  }
					  else
					{
						JOptionPane.showMessageDialog(frm, "Empty String Entered","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					}
			      }
			});
			
			
			jbdone.setBackground(new java.awt.Color(255, 153, 153));
			jbdone.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbdone.setForeground(new java.awt.Color(51, 51, 51));
			jbdone.setText("Add Productions");
			jbdone.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				  {
					 grammerflag=2;
					if(count==2)
					  {
						gp.showall(gr);
						grammerinputActionPerformed(2);
					  }
					 else if (count!=2)
					 {
						 JOptionPane.showMessageDialog(frm, "First Enter The Vertices And Sigma Variables","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					 }
					 
			      }
			});

			jbremovenull.setBackground(new java.awt.Color(255, 153, 153));
			jbremovenull.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbremovenull.setForeground(new java.awt.Color(51, 51, 51));
			jbremovenull.setText("Remove Null,Unit and Useless Productions");
			jbremovenull.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				  {
					 
					  if(cunt2==1)
					  kk=JOptionPane.showConfirmDialog(frm,"HAVE YOU ENTERED ALL PRODUCTIONS?","CONFIRM DIALOG",JOptionPane.YES_NO_CANCEL_OPTION);
					  if (kk!=0)
						{
						      cunt2=1;
							 JOptionPane.showMessageDialog(frm, "First Enter All Productions","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}
					  else if(kk==0)
					    {
							  gr.TYPE=3;
							  gr.productions=new prod[gr.count_vertex];
							  for (i=0;i<gr.count_vertex;i++)
								{
								   gr.productions[i]=new prod();
								}
								for (i=0;i<gp.index;i++)
								{
									result=gr.addprod(gp.w[i],gp.str[i]);
								}
								//GRAMMAR g2=gr.removenull();
								gp.removenullprod(gr,vertex,symbol);
								cunt2++;
								 grammerinputActionPerformed(3);
						}
						
					 
					 //grammerflag=2;//for remove null productions button
			      }
			});

			jbremoveunit.setBackground(new java.awt.Color(255, 153, 153));
			jbremoveunit.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			jbremoveunit.setForeground(new java.awt.Color(51, 51, 51));
			jbremoveunit.setText("Costruct DFA");
			jbremoveunit.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				  {
					grnull=gr.removenull();
					grunit=grnull.removeunit();
					gruseless=grunit.removeunit();
					nfa=gruseless.makenfa();
					newdfa=nfa.nfatodfa();
					dfa=newdfa.normalize();
					 temp=re.fatore(nfa);
					 cunt++;
					 if(cunt==1)
					  {
							java.awt.EventQueue.invokeLater(new Runnable() 
								{
										public void run() 
										{
										//dispose();
										System.out.println("out ");
										new automata(2,temp,"condfa",af,dfa,nfa).setVisible(true);
										}
								});
							 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
							 
							// grammerinputActionPerformed(4);
					  }
				  }
			
								
				});

		   if ( grammerflag==1)
			    {
			
					jt2.add(jbvertex);
					jt2.add(jbsigma);
					jt2.add(jbstart);
					jt2.add(jbdone);
				}
			else if ( grammerflag==2)
				{
					jt2.remove(jbvertex);
					jt2.remove(jbsigma);
					jt2.remove(jbstart);
					jt2.remove(jbdone);
					jt2.add(jbremovenull);
				}
			else if(grammerflag==3)
				{
					jt2.remove(jbremovenull);
					jt2.add(jbremoveunit);
				}

			 jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			 jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
			 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			 jPanel1.setLayout(jPanel1Layout);
			 jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 1000, Short.MAX_VALUE)
		    .addComponent(gp)
              );
             jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		    .addComponent(gp)
              );

			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			 getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
			 );
			 layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
             .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			
			);


		}

		public void nfainputActionPerformed(arrowfinding af1,NFA nfa1)
		{
			nfapanel nf1 = new nfapanel();
			arrowfinding af2= new arrowfinding();
			nf=nf1;
			nfa=nfa1;
			af2=af1;
			if(enne=="nfainput")
				{
					System.out.println(enne);
					nf.secondinput(af2,nfa);
				}
				else
			{nf.enterstring(enterreg);}
			 jt2.setBackground(new java.awt.Color(204, 255, 204));
			 jt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
             jt2.setRollover(true);

			 jb11.setBackground(new java.awt.Color(255, 153, 153));
			 jb11.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			 jb11.setForeground(new java.awt.Color(51, 51, 51));
			 jb11.setText("Process String");
			 jb11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					nf.showdialog(nfa,enne);
				}
			});
			jt2.add(jb11);

			 jb111.setBackground(new java.awt.Color(255, 153, 153));
			 jb111.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			 jb111.setForeground(new java.awt.Color(51, 51, 51));
			 jb111.setText("CONSTRUCT DFA");
			 jb111.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					java.awt.EventQueue.invokeLater(new Runnable() 
						{
							 public void run() 
								{
									//dispose();
									 new automata(2,enterreg,"condfa",af,dfa,nfa).setVisible(true);
								}
						});
						setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	 
						}
			});
			jt2.add(jb111);

			 jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			 jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
			 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			 jPanel1.setLayout(jPanel1Layout);
			 jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 1000, Short.MAX_VALUE)
		    .addComponent(nf)
              );
             jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		    .addComponent(nf)
              );

			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			 getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
			 );
			 layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
             .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			
			);
			
			

		}

		public void dfashowing(arrowfinding af1,DFA dfa1)
		{
			int kkk=0;
			dfapanel nf1 = new dfapanel();
			arrowfinding af2= new arrowfinding();
			//DFA dfa2=new DFA();
			df=nf1;
			af2=af1;
			dfa=dfa1;
			if(enne!="dfainput")
			{
				if(enne=="mindfa")
				{df.flag=1;}
				else if(enne!="mindfa")
					{df.flag=0;}
				df.enterstring(enterreg);
			}
			else if(enne=="dfainput")
				{
					System.out.println(enne);
					df.secondinput(af2,dfa);
				}

			 jt2.setBackground(new java.awt.Color(204, 255, 204));
			 jt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
             jt2.setRollover(true);

			 jb11.setBackground(new java.awt.Color(255, 153, 153));
			 jb11.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			 jb11.setForeground(new java.awt.Color(51, 51, 51));
			 jb11.setText("Process String");
			 jb11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					df.showdialog(dfa,enne);
				}
			});
			jt2.add(jb11);

			 jb111.setBackground(new java.awt.Color(255, 153, 153));
			 jb111.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			 jb111.setForeground(new java.awt.Color(51, 51, 51));
			 jb111.setText("MINIMIZE DFA");
			 jb111.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt)
				{	
					if(enne=="dfainput")
						{
							//df.secondinput(af);
							java.awt.EventQueue.invokeLater(new Runnable() 
							{
								public void run() 
									{
										//dispose();
										enterreg=re.fatore(dfa);
										 new automata(2,enterreg,"mindfa",af,dfa,nfa).setVisible(true);
									}
							});
							setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
						}
					else
					{
						if(df.count1==df.count2)
						{
							JOptionPane.showMessageDialog(frml, "IT IS ALREADY MINIMIZED","RESULT",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
						}
						else
						{
							java.awt.EventQueue.invokeLater(new Runnable() 
							{
								public void run() 
									{
										//dispose();
										 new automata(2,enterreg,"mindfa",af,dfa,nfa).setVisible(true);
									}
							});
							setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
						}
					}
	 
				}
			});
			jt2.add(jb111);

			jb11pr.setBackground(new java.awt.Color(255, 153, 153));
			 jb11pr.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			 jb11pr.setForeground(new java.awt.Color(51, 51, 51));
			 jb11pr.setText("Print R.E");
			 jb11pr.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {

						if(enne=="dfainput")
						{enterreg=re.fatore(dfa);}
						else
							{df.showre();}
						JOptionPane.showMessageDialog(frml,enterreg,"RESULT",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
					
					//df.showdialog(dfa,enne);
				}
			});
			jt2.add(jb11pr);

			 jt2.setBackground(new java.awt.Color(204, 255, 204));
			 jt2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0), 3));
             jt2.setRollover(true);

			/* jb11.setBackground(new java.awt.Color(255, 153, 153));
			 jb11.setFont(new java.awt.Font("Comic Sans MS", 3, 12));
			 jb11.setForeground(new java.awt.Color(51, 51, 51));
			 jb11.setText("CONSTRUCT RE");
			 jb11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					df.showdialog();
				}
			});
			jt2.add(jb11);
			*/

			 jPanel1.setBackground(new java.awt.Color(255, 255, 204));
			 jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 3));
       
			 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
			 jPanel1.setLayout(jPanel1Layout);
			 jPanel1Layout.setHorizontalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGap(0, 1000, Short.MAX_VALUE)
		    .addComponent(df)
              );
             jPanel1Layout.setVerticalGroup(
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
		    .addComponent(df)
              );

			 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			 getContentPane().setLayout(layout);
			layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jt2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		
			 );
			 layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
             .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
             .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			
			);
			
			
		}


    
    public static void main(String args[]) {
			final arrowfinding af= new arrowfinding();
			final DFA dfa1=new DFA();
			final NFA nfa1=new NFA();
			java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new automata(0,"zb","zb",af,dfa1,nfa1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    public javax.swing.JButton edge;
    public javax.swing.JButton initial;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenu jMenu4;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JPanel jPanel1;
	public javax.swing.JPanel jPanel2;
	public javax.swing.JPanel jPanel3;
    public javax.swing.JButton jb1;
	public javax.swing.JButton jb11,jbvertex,jbsigma,jbdone,jbstart,jbremovenull,jbremoveunit;
	public javax.swing.JButton jbs,jbis,jbss,jbiss,jbfs;// for pdapanel
	public javax.swing.JButton jb11pr;
	public javax.swing.JButton jb111;
	public javax.swing.JButton nfabutton;
	public javax.swing.JButton dfabutton;
	public javax.swing.JButton dfaminbutton;
	public javax.swing.JToolBar jt1;
	public javax.swing.JToolBar jt2;
    public javax.swing.JMenuItem label;
    public javax.swing.JMenu window;
	public javax.swing.JButton finalbutton;
	public javax.swing.JButton show;
	public javax.swing.JButton show1;
	public javax.swing.JMenuItem input1;
    public javax.swing.JMenuItem input2;
	public javax.swing.JMenuItem nfainput;
	public javax.swing.JMenuItem grammerinput,contextgrammerinput,mealyinput,mooreinput,pdainput;
	 JFrame frm = new JFrame();
    // End of variables declaration

}



class auto2 extends javax.swing.JPanel
 {
	int flag,n1=0,count,initial,flag4,w,f=0,flaglabel=0,checkedges,checkedgeflag=0,checklabeldupli;
	int same[]=new int[20];
	char sigmalabel[]=new char[20];
	String sigmalabel1[]=new String[20];

	 int third[]=new int[20];
	 int fourth[]=new int[20];
	int ll,i=0,j=0,n=0,j1=0,t,mx=0,my1=-1,my=0,x3=0,y3=0,x4=0,y4=0,delx=0,dely=0,x31=0,y31=0;
	int xmid=0,ymid=0,optionflag,flag2,flag3,x32,y32,ini,nfaflag;
	int x[]=new int[20];
	int init_state;
	int y[]=new int[20];
	int a5[]=new int[20];
	int b5[]=new int[20];
	int c5[]=new int[20];
	int d5[]=new int[20];
	int a6[]=new int[20];
	int b6[]=new int[20];
	int c6[]=new int[20];
	int d6[]=new int[20];
	int first[]=new int[20];
	int seco[]=new int [20];
	int finalstates[]=new int[20];
	int xx[]=new int[20];
	int yy[]=new int[20];
	int label,flagedges;
	 String entersig;
	 arrowfinding af= new arrowfinding();
	 char sigmalabel3[];
	 public javax.swing.JPopupMenu Pmenu;
	 JFrame frm = new JFrame();
     JMenuItem menuItem ;
				String sig="",ed,sr;
				
		char latest;
		int nn;
	
	DFA dfa=new DFA();
	NFA nfa=new NFA();
	GTG gt= new GTG();
	    public auto2()
    {
	
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    public void initComponents()
		{
			 bevpanel = new javax.swing.JPanel();
			 edges = new javax.swing.JLabel();
			  jd = new javax.swing.JDialog();
			sigma = new javax.swing.JLabel();
			add = new javax.swing.JButton();
			enterstr = new javax.swing.JButton();
			edgecombo = new javax.swing.JComboBox();
			sigmacombo = new javax.swing.JComboBox();
			setBackground(new java.awt.Color(255, 255, 204));
			setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0), 3));
			addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
			public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
			});
			  bevpanel.setBackground(new java.awt.Color(153, 255, 204));
			bevpanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        edges.setText("EDGES");

        sigma.setText("SIGMA");
		enterstr.setText("REPLACE");
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
        });

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

	public void funct()
	 {
		dfa.count_states=count;
		//System.out.println("dfa.count :"+dfa.count_states);
		dfa.states= new int[dfa.count_states];
		for (int i=0;i<dfa.count_states ;i++ )
		{
			dfa.states[i]=i;
		}
		
	 }
	 public void showsigma()
	 {
		 Object entersigma;
		
		 entersigma=JOptionPane.showInputDialog(frm, "Please enter the SIGMA VARIABLES", "ENTER SIGMA",JOptionPane.QUESTION_MESSAGE, null, null, null);
		 entersig=(String)entersigma;
		 if(entersig!=null)
		  {
			showall();
		  }
	 }
	public void funct2()
	 {
		dfa.count_fstates=ini;
		dfa.fstates= new int[dfa.count_fstates];
		for (int i=0;i<dfa.count_fstates ;i++ )
			{
				dfa.fstates[i]=finalstates[i];
				System.out.println("finalstates = "+dfa.fstates[i]);
			}
	 }
		
	 public int ispresent(String st,char ch)
	 {
		 int h;
		 for(h=0;h<st.length();h++)
		 {
			 if(ch==st.charAt(h))
				 return 1;
		 }
		 return -1;
	 }

	 public int present(int a[],int size,int ch)
	 {
		 int h;
		 for(h=0;h<size;h++)
		 {
			 if(ch==a[h])
				 return 1;
		 }
		 return -1;
	 }

	 public int dfablog(int fourt[],String siglabel1[],int fl,int w,char lat)
	 {
		 int h,hh;
		 for (h=0;h<fl;h++)
		 {
			 if(fourt[h]!=w)
			 {
				 for(hh=0;hh<siglabel1[h].length();hh++)
				 {
					 if(siglabel1[h].charAt(hh)==lat)
					 {
						 return 1;
					 }
				 }
			 }
		 }
		 return -1;
	 }
	 public void addActionPerformed(java.awt.event.ActionEvent evt)
		 {
			 
			 int kk=0,kt,flage,check,checkint;
			 String sigi;
			 int checklabeldupli2=0;
			
			checklabeldupli=f;
			System.out.println("ed  "+ed);
			System.out.println("sr  "+sr);
		 	w=(int)(ed).charAt(0);
			w=w-48;
			latest=(sr).charAt(4);
			if(checklabeldupli>0)
				{
					for(kt=0;kt<f;kt++)
						{
						      System.out.println("sigmalabel1= "+ sigmalabel1[kt]);
							  System.out.println("sigmalatest=  "+latest);
							   flage=0;
							   check=ispresent(sigmalabel1[kt],latest);
							   checkint=present(fourth,f,seco[w]);
							if((first[w]==third[kt])&&(seco[w]==fourth[kt])&&(check==-1))
								{
									//sigi=sigmalabel1[kt];
									checklabeldupli2=2;
									System.out.println("kk= "+checklabeldupli2);
									//if(nfaflag!=0)
									sigmalabel1[kt]=sigmalabel1[kt]+"+"+latest;
									//sigmalabel[kt]=latest;
									repaint();
									break;
								}
							if((first[w]==third[kt])&&(seco[w]!=fourth[kt])&&(check==1))
								{
									JOptionPane.showMessageDialog(frm, "IN DFA THIS IS NOT ALLOWED","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
									checklabeldupli2=1;
									//checklabeldupli=1;
									//System.out.println("kkll= "+checklabeldupli2);
									break;
								}
							if ((first[w]==third[kt])&&(seco[w]!=fourth[kt])&&(check==-1))
								{
								  checklabeldupli2=0;
								  break;
								}
						}
				}
				if((checklabeldupli==0)||(checklabeldupli2==0))
					{
						third[f]=first[w];
						fourth[f]=seco[w];
						//sigmalabel[f]=latest;
						sigmalabel1[f]=""+latest;
						f=f+1;
						flaglabel=1;
						t=0;
						repaint();
					}
		 }
			
		 public void enterstrActionPerformed(java.awt.event.ActionEvent evt)
			 {
					int checklabeldupli2=0,check,checkint;
					 int kk,kt;
					checklabeldupli=f;
		 			w=(int)(ed).charAt(0);
					w=w-48;
					latest=(sr).charAt(4);
					if(checklabeldupli>0)
					{
						for(kt=0;kt<f;kt++)
							{
								check=ispresent(sigmalabel1[kt],latest);
								
								//if((first[w]==third[kt])&&(seco[w]==fourth[kt])&&(check==-1))
								if((first[w]==third[kt]))
									{
										 checkint=dfablog(fourth,sigmalabel1,f,seco[w],latest);
										 checklabeldupli2=2;
										 if(checkint==-1)
										 {
											 kk=JOptionPane.showConfirmDialog(frm,"DO YOU WANT TO CHANGE EDGELABEL","CONFIRM DIALOG",JOptionPane.YES_NO_CANCEL_OPTION);
											 System.out.println("kk= "+kk);
											 if(kk==0)
												{
													sigmalabel1[kt]=""+latest;
													repaint();
												}
											 break;
										 }
										 else if (checkint==1)
										 {
											JOptionPane.showMessageDialog(frm, "IN DFA THIS IS NOT ALLOWED","WARNING",JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/smile.gif"));
												checklabeldupli2=1;
												break;
										 }
										 else if(sigmalabel1[kt]=="")
										{
											checklabeldupli2=0;
											break;
										}
									}
								
							}
					}

					if((checklabeldupli==0)||(checklabeldupli2==0))
					{
						 System.out.println("kk= "+checklabeldupli2);
						third[f]=first[w];
						fourth[f]=seco[w];
						sigmalabel1[f]=""+latest;
						 System.out.println("kk= "+sigmalabel1[f]);
						f=f+1;
						flaglabel=1;
						t=0;
						repaint();
					}
								   
			 }

		public int findindex(char sigma[],int count_sigma,char c)
			{
                int il=0;
                for(il=0;il<count_sigma;il++)
                {
                        if(c==sigma[il])
                                return il;
                }
                return -1;
			}

		
	public void popup()
	 {
		Pmenu = new JPopupMenu();
		menuItem = new JMenuItem("Cut");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Copy");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Paste");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Delete");
		Pmenu.add(menuItem);
		menuItem = new JMenuItem("Undo");
		Pmenu.add(menuItem);
		menuItem.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){}
		});
		this.addMouseListener(new MouseAdapter()
			{
				public void mouseReleased(MouseEvent Me)
				{
					if(Me.isPopupTrigger())
					{
						x3=Me.getX();
						y3=Me.getY();
						for(my=0;my<i;my++)
							{
								if((x[my]<=x3)&&(x3<=x[my]+50)&&(y[my]<=y3)&&(y3<=y[my]+50))
									{
										Pmenu.show(Me.getComponent(), Me.getX(), Me.getY());
										break;
									}	
							}
						
					}
			  }
		});
	 }


	 public void showall()
		{
			int it;
			 javax.swing.GroupLayout bevpanelLayout = new javax.swing.GroupLayout(bevpanel);
			bevpanel.setLayout(bevpanelLayout);
			bevpanelLayout.setHorizontalGroup(
            bevpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bevpanelLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(edges)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edgecombo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(sigma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sigmacombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(add)
				.addGap(88, 88, 88)
                .addComponent(enterstr)
                .addContainerGap(187, Short.MAX_VALUE))
			);
				 bevpanelLayout.setVerticalGroup(
				bevpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bevpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bevpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edges)
                    .addComponent(add)
					 .addComponent(enterstr)
                    .addComponent(sigmacombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sigma)
                    .addComponent(edgecombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
				//sig= text.getText();
				//sig = "abc";
				//dfa.count_states=i;
				nn=n1;
				if(nfaflag==1)
				{entersig=entersig+'\\';}
				System.out.println("string=    "+entersig);
				dfa.count_sigma=entersig.length();
				System.out.println("dfa.count_states,dfa.count_sigma :     "+dfa.count_states+" " + dfa.count_sigma);
				dfa.sigma = new char[dfa.count_sigma];
				System.out.println("dfa.count_states,dfa.count_sigma :     "+dfa.count_states+" " + dfa.count_sigma);
				dfa.tr_table= new int[dfa.count_states][dfa.count_sigma]; 
				dfa.sigma=entersig.toCharArray();
						{
							for(it=0;it<nn;it++)
								{
									edgecombo.addItem(it+".)  q"+first[it]+"--->"+"q"+seco[it]);
								}
							for(it=0;it< dfa.count_sigma;it++)
									{
										sigmacombo.addItem((it+1)+".) "+dfa.sigma[it]);
									}
									//if(nfaflag==1)
									//sigmacombo.addItem((it+1)+".) "+'\\');

							 
								
						}
			 
         


	}

		public void edgecomboActionPerformed(java.awt.event.ActionEvent evt)
				{
				
				ed = (String)edgecombo.getSelectedItem();
					//System.out.println("ed  "+ed);
				
				}

			public void sigmacomboActionPerformed(java.awt.event.ActionEvent evt)
				{
					
					
					sr = (String)sigmacombo.getSelectedItem();
						//System.out.println("sr  "+sr);
				
				}
public void formMouseClicked(java.awt.event.MouseEvent evt)

  {
	if((t==1)&&(flag2!=1)&&(flag3!=1)&&(flagedges!=1))
	{
		x[i]=evt.getX();
		y[i]=evt.getY();
		System.out.println("x[" +i+ "], y[] --->" + x[i] +"   "+y[i]);
		
		count++;
		flagedges=0;
		repaint();
		i++;
	}
	 if((flag2==1)&&(flag3!=1))
	  {
			x31=evt.getX();
			y31=evt.getY();
			for(my=0;my<i;my++)
			{
				if((x[my]<=x31)&&(x31<=x[my]+50)&&(y[my]<=y31)&&(y31<=y[my]+50))
				{
					my1=my;
					init_state=my;
					System.out.println("initialstate = "+init_state);
					t=0;flagedges=0;
					repaint();			
					break;
				}	
			}
		  
	  }
		if (flag3==1)
			{
				x32=evt.getX();
				y32=evt.getY();
				for(my=0;my<i;my++)
					{
						if((x[my]<=x32)&&(x32<=x[my]+50)&&(y[my]<=y32)&&(y32<=y[my]+50))
							{
								finalstates[ini]=my;
								xx[ini]=x[my];
								yy[ini]=y[my];
								ini++;
							}	
					}
					flagedges=0;
					flag2=0;
					t=0;
					repaint();
			}
	
	
  }

  public void formMousePressed(java.awt.event.MouseEvent evt)
  {     
	  if(flagedges==1)
		{
			x3=evt.getX();
			y3=evt.getY();
	
			for(my=0;my<i;my++)
			{
				if((x[my]<=x3)&&(x3<=x[my]+50)&&(y[my]<=y3)&&(y3<=y[my]+50))
				{
					j1=1;	
					break;
				}	
				
			}
		}
 }

 public void formMouseReleased(java.awt.event.MouseEvent evt)
 {
	 int gl;
	if((flagedges==1)&&(j1==1))
	{
       	x4=evt.getX();
        y4=evt.getY();
	
	for(mx=0;mx<i;mx++)
	  {
		if((x[mx]<=x4)&&(x4<=x[mx]+50)&&(y[mx]<=y4)&&(y4<=y[mx]+50))
		    {
			checkedges=n1;
			if(checkedges==0)
				{
					first[n1]=my;
					seco[n1]=mx;
					n1++;
				}
			else if(checkedges>0)
				{
					for(gl=0;gl<checkedges;gl++)
						{
							if(first[gl]==my && seco[gl]==mx)
								{
									checkedgeflag=1;
								}
						}
					if(checkedgeflag==0)
						{
							first[n1]=my;
							seco[n1]=mx;
							n1++;
							
						}
				}

			ll=mx;
			delx=x[mx]-x[my];
			if(delx<0)
			{delx=delx * -1;}
			
			dely=y[mx]-y[my];
			if(dely<0)
			{dely=dely * -1;}

			 if ((delx==0)&&(dely==0))
				{
					same[n]=-1;
					a5[n]=x[mx];
					b5[n]=y[mx];
					c5[n]=x[my];
					d5[n]=y[my];
				}
			
			else if(delx>=dely)
			{
				if(y[mx]>y[my])
				{
					a5[n]=x[my]+26;b5[n]=y[my]+5;
					c5[n]=x[mx]+26;d5[n]=y[mx]+5;
					a6[n]=x[my]+26;b6[n]=y[my]+6;
					c6[n]=x[mx]+26;d6[n]=y[mx]+6;
					same[n]=1;
				}
				else if(y[mx]<=y[my])
				{
					a5[n]=x[my]+26;b5[n]=y[my]+45;
					c5[n]=x[mx]+26;d5[n]=y[mx]+45;
					a6[n]=x[my]+26;b6[n]=y[my]+46;
					c6[n]=x[mx]+26;d6[n]=y[mx]+46;
					same[n]=1;
				}
			}

             else if(delx<dely)
			{
				if(x[mx]<=x[my])
				{
					a5[n]=x[my]+5;b5[n]=y[my]+27;
					c5[n]=x[mx]+5;d5[n]=y[mx]+27;
					a6[n]=x[my]+4;b6[n]=y[my]+27;
					c6[n]=x[mx]+4;d6[n]=y[mx]+27;
					same[n]=1;
				}
				else if(x[mx]>x[my])
				{
					a5[n]=x[my]+45;b5[n]=y[my]+27;
					c5[n]=x[mx]+45;d5[n]=y[mx]+27;
					a6[n]=x[my]+44;b6[n]=y[my]+27;
					c6[n]=x[mx]+44;d6[n]=y[mx]+27;
					same[n]=1;
				}
			}
			
				t=0;n++;
				flag2=0;
			  repaint();
			j1=0;
			break;
			}	
		    	
	  }
	
	}
 }

	
 public void paint(Graphics g)
 {
	      	int xmid=0,ymid=0,f1=0,f2=0,g1=0,g2=0,ct;       
	   super.paint(g);			
				
				for(j=0;j<i;j++)
					{
						g.setColor(Color.red);
						if(my1==j)
						{
							g.setColor(Color.blue);	
						}
						g.fillOval(x[j],y[j],50,50);
						g.drawString("q"+j,x[j]+23,y[j]+60);
					}
					
					for(j=0;j<n;j++)	
					{
						g.setColor(Color.red);
						f1=af.arrowf1(a5[j],b5[j],c5[j],d5[j]);
						g1=af.arrowg1(a5[j],b5[j],c5[j],d5[j]);
						f2=af.arrowf2(a5[j],b5[j],c5[j],d5[j]);
						g2=af.arrowg2(a5[j],b5[j],c5[j],d5[j]);
					   	xmid=(a5[j]+c5[j])/2;
       					ymid=(b5[j]+d5[j])/2;
      					if(same[j]==1)
							{
      							g.drawLine(a5[j],b5[j],c5[j],d5[j]);
								 g.drawLine(a6[j],b6[j],c6[j],d6[j]);
								g.setColor(Color.blue);
     							g.drawLine(f1,g1,xmid,ymid);
    							g.drawLine(f2,g2,xmid,ymid);
								if(flaglabel==1)
									{
										for(ct=0;ct<f;ct++)
											{
												if((third[ct]==first[j])&&(fourth[ct]==seco[j]))
													{
														g.setColor(Color.black);
														g.drawString(" "+sigmalabel1[ct],xmid-4,ymid-4);
													}
											}
									
									}
							}
							else if((same[j]==-1))
								{
									g.setColor(Color.black);
									g.drawArc(a5[j],b5[j]-25,50,70,0,180);
									if(flaglabel==1)
										{
											for(ct=0;ct<f;ct++)
											{
												if((third[ct]==first[j])&&(fourth[ct]==seco[j]))
													{
														g.setColor(Color.black);
														g.drawString(" "+sigmalabel1[ct],a5[j]+25,b5[j]-22);
													}
										}
										}
								}

					}
					for (j=0;j<ini;j++)
					{
						g.setColor(Color.black);
						g.fillOval(xx[j],yy[j],50,50);
						
					}
					if(label==1)
						popup();
				}

		
  
    public javax.swing.JButton add;
	 public javax.swing.JButton enterstr;
    public javax.swing.JPanel bevpanel;
    public javax.swing.JComboBox edgecombo;
    public javax.swing.JLabel edges;
    public javax.swing.JLabel sigma;
    public javax.swing.JComboBox sigmacombo;
	public javax.swing.JDialog jd;
 }

    

  
