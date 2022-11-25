class  arrowfinding
{
	DFA dfa=new DFA();
	NFA nfa = new NFA();
	 int tag,i,j,t,delx,fi,se,dely,k;
	int flag1,tempfirst,tempsecond;
	int first[]=new int[50];
	int second[]=new int[50];
	int linex1[]=new int[50];
	int liney1[]=new int[50];
	int linex2[]=new int[50];
	int liney2[]=new int[50];
	int same[]=new int[50];
	int statex[]=new int[50];
	 int statey[]=new int[50];
	String sig[]=new String[50];
	 String tempsig;
	 int kkk=-1;
	 int ii;
	 public void statesinitial(int tot)
		{   
			if(tot<=7)
			{
				statex[0]=305;statey[0]=61;
				statex[1]=579;statey[1]=79;
				statex[2]=204;statey[2]=203;
				statex[3]=302;statey[3]=372;
				statex[4]=739;statey[4]=212;
				statex[5]=626;statey[5]=352;
				statex[6]=472;statey[6]=418;
				statex[7]=290;statey[7]=463;
				//statex[8]=589;statey[8]=150;
				//statex[9]=953;statey[9]=413;
				//statex[10]=774;statey[10]=317;
				//statex[11]=885;statey[11]=553;
				//statex[12]=691;statey[12]=7;
				//statex[13]=924;statey[13]=135;
			}
			else if(tot>7)
			{
				statex[0]=30;statey[0]=192;
				statex[1]=49;statey[1]=375;
				statex[2]=138;statey[2]=112;
				statex[3]=249;statey[3]=295;
				statex[4]=181;statey[4]=495;
				statex[5]=305;statey[5]=157;
				statex[6]=332;statey[6]=441;
				statex[7]=486;statey[7]=490;
				statex[8]=431;statey[8]=367;
				statex[9]=543;statey[9]=223;
				statex[10]=438;statey[10]=67;
				statex[11]=635;statey[11]=31;
				statex[12]=711;statey[12]=248;
				statex[13]=624;statey[13]=369;
				statex[14]=688;statey[14]=421;
				statex[15]=839;statey[15]=418;
				statex[16]=950;statey[16]=447;
				statex[17]=915;statey[17]=356;
				statex[18]=849;statey[18]=238;
				statex[19]=956;statey[19]=157;
				//statex[13]=624;statey[13]=369;
			}
			
		}
	public int arrowf1(int a5j,int b5j,int c5j,int d5j)
	  {
		double xmid=0.0,ymid=0.0,x1=0.0,y1=0.0,x2=0.0,y2=0.0,m1=0.0,m=0.0,c=0.0,c1=0.0,l1=0.0,l2=0.0,l3=0.0,k1=0.0,k2=0.0,a=0.0,b=0.0;      
      	        int q=0,w=0,e=0,r=0,f1=0,f2=0,g1=0,g2=0;      
		x1=(double)a5j;
						y1=(double)b5j;
						x2=(double)c5j;
						y2=(double)d5j;
	 

       					xmid=(x1+x2)/2;
       					ymid=(y1+y2)/2;
      					// System.out.println("xmid  "+xmid);
       					//System.out.println("ymid  "+ymid);
							
        				m1=((ymid+0.02)-y1)/((xmid+0.01)-x1);
      					//System.out.println("m1  "+m1);
      						c1=ymid-(m1*xmid);
     					 //System.out.println("c1=  "+c1);
     					 m=-(1/m1);
      					 a=xmid;b=ymid;
    
     					 l1=(90-(a*a))*m1*m1;
     					 l2=2*a*m1*(b-c1);
      					l3=(2*b*c1)+90-(b*b)-(c1*c1);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
      					l3=a+(b*m1)-(c1*m1);

						if(c5j>a5j)
      					{l1=l3-l2;}
						else 
						{l1=l3+l2;}
	
      					l2=l1/(1+(m1*m1));
       					l3=(l2*m1)+c1;
       					//System.out.println( " l1, l2  "+ l2 +"  "+ l3);
         				c=l3-(m*l2);
       					e=(int)l2;
       					r=(int)l3;


      					a=l2;b=l3;
      					l1=(90-(a*a))*m*m;
      					l2=2*a*m*(b-c);
      					l3=(2*b*c)+90-(b*b)-(c*c);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
     					l3=a+(b*m)-(c*m);
     					 //System.out.println(" l2 , l3 "+l2+"  "+l3);
      						l1=l3-l2;
      
     					k1=l3+l2;
     					l2=l1/(1+(m*m));
     
      					 k1=k1/(1+(m*m));
     					 //System.out.println("l1=  "+l1);
      					f1=(int)l2;
      					l3=(l2*m)+c;
      					g1=(int)l3;
      					f2=(int)k1;
      					k2=(k1*m)+c;
      					g2=(int)k2;
      					//System.out.println(f1+"  "+ g1);
      					//System.out.println(f2+"  "+g2);
						return f1;
	  }
	  public int arrowf2(int a5j,int b5j,int c5j,int d5j)
	 {
		  double xmid=0.0,ymid=0.0,x1=0.0,y1=0.0,x2=0.0,y2=0.0,m1=0.0,m=0.0,c=0.0,c1=0.0,l1=0.0,l2=0.0,l3=0.0,k1=0.0,k2=0.0,a=0.0,b=0.0;      
      		  int q=0,w=0,e=0,r=0,f1=0,f2=0,g1=0,g2=0;      
		  				x1=(double)a5j;
						y1=(double)b5j;
						x2=(double)c5j;
						y2=(double)d5j;
	 

       					xmid=(x1+x2)/2;
       					ymid=(y1+y2)/2;
      					// System.out.println("xmid  "+xmid);
       					//System.out.println("ymid  "+ymid);

        				m1=((ymid+0.02)-y1)/((xmid+0.01)-x1);
      					//System.out.println("m1  "+m1);
      						c1=ymid-(m1*xmid);
     					 //System.out.println("c1=  "+c1);
     					 m=-(1/m1);
      					 a=xmid;b=ymid;
    
     					 l1=(90-(a*a))*m1*m1;
     					 l2=2*a*m1*(b-c1);
      					l3=(2*b*c1)+90-(b*b)-(c1*c1);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
      					l3=a+(b*m1)-(c1*m1);

						if(c5j>a5j)
      					{l1=l3-l2;}
						else 
						{l1=l3+l2;}
	
      					l2=l1/(1+(m1*m1));
       					l3=(l2*m1)+c1;
       					//System.out.println( " l1, l2  "+ l2 +"  "+ l3);
         				c=l3-(m*l2);
       					e=(int)l2;
       					r=(int)l3;


      					a=l2;b=l3;
      					l1=(90-(a*a))*m*m;
      					l2=2*a*m*(b-c);
      					l3=(2*b*c)+90-(b*b)-(c*c);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
     					l3=a+(b*m)-(c*m);
     					 //System.out.println(" l2 , l3 "+l2+"  "+l3);
      						l1=l3-l2;
      
     					k1=l3+l2;
     					l2=l1/(1+(m*m));
     
      					 k1=k1/(1+(m*m));
     					 //System.out.println("l1=  "+l1);
      					f1=(int)l2;
      					l3=(l2*m)+c;
      					g1=(int)l3;
      					f2=(int)k1;
      					k2=(k1*m)+c;
      					g2=(int)k2;
      					//System.out.println(f1+"  "+ g1);
      					//System.out.println(f2+"  "+g2);
						return f2;
	 }
	public int arrowg1(int a5j,int b5j,int c5j,int d5j)
	 {
		double xmid=0.0,ymid=0.0,x1=0.0,y1=0.0,x2=0.0,y2=0.0,m1=0.0,m=0.0,c=0.0,c1=0.0,l1=0.0,l2=0.0,l3=0.0,k1=0.0,k2=0.0,a=0.0,b=0.0;      
      		int q=0,w=0,e=0,r=0,f1=0,f2=0,g1=0,g2=0;      
						x1=(double)a5j;
						y1=(double)b5j;
						x2=(double)c5j;
						y2=(double)d5j;
	 

       					xmid=(x1+x2)/2;
       					ymid=(y1+y2)/2;
      					// System.out.println("xmid  "+xmid);
       					//System.out.println("ymid  "+ymid);

        				m1=((ymid+0.02)-y1)/((xmid+0.01)-x1);
      					//System.out.println("m1  "+m1);
      						c1=ymid-(m1*xmid);
     					 //System.out.println("c1=  "+c1);
     					 m=-(1/m1);
      					 a=xmid;b=ymid;
    
     					 l1=(90-(a*a))*m1*m1;
     					 l2=2*a*m1*(b-c1);
      					l3=(2*b*c1)+90-(b*b)-(c1*c1);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
      					l3=a+(b*m1)-(c1*m1);

						if(c5j>a5j)
      					{l1=l3-l2;}
						else 
						{l1=l3+l2;}
	
      					l2=l1/(1+(m1*m1));
       					l3=(l2*m1)+c1;
       					//System.out.println( " l1, l2  "+ l2 +"  "+ l3);
         				c=l3-(m*l2);
       					e=(int)l2;
       					r=(int)l3;


      					a=l2;b=l3;
      					l1=(90-(a*a))*m*m;
      					l2=2*a*m*(b-c);
      					l3=(2*b*c)+90-(b*b)-(c*c);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
     					l3=a+(b*m)-(c*m);
     					 //System.out.println(" l2 , l3 "+l2+"  "+l3);
      						l1=l3-l2;
      
     					k1=l3+l2;
     					l2=l1/(1+(m*m));
     
      					 k1=k1/(1+(m*m));
     					 //System.out.println("l1=  "+l1);
      					f1=(int)l2;
      					l3=(l2*m)+c;
      					g1=(int)l3;
      					f2=(int)k1;
      					k2=(k1*m)+c;
      					g2=(int)k2;
      					//System.out.println(f1+"  "+ g1);
      					//System.out.println(f2+"  "+g2);
						return g1;
	 }
	  public int arrowg2(int a5j,int b5j,int c5j,int d5j)
	 {
		  double xmid=0.0,ymid=0.0,x1=0.0,y1=0.0,x2=0.0,y2=0.0,m1=0.0,m=0.0,c=0.0,c1=0.0,l1=0.0,l2=0.0,l3=0.0,k1=0.0,k2=0.0,a=0.0,b=0.0;      
	       	int q=0,w=0,e=0,r=0,f1=0,f2=0,g1=0,g2=0;      
		  				x1=(double)a5j;
						y1=(double)b5j;
						x2=(double)c5j;
						y2=(double)d5j;
	 

       					xmid=(x1+x2)/2;
       					ymid=(y1+y2)/2;
      					// System.out.println("xmid  "+xmid);
       					//System.out.println("ymid  "+ymid);

        				m1=((ymid+0.02)-y1)/((xmid+0.01)-x1);
      					//System.out.println("m1  "+m1);
      						c1=ymid-(m1*xmid);
     					 //System.out.println("c1=  "+c1);
     					 m=-(1/m1);
      					 a=xmid;b=ymid;
    
     					 l1=(90-(a*a))*m1*m1;
     					 l2=2*a*m1*(b-c1);
      					l3=(2*b*c1)+90-(b*b)-(c1*c1);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
      					l3=a+(b*m1)-(c1*m1);

						if(c5j>a5j)
      					{l1=l3-l2;}
						else 
						{l1=l3+l2;}
	
      					l2=l1/(1+(m1*m1));
       					l3=(l2*m1)+c1;
       					//System.out.println( " l1, l2  "+ l2 +"  "+ l3);
         				c=l3-(m*l2);
       					e=(int)l2;
       					r=(int)l3;


      					a=l2;b=l3;
      					l1=(90-(a*a))*m*m;
      					l2=2*a*m*(b-c);
      					l3=(2*b*c)+90-(b*b)-(c*c);
     					l1=l1+l2+l3;
     					l2=Math.sqrt(l1);
     					l3=a+(b*m)-(c*m);
     					 //System.out.println(" l2 , l3 "+l2+"  "+l3);
      						l1=l3-l2;
      
     					k1=l3+l2;
     					l2=l1/(1+(m*m));
     
      					 k1=k1/(1+(m*m));
     					 //System.out.println("l1=  "+l1);
      					f1=(int)l2;
      					l3=(l2*m)+c;
      					g1=(int)l3;
      					f2=(int)k1;
      					k2=(k1*m)+c;
      					g2=(int)k2;
      					//System.out.println(f1+"  "+ g1);
      					//System.out.println(f2+"  "+g2);
						return g2;
	 }
	  void	dfaprinting(DFA dfa1)
			{
				dfa = dfa1;
				
				for(i=0;i<dfa.count_states;i++)
				{
					for(j=0;j<dfa.count_sigma;j++)
					{	
						if(dfa.tr_table[i][j]!=-1)
						{
							kkk++;
							System.out.println("k = "+kkk);
							tag=0;
							if(kkk==0)
							{
								first[kkk]=dfa.states[i];
								second[kkk]=dfa.tr_table[i][j];
								if(dfa.tr_table[i][j]==dfa.states[i])
								{
									same[kkk]=1;
								}
								sig[kkk]=" "+dfa.sigma[j];
														
							}

							else if(kkk>0)
							{
								for(t=0;t<kkk;t++)
								{
									
										tempfirst=dfa.states[i];
										tempsecond=dfa.tr_table[i][j];
										tempsig=" "+dfa.sigma[j];
										if((first[t]==tempfirst)&&(second[t]==tempsecond)&&(sig[t]!=tempsig))
										{
											sig[t]=sig[t]+dfa.sigma[j];
											kkk--;
											tag=1;
											break;
										}									
								}
									if(tag==0)	
										{											
												first[kkk]=dfa.states[i];
												second[kkk]=dfa.tr_table[i][j];
												if(dfa.tr_table[i][j]==dfa.states[i])
													{
														same[kkk]=1;
													}
												sig[kkk]=" "+dfa.sigma[j];																	
										}										
							}
						}

					}
				}
					for(i=0;i<=kkk;i++)
					{
						System.out.println(" "+first[i]+"----> "+second[i]+"===="+sig[i]);
					}
					for(i=0;i<=kkk;i++)
					{
						System.out.println(" "+first[i]+"----> "+second[i]+"===="+sig[i]);
					}
				makeline(2);
			}

		void nfaprinting(NFA dfa1)
			{
				nfa = dfa1;
				
				for(i=0;i<nfa.count_states;i++)
				{
					for(j=0;j<nfa.count_sigma;j++)
					{	
						if(nfa.tr_table[i][j].no_state!=0)
						{
							
							  for(k=0;k<nfa.tr_table[i][j].no_state;k++)
                                        {
												kkk++;
                                              // System.out.print("q"+nfa.tr_table[i][j].index[k]+" ");
											   first[kkk]=i;
											   second[kkk]=nfa.tr_table[i][j].index[k];
											   
                                        
											System.out.println("k = "+kkk);
											tag=0;
											if(kkk==0)
											{
												first[kkk]=i;
												second[kkk]=nfa.tr_table[i][j].index[k];
												if(nfa.tr_table[i][j].index[k]==i)
													{
														same[kkk]=1;
													}
												sig[kkk]=" "+nfa.sigma[j];
														
											}

							else if(kkk>0)
							{
								for(t=0;t<kkk;t++)
								{
									
										tempfirst=i;
										tempsecond=nfa.tr_table[i][j].index[k];
										tempsig=" "+nfa.sigma[j];
										if((first[t]==tempfirst)&&(second[t]==tempsecond)&&(sig[t]!=tempsig))
										{
											sig[t]=sig[t]+nfa.sigma[j];
											kkk--;
											tag=1;
											break;
										}									
								}
									if(tag==0)	
										{											
												first[kkk]=i;
												second[kkk]=nfa.tr_table[i][j].index[k];
												if(nfa.tr_table[i][j].index[k]==i)
													{
														same[kkk]=1;
													}
												sig[kkk]=" "+nfa.sigma[j];																	
										}										
							}
										}
						}

					}
				}
					for(i=0;i<=kkk;i++)
					{
						System.out.println(" "+first[i]+"----> "+second[i]+"===="+sig[i]);
					}
					makeline(1);
			}

			 public int findindex(int arr[],int count_arr,int c)
			{
                int i=0;
                for(i=0;i<count_arr;i++)
                {
                        if(c==arr[i])
                                return i;
                }
				return -1;
             }

			void makeline(int h)
				{
					for(i=0;i<=kkk;i++)
					{	
						fi=first[i];
						se=second[i];
						if(h==2)
						{
							fi=findindex(dfa.states,dfa.count_states,fi);
							se=findindex(dfa.states,dfa.count_states,se);
							System.out.println("fi, se"+fi+se);
						}
						delx=statex[fi]-statex[se];
						if(delx<0)
							{delx=delx * -1;}
			
						dely=statey[fi]-statey[se];
						if(dely<0)
						{dely=dely * -1;}

						if ((delx==0)&&(dely==0))
							{
								//same[i]=-1;
								linex1[i]=statex[se];
								liney1[i]=statey[se];
								linex2[i]=statex[fi];
								liney2[i]=statey[fi];
							}
			

						else if(delx>=dely)
							{
								if(statey[se]>statey[fi])
									{
										linex1[i]=statex[fi]+26;liney1[i]=statey[fi]+5;
										linex2[i]=statex[se]+26;liney2[i]=statey[se]+5;
										//a6[n]=x[my]+26;b6[n]=y[my]+6;
										//c6[n]=x[mx]+26;d6[n]=y[mx]+6;
										//same[n]=1;
									}
								else if(statey[se]<=statey[fi])
									{
										linex1[i]=statex[fi]+26;liney1[i]=statey[fi]+45;
										linex2[i]=statex[se]+26;liney2[i]=statey[se]+45;
										//a6[n]=x[my]+26;b6[n]=y[my]+46;
										//c6[n]=x[mx]+26;d6[n]=y[mx]+46;
										//same[n]=1;
									}
							}

						else if(delx<dely)
							{
								if(statex[se]<=statex[fi])
									{
										linex1[i]=statex[fi]+5;liney1[i]=statey[fi]+27;
										linex2[i]=statex[se]+5;liney2[i]=statey[se]+27;
										//a6[n]=x[my]+4;b6[n]=y[my]+27;
										//c6[n]=x[mx]+4;d6[n]=y[mx]+27;
										//same[n]=1;
									}
								else if(statex[se]>statex[fi])
									{
										linex1[i]=statex[fi]+45;liney1[i]=statey[fi]+27;
										linex2[i]=statex[se]+45;liney2[i]=statey[se]+27;
										//a6[n]=x[my]+44;b6[n]=y[my]+27;
										//c6[n]=x[mx]+44;d6[n]=y[mx]+27;
										//same[n]=1;
									}
							}

					}
					flag1=1;
					for (i=0;i<=kkk;i++ )
						{
							System.out.println(linex1[i]+" , "+liney1[i]+"-------> "+linex2[i]+" , "+liney2[i]);
						}
				}
	

	/*void makelinenfa(int fi,int se)
	{
					delx=statex[fi]-statex[se];
						if(delx<0)
							{delx=delx * -1;}
			
						dely=statey[fi]-statey[se];
						if(dely<0)
						{dely=dely * -1;}

						if ((delx==0)&&(dely==0))
							{
								same[ii]=1;
								linex1[ii]=statex[se];
								liney1[ii]=statey[se];
								linex2[ii]=statex[fi];
								liney2[ii]=statey[fi];
							}
			

						else if(delx>=dely)
							{
								if(statey[se]>statey[fi])
									{
										linex1[ii]=statex[fi]+26;liney1[ii]=statey[fi]+5;
										linex2[ii]=statex[se]+26;liney2[ii]=statey[se]+5;
										//a6[n]=x[my]+26;b6[n]=y[my]+6;
										//c6[n]=x[mx]+26;d6[n]=y[mx]+6;
										same[ii]=-1;
									}
								else if(statey[se]<=statey[fi])
									{
										linex1[ii]=statex[fi]+26;liney1[ii]=statey[fi]+45;
										linex2[ii]=statex[se]+26;liney2[ii]=statey[se]+45;
										//a6[n]=x[my]+26;b6[n]=y[my]+46;
										//c6[n]=x[mx]+26;d6[n]=y[mx]+46;
										same[ii]=-1;
									}
							}

						else if(delx<dely)
							{
								if(statex[se]<=statex[fi])
									{
										linex1[ii]=statex[fi]+5;liney1[ii]=statey[fi]+27;
										linex2[ii]=statex[se]+5;liney2[ii]=statey[se]+27;
										//a6[n]=x[my]+4;b6[n]=y[my]+27;
										//c6[n]=x[mx]+4;d6[n]=y[mx]+27;
										same[ii]=-1;
									}
								else if(statex[fi]>statex[se])
									{
										linex1[ii]=statex[fi]+45;liney1[ii]=statey[fi]+27;
										linex2[ii]=statex[se]+45;liney2[ii]=statey[se]+27;
										//a6[n]=x[my]+44;b6[n]=y[my]+27;
										//c6[n]=x[mx]+44;d6[n]=y[mx]+27;
										same[ii]=-1;
									}
							}
							ii++;


	}*/
}
