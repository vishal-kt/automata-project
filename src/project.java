class DFA
{
        public int count_states;		// Defines template for DFA.		
        public int states[];
        public char sigma[];
        public int count_sigma;
        public int tr_table[][];
        public int init_state;
        public int fstates[];
        public int count_fstates;
        public DFA()
        {
               count_states=0;
               count_sigma=0;
               count_fstates=0;
               init_state=-1;
        }
        public boolean process(String s)	// Checks acceptance of string of characters.	
        {
                char c;
                int i=0,l,pos;
                int current;
                current=init_state;
                l=s.length();
                try{
                for(i=0;i<l;i++)
                {
                        c=s.charAt(i);
                        pos=NFA.findindex(sigma,count_sigma,c);
                        current=tr_table[NFA.findindex(states,count_states,current)][pos];
                }}
                catch(Exception e){						// Not Accepted
                        return false;}
                for(i=0;i<count_fstates;i++)
                {
                        if(current==fstates[i])			// Accepted
                        {
                                return true;
                        }
                }
                return false;
        }

		//Minimizes equivalent states in given DFA.

        public DFA minimize()					
        {
                int state1=-1,state2=-1,index1,index2,areequal=0,x=0;
                int reduce[]=new int[count_states];
                boolean carryon=true;
                boolean distinct[][]=new boolean[count_states][count_states];

						// Distinct[][] is table (states by states)
						// which marks equivalent and non-equivalent states.

                for(int i=0;i<count_states;i++)
                {
                        reduce[i]=-1;
                        for(int j=0;j<count_states;j++)
                                distinct[i][j]=distinct[j][i]=false;	// Initializes table entries as false
																		// means initially all states are equivalent.
                }
                for(int i=0;i<count_fstates;i++)
                {
                        state1=fstates[i];
                        index1=NFA.findindex(states,count_states,state1);
                        for(int j=0;j<count_states;j++)
                        {
                                state2=states[j];
                                index2=NFA.findindex(fstates,count_fstates,state2);
                                if(index2==-1)
                                {
                                        distinct[j][index1]=distinct[index1][j]=true;  //Final states are distinguishable with non-final states.
                                }
                        }
                }
                while(carryon) //Repeat untill no new entry is made into table.
                {
                        carryon=false;
                        for(int i=0;i<count_states;i++)
                        {
                                for(int j=0;j<i;j++)
                                {
                                        if(!distinct[i][j])
                                        for(int k=0;k<count_sigma;k++)
                                        {
                                                try
                                                {
                                                        state1=tr_table[i][k];
                                                        index1=NFA.findindex(states,count_states,state1);
                                                        state2=tr_table[j][k];
                                                        index2=NFA.findindex(states,count_states,state2);
                                                        distinct[j][i]=distinct[i][j]=distinct[index1][index2];
                                                }
                                                catch(Exception e)
                                                {
                                                        if(state1==-1&&state2==-1)
                                                                distinct[i][j]=distinct[i][j]=false;
                                                        else
                                                                distinct[i][j]=distinct[j][i]=true;
                                                }
                                                if(distinct[i][j])
                                                {
                                                        carryon=true;
                                                        break;
                                                }
                                        }
                                }
                        }
                }
                for(int i=count_states-1;i>=0;i--)   
                {
                        for(int j=0;j<i;j++)
                        {
                                if(distinct[i][j]==false)
                                {
                                        reduce[i]=states[j];      areequal++;
                                        break;
                                }
                        }
                }
				
				//Reduce and Rename equivalent states.

                DFA dfa=new DFA();
                dfa.count_states=count_states-areequal;
                dfa.states=new int[dfa.count_states];
                for(int k=0;k<count_states;k++)
                {
                        if(reduce[k]==-1)
                        {
                                dfa.states[x]=states[k];
                                x++;
                        }
                }
                dfa.count_sigma=count_sigma;
                dfa.sigma=new char[dfa.count_sigma];
                for(int i=0;i<dfa.count_sigma;i++)
                {
                        dfa.sigma[i]=sigma[i];
                }
                index1=NFA.findindex(states,count_states,init_state);
                if(reduce[index1]==-1)
                {
                        dfa.init_state=init_state;
                }
                else
                {
                        dfa.init_state=reduce[index1];
                }
                for(int k=0;k<count_fstates;k++)
                {
                        index1=NFA.findindex(states,count_states,fstates[k]);
                        if(reduce[index1]==-1)
                        {
                                dfa.count_fstates++;
                                dfa.fstates=NFA.realloc(dfa.fstates,dfa.count_fstates-1,dfa.count_fstates);
                                dfa.fstates[dfa.count_fstates-1]=fstates[k];
                        }
                }
                dfa.tr_table=new int[dfa.count_states][dfa.count_sigma];
                for(int k=0;k<dfa.count_states;k++)
                {
                        int i=NFA.findindex(states,count_states,dfa.states[k]);
                        if(reduce[i]==-1)
                        {
                                for(int j=0;j<dfa.count_sigma;j++)
                                {
                                        state1=tr_table[i][j];
                                        if(state1==-1)
                                        {
                                                dfa.tr_table[k][j]=-1;                                               
                                        }
                                        else
                                        {
                                                index1=NFA.findindex(states,count_states,state1);
                                                if(reduce[index1]==-1)
                                                {               
                                                        dfa.tr_table[k][j]=state1;
                                                }
                                                else
                                                {
                                                        dfa.tr_table[k][j]=reduce[index1];
                                                }
                                        }
                                }
                        }
                }
                for(int i=0;i<count_states;i++)
                {
                        if(reduce[i]!=-1)
                        System.out.println("q"+states[i]+" ----> q"+reduce[i]);
                }
                return dfa;
        }
}  
class nfastate
{
        public int index[];
        public int no_state;
        public nfastate()
        {
                no_state=0;
        }
}
class NFA
{
        public int count_states;
        public char sigma[];
        public int count_sigma;
        public nfastate tr_table[][];
        public int init_state;
        public int fstates[];
        public int count_fstates;
        private int current[];
        private int next[];
        private nfastate tmp[][];
        private nfastate tmp1[];
        private int csize,nsize;
        public NFA()
        {
                count_states=0;
                count_sigma=0;
                count_fstates=0;
        }
        public static void sort(int current[],int csize)
        {
                int i,j,key;
                for(i=1;i<csize;i++)
                {
                        key=current[i];
                        for(j=i-1 ; j>=0 && current[j]>key ;j--)
                        {
                                current[j+1]=current[j];
                        }
                        current[j+1]=key;
                }
        }
        public static void sort(char current[],int csize)
        {
                int i,j;
                char key;
                for(i=1;i<csize;i++)
                {
                        key=current[i];
                        for(j=i-1 ; j>=0 && current[j]>key ;j--)
                        {
                                current[j+1]=current[j];
                        }
                        current[j+1]=key;
                }
        }
        public static void sort(char current[],int size1,int size2)
        {
                int i,j;
                char key;
                for(i=size1+1;i<size2;i++)
                {
                        key=current[i];
                        for(j=i-1 ; j>=size1 && current[j]>key ;j--)
                        {
                                current[j+1]=current[j];
                        }
                        current[j+1]=key;
                }
        }
		public static void sort(String current[],int size1,int size2)
        {
                int i,j;
                String key;
                for(i=size1+1;i<size2;i++)
                {
                        key=current[i];
                        for(j=i-1 ; j>=size1 && current[j].compareTo(key)>0 ;j--)
                        {
                                current[j+1]=current[j];
                        }
                        current[j+1]=key;
                }
        }
        public static int[] realloc(int a[],int size1,int size2)
        {
                int tmp[]=new int[size1];
                int i;
                for(i=0;i<size1;i++)
                {
                        tmp[i]=a[i];
                }
                a=new int[size2];
                for(i=0;i<size1;i++)
                {
                        a[i]=tmp[i];
                }
                return a;
        }
        public static nfastate[] realloc(nfastate arr[],int n1,int n2)
        {
                nfastate tmp1[];
                int l,m,n;
                tmp1=new nfastate[n1];
                for(l=0;l<n1;l++)
                {
                        tmp1[l]=new nfastate();
                        tmp1[l].no_state=arr[l].no_state;
                        tmp1[l].index=new int[tmp1[l].no_state];
                        for(m=0;m<tmp1[l].no_state;m++)
                        {
                                tmp1[l].index[m]=arr[l].index[m];
                        }
                }
                arr=new nfastate[n2];
                for(l=0;l<n1;l++)
                {
                        arr[l]=new nfastate();
                        arr[l].no_state=tmp1[l].no_state;
                        arr[l].index=new int[arr[l].no_state];
                        for(m=0;m<arr[l].no_state;m++)
                        {
                                arr[l].index[m]=tmp1[l].index[m];
                        }
                }
                return arr;
        }
        public static nfastate[][] realloc(nfastate mat[][],int r1,int c1,int r2,int c2)
        {
                nfastate tmp[][];
                int l,m,n;
                tmp=new nfastate[r1][c1];
                for(l=0;l<r1;l++)
                {
                        for(m=0;m<c1;m++)
                        {
                                tmp[l][m]=new nfastate();
                                tmp[l][m].no_state=mat[l][m].no_state;
                                tmp[l][m].index= new int[tmp[l][m].no_state];
                                for(n=0;n<tmp[l][m].no_state;n++)
                                {
                                        tmp[l][m].index[n]=mat[l][m].index[n];
                                }
                        }
                }
                mat = new nfastate[r2][c2];
                for(l=0;l<r2;l++)
                {
                        for(m=0;m<c2;m++)
                        {
                                mat[l][m]=new nfastate();
                        }
                }
                for(l=0;l<r1;l++)
                {
                        for(m=0;m<c1;m++)
                        {
                                mat[l][m].no_state=tmp[l][m].no_state;
                                mat[l][m].index=new int[tmp[l][m].no_state];
                                for(n=0;n<tmp[l][m].no_state;n++)
                                {
                                        mat[l][m].index[n]=tmp[l][m].index[n];
                                }
                         }
                }
                return mat;
        }
        private void extend() //  adds null moves
        {
                boolean present;
                int i,j,k,tmp3,tmp2;
                for(i=0;i<csize;i++)
                {
                        tmp3=current[i];
                        for(k=0;k<tr_table[tmp3][count_sigma-1].no_state;k++)
                        {
                                tmp2=tr_table[tmp3][count_sigma-1].index[k];
                                present=false;
                                for(j=csize-1;j>=0;j--)
                                {
                                        if(current[j]==tmp2)
                                        {
                                                present=true;
                                                break;
                                        }
                                }
                                if(present==false)
                                        current[csize++]=tmp2;
                        }
                }
        }
        public boolean processchar(char c)      // process single character
        {
                int pos,i,j,k,tmp1;
                boolean present;
                pos=findindex(sigma,count_sigma,c);
                if(pos==-1)
                        return false;
                for(i=0;i<csize;i++)
                {
                        for(j=0;j<tr_table[current[i]][pos].no_state;j++)
                        {
                                tmp1=tr_table[current[i]][pos].index[j];
                                present=false;
                                for(k=nsize-1;k>=0;k--)
                                {
                                        if(tmp1==next[k])
                                        {
                                                present=true;
                                                break;
                                         }
                                }
                                if(present==false)
                                {
                                        next[nsize]=tmp1;
                                        nsize++;
                                }
                        }
                }
                return true;
        }
        public boolean process(String s) // process entire string 
        {
                char c;
                boolean result=false;
                int i=0,j,l,st=0;
                current=new int[count_states];csize=1;
                current[0]=init_state;
                next=new int[count_states];nsize=0;
                extend();
                l=s.length();
                while(st<l)
                {
                        c=s.charAt(st++);
                        result=processchar(c);
                        if(result==false||nsize==0)
                                return false;
                        for(i=0;i<nsize;i++)
                        {
                                current[i]=next[i];
                        }
                        csize=nsize;nsize=0;
                        extend();
                }
                for(i=0;i<csize;i++)
                {
                        for(j=0;j<count_fstates;j++)
                        {
                                if(current[i]==fstates[j])
                                        return true;
                        }
                }
                return false;         
        }       
        public static int findindex(char sigma[],int count_sigma,char c)
        {
                int i=0;
                for(i=0;i<count_sigma;i++)
                {
                        if(c==sigma[i])
                                return i;
                }
                return -1;
        }
        public static int findindex(String sigma[],int count_sigma,String c)
        {
                int i=0;
                for(i=0;i<count_sigma;i++)
                {
						//if(sigma[i])
                        if(c.equals(sigma[i]))
                                return i;
                }
                return -1;
        }
        public static int findindex(int arr[],int count_arr,int c)
        {
                int i=0;
                for(i=0;i<count_arr;i++)
                {
                        if(c==arr[i])
                                return i;
                }
                return -1;
        }
        public static boolean issame(int a[],int asize,int b[],int bsize)
        {
                if(asize!=bsize)
                        return false;
                if(asize==0)
                        return true;
                for(int i=0;i<asize;i++)
                {
                        if(a[i]!=b[i])
                                return false;
                }
                return true;
        }
        public boolean isany(int a[],int asize,int b[],int bsize)
        {
                int i,j;
                for(i=0;i<asize;i++)
                        for(j=0;j<bsize;j++)
                                if(a[i]==b[j])
                                        return true;
                return false;
        }
        public NEWDFA nfatodfa()     //NFA TO DFA conversion
        {
                int i,j,k,l,m,n,t,key=0;
                current = new int[count_states];
                next = new int[count_states];
                current[0]=init_state;
                csize=1;nsize=0;
                boolean exist=false;
                NEWDFA newdfa = new NEWDFA();

         //------------------------ASSIGNS SIGMA'S---------------------//

                newdfa.count_sigma=count_sigma-1;        
                newdfa.sigma=new char[newdfa.count_sigma];
                for(i=0;i<newdfa.count_sigma;i++)
                {
                        newdfa.sigma[i]=sigma[i];
                }
        //-----------------------ASSIGNS q0------------------------------//

                newdfa.init_state=new nfastate();
                newdfa.init_state.no_state=1;
                newdfa.init_state.index=new int[1];
                newdfa.init_state.index[0]=init_state;

       //------------------------ASSIGNS Q----------------------------//

                newdfa.count_rstates=1;
                newdfa.rstates = new nfastate[newdfa.count_rstates];
                newdfa.rstates[0]=new nfastate();
                newdfa.rstates[0].no_state=1;
                newdfa.rstates[0].index=new int[newdfa.rstates[0].no_state];
                newdfa.rstates[0].index[0]=current[0];

        //-------------------CONVERSION-----------------------------//

                newdfa.tr_table = new nfastate[newdfa.count_rstates][newdfa.count_sigma];
                for(i=0;i<newdfa.count_rstates;i++)
                {
                        newdfa.tr_table=realloc(newdfa.tr_table,i,newdfa.count_sigma,i+1,newdfa.count_sigma);        
						for(j=0;j<newdfa.count_sigma;j++)
                        {
                                csize=newdfa.rstates[i].no_state;
                                for(t=0;t<csize;t++)
                                {
                                        current[t]=newdfa.rstates[i].index[t];
                                }
                                extend();
                                processchar(newdfa.sigma[j]);
                                for(k=0;k<nsize;k++)
                                {
                                        current[k]=next[k];
                                }
                                csize=nsize;nsize=0;
                                extend();
                                sort(current,csize);
                                newdfa.tr_table[i][j]=new nfastate();
                                newdfa.tr_table[i][j].no_state=csize;
                                newdfa.tr_table[i][j].index=new int[csize];
                                for(k=0;k<csize;k++)
                                {
                                        newdfa.tr_table[i][j].index[k]=current[k];
                                }
                                for(t=0;t<newdfa.count_rstates;t++)
                                {
                                        exist=issame(current,csize,newdfa.rstates[t].index,newdfa.rstates[t].no_state);
                                        if(exist)
                                                break;
                                }
                                if(csize==0)
                                        exist=true;
                                if(!exist)
                                {
                                        newdfa.rstates=realloc(newdfa.rstates,newdfa.count_rstates,++newdfa.count_rstates);
                                        newdfa.rstates[newdfa.count_rstates-1]=new nfastate();
                                        newdfa.rstates[newdfa.count_rstates-1].no_state=csize;
                                        newdfa.rstates[newdfa.count_rstates-1].index=new int[csize];
                                        for(m=0;m<csize;m++)
                                        {
                                                newdfa.rstates[newdfa.count_rstates-1].index[m]=current[m];
                                        }
                                }       // END OF IF
                        }        // END OF J FOR LOOP
                } //END OF I FOR LOOP

      //-------------------------FINAL STATES-----------------------//
                exist=false;
                for(i=0;i<newdfa.count_rstates;i++)
                {
                        exist=isany(newdfa.rstates[i].index,newdfa.rstates[i].no_state,fstates,count_fstates);
                        if(exist)
                        {
                                  newdfa.rfstates=realloc(newdfa.rfstates,newdfa.count_rfstates,++newdfa.count_rfstates);
								  newdfa.rfstates[newdfa.count_rfstates-1]=new nfastate();
                                  newdfa.rfstates[newdfa.count_rfstates-1].no_state=newdfa.rstates[i].no_state;
                                  newdfa.rfstates[newdfa.count_rfstates-1].index=new int[newdfa.rstates[i].no_state];
                                  for(m=0;m<newdfa.rstates[i].no_state;m++)
                                  {
                                        newdfa.rfstates[newdfa.count_rfstates-1].index[m]=newdfa.rstates[i].index[m];
                                  }
                        }
                }
                current[0]=init_state;csize=1;
                extend();
                exist=isany(current,csize,fstates,count_fstates);
                if(exist) 
                {
                        current[0]=init_state;csize=1;
                        for(i=0;i<newdfa.count_rfstates;i++)
                        {
                                boolean present=issame(newdfa.rfstates[i].index,newdfa.rfstates[i].no_state,current,csize);
                                if(present)
                                        return newdfa;
                        }
                        newdfa.rfstates=realloc(newdfa.rfstates,newdfa.count_rfstates,++newdfa.count_rfstates);
                        newdfa.rfstates[newdfa.count_rfstates-1]=new nfastate();
                        newdfa.rfstates[newdfa.count_rfstates-1].no_state=1;
                        newdfa.rfstates[newdfa.count_rfstates-1].index=new int[1];
                        newdfa.rfstates[newdfa.count_rfstates-1].index[0]=init_state;
                        return newdfa; 
                }
                return newdfa;
        }
} 
class NEWDFA
{
        public nfastate rstates[];
        public int count_rstates;
        public char sigma[];
        public int count_sigma;
        public nfastate tr_table[][];
        public nfastate init_state;
        public nfastate rfstates[];
        public int count_rfstates;
        public NEWDFA()
        {
                count_rfstates=0;
                count_rstates=0;
                count_sigma=0;
        }
        public DFA normalize()  //normalizes newdfa
        {
                int i,j,t;
                boolean present;
                DFA dfa = new DFA();
                dfa.count_states=count_rstates;
                dfa.states=new int[dfa.count_states];
                for(i=0;i<dfa.count_states;i++)
                {
                        dfa.states[i]=i;
                }
                dfa.count_sigma=count_sigma;
                dfa.sigma=new char[count_sigma];
                for(i=0;i<count_sigma;i++)
                {
                        dfa.sigma[i]=sigma[i];
                }
                dfa.init_state=0;
                dfa.tr_table=new int[dfa.count_states][dfa.count_sigma];
                for(t=0;t<count_rstates;t++)
                {
                        for(i=0;i<count_rstates;i++)
                        {
                                for(j=0;j<count_sigma;j++)
                                {
                                        present=NFA.issame(rstates[t].index,rstates[t].no_state,tr_table[i][j].index,tr_table[i][j].no_state);
                                        if(present==true)
                                                dfa.tr_table[i][j]=t;
                                        else
                                                if(tr_table[i][j].no_state==0)
                                                        dfa.tr_table[i][j]=-1;
                                }
                        }
                }
                dfa.count_fstates=count_rfstates;
                dfa.fstates=new int[dfa.count_fstates];
                for(t=0;t<count_rstates;t++)
                {
                        for(i=0;i<count_rfstates;i++)
                        {
                                present=NFA.issame(rstates[t].index,rstates[t].no_state,rfstates[i].index,rfstates[i].no_state);
                                if(present==true)
                                {
                                        dfa.fstates[i]=t;
                                        break;
                                }
                        }
                }
				NFA.sort(dfa.fstates,dfa.count_fstates);
                return dfa;
        }
}
class GTG
{
        public int count_states;
        public String graph[][];
        public GTG()
        {
                count_states=2;
                graph=new String[count_states][count_states];
                for(int i=0;i<count_states;i++)
                {
                        for(int j=0;j<count_states;j++)
                        {
                                graph[i][j]=new String("");
                        }
                }
        }
        public GTG(int x)
        {
                count_states=x;
                graph=new String[count_states][count_states];
                for(int i=0;i<count_states;i++)
                {
                        for(int j=0;j<count_states;j++)
                        {
                                graph[i][j]=new String("");
                        }
                }

        }
        public GTG(char ch)
        {
                count_states=2;
                graph=new String[count_states][count_states];
                for(int i=0;i<count_states;i++)
                {
                        for(int j=0;j<count_states;j++)
                        {
                                graph[i][j]=new String("");
                        }
                }
                graph[0][1]=new String(""+ch+"");
        }
        public GTG(DFA dfa)			// Constructs GTG from DFA
        {
                int i,j,current;
                count_states=dfa.count_states+1;
                graph=new String[count_states][count_states];
                for(i=0;i<count_states;i++)
                {
                        for(j=0;j<count_states;j++)
                        {
                                graph[i][j]=new String("");
                        }
                }
                for(i=0;i<dfa.count_states;i++)
                {
                        for(j=0;j<dfa.count_sigma;j++)
                        {
                                current=NFA.findindex(dfa.states,dfa.count_states,dfa.tr_table[i][j]);
                                if(current>=0)
                                {
                                        if(graph[i][current].equals(""))
                                                graph[i][current]=new String(dfa.sigma,j,1);
                                        else
                                                graph[i][current]=graph[i][current]+"+"+dfa.sigma[j];
                                }
                        }
                }
                for(i=0;i<dfa.count_fstates;i++)
                {
                        current=NFA.findindex(dfa.states,dfa.count_states,dfa.fstates[i]);
                        if(graph[current][count_states-1].equals(""))
                                graph[current][count_states-1]="\\";
                        else
                                graph[current][count_states-1].concat("+"+"\\");
                }
        }
        public NFA tonfa(char sigma[],int size)         //Constructs NFA from GTG
        {
                NFA nfa=new NFA();
                char c;
                int i,j;
                nfa.count_states=count_states;
                System.out.println();
                i=NFA.findindex(sigma,size,'\\');
                if(i>=0)
                {
                        c=sigma[size-1];
                        sigma[size-1]='\\';
                        sigma[i]=c;
                        NFA.sort(sigma,size-1);
                        nfa.count_sigma=size;
                        nfa.sigma=new char[nfa.count_sigma];
                 }
                 else
                 {
                        NFA.sort(sigma,size);
                        nfa.count_sigma=size+1;
                        nfa.sigma=new char[nfa.count_sigma];
                        nfa.sigma[nfa.count_sigma-1]='\\';
                  }
                  for(i=0;i<size;i++)
                  {
                        nfa.sigma[i]=sigma[i];
                  }
                  nfa.init_state=0;
                  nfa.count_fstates=1;
                  nfa.fstates=new int[nfa.count_fstates];
                  nfa.fstates[0]=count_states-1;
                  nfa.tr_table=new nfastate[nfa.count_states][nfa.count_sigma];
                  for(i=0;i<count_states;i++)
                  {
                        for(j=0;j<count_states;j++)
                        {
                                try
                                {
                                        for(int k=0;k<graph[i][j].length();k++)
                                        {
                                                c=graph[i][j].charAt(k);
                                                if(c=='+')
                                                        continue;
                                                int pos=NFA.findindex(nfa.sigma,nfa.count_sigma,c);
                                                if(nfa.tr_table[i][pos]==null)
                                                {
                                                        nfa.tr_table[i][pos]=new nfastate();
                                                }
                                                nfa.tr_table[i][pos].index=NFA.realloc(nfa.tr_table[i][pos].index,nfa.tr_table[i][pos].no_state,nfa.tr_table[i][pos].no_state+1);
                                                nfa.tr_table[i][pos].no_state++;
                                                nfa.tr_table[i][pos].index[nfa.tr_table[i][pos].no_state-1]=j;
                                        }
                                 }
                                 catch(Exception e)
                                 {
                                 }
                        }
                  }
                  for(i=0;i<nfa.count_states;i++)
                  {
                        for(j=0;j<nfa.count_sigma;j++)
                        {
                                if(nfa.tr_table[i][j]==null)
                                {
                                        nfa.tr_table[i][j]=new nfastate();
                                        nfa.tr_table[i][j].no_state=0;
                                        nfa.tr_table[i][j].index=new int[nfa.tr_table[i][j].no_state];
                                }
                        }
                  }
                  return nfa;
         }
}
class node
{
        public GTG gtg;
        public node next;
}
class stack
{
        private node top;
        public stack()
        {
                top=null;
        }
        public void push(GTG gtg)
        {
                node tmp;
                tmp=new node();
                tmp.gtg=gtg;
                tmp.next=top;
                top=tmp;
        }
        public GTG pop()
        {
                node tmp;
                if(top!=null)
                {
                        tmp=top;
                        top=top.next;
                        return tmp.gtg; 
                }
                return null;             
        }
        public boolean empty()
        {
                if(top==null)
                       return true;
                return false;
        }
}
class symbol
{
        char ch;
        symbol next;
}
class stack2
{
        private symbol top;
        public stack2()
        {
                top=null;
        }
        public void push(char c)
        {
                symbol tmp;
                tmp=new symbol();
                tmp.ch=c;
                tmp.next=top;
                top=tmp;
        }
        public char pop()
        {
                symbol tmp;
                tmp=top;
                top=top.next;
                return tmp.ch;           
        }
        public boolean empty()
        {
                if(top==null)
                      return true;
                return false;
        }
        public char stacktop()
        {
                char c=pop();
                push(c);
                return c;
        }
}
class RE
{
		/* Construction of R.E. from given DFA */

        public String fatore(DFA dfa)			//Constructs GTG from DFA and makes RE
        {
                String s=new String();
                GTG gtg1=new GTG(dfa);
                s= makere(gtg1);
                if(s.equals(""))
                        s="\\";
                return s;
        }
        public String fatore(NFA nfa)			//Constructs GTG from NFA and makes RE
        {
                String s=new String();
                NEWDFA nd=nfa.nfatodfa();
                DFA dfa=nd.normalize();
                dfa=dfa.minimize();
                GTG gtg1=new GTG(dfa);
                s=makere(gtg1);
                if(s.equals(""))
                        s="\\";
                return s;
        }
        public String makere(GTG gtg)			// Constructs Regular Expression from given GTG
        {
                String s;
                String s1,s2,s3,s4,s5,s6="";;
                int p,q,k,key=0;
                for(k=gtg.count_states-2;k>0;k--)
                {
                        for(p=0;p<gtg.count_states;p++)
                        {
                                for(q=0;q<gtg.count_states;q++)
                                {
                                        if(p!=k && q!=k)
                                        {
                                                if((p<k&&q<k)||(p==gtg.count_states-1 || q==gtg.count_states-1))
                                                {
                                                        s1=gtg.graph[p][q];
                                                        s2=gtg.graph[p][k];
                                                        s3=gtg.graph[k][k];
                                                        s4=gtg.graph[k][q];
                                                        if(s4.equals(""))
                                                                s5="";
                                                        else
                                                        {
                                                                if(s3.equals("\\")||s3.equals(""))
                                                                        s3="\\";
                                                                else
                                                                {
                                                                        if(s3.length()==1)
                                                                                s3=s3+"*";
                                                                        else
                                                                                s3="("+s3+")"+"*";
                                                                }
                                                                if(s2.equals(""))
                                                                        s5="";
                                                                else
                                                                {
                                                                        if(s2.equals("\\"))
                                                                                s2="";  
                                                                        if(s4.equals("\\"))
                                                                                s4="";
                                                                        if(s3.equals("\\"))
                                                                                s3="";
                                                                        if(present(s2,'+')&&s2.charAt(0)!='(')
                                                                                s2="("+s2+")";
                                                                        if(present(s4,'+')&&s4.charAt(0)!='(')
                                                                                s4="("+s4+")";
                                                                        s5=s2+s3+s4;
                                                                }
                                                        }
                                                        if(s5.equals(""))
                                                                s6=s1;
                                                        else
                                                        {
                                                                if(s1.equals(""))
                                                                        s6=s5;
                                                                else
                                                                        s6=s1+"+"+s5;
                                                        }
                                                        gtg.graph[p][q]=s6;
                                                        s6="";
                                                }
                                        }
                                }
                        }
                }
                s1=gtg.graph[0][0];
                s2=gtg.graph[0][gtg.count_states-1];
                s3=gtg.graph[gtg.count_states-1][0];
                s4=gtg.graph[gtg.count_states-1][gtg.count_states-1];
                if(s2.equals(""))
                        return "EMPTY SET ---> NO LANGUAGE IS ACCEPTED BY THIS FINITE AUTOMATON";
                else
                {
                        if(s1.equals("")||s1.equals("\\"))
                                s1="\\";
                        else
                        {
                                if(s1.length()==1)
                                        s1=s1+"*";
                                else
                                        s1="("+s1+")"+"*";
                        }
                        if(s3.equals(""))
                                s5="";
                        else
                        {
                                if(s3.equals("\\"))
                                        s3="";
                                if(s1.equals("\\"))
                                        s1="";
                                if(s2.equals("\\"))
                                        s2="";
                                if(present(s3,'+')&&s3.charAt(0)!='(')
                                        s3="("+s3+")";
                                if(present(s2,'+')&&s2.charAt(0)!='(')
                                        s2="("+s2+")";                                   
                                s5=s3+s1+s2;
                        }
                        if(s5.equals(""))
                                s6=s4;
                        else
                                if(s4.equals(""))
                                        s6=s5;
                                else
                                        s6=s4+"+"+s5;
                        if(s6.equals("")||s6.equals("\\"))
                                s6="";
                        else
                                if(s6.length()==1)
                                        s6=s6+"*";
                                else
                                        s6="("+s6+")"+"*";
                        if(s1.equals("\\"))
                                s1="";
                        if(s2.equals("\\"))
                                s2="";
                        if(present(s1,'+') && s1.charAt(0)!='(')
                                s1="("+s1+")";
                        if(present(s2,'+')&&s2.charAt(0)!='(')
                                s2="("+s2+")";                                   
                        s=new String(s1+s2+s6);
                }
                return s;
        }

		/* Construction of NFA from given R.E.*/
        public NFA retofa(String s1)  //Constructs NFA from given Regular Expression
        {
                GTG g1,g2,g;
                String st;
                char c,sigma[]=new char[0];;
                int size=0,i=0,l;
                stack s=new stack();
                try							// First Make a GTG(Generalized Transition Graph) for whole R.E.
                {
                        st=insertdot(s1);
                        st=postfix(st);
                        if(st.equals(""))
                        {
                                g=new GTG();                        
                                return g.tonfa(sigma,size);
                        }
                        sigma=new char[st.length()];
                        l=st.length();
                        while(i<l)
                        {
                                c=st.charAt(i++);
                                if(c=='.'||c=='+')
                                {
                                        g2=s.pop();
                                        g1=s.pop();
                                        g=combine(g1,g2,c);
                                        s.push(g);
                                }
                                else
                                {
                                        if(c=='*')
                                        {
                                                g1=s.pop();
                                                g=combine(g1);
                                                s.push(g);
                                        }
                                        else
                                        {
                                                GTG gtg=new GTG(c);
                                                s.push(gtg);
                                                if(NFA.findindex(sigma,size,c)==-1)
                                                sigma[size++]=c;
                                        }
                                }
                        } 
                        g=s.pop();
                }
                catch(NullPointerException e)
                {
                        return null;
                }
                if(!s.empty())
                        return null;
                return g.tonfa(sigma,size);					//Now make a NFA from above found GTG.
        }
        public GTG combine(GTG g1,GTG g2,char c)
        {
                GTG g=new GTG();
                int i,j;
                g.count_states=g1.count_states+g2.count_states+2;
                g.graph=new String[g.count_states][g.count_states];
                for(i=0;i<g1.count_states;i++)
                {
                        for(j=0;j<g1.count_states;j++)
                        {
                                g.graph[i+1][j+1]=g1.graph[i][j];
                        }
                }
                for(i=0;i<g2.count_states;i++)
                {
                        for(j=0;j<g2.count_states;j++)
                        {
                                g.graph[i+1+g1.count_states][j+1+g1.count_states]=g2.graph[i][j];
                        }
                }
                if(c=='.')
                {
                        g.graph[0][1]="\\";
                        g.graph[g1.count_states][g1.count_states+1]="\\";
                        g.graph[g.count_states-2][g.count_states-1]="\\";
                        return g;
                }
                else
                {
                        g.graph[0][1]="\\";
                        g.graph[0][g1.count_states+1]="\\";
                        g.graph[g1.count_states][g.count_states-1]="\\";
                        g.graph[g.count_states-2][g.count_states-1]="\\";
                        return g;
                 }
         }
         public GTG combine(GTG g1)
         {
                int i,j;
                GTG g=new GTG();
                g.count_states=g1.count_states+2;
                g.graph=new String[g.count_states][g.count_states];
                for(i=0;i<g1.count_states;i++)
                {
                        for(j=0;j<g1.count_states;j++)
                        {
                                g.graph[i+1][j+1]=g1.graph[i][j];
                        }
                }
                g.graph[0][1]="\\";
                g.graph[0][g.count_states-1]="\\";
                g.graph[g.count_states-2][g.count_states-1]="\\";
                g.graph[g.count_states-1][0]="\\";
                return g;
        }
        public String insertdot(String s)
        {
                String st;
                char ch;
                int l=s.length(),i,k=0,key=0,key2=0;
                char arr[]=new char[2*l];
                int size=0;
                for(i=0;i<l;i++)
                { 
                        ch=s.charAt(i);
                        if((ch<='z' && ch>='a')||(ch<='Z' && ch>='A')||(ch<='9' && ch>='0')||ch=='\\')
                        {
                                if(key==1&&key2==1)
                                {
                                        arr[size++]='.';
                                }
                                arr[size++]=ch;
                                key=1;key2=1;
                        }
                        if(ch=='+')
                        {
                                arr[size++]=ch;
                                key=0;
                        }
                        if(ch=='*')
                        {
                                arr[size++]=ch;
                                key=1;
                        }
                        if(ch=='(')
                        {
                                if(key==1&&key2==1)
                                {
                                        arr[size++]='.';key=0;key2=0;
                                }
                                arr[size++]=ch;
                        }                
                        if(ch==')')
                        {
                                arr[size++]=ch;
                                if(key==0)
                                {
                                        key=1;
                                }
                        }                        
                }
                st=new String(arr,0,size);
                return st;
        }
        public boolean present(String s,char c)
        {
                for(int i=0;i<s.length();i++)
                {
                        if(c==s.charAt(i))
                                return true;
                }
                return false;
         }
         public String postfix(String st)
         {
                stack2 s=new stack2();
                char c;
                int l=st.length(),i=0,k=0;
                char post[]=new char[l];
                while(i<l)
                {
                        c=st.charAt(i++);
                        if(c=='.'||c=='*'||c=='+'||c=='('||c==')')
                        {
                                while(!s.empty()&&prcd(s.stacktop(),c))
                                {
                                        post[k++]=s.pop();
                                }
                                if(s.empty()&&c==')')
                                        return null;
                                if(s.empty()||c!=')')
                                        s.push(c);
                                else
                                        s.pop();
                        }
                        else  
                                post[k++]=c;
                 }
                 while(!s.empty())
                 {
                        char ch=s.pop();
                        if(ch=='(')
                                return null;
                        post[k++]=ch;
                 }
                 String s2=new String(post,0,k);
                 return s2;
        }
        public boolean prcd(char c1,char c2)
        {
                if(c2=='(')
                        return false;
                if(c1=='(')
                        return false;
                if(c2==')'&&c1!='(')
                        return true;
                if(c1=='*')
                        return true;
                if(c1=='.'&&c2!='*')
                        return true;
                if(c1=='+'&&c2=='+')
                        return true;
                return false;
        }
}
class tr_fn
{
        public  int state;
        public  char output;
        public tr_fn()
        {
                state=-1;
                output='-';
        }
}
class Mealy extends DFA
{
        public tr_fn ml_table[][];
        public Mealy()
        {
                super();
        }
        public String process2(String s)
        {
                char c;
                String st=new String();
                int i=0,j,l,pos;
                tr_fn current = new tr_fn();
                current.state = init_state;
                l = s.length();
                try
                {
                        System.out.print("path of Mealy machine is q"+init_state);
                        for(i=0;i<l;i++)
                        {
                                c=s.charAt(i);
                                pos=NFA.findindex(sigma,count_sigma,c);
                                current=ml_table[NFA.findindex(states,count_states,current.state)][pos];
                                System.out.print("---->q"+current.state);
                                st=st+current.output;
                        }
                        return st;
                }
                catch(Exception e)
                {
                        return "Invalid string ";
                }
        }
        public Moore mltomr(Mealy ml)
        {
                move split[]=new move[ml.count_states];
                for(int i=0;i<ml.count_states;i++)
                {
                        split[i]=new move();
                }
                for(int i=0;i<ml.count_states;i++)
                {
                        for(int j=0;j<ml.count_sigma;j++)
                        {
                                char c=ml.ml_table[i][j].output;
                                int pos=NFA.findindex(ml.states,ml.count_states,ml.ml_table[i][j].state);
                                split[pos].state=ml.ml_table[i][j].state;
                                split[pos].pushstring=split[pos].pushstring+c;
                        }
                }
                for(int i=0;i<ml.count_states;i++)
                {
                        System.out.println("q"+split[i].state+"-->"+split[i].pushstring);
                }
                return null;
        }
}
class Moore extends DFA
{
        public char mr_op[];
        public Moore()
        {
               super();
        }
        public static Mealy MrtoMl(Moore mr)
	{
		int i,j,pos;
                Mealy nml = new Mealy();
                nml.count_states=mr.count_states;
                nml.states=new int[nml.count_states];
                for(i=0;i<nml.count_states;i++)
                {
                        nml.states[i]=mr.states[i];
                }
                nml.count_sigma=mr.count_sigma;
                nml.sigma=new char[nml.count_sigma];
                for(i=0;i<nml.count_sigma;i++)
                {
                        nml.sigma[i]=mr.sigma[i];
                }
                nml.init_state=mr.init_state;
                nml.ml_table=new tr_fn[nml.count_states][nml.count_sigma];
		try
		{
                        for(i=0;i<mr.count_states;i++)
                        {
                                for(j=0;j<mr.count_sigma;j++)
                                {
                                        nml.ml_table[i][j]=new tr_fn();
                                        nml.ml_table[i][j].state=mr.tr_table[i][j];
                                        pos=NFA.findindex(mr.states,mr.count_states,mr.tr_table[i][j]);
                                        nml.ml_table[i][j].output = mr.mr_op[pos];
                                }
                        }
                        return nml;
		}
		catch(Exception e)
		{
                        return null;
		}
	}
        public String process1(String s)   //process string of characters
        {
                char c;
                int i=0,j,l,pos;
                int current;
                current=init_state;
                l=s.length();
                String output=new String();
                try
                {
                        System.out.print("path of moore machine is q"+init_state);
                        output=output+mr_op[init_state];
                        for(i=0;i<l;i++)
                        {
                                c=s.charAt(i);
                                pos=NFA.findindex(sigma,count_sigma,c);
                                current=tr_table[NFA.findindex(states,count_states,current)][pos];
                                System.out.print("---->"+"q"+current);
                                output=output+mr_op[current];
                        }
                        return output;             
		}
                catch(Exception e)
		{
                        return "INVALID STRING";
		}
	}
}
class prod
{
        String choices[];
        int no_choices;
        public prod()
        {
                no_choices=0;
        }
}
class sentencialform
{
        sentencialform father;
        int index;
        StringBuffer s;
        int prono;
        sentencialform child;
        public sentencialform()
        {
                child=father=null;
                index=prono=0;
        }
}      
class GRAMMAR 
{
		int TYPE;
        String vertex[];
        int count_vertex;
        char sigma[];
        int count_sigma;
        String start;
        prod productions[];
		int irange;
		public GRAMMAR()
        {
                count_vertex=0;
                count_sigma=0;
        }
		public GRAMMAR(DFA dfa)         //Constructs Grammer from DFA
        {
                int i,j,next,x,y;
                String s=new String();
                char c='A';
                TYPE=3;
                count_vertex=dfa.count_states;
                vertex=new String[count_vertex];
                for(i=0;i<count_vertex;i++)
                {
                        if(dfa.states[i]!=dfa.init_state)
                        {
                                vertex[i]=new String(""+c);    c++;
                        }
                        else
                        {
                                start=vertex[i]=new String("S");
                        }
                }
                count_sigma=dfa.count_sigma;
                sigma=new char[count_sigma];
                for(i=0;i<count_sigma;i++)
                {
                        sigma[i]=dfa.sigma[i];
                }
                productions=new prod[count_vertex];
                for(i=0;i<dfa.count_states;i++)
                {
                        productions[i]=new prod();
                        for(j=0;j<dfa.count_sigma;j++)
                        {
                                next=dfa.tr_table[i][j];
                                if(next>=0)
                                {
                                       y=NFA.findindex(dfa.states,dfa.count_states,next);
                                       s=vertex[y];
                                       productions[i]=GRAMMAR.add(productions[i],dfa.sigma[j]+""+s);
                                }
                        }
                }
                for(i=0;i<dfa.count_fstates;i++)
                {
                        y=NFA.findindex(dfa.states,dfa.count_states,dfa.fstates[i]);
                        productions[y]=GRAMMAR.add(productions[y],"\\");
                }
        }
		public NFA makenfa()      //Construct NFA from GRAMMAR
        {
                int i,j,k,x,y,z,u,t;
                char c;
                String V,s=new String();
                NFA nfa=new NFA();
                nfa.count_states=count_vertex+1;
                NFA.sort(sigma,count_sigma);
                nfa.count_sigma=count_sigma+1;
                nfa.sigma=new char[nfa.count_sigma];
                for(i=0;i<nfa.count_sigma-1;i++)
                {
                        nfa.sigma[i]=sigma[i];
                }
                nfa.sigma[i]='\\';
                nfa.init_state=NFA.findindex(vertex,count_vertex,start);
                nfa.tr_table=new nfastate[nfa.count_states][nfa.count_sigma];
                for(i=0;i<nfa.count_states;i++)
                {
                        for(j=0;j<nfa.count_sigma;j++)
                        {
                                nfa.tr_table[i][j]=new nfastate();
                        }
                }
                nfa.count_fstates=1;
                nfa.fstates=new int[1];
                nfa.fstates[0]=nfa.count_states-1;
                for(i=0;i<count_vertex;i++)
                {
                        for(j=0;j<productions[i].no_choices;j++)
                        {
                                s=productions[i].choices[j];
                                x=s.length();
                                if(x>0)
                                {
                                        c=s.charAt(x-1);
										V=new String(""+c);
                                        t=NFA.findindex(vertex,count_vertex,V);
                                        if(t==-1)
                                                t=nfa.fstates[0];
                                        if(x==1)
                                        {
                                                k=NFA.findindex(sigma,count_sigma,c);
                                                if(k<0)
                                                        k=nfa.count_sigma-1;
                                                y=nfa.tr_table[i][k].no_state;
                                                nfa.tr_table[i][k].index=NFA.realloc(nfa.tr_table[i][k].index,y,y+1);
                                                nfa.tr_table[i][k].no_state++;
                                                nfa.tr_table[i][k].index[y]=t;
                                                
                                        }
                                        if(x==2)
                                        {
                                                c=s.charAt(0);
                                                k=NFA.findindex(nfa.sigma,nfa.count_sigma,c);
                                                y=nfa.tr_table[i][k].no_state;
                                                nfa.tr_table[i][k].index=NFA.realloc(nfa.tr_table[i][k].index,y,y+1);
                                                nfa.tr_table[i][k].no_state++;
                                                nfa.tr_table[i][k].index[y]=t;
                                        }
                                        if(x>2)
                                        {
                                                int w=nfa.count_states;
                                                nfa.tr_table=NFA.realloc(nfa.tr_table,nfa.count_states,nfa.count_sigma,nfa.count_states+x-2,nfa.count_sigma);
                                                nfa.count_states=nfa.count_states+x-2;
                                                u=i;
                                                for(z=0;z<x-1;z++)
                                                {
                                                        c=s.charAt(z);
                                                        k=NFA.findindex(nfa.sigma,nfa.count_sigma,c);
                                                        y=nfa.tr_table[u][k].no_state;
                                                        nfa.tr_table[u][k].index=NFA.realloc(nfa.tr_table[u][k].index,y,y+1);
                                                        nfa.tr_table[u][k].no_state++;
                                                        if((w+z)==nfa.count_states)
                                                                nfa.tr_table[u][k].index[y]=t;
                                                        else
                                                                nfa.tr_table[u][k].index[y]=w+z;
                                                        u=w+z;
                                                }
                                        }
                                }
                        }
                }
                return nfa;
        }
		public int addprod(char V,String s)
        {	
			/*
			return value 0 : Duplicate Production Enetered
			1 : Empty String
			2 : Invalid Production Entered
			3. Successfully entered
			*/
            int x,y,z,l=s.length();
            char c;
			x=NFA.findindex(vertex,count_vertex,""+V);
			if(findindex(productions[x],s))
		 			return 0;				// Duplicate Production entered.
            if(l==0)
					return 1;              // Empty String.
			if(l==1)
			{
					c=s.charAt(0);
					if(c=='\\')
							productions[x]=add(productions[x],"\\");
					else
					{
							y=NFA.findindex(vertex,count_vertex,""+c);
							if(y<0)
									y=NFA.findindex(sigma,count_sigma,c);
							if(y<0)
									return 2;				// Invalid Production Entered.
							productions[x]=add(productions[x],s);
					}
					return 3;								// Successfully Entered
			}

		/* -------------- For Right Linear Grammar ------------------------ */
			if(l>=2&&TYPE==3)
			{
					for(int i=0;i<l-1;i++)
					{
							c=s.charAt(i);
							y=NFA.findindex(sigma,count_sigma,c);
							if(y<0)
									return 2;				// Invalid production entered	
					}
					c=s.charAt(l-1);
					y=NFA.findindex(sigma,count_sigma,c);
					if(y<0)
							y=NFA.findindex(vertex,count_vertex,""+c);
					if(y<0)
							return 2;				// Invalid Production Entered.
					productions[x]=add(productions[x],s);
			}
		/* ----------------For Context-free-grammar -------------------------- */
			if(l>=2&&TYPE==2)		
			{
					for(int i=0;i<l;i++)
					{
							c=s.charAt(i);
							y=NFA.findindex(sigma,count_sigma,c);
							if(y<0)
									y=NFA.findindex(vertex,count_vertex,""+c);
							if(y<0)
									return 2;				// Invalid production entered	
					}
					productions[x]=add(productions[x],s);
			}
			return 3;					// Successfully Entered						
        }
		public sentencialform parse(String s)
        {
                char c,V;
				String text;
                prod tmp;
                int max=s.length(),pos,k=0,t,i;
                StringBuffer st;
                String s1;
                sentencialform root=new sentencialform();
                sentencialform current,next;
                root.s=new StringBuffer(""+start+"");
                current=root;
				while(current!=null)
                {
                        next=null;
						if(s.equals(current.s.substring(0)))
								return root;
						try
						{
								while(s.charAt(current.index)==current.s.charAt(current.index))
										current.index++;
								V=current.s.charAt(current.index);
								if(isterminal(V))
								{
										current.child=null;
										current=current.father;
										continue;
								}
								else
								{
										text=new String(""+V);
										while(current.index<current.s.length()&&current.s.charAt(current.index)=='\'')
										{
												text+='\'';
										}
								}
								pos=NFA.findindex(vertex,count_vertex,text);
								tmp=productions[pos];	
						}
						catch(Exception e)
						{
								current.child=null;
                                current=current.father;
								continue;								
						}
                        while(current.prono<tmp.no_choices)
                        {
								s1=tmp.choices[current.prono++];   k=0;
							    if(current.s.length()-1+s1.length()>max)
                                        continue;
                                while(k<s1.length()&&s1.charAt(k)==s.charAt(current.index+k))
										k++;
                                if(k==s1.length())
                                {
                                        next=new sentencialform();
                                        next.s=new StringBuffer(current.s);
										next.s.replace(current.index,current.index+1,s1);
                                        next.index=current.index+k;
                                        current.child=next;  next.father=current; current=next;
                                        break;
                                }
                                if((s1.charAt(k)>='A'&&s1.charAt(k)<='Z'))
                                {
                                        next=new sentencialform();
                                        next.s=new StringBuffer(current.s);
                                        next.index=current.index+k;
                                        next.s.replace(current.index,current.index+1,s1);
                                        current.child=next;  next.father=current; current=next;
                                        break;
                                }
                        }
                        if(next==null)
                        {
                                current.child=null;
                                current=current.father;
                        }
                }
                return null;
        }
		public GRAMMAR tochomskey(GRAMMAR g)
		{
				String s=new String();
				String tmp=new String();
				StringBuffer st;
				char c='A';
				int prime=1,p;
				irange=count_vertex=g.count_vertex;
				vertex=new String[count_vertex];
				for(int i=0;i<count_vertex;i++)
				{
						vertex[i]=new String(""+g.vertex[i]);
				}
				count_sigma=g.count_sigma;
				sigma=new char[count_sigma];
				for(int i=0;i<count_sigma;i++)
				{
						sigma[i]=g.sigma[i];
				}
				start=new String(""+g.start);
				productions=new prod[count_vertex];
				for(int i=0;i<g.count_vertex;i++)
				{
						productions[i]=new prod();
						for(int j=0;j<g.productions[i].no_choices;j++)
						{
								productions[i]=GRAMMAR.add(productions[i],g.productions[i].choices[j]);
						}
				}							
				for(int i=0;i<count_vertex;i++)
				{
						for(int j=0;j<productions[i].no_choices;j++)
						{
								s=productions[i].choices[j];
								st=new StringBuffer(s);	
								if(s.length()>2)
								{
										p=exists(s.substring(1));
										if(p==-1||p<irange)
										{
												if(c=='Z')
												{
														prime++;
												}
												tmp=""+c;
												for(int x=0;x<prime;x++)
												{
														tmp = tmp+ "'";
												}
												c++;
												st.replace(1,s.length(),tmp);
												add(tmp,s.substring(1));
										}
										else
										{
												st.replace(1,s.length(),vertex[p]);
										}
										productions[i].choices[j]=new String(st.substring(0));
								}
						}
				}
				for(int i=0;i<count_vertex;i++)
				{
						for(int j=0;j<productions[i].no_choices;j++)
						{
								s=productions[i].choices[j];
								if(s.length()>1)
								{
										for(int k=0;k<s.length();k++)
										{
												if(GRAMMAR.isterminal(s.charAt(k)))
												{
														p=exists(""+s.charAt(k));
														st=new StringBuffer(s);
														if(p==-1||p<irange)
														{
																if(c=='Z')
																{
																		prime++;
																}
																tmp=""+c;
																for(int x=0;x<prime;x++)
																{
																		tmp = tmp+ "'";
																}
																c++;
																st.replace(k,k+1,tmp);
																add(tmp,""+s.charAt(k));
														}
														else
																st.replace(k,k+1,vertex[p]);
														s=st.substring(0);
												}
										}
										productions[i].choices[j]=new String(s);
								}
						}
				}
				for(int i=0;i<count_vertex;i++)
				{
						for(int j=0;j<productions[i].no_choices;j++)
						{
								s=productions[i].choices[j];
								if(s.length()==1)
								{
										productions[i]=GRAMMAR.add(productions[i],s);
								}
						}
				}
				return this;
		}
		public GRAMMAR simplify()
		{
				int k=0,x,y,index,prime=0;
				char c='A';
				GRAMMAR ck=new GRAMMAR();
				String tmp=new String(),st=new String();
				int count_vt=count_vertex;
				String vt[]=new String[count_vertex];
				ck.count_vertex=1;
				ck.sigma=new char[count_sigma];
				ck.count_sigma=count_sigma;
				for(int i=0;i<count_sigma;i++)
						ck.sigma[i]=sigma[i];
				ck.start=start;
				ck.productions=new prod[count_vertex];
				for(int i=0;i<count_vertex;i++)
				{
						ck.productions[i]=new prod();
						vt[i]=new String("");
						//System.out.print(vertex[i]+"   ");
				}
				vt[0]=new String("S");
				for(int i=0;i<count_vertex;i++)
				{
						for(int j=0;j<productions[i].no_choices;j++)
						{
								k=0;index=0;
								StringBuffer s=new StringBuffer(productions[i].choices[j]);
								if(s.charAt(0)!='[')
										k++;
								while(k<s.length())
								{
										st=new String("");		x=0;
										do
										{
												st+=s.charAt(k);
												k++;x++;
										}while(k<s.length()&&s.charAt(k)!='[');
										index=NFA.findindex(vertex,count_vertex,st);										
										if(vt[index].equals(""))
										{
												tmp=new String(""+c);
												c++;
												for(int t=0;t<prime;t++)
														tmp+="'";
												if(c=='S')	
														c++;	
												if(c=='Z')
												{
														c='A';	prime++;
												}
												vt[index]=new String(tmp);
											//	System.out.print(st+" (NULL)---> ");
												s.replace(k-x,k,tmp);
											//	System.out.println(tmp);
												ck.count_vertex++;
										}
										else
										{
												tmp=new String(vt[index]);
											//	System.out.print(st+" (NOT NULL)---> ");
												s.replace(k-x,k,tmp);
											//	System.out.println(vt[index]);
										}
										k=k-x+tmp.length();
								}
								ck.productions[i]=GRAMMAR.add(ck.productions[i],s.substring(0));							
						}
				}
				ck.vertex=new String[ck.count_vertex];
				ck.vertex[0]=new String("S");
				for(int i=1;i<count_vt;i++)
				{
						if(vt[i]!=null)
						{
								ck.vertex[i]=new String(vt[i]);
						}
				}
				return ck;
		}
		public static prod realloc(prod arr)
        {
                int l,m;
                prod tmp=new prod();
                try
                {
                        tmp.no_choices=arr.no_choices;
                }
                catch(NullPointerException e)
                {
                        tmp.no_choices=0;
                }
                tmp.choices=new String[tmp.no_choices];
                for(m=0;m<tmp.no_choices;m++)
                {
                        tmp.choices[m]=new String(arr.choices[m]);
                }
                arr=new prod();
                arr.no_choices=tmp.no_choices+1;
                arr.choices=new String[arr.no_choices];
                for(m=0;m<arr.no_choices-1;m++)
                {
                        arr.choices[m]=new String(tmp.choices[m]);
                }
                return arr;
        }
        public static prod add(prod arr,String s)
        {
                int k;
                try
                {
                        k=arr.no_choices;
                        for(int i=0;i<k;i++)
                        {
                                if(arr.choices[i].equals(s))
                                        return arr;
                        }
                }
                catch(NullPointerException e)
                {
                        k=0;
                }
				//if(s.equals("\\"))
                  //      return arr;
                for(int i=0;i<k;i++)
                {
                        if(findindex(arr,s))
                                return arr;
                }
                arr=realloc(arr);
                arr.choices[arr.no_choices-1]=new String(s);
                return arr;
        }
        public static boolean findindex(prod arr,String s)
        {
                int n=arr.no_choices;
                for(int i=0;i<n;i++)
                {
                        if(arr.choices[i].equals(s))
                                return true;
                }
                return false;
        }
        public static boolean isterminal(String s)		// Terminal symbols can not be block letter or '\'' or '\`' 
        {
                char c;
				if(s.length()==0)
						return false;
                for(int i=0;i<s.length();i++)
                {
                        c=s.charAt(i);
                        if(!isterminal(c))
                                return false;
                }
                return true;
        }
        public static boolean isterminal(char c)	// Terminal symbol can not be block letter or '\'' or '\`' 
        {
                if((c>='A'&&c<='Z')||c=='|'||c=='\''||c=='`')
						return false;
                return true;
        }
		public static boolean isvertex(String s)	//Non-Terminal Symbols can be just capital letters.
		{
				char c;
				if(s.length()==0)
						return false;
				for(int i=0;i<s.length();i++)
                {
                        c=s.charAt(i);
                        if(!(c>='A'&&c<='Z'))
                                return false;
                }
                return true;
		}
		public static boolean isdistinct(String s)		// Given String is distinct or not.
		{
				int l=s.length();
				for(int i=0;i<l-1;i++)
				{
						for(int j=i+1;j<l;j++)
						{
								if(s.charAt(i)==s.charAt(j))
								     return false;				
						}
				}
				return true;
		}
		public GRAMMAR removenull()
        {
                boolean carryon=false;
                String nullable[]=new String[count_vertex];
                int count_nullable=0,i,j,k;
                String s;
                System.out.print("\nNullable Vertices are : --->");
                do
                {
                carryon=false;
                for(i=0;i<count_vertex;i++)
                {
                         if(NFA.findindex(nullable,count_nullable,vertex[i])!=-1)
                                continue;
                         k=productions[i].no_choices;
                         for(j=0;j<k;j++)
                         {
                                if(productions[i].choices[j].equals("\\"))
                                {
                                        nullable[count_nullable]=new String(vertex[i]);
                                        count_nullable++;
                                        System.out.print(vertex[i]+",");
                                        carryon=true;
                                        break;
                                }
                                if(iscontained(productions[i].choices[j],nullable,count_nullable))
                                {
                                        nullable[count_nullable]=new String(vertex[i]);
                                        count_nullable++;
                                        System.out.print(vertex[i]+",");
                                        carryon=true;
                                        break;
                                }
                         }
                }
                }while(carryon==true);
                System.out.print("\n");
                if(count_nullable==0)
                        return this;
                GRAMMAR g=new GRAMMAR();
                g.count_vertex=count_vertex;
                g.vertex=new String[g.count_vertex];
                for(i=0;i<g.count_vertex;i++)
                        g.vertex[i]=new String(vertex[i]);
                g.count_sigma=count_sigma;
                g.sigma=new char[g.count_sigma];
                for(i=0;i<g.count_sigma;i++)
                g.sigma[i]=sigma[i];
                g.start=new String(start);
                g.productions=new prod[g.count_vertex];
                for(i=0;i<count_vertex;i++)
                {
                        g.productions[i]=new prod();
                        for(j=0;j<productions[i].no_choices;j++)
                        {
                                nfastate get;
                                s=new String(productions[i].choices[j]);
                                get=getplaces(s,nullable,count_nullable);
                                if(get.no_state==0)
                                {
										//System.out.println("\nS --> "+s);
										if(s.equals("\\"))
												continue;
                                        g.productions[i]=GRAMMAR.add(g.productions[i],s);
                                        continue;
                                }
                                int status[]=new int[get.no_state];
                                int range=get.no_state-1;
                                for(k=0;k<get.no_state;k++)
                                {
                                        status[k]=0;
                                }
                                while(status[0]<2)
                                {
                                        String st=new String(permute(s,status,get));
										//System.out.println("\nSt --> "+st);
										if(st.equals("\\"))
												break;
                                        g.productions[i]=GRAMMAR.add(g.productions[i],st);
                                        status[range]++;
                                        while(range>0&&status[range]==2)
                                        {
                                                status[range]=0;
                                                range--;
                                                status[range]++;
                                        }
                                        range=get.no_state-1;
                                }
                        }
                }
                return g;
        }
		public GRAMMAR removeunit()
        {
                boolean carryon;
				String st;
                int depen[][]=new int[count_vertex][count_vertex];
                int unit[]=new int[count_vertex];
                int count_unit=0,k=0;
                for(int i=0;i<count_vertex;i++)
                {
                        for(int j=0;j<count_vertex;j++)
                        {
                                depen[i][j]=0;
                        }
                }
                for(int i=0;i<count_vertex;i++)
                {
                        for(int j=0;j<productions[i].no_choices;j++)
                        {
                                String s=productions[i].choices[j];
								if(!(s.charAt(0)>='A'&&s.charAt(0)<='Z'))
										continue;
								st=""+s.charAt(0);		k=1;		
								while(k<s.length()&&s.charAt(k)=='\'')
								{
										st+=s.charAt(k);	k++;
								}
                                if(k==s.length())
                                {
                                        depen[i][NFA.findindex(vertex,count_vertex,st)]=1;
                                        if(NFA.findindex(unit,count_unit,i)==-1)
                                                unit[count_unit++]=i;
                                }
                        }
                }
                GRAMMAR g=new GRAMMAR();
                g.count_vertex=count_vertex;
                g.vertex=new String[g.count_vertex];
                for(int i=0;i<g.count_vertex;i++)
                        g.vertex[i]=new String(vertex[i]);
                g.count_sigma=count_sigma;
                g.sigma=new char[g.count_sigma];
                for(int i=0;i<g.count_sigma;i++)
                        g.sigma[i]=sigma[i];
                g.start=new String(start);
                g.productions=new prod[g.count_vertex];
                for(int i=0;i<count_vertex;i++)
                {
                        g.productions[i]=new prod();
                        for(int j=0;j<productions[i].no_choices;j++)
                        {
                                String s=productions[i].choices[j];
								st=""+s.charAt(0);	k=1;
								if(!(s.charAt(0)>='A'&&s.charAt(0)<='Z'))
								{
										g.productions[i]=GRAMMAR.add(g.productions[i],s);
										continue;
								}
								while(k<s.length()&&s.charAt(k)=='\'')
								{
										st+=s.charAt(k);	k++;
								}
                                if(k<s.length())
                                {
                                        g.productions[i]=GRAMMAR.add(g.productions[i],s);
                                }
                        }
                }
                for(int i=0;i<count_unit;i++)
                {
                        int count_derive=0,derive[]=new int[count_vertex];
                        for(int j=0;j<count_vertex;j++)
                        {
                                if(depen[unit[i]][j]==1)
                                {
                                        derive[count_derive++]=j;
                                }
                        }
                        for(int j=0;j<count_derive;j++)
                        {
                                int tmp3=derive[j];
                                for(k=0;k<count_vertex;k++)
                                {
                                        int tmp2=depen[tmp3][k];
                                        if(tmp2==0)
                                                continue;
                                        if(NFA.findindex(derive,count_derive,k)==-1)
                                                derive[count_derive++]=k;
                                }
                        }
                        System.out.print("\n"+vertex[unit[i]]+" ---> ");
                        for(int j=0;j<count_derive;j++)
                        {
                                System.out.print(vertex[derive[j]]+" ");
                                for(k=0;k<g.productions[derive[j]].no_choices;k++)
                                {
                                        g.productions[unit[i]]=GRAMMAR.add(g.productions[unit[i]],g.productions[derive[j]].choices[k]);        
                                }
                        }
                }
                return g;
        }
		public GRAMMAR removeuseless()
        {
                String tmp_vertex[]=new String[count_vertex];
                int count_tmpvertex=0;
                char tmp_sigma[]=new char[count_sigma];
                int count_tmpsigma=0;
                boolean carryon=false;
                String used_symbols[]=new String[count_vertex+count_sigma];
                int count_usedsymbols=0;
                for(int i=0;i<count_sigma;i++)
                {
                        used_symbols[count_usedsymbols++]=new String(""+sigma[i]);
                }
                for(int i=0;i<count_vertex;i++)
                {
                        for(int j=0;j<productions[i].no_choices;j++)
                        {
                                if(GRAMMAR.isterminal(productions[i].choices[j]))
                                {
                                        used_symbols[count_usedsymbols++]=new String(vertex[i]);
                                        break;
                                }
                        }
                }
                do
                {
                        carryon=false;
                        for(int i=0;i<count_vertex;i++)
                        {
                                if(NFA.findindex(used_symbols,count_usedsymbols,vertex[i])==-1)
                                for(int j=0;j<productions[i].no_choices;j++)
                                {
                                        if(iscontained(productions[i].choices[j],used_symbols,count_usedsymbols))
                                        {
                                                used_symbols[count_usedsymbols++]=new String(vertex[i]);
                                                carryon=true;
                                                break;
                                        }
                                }
                        }
                }while(carryon);
                for(int i=count_sigma;i<count_usedsymbols;i++)
                {
                        tmp_vertex[count_tmpvertex++]=new String(used_symbols[i]);
                }
                System.out.print("Vertices giving terminal strings are : ");
                for(int i=0;i<count_tmpvertex;i++)
                {
                        System.out.print(" "+tmp_vertex[i]);
                }
                String derive[]=new String[count_vertex];
                int count_derive=0;
                derive[count_derive++]=new String(start);
                for(int i=0;i<count_derive;i++)
                {
                        int tmp=NFA.findindex(vertex,count_vertex,derive[i]);
						String st;
                        for(int j=0;j<productions[tmp].no_choices;j++)
                        {
                                String s=productions[tmp].choices[j];
								int k=0;
								int m=count_derive; 	
                                while(k<s.length())
                                {
										st=""+s.charAt(k);	k++;
										while(k<s.length()&&s.charAt(k)=='\'')
										{
												st+=s.charAt(k);	k++;
										}
										if(GRAMMAR.isterminal(st))
												continue;
										if(NFA.findindex(tmp_vertex,count_tmpvertex,st)<0)
                                      	{
												count_derive=m;
												break;
										}
					                    if(NFA.findindex(derive,count_derive,st)==-1)
												derive[count_derive++]=new String(st);                                 
                                }
                        }
                }
                count_usedsymbols=count_sigma;
                for(int i=0;i<count_derive;i++)
                {
                        used_symbols[count_usedsymbols++]=new String(derive[i]);
                }
                System.out.print("\n\nVertices giving terminal strings and derivable from start symbols are : ");
                for(int i=count_sigma;i<count_usedsymbols;i++)
                {
                        System.out.print(" "+used_symbols[i]);
                }
                System.out.println();
                GRAMMAR g=new GRAMMAR();
                g.count_vertex=count_derive;
                g.vertex=new String[g.count_vertex];
                for(int i=0;i<g.count_vertex;i++)
                        g.vertex[i]=new String(derive[i]);
                NFA.sort(g.vertex,1,g.count_vertex);
                g.start=new String(start);
                g.productions=new prod[g.count_vertex];
                for(int i=0;i<g.count_vertex;i++)
                {
                        g.productions[i]=new prod();
                        int tmp=NFA.findindex(vertex,count_vertex,g.vertex[i]);
                        for(int j=0;j<productions[tmp].no_choices;j++)
                        {
                                String s=productions[tmp].choices[j];
                                if(iscontained(s,used_symbols,count_usedsymbols))
                                {
                                        g.productions[i]=GRAMMAR.add(g.productions[i],s);
                                        for(int k=0;k<s.length();k++)
                                        {
                                                if(GRAMMAR.isterminal(s.charAt(k))&&NFA.findindex(tmp_sigma,count_tmpsigma,s.charAt(k))==-1)
                                                {
                                                        tmp_sigma[count_tmpsigma++]=s.charAt(k);
                                                }
                                        }
                                }
                        }
                }
                g.count_sigma=count_tmpsigma;
                g.sigma=new char[g.count_sigma];
                for(int i=0;i<g.count_sigma;i++)
                {
                        g.sigma[i]=tmp_sigma[i];
                }
                NFA.sort(g.sigma,g.count_sigma);
                return g;
        }
		public String permute(String s,int status[],nfastate get)
        {
                String st=new String();
                int l,m,p=0;
                for(int i=0;i<get.no_state;i++)
                {
                        l=get.index[i];
                        m=status[i];
                        while(p<l)
                        {
                               st+=s.charAt(p);
							   p++;
                        }
                        if(m==0)
                        {
                                st+=s.charAt(p);
								p++;
								while(p<s.length()&&s.charAt(p)=='\'')
								{
										st+=s.charAt(p);
										p++;
								}
                        }
                        if(m==1)
                        {
                                p++;
								while(p<s.length()&&s.charAt(p)=='\'')
								{
										p++;
								}
                        }
                }
                while(p<s.length())
                {
                       st+=s.charAt(p);
					   p++;
                }
                if(st.length()==0)
                        return "\\";
                return st;
        }

		public int exists(String tmp)
		{	
				for(int i=0;i<count_vertex;i++)
				{
						for(int j=0;j<productions[i].no_choices;j++)
						{
								if(productions[i].choices[j].equals(tmp)&&i>=irange)
										return i;
						}
				}
				return -1;
		}
		public void add(String V,String s)
		{
                String tmp[]=new String[count_vertex];
				prod ptmp[]=new prod[count_vertex];
				int i;
				for(i=0;i<count_vertex;i++)
				{
						tmp[i]=vertex[i];
				}
				for(i=0;i<count_vertex;i++)
				{
						ptmp[i]=productions[i];
				}
				count_vertex++;
				vertex=new String[count_vertex];
				for(i=0;i<count_vertex-1;i++)
				{
						vertex[i]=tmp[i];
				}
				vertex[i]=new String(V);
				productions=new prod[count_vertex];
				for(i=0;i<count_vertex-1;i++)
				{
						productions[i]=ptmp[i];
				}
				productions[i]=GRAMMAR.add(productions[i],s);

        }
		public static boolean iscontained(String s,String nullable[],int length)
        {
                int result,i=0;
				String st=new String();
				//System.out.println("\ns--> "+s);
                while(i<s.length())
                {
						st="";
						st+=s.charAt(i);
						i++;
						while(i<s.length()&&s.charAt(i)=='\'')
						{
								st+=s.charAt(i);
								i++;
						}
                        result=NFA.findindex(nullable,length,st);
                        if(result==-1)
                                return false;
                }
                return true;
        }
        public nfastate getplaces(String s,String nullable[],int count_nullable)
        {
				String st;
                int i=0,l=s.length();
                nfastate get=new nfastate();
                get.index=new int[l];
                while(i<l)
                {
						st="";
						st+=s.charAt(i);
						i++;
						while(i<s.length()&&s.charAt(i)=='\'')
						{
								st+=s.charAt(i);
								i++;
						}
                        if(NFA.findindex(nullable,count_nullable,st)>=0)
                        {
                                get.index[get.no_state]=i-st.length();
                                get.no_state++;
                        }
                }
                return get;
        }
}
class PDA
{
        int count_states;
        int states[];                   
        int count_sigma;
        char sigma[];
        int count_stacksymbol;
        char stacksymbol[];
        pdastate tr_table[][];
        int init_state;
        char init_stacksymbol;
        int count_fstates;
        int fstates[];
        public PDA()
        {
                count_states=0;
                count_sigma=0;
                count_stacksymbol=0;
                count_fstates=0;
                init_stacksymbol='!';
        }
		public PDA(GRAMMAR g)
		{
				int i,j;
				count_states=3;
				states=new int[count_states];
				for(i=0;i<count_states;i++)
				{
						states[i]=i;
				}
				count_sigma=g.count_sigma+1;
				sigma=new char[count_sigma];
				for(i=0;i<count_sigma-1;i++)
				{
						sigma[i]=g.sigma[i];
				}
				sigma[i]='\\';
				count_stacksymbol=g.count_sigma+g.count_vertex;
				stacksymbol=new char[count_stacksymbol];
				for(i=0;i<g.count_vertex;i++)
				{
						stacksymbol[i]=g.vertex[i].charAt(0);
				}
				for(i=g.count_vertex;i<g.count_sigma;i++)
				{
						stacksymbol[i]=g.sigma[i-g.count_vertex];
				}
				init_state=0;
				init_stacksymbol=g.start.charAt(0);
				count_fstates=1;
				fstates=new int[count_fstates];
				fstates[0]=2;
				tr_table=new pdastate[count_states][count_sigma];
				for(i=0;i<count_states;i++)
				{	
						for(j=0;j<count_sigma;j++)
						{
								tr_table[i][j]=new pdastate(); 
						}
				}
				tr_table[0][count_sigma-1].count_choices=1;
				tr_table[0][count_sigma-1].choices=new move[1];
				tr_table[0][count_sigma-1].choices[0]=new move();
				tr_table[0][count_sigma-1].choices[0].topsymbol=init_stacksymbol;
				tr_table[0][count_sigma-1].choices[0].state=1;
				tr_table[0][count_sigma-1].choices[0].pushstring=""+init_stacksymbol;
				int count=0;
				for(i=0;i<g.count_vertex;i++)
				{
						for(j=0;j<g.productions[i].no_choices;j++)
						{
								count++;		
						}
				}
				tr_table[1][count_sigma-1].count_choices=count+1;
				tr_table[1][count_sigma-1].choices=new move[count+1];
				for(i=0;i<count+1;i++)
				{
						tr_table[1][count_sigma-1].choices[i]=new move();
				}
				count=0;
				for(i=0;i<g.count_vertex;i++)
				{
						for(j=0;j<g.productions[i].no_choices;j++)
						{
								String s=g.productions[i].choices[j];
								tr_table[1][count_sigma-1].choices[count].state=1;
								tr_table[1][count_sigma-1].choices[count].topsymbol=g.vertex[i].charAt(0);
								tr_table[1][count_sigma-1].choices[count].pushstring=new String(s);
								count++;
						}
				}
				tr_table[1][count_sigma-1].choices[count].state=2;
				tr_table[1][count_sigma-1].choices[count].topsymbol='\\';
				tr_table[1][count_sigma-1].choices[count].pushstring=new String("");
				for(i=0;i<g.count_sigma;i++)
				{
						tr_table[1][i].count_choices=1;
						tr_table[1][i].choices=new move[1];
						tr_table[1][i].choices[0]=new move();
						tr_table[1][i].choices[0].state=1;
						tr_table[1][i].choices[0].topsymbol=g.sigma[i];
						tr_table[1][i].choices[0].pushstring="";
				}
		}
        public ID process(String s)
        {
                ID root=new ID();
                ID current,next;
                int state,index,pos=-1,i,count=0;
                char c,sc;
                String s1;
                root.state=init_state;
                root.input=new StringBuffer(s);
                root.stack=new StringBuffer(""+init_stacksymbol);
                root.father=null;
                root.child=null;
                current=root;
                System.out.println();
                try{
                do
                {
						//System.out.println("(q"+current.state+","+current.input+","+current.stack+")");
						next=null;
						if(current.stack.length()>s.length())
						{
								current=current.father;
								continue;		
						}
                        if(current.input.length()==0)
                        {
                                if(NFA.findindex(fstates,count_fstates,current.state)>=0)
                                {
                                        return root;
                                }
                                if(current.stack.length()==0)
                                {
										if(current.input.length()==0)
										{
												next=new ID();
												next.state=fstates[0];
												next.input=new StringBuffer("");
												next.stack=new StringBuffer("");
												next.child=null;
												current.child=next;
												next.father=current;
												return root;
										}
										current=current.father;
                                        current.child=null;
                                        continue;
                                }
                                if(current.mark==0)
                                {
                                        pos=count_sigma-1;
                                        current.radix=0;
                                        current.mark=1;
                                }
                        }
						if(current.stack.length()==0)
						{
								current=current.father;
								continue;				
						}
                        index=NFA.findindex(states,count_states,current.state);
						sc=current.stack.charAt(0);
                        if(current.input.length()!=0 && current.mark==0)
                        {
                                c=current.input.charAt(0);
                                pos=NFA.findindex(sigma,count_sigma,c);
                        }
                        for(i=current.radix;i<tr_table[index][pos].count_choices;i++)
                        {
                                if(tr_table[index][pos].choices[i].topsymbol==sc)
                                {
                                        next=new ID();
                                        next.state=tr_table[index][pos].choices[i].state;
                                        if(next.state==-1)
                                        {
                                                continue;
                                        }
                                        next.input=new StringBuffer(current.input);
                                        if(pos!=count_sigma-1)
                                        {
                                                next.input.deleteCharAt(0);
                                        }
                                        next.stack=new StringBuffer(current.stack);
                                        s1=new String(tr_table[index][pos].choices[i].pushstring);
                                        next.stack.replace(0,1,s1);
                                        current.radix++;  
                                        next.father=current;    current.child=next;
                                        current=next;  
                                        break;
                                }
                        }
                        if(i==tr_table[index][pos].count_choices && current.mark==0)
                        {
                                current.radix=0;
                                current.mark=1;
                                pos=count_sigma-1;
                                continue;
                        }
                        if(next==null)
                        {
                                current=current.father;
                        }
						
                }while(current!=null);
                return null;
                }
                catch(OutOfMemoryError e)
                {
						System.out.println("(q"+current.state+","+current.input+","+current.stack+")");
                        System.out.println();
                        return null;
                }
        }
		public GRAMMAR tocfg()
		{
				int i,j,k,l,index,range=0,status[];
				String s=new String(),st;
				move m;
				GRAMMAR cg=new GRAMMAR();
				cg.vertex=new String[count_states*count_states*count_stacksymbol+1];
				cg.vertex[0]=new String("S");
				cg.count_vertex=1;
				for(i=0;i<count_states;i++)
				{
						for(j=0;j<count_stacksymbol;j++)
						{
								for(k=0;k<count_states;k++)
								{
										s="[q"+states[i]+" "+stacksymbol[j]+" q"+states[k]+"]";
										cg.vertex[cg.count_vertex]=new String(s);
										cg.count_vertex++;
								}
						}
				}
				cg.count_sigma=count_sigma-1;
				cg.sigma=new char[cg.count_sigma];
				for(i=0,k=0;i<count_sigma;i++)
				{
						if(sigma[i]!='\\')
						{
								cg.sigma[k++]=sigma[i];
						}
				}
				cg.start="S";
				cg.productions=new prod[count_states*count_states*count_stacksymbol+1];
				for(i=0;i<cg.count_vertex;i++)
				{
						cg.productions[i]=new prod();
				}
				cg.productions[0].no_choices=count_states;
				cg.productions[0].choices=new String[count_states];
				for(i=0;i<count_states;i++)
				{
						s="[q"+init_state+" "+init_stacksymbol+" q"+states[i]+"]";
						cg.productions[0].choices[i]=new String(s);;
				}
				for(i=0;i<count_states;i++)
				{
						for(j=0;j<count_sigma;j++)
						{
								for(k=0;k<tr_table[i][j].count_choices;k++)
								{
										m=tr_table[i][j].choices[k];
										st=m.pushstring;
										l=st.length();
										if(l==0)
										{
												s="[q"+states[i]+" "+m.topsymbol+" q"+m.state+"]";
												index=NFA.findindex(cg.vertex,cg.count_vertex,s);
												s=""+sigma[j];
												cg.productions[index]=GRAMMAR.realloc(cg.productions[index]);
												cg.productions[index].choices[cg.productions[index].no_choices-1]=new String(s);
										}
										else
										{
												status=new int[l];
												for(int x=0;x<l;x++)
													status[x]=0;
												range=l-1;
												while(status[0]<count_states)
												{
														s="[q"+states[i]+" "+m.topsymbol+" q"+states[status[l-1]]+"]";														
														index=NFA.findindex(cg.vertex,cg.count_vertex,s);
														s=""+sigma[j];
														if(sigma[j]=='\\')
																s="";
														s+="[q"+m.state+" "+st.charAt(0)+" q"+states[status[0]]+"]";
														for(int x=1;x<l;x++)
														{
																s+="[q"+states[status[x-1]]+" "+st.charAt(x)+" q"+states[status[x]]+"]";
														}
														status[range]++;
														cg.productions[index]=GRAMMAR.add(cg.productions[index],s);						
														while(range>0&&status[range]==count_states)
														{
																status[range]=0;
																range--;
																status[range]++;
														}
														range=l-1;
												}
										}
								}
						}

				}
				System.out.println("\n\n\n");
				return cg;
		}
}
class move
{
        char topsymbol;
        int state;
        String pushstring;
        public move()
        {
                pushstring=new String("");
        }
}
class pdastate
{
        int count_choices;
        move choices[];
		public pdastate()
		{
				count_choices=0;
		}
}
class ID
{
        int state;
        StringBuffer input;
        StringBuffer stack;
        ID father;
        ID child;
        int radix;
        int mark;
        public ID()
        {
                state=-1; radix=0; mark=0;
        }
}
class project
{
        public static void printnewdfa(NEWDFA newdfa)
        {
                int i,j,k;
                System.out.println("\n");
                for(j=0;j<newdfa.count_sigma;j++)
                {
                        System.out.print("   \t"+newdfa.sigma[j]);
                }
                System.out.println("\n");
                for(i=0;i<newdfa.count_rstates;i++)
                {
                        System.out.print("[");
                        for(k=0;k<newdfa.rstates[i].no_state-1;k++)
                        {
                                System.out.print("q"+newdfa.rstates[i].index[k]+" ");
                        }
                        System.out.print("q"+newdfa.rstates[i].index[k]+"]");
                        for(j=0;j<newdfa.count_sigma;j++)
                        {
                                if(newdfa.tr_table[i][j].no_state==0)
                                {
                                        System.out.print("\t");
                                        System.out.print("-");
                                        continue;
                                }
                                System.out.print("\t[");
                                for(k=0;k<newdfa.tr_table[i][j].no_state-1;k++)
                                {
                                        System.out.print("q"+newdfa.tr_table[i][j].index[k]+" ");
                                }
                                System.out.print("q"+newdfa.tr_table[i][j].index[k]+"]");
                        }
                        System.out.println();
                }
                System.out.print("\nInitial State is :  [q"+newdfa.init_state.index[0]+"]");
                System.out.print("\nFinal States are : ");
                if(newdfa.count_rfstates==0)
                {
                        System.out.print("-");
                }
                else
                for(i=0;i<newdfa.count_rfstates;i++)
                {
                        System.out.print("[");
                        for(j=0;j<newdfa.rfstates[i].no_state-1;j++)
                        {
                                System.out.print("q"+newdfa.rfstates[i].index[j]+" ");
                        }
                        System.out.print("q"+newdfa.rfstates[i].index[j]+"] ; ");
                }
        }
 	public static void printMoore(Moore mr)
        {
                int i,j;
                System.out.print("\n\n  ");
                for(j=0;j<mr.count_sigma;j++)
                {
                        System.out.print("\t\t"+mr.sigma[j]);
                }
                System.out.println("\n");
                for(i=0;i<mr.count_states;i++)
                {
                        System.out.print("q"+mr.states[i]+"\t");
                        for(j=0;j<mr.count_sigma;j++)
                        {
                                if(mr.tr_table[i][j]==-1)
                                        System.out.print(" -"+"\t");
                                else
                                        System.out.print("\t"+"q"+mr.tr_table[i][j]+"\t");
                        }
                        System.out.println("\t"+mr.mr_op[i]);
                }
                
				System.out.println();
                System.out.println("\nInitial State is : q"+mr.init_state);
                
                System.out.println();
        }
        public static void printMealy(Mealy ml)
        {
                int i,j;
                System.out.print("\n\n  ");
                for(j=0;j<ml.count_sigma;j++)
                {
                        System.out.print("\t\t"+ml.sigma[j]);
                }
                System.out.println("\n");
                for(i=0;i<ml.count_states;i++)
                {
                        System.out.print("q"+ml.states[i]+"\t\t");
                        for(j=0;j<ml.count_sigma;j++)
						{
                               System.out.print("q"+ml.ml_table[i][j].state);
                               System.out.print("  "+ml.ml_table[i][j].output+"\t\t");
                        }
                        System.out.println();                        
                }              
				System.out.println();
                System.out.println("\nInitial State is : q"+ml.init_state);                
        }
        public static void printgrammar(GRAMMAR g)
        {
                int i,j;
                System.out.print("\n\nVertices are : { ");
				if(g.count_vertex==0)
				{
						System.out.println("\nEMPTY LANGUAGE\n");
						return;
				}
                for(i=0;i<g.count_vertex-1;i++)
                {
                        System.out.print(g.vertex[i]+",");
                }
                System.out.println(g.vertex[i]+" }");
                System.out.print("\nSigma Symbols are : { ");
                for(i=0;i<g.count_sigma-1;i++)
                {
                        System.out.print(g.sigma[i]+",");
                }
                if(g.count_sigma>0)
                        System.out.println(g.sigma[i]+" }");
                else
                        System.out.println(" }");
                System.out.println("\nStarting vertex is : "+g.start);
                if(g.count_sigma>0)
                {
                        System.out.print("\nProductions are : ");
                        for(i=0;i<g.count_vertex;i++)
                        {
                                
								if(g.productions[i].no_choices==0)
										continue;
								System.out.print("\n\n"+g.vertex[i]+" --> ");
                                for(j=0;j<g.productions[i].no_choices-1;j++)
                                {
                                       System.out.print(g.productions[i].choices[j]+" | ");
                                }
                                System.out.print(g.productions[i].choices[j]);
                        }
                        System.out.println("\n");
                }
                else
                        System.out.println("\nEMPTY LANGUAGE\n");
				System.out.println("-----------------------------------------------------------");
        }
        public static void printnfa(NFA nfa)
        {
                int i,j,k;
                System.out.println("\n");
                System.out.print("      ");
                for(j=0;j<nfa.count_sigma;j++)
                {
                        System.out.print("\t"+nfa.sigma[j]);
                }
                System.out.println("\n");
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
                System.out.println("\nInitial State is :  q"+nfa.init_state+" ");
                System.out.print("Final States are : ");
                if(nfa.count_fstates==0)
                {
                        System.out.print("-");
                }
                else
                for(i=0;i<nfa.count_fstates;i++)
                {
                        System.out.print(" q"+nfa.fstates[i]+" ; ");
                }
                System.out.println("\n");
        }
        public static void printdfa(DFA dfa)
        {
                int i,j;
                System.out.print("\n\n\n  ");
                for(j=0;j<dfa.count_sigma;j++)
                {
                        System.out.print("\t"+dfa.sigma[j]);
                }
                System.out.println("\n");
                for(i=0;i<dfa.count_states;i++)
                {
                        System.out.print("q"+dfa.states[i]+"\t");
                        for(j=0;j<dfa.count_sigma;j++)
                        {
                                if(dfa.tr_table[i][j]==-1)
                                        System.out.print(" -"+"\t");
                                else
                                        System.out.print("q"+dfa.tr_table[i][j]+"\t");
                        }
                        System.out.println();
                }
                System.out.println("\nInitial State is : q"+dfa.init_state);
                System.out.print("Final states are : ");
                if(dfa.count_fstates==0)
                {
                        System.out.print("-");
                }
                else
                for(i=0;i<dfa.count_fstates;i++)
                {
                        System.out.print("q"+dfa.fstates[i]+" ; ");
                }
                System.out.println("\n");
        }
        public static void printpda(PDA pda)
        {
                int i,j,k;
                char c;
                int x,y=0;
                String s;
                System.out.println("\n");
                for(i=0;i<pda.count_states;i++)
                {
                        for(j=0;j<pda.count_sigma;j++)
                        {
                                for(k=0;k<pda.tr_table[i][j].count_choices;k++)
                                {
									c=pda.tr_table[i][j].choices[k].topsymbol;
                                    x=pda.tr_table[i][j].choices[k].state;
                                    s=pda.tr_table[i][j].choices[k].pushstring;
                                    if(s.equals(""))
                                            s="\\";
									System.out.print(" $(q"+pda.states[i]+","+pda.sigma[j]+","+c+") = ");
                                    System.out.print("(q"+x+","+s+")");
									y++;
									if(y!=3)
											System.out.print("\t");
									else
									{
											y=0;	System.out.println("\n");
									}
                                }
                        }
                 }
                System.out.print("\n\n Initial State is : q"+pda.init_state);
                System.out.print("\t\tFinal states are : ");
                if(pda.count_fstates==0)
                {
                        System.out.print("-");
                }
                else
                for(i=0;i<pda.count_fstates;i++)
                {
                        System.out.print("q"+pda.fstates[i]+" ; ");
                }
                System.out.println("\n");
                System.out.println(" Initial StackSymbol is : "+pda.init_stacksymbol+"\n");
                System.out.print("-----------------------------------------------------------------------");
        }    
	public static void getMoore(Moore mr)
        {
                int i,j;
                char c = 'p';
                mr.count_states=6;
                mr.mr_op=new char[mr.count_states];
                mr.states=new int[mr.count_states];
                for(i=0;i<mr.count_states;i++)
                {
                        mr.states[i]=i;
                }              
                mr.count_sigma=2;
                mr.sigma=new char[mr.count_sigma];   
                for(i=0;i<mr.count_sigma;i++)
                {
                        mr.sigma[i]=c;
                        c++;
                }
                mr.init_state=1;               
                mr.tr_table= new int[mr.count_states][mr.count_sigma];

                mr.tr_table[0][0]=3;
                mr.tr_table[0][1]=1;

                mr.tr_table[1][0]=4;          
                mr.tr_table[1][1]=2;

                mr.tr_table[2][0]=5;
                mr.tr_table[2][1]=3;
                
                mr.tr_table[3][0]=3;
                mr.tr_table[3][1]=0;

                mr.tr_table[4][0]=1;
                mr.tr_table[4][1]=3;
                
                mr.tr_table[5][0]=2;
                mr.tr_table[5][1]=0;
			
                mr.mr_op[0]='a';
                mr.mr_op[1]='b';
                mr.mr_op[2]='a'; 
                mr.mr_op[4]='a';
                mr.mr_op[5]='a'; 
                mr.mr_op[3]='b';
        }	
        public static void getMealy(Mealy ml)
        {
                int i,j;
                char c = '0';
                ml.count_states=4;
                ml.states=new int[ml.count_states];
                ml.count_sigma=2;
                ml.ml_table = new tr_fn[ml.count_states][ml.count_sigma];
                for(i=0;i<ml.count_states;i++)
                {
                        ml.states[i]=i;
                }               
                ml.sigma=new char[ml.count_sigma];   
                for(i=0;i<ml.count_sigma;i++)
                {
                        ml.sigma[i]=c;
                        c++;
                }
                ml.init_state=2;            
                ml.ml_table[0][0]=new tr_fn();
                ml.ml_table[0][0].state=1;
                ml.ml_table[0][0].output='1';

                ml.ml_table[0][1]=new tr_fn();
                ml.ml_table[0][1].state=3;
                ml.ml_table[0][1].output='0';

                ml.ml_table[1][0]=new tr_fn();
                ml.ml_table[1][0].state=3; 
                ml.ml_table[1][0].output='0';

                ml.ml_table[1][1]=new tr_fn();
                ml.ml_table[1][1].state=2;
                ml.ml_table[1][1].output='0';

                ml.ml_table[2][0]=new tr_fn();
                ml.ml_table[2][0].state=2;
                ml.ml_table[2][0].output='1';

                ml.ml_table[2][1]=new tr_fn();
                ml.ml_table[2][1].state=0;
                ml.ml_table[2][1].output='1';

                ml.ml_table[3][0]=new tr_fn();
                ml.ml_table[3][0].state=0;
                ml.ml_table[3][0].output='0';

                ml.ml_table[3][1]=new tr_fn();
                ml.ml_table[3][1].state=1;
                ml.ml_table[3][1].output='0';
        }
        public static void getdfa(DFA dfa)
        {
                int i,j;
                char c = '0';
                dfa.count_states=6;
                dfa.states=new int[dfa.count_states];
                for(i=0;i<dfa.count_states;i++)
                {
                        dfa.states[i]=i;
                }
                dfa.states[0]=0;
                dfa.states[1]=1;
                dfa.states[2]=2;
                dfa.states[3]=3;
                dfa.states[4]=4;
                dfa.states[5]=5;

                dfa.count_sigma=2;
                dfa.sigma=new char[dfa.count_sigma];   
                for(i=0;i<dfa.count_sigma;i++)
                {
                        dfa.sigma[i]=c;
                        c++;
                }
                dfa.init_state=0;
                dfa.count_fstates=4;
                dfa.fstates=new int[dfa.count_fstates];
                dfa.fstates[0]=2;
                dfa.fstates[1]=3;
                dfa.fstates[2]=4;
                dfa.fstates[3]=5;

                dfa.tr_table= new int[dfa.count_states][dfa.count_sigma];
                                   
                dfa.tr_table[0][0]=1;
                dfa.tr_table[0][1]=2;

                dfa.tr_table[1][0]=1;          
                dfa.tr_table[1][1]=2;

                dfa.tr_table[2][0]=4;
                dfa.tr_table[2][1]=3;
                
                dfa.tr_table[3][0]=1;
                dfa.tr_table[3][1]=5;

                dfa.tr_table[4][0]=4;
                dfa.tr_table[4][1]=5;

                dfa.tr_table[5][0]=4;
                dfa.tr_table[5][1]=5;
               /*
                dfa.tr_table[6][0]=4;
                dfa.tr_table[6][1]=5;

                dfa.tr_table[7][0]=8;          
                dfa.tr_table[7][1]=9;

                dfa.tr_table[8][0]=1;
                dfa.tr_table[8][1]=0;
                
                dfa.tr_table[9][0]=6;
                dfa.tr_table[9][1]=1; */

        }
        public static void getnfa(NFA nfa)
        {
                int i,j,x;
                char c = '0';
                nfa.count_states=4;
                nfa.count_sigma=3;
                nfa.sigma=new char[nfa.count_sigma];   
                for(i=0;i<nfa.count_sigma-1;i++)
                {
                        nfa.sigma[i]=c;
                        c++;
                }
                nfa.sigma[i]='\\';
                nfa.init_state=0;
                nfa.count_fstates=2;
                nfa.fstates=new int[nfa.count_fstates];    
                nfa.fstates[0]=2;
                nfa.fstates[1]=3;
                nfa.tr_table=new nfastate[nfa.count_states][nfa.count_sigma];
                for(i=0;i<nfa.count_states;i++)
                {
                        for(j=0;j<nfa.count_sigma;j++)
                        {
                                nfa.tr_table[i][j]=new nfastate();
                        }
                }
                x=nfa.tr_table[0][0].no_state=1;
                nfa.tr_table[0][0].index=new int[x];
                nfa.tr_table[0][0].index[0]=1;

                x=nfa.tr_table[0][1].no_state=0;
                nfa.tr_table[0][1].index=new int[x];

                x=nfa.tr_table[0][2].no_state=1;
                nfa.tr_table[0][2].index=new int[x];
                nfa.tr_table[0][2].index[0]=2;

                x=nfa.tr_table[1][0].no_state=1;
                nfa.tr_table[1][0].index=new int[x];
                nfa.tr_table[1][0].index[0]=1;

                x=nfa.tr_table[1][1].no_state=2;
                nfa.tr_table[1][1].index=new int[x];
                nfa.tr_table[1][1].index[0]=2;
                nfa.tr_table[1][1].index[1]=3;

                x=nfa.tr_table[1][2].no_state=0;
                nfa.tr_table[1][2].index=new int[x];

                x=nfa.tr_table[2][0].no_state=1;
                nfa.tr_table[2][0].index=new int[x];
                nfa.tr_table[2][0].index[0]=1;

                x=nfa.tr_table[2][1].no_state=1;
                nfa.tr_table[2][1].index=new int[x];
                nfa.tr_table[2][1].index[0]=0;

                x=nfa.tr_table[2][2].no_state=0;
                nfa.tr_table[2][2].index=new int[x];

                x=nfa.tr_table[3][0].no_state=1;
                nfa.tr_table[3][0].index=new int[x];
                nfa.tr_table[3][0].index[0]=3;

                x=nfa.tr_table[3][1].no_state=1;
                nfa.tr_table[3][1].index=new int[x];
                nfa.tr_table[3][1].index[0]=2;

                x=nfa.tr_table[3][2].no_state=1;
                nfa.tr_table[3][2].index=new int[x];
                nfa.tr_table[3][2].index[0]=2;
        }
        public static void getpda1(PDA pda)
        {                                       //WW^R
                int i,j;
                char c='a';
                pda.count_states=3;
                pda.states=new int[pda.count_states];
                for(i=0;i<pda.count_states;i++)
                {
                        pda.states[i]=i;
                }
                pda.count_sigma=3;
                pda.sigma=new char[pda.count_sigma];
                for(i=0;i<pda.count_sigma;i++)
                {
                        pda.sigma[i]=c++;
                }
                pda.sigma[pda.count_sigma-1]='\\';
                pda.count_stacksymbol=3;
                pda.stacksymbol=new char[pda.count_stacksymbol];
                pda.stacksymbol[0]='z';
                c='a';
                for(i=1;i<pda.count_stacksymbol;i++)
                {
                        pda.stacksymbol[i]=c++;
                }
                pda.init_state=0;
                pda.init_stacksymbol='z';
                pda.count_fstates=1;
                pda.fstates=new int[pda.count_fstates];
                pda.fstates[0]=2;

                pda.tr_table=new pdastate[pda.count_states][pda.count_sigma];
                for(i=0;i<pda.count_states;i++)
                {
                        for(j=0;j<pda.count_sigma;j++)
                        {
                                pda.tr_table[i][j]=new pdastate();
                        }
                }
                pda.tr_table[0][0].count_choices=3;
                pda.tr_table[0][0].choices=new move[3];
                pda.tr_table[0][0].choices[0]=new move();
                pda.tr_table[0][0].choices[0].topsymbol='a';
                pda.tr_table[0][0].choices[0].state=0;
                pda.tr_table[0][0].choices[0].pushstring="aa";
                pda.tr_table[0][0].choices[1]=new move();
                pda.tr_table[0][0].choices[1].topsymbol='b';
                pda.tr_table[0][0].choices[1].state=0;
                pda.tr_table[0][0].choices[1].pushstring="ab";
                pda.tr_table[0][0].choices[2]=new move();
                pda.tr_table[0][0].choices[2].topsymbol='z';
                pda.tr_table[0][0].choices[2].state=0;
                pda.tr_table[0][0].choices[2].pushstring="az";
                
                pda.tr_table[0][1].count_choices=3;
                pda.tr_table[0][1].choices=new move[3];
                pda.tr_table[0][1].choices[0]=new move();
                pda.tr_table[0][1].choices[0].topsymbol='a';
                pda.tr_table[0][1].choices[0].state=0;
                pda.tr_table[0][1].choices[0].pushstring="ba";
                pda.tr_table[0][1].choices[1]=new move();
                pda.tr_table[0][1].choices[1].topsymbol='b';
                pda.tr_table[0][1].choices[1].state=0;
                pda.tr_table[0][1].choices[1].pushstring="bb";
                pda.tr_table[0][1].choices[2]=new move();
                pda.tr_table[0][1].choices[2].topsymbol='z';
                pda.tr_table[0][1].choices[2].state=0;
                pda.tr_table[0][1].choices[2].pushstring="bz";

                pda.tr_table[0][2].count_choices=2;
                pda.tr_table[0][2].choices=new move[2];
                pda.tr_table[0][2].choices[0]=new move();
                pda.tr_table[0][2].choices[0].topsymbol='a';
                pda.tr_table[0][2].choices[0].state=1;
                pda.tr_table[0][2].choices[0].pushstring="a";
                pda.tr_table[0][2].choices[1]=new move();
                pda.tr_table[0][2].choices[1].topsymbol='b';
                pda.tr_table[0][2].choices[1].state=1;
                pda.tr_table[0][2].choices[1].pushstring="b";

                pda.tr_table[1][0].count_choices=1;
                pda.tr_table[1][0].choices=new move[1];
                pda.tr_table[1][0].choices[0]=new move();
                pda.tr_table[1][0].choices[0].topsymbol='a';
                pda.tr_table[1][0].choices[0].state=1;
                pda.tr_table[1][0].choices[0].pushstring="";

                pda.tr_table[1][1].count_choices=1;
                pda.tr_table[1][1].choices=new move[1];
                pda.tr_table[1][1].choices[0]=new move();
                pda.tr_table[1][1].choices[0].topsymbol='b';
                pda.tr_table[1][1].choices[0].state=1;
                pda.tr_table[1][1].choices[0].pushstring="";

                pda.tr_table[1][2].count_choices=1;
                pda.tr_table[1][2].choices=new move[1];
                pda.tr_table[1][2].choices[0]=new move();
                pda.tr_table[1][2].choices[0].topsymbol='z';
                pda.tr_table[1][2].choices[0].state=2;
                pda.tr_table[1][2].choices[0].pushstring="z";
        }
        public static void getpda2(PDA pda)
        {                                       
                int i,j;
                char c='0';
                pda.count_states=2;
                pda.states=new int[pda.count_states];
                for(i=0;i<pda.count_states;i++)
                {
                        pda.states[i]=i;
                }
                pda.count_sigma=3;
                pda.sigma=new char[pda.count_sigma];
                for(i=0;i<pda.count_sigma;i++)
                {
                        pda.sigma[i]=c++;
                }
                pda.sigma[pda.count_sigma-1]='\\';
                pda.count_stacksymbol=2;
                pda.stacksymbol=new char[pda.count_stacksymbol];
                pda.stacksymbol[0]='X';
                pda.stacksymbol[1]='Z';
                pda.init_state=0;
                pda.init_stacksymbol='Z';
                pda.count_fstates=0;
                pda.fstates=new int[pda.count_fstates];
               // pda.fstates[0]=0;
                pda.tr_table=new pdastate[pda.count_states][pda.count_sigma];
                for(i=0;i<pda.count_states;i++)
                {
                        for(j=0;j<pda.count_sigma;j++)
                        {
                                pda.tr_table[i][j]=new pdastate();
                        }
                }
                pda.tr_table[0][0].count_choices=2;
                pda.tr_table[0][0].choices=new move[2];
                pda.tr_table[0][0].choices[0]=new move();
                pda.tr_table[0][0].choices[0].topsymbol='Z';
                pda.tr_table[0][0].choices[0].state=0;
                pda.tr_table[0][0].choices[0].pushstring="XZ";
				pda.tr_table[0][0].choices[1]=new move();
                pda.tr_table[0][0].choices[1].topsymbol='X';
                pda.tr_table[0][0].choices[1].state=0;
                pda.tr_table[0][0].choices[1].pushstring="XX";

                pda.tr_table[0][1].count_choices=1;
				pda.tr_table[0][1].choices=new move[1];
                pda.tr_table[0][1].choices[0]=new move();
                pda.tr_table[0][1].choices[0].topsymbol='X';
                pda.tr_table[0][1].choices[0].state=1;
                pda.tr_table[0][1].choices[0].pushstring="";

				pda.tr_table[0][2].count_choices=0;

                pda.tr_table[1][0].count_choices=0;

                pda.tr_table[1][1].count_choices=1;
                pda.tr_table[1][1].choices=new move[1];
                pda.tr_table[1][1].choices[0]=new move();
                pda.tr_table[1][1].choices[0].topsymbol='X';
                pda.tr_table[1][1].choices[0].state=1;
                pda.tr_table[1][1].choices[0].pushstring="";

                pda.tr_table[1][2].count_choices=2;
				pda.tr_table[1][2].choices=new move[2];
                pda.tr_table[1][2].choices[0]=new move();
                pda.tr_table[1][2].choices[0].topsymbol='X';
                pda.tr_table[1][2].choices[0].state=1;
                pda.tr_table[1][2].choices[0].pushstring="";
				pda.tr_table[1][2].choices[1]=new move();
                pda.tr_table[1][2].choices[1].topsymbol='Z';
                pda.tr_table[1][2].choices[1].state=1;
                pda.tr_table[1][2].choices[1].pushstring="";
        }
		public static void getpda3(PDA pda)
        {											//a^n b^n    
                int i,j;
                char c='a';
                pda.count_states=3;
                pda.states=new int[pda.count_states];
                for(i=0;i<pda.count_states;i++)
                {
                        pda.states[i]=i;
                }
                pda.count_sigma=3;
                pda.sigma=new char[pda.count_sigma];
                for(i=0;i<pda.count_sigma;i++)
                {
                        pda.sigma[i]=c++;
                }
                pda.sigma[pda.count_sigma-1]='\\';
                pda.count_stacksymbol=3;
                pda.stacksymbol=new char[pda.count_stacksymbol];
                pda.stacksymbol[0]='a';
                pda.stacksymbol[1]='b';
				pda.stacksymbol[2]='S';
                pda.init_state=0;
                pda.init_stacksymbol='S';
                pda.count_fstates=1;
				pda.fstates=new int[1];
				pda.fstates[0]=2;
                pda.tr_table=new pdastate[pda.count_states][pda.count_sigma];
                for(i=0;i<pda.count_states;i++)
                {
                        for(j=0;j<pda.count_sigma;j++)
                        {
                                pda.tr_table[i][j]=new pdastate();
                        }
                }
                pda.tr_table[0][0].count_choices=0;
                
                pda.tr_table[0][1].count_choices=0;
			
				pda.tr_table[0][2].count_choices=1;
				pda.tr_table[0][2].choices=new move[1];
                pda.tr_table[0][2].choices[0]=new move();
                pda.tr_table[0][2].choices[0].topsymbol='S';
                pda.tr_table[0][2].choices[0].state=1;
                pda.tr_table[0][2].choices[0].pushstring="S";
				
			
                pda.tr_table[1][0].count_choices=1;
				pda.tr_table[1][0].choices=new move[1];
                pda.tr_table[1][0].choices[0]=new move();
                pda.tr_table[1][0].choices[0].topsymbol='a';
                pda.tr_table[1][0].choices[0].state=1;
                pda.tr_table[1][0].choices[0].pushstring="";
				
				pda.tr_table[1][1].count_choices=1;
				pda.tr_table[1][1].choices=new move[1];
                pda.tr_table[1][1].choices[0]=new move();
				pda.tr_table[1][1].choices[0].topsymbol='b';
                pda.tr_table[1][1].choices[0].state=1;
                pda.tr_table[1][1].choices[0].pushstring="";
				
                pda.tr_table[1][2].count_choices=3;
				pda.tr_table[1][2].choices=new move[3];
                pda.tr_table[1][2].choices[0]=new move();
                pda.tr_table[1][2].choices[0].topsymbol='S';
                pda.tr_table[1][2].choices[0].state=1;
                pda.tr_table[1][2].choices[0].pushstring="aSb";
				pda.tr_table[1][2].choices[1]=new move();
				pda.tr_table[1][2].choices[1].topsymbol='S';
                pda.tr_table[1][2].choices[1].state=1;
                pda.tr_table[1][2].choices[1].pushstring="ab";
				pda.tr_table[1][2].choices[2]=new move();
				pda.tr_table[1][2].choices[2].topsymbol='\\';
                pda.tr_table[1][2].choices[2].state=2;
                pda.tr_table[1][2].choices[2].pushstring="";

				pda.tr_table[2][0].count_choices=0;

                pda.tr_table[2][1].count_choices=0;

                pda.tr_table[2][2].count_choices=0;
		}
        public static void getrightgrammar(GRAMMAR g)
        {
                char c='a';
                g.count_vertex=4;
                g.vertex=new String[g.count_vertex];
                g.vertex[0]="S";
                g.vertex[1]="A";
                g.vertex[2]="B";
                g.vertex[3]="C";

                g.count_sigma=6;
                g.sigma=new char[g.count_sigma];
                for(int i=0;i<g.count_sigma;i++)
                {
                        g.sigma[i]=c;
                        c++;
                }
                g.start="S";

                g.productions=new prod[g.count_vertex];

                g.productions[0]=new prod();
                g.productions[0].no_choices=4;
                g.productions[0].choices=new String[4];
                g.productions[0].choices[0]=new String("aA");
                g.productions[0].choices[1]=new String("cB");
                g.productions[0].choices[2]=new String("bC");
                g.productions[0].choices[3]=new String("\\");

                g.productions[1]=new prod();
                g.productions[1].no_choices=3;
                g.productions[1].choices=new String[3];
                g.productions[1].choices[0]=new String("dA");
                g.productions[1].choices[1]=new String("aS");
                g.productions[1].choices[2]=new String("\\");

                g.productions[2]=new prod();
                g.productions[2].no_choices=2;
                g.productions[2].choices=new String[2];
                g.productions[2].choices[0]=new String("B");
                g.productions[2].choices[1]=new String("f");

                g.productions[3]=new prod();
                g.productions[3].no_choices=2;
                g.productions[3].choices=new String[2];
                g.productions[3].choices[0]=new String("cA");
                g.productions[3].choices[1]=new String("S");

        }
        public static void getcfg(GRAMMAR g)
        {
                char c='a';
                g.count_vertex=5;
                g.vertex=new String[g.count_vertex];
                g.vertex[0]="S";
                g.vertex[1]="A";
                g.vertex[2]="B";
                g.vertex[3]="C";
                g.vertex[4]="D";

                g.count_sigma=5;
                g.sigma=new char[g.count_sigma];
                for(int i=0;i<g.count_sigma;i++)
                {
                        g.sigma[i]=c;
                        c++;
                }
                g.start="S";

                g.productions=new prod[g.count_vertex];

                g.productions[0]=new prod();
                g.productions[0].no_choices=4;
                g.productions[0].choices=new String[4];
                g.productions[0].choices[0]=new String("bA");
                g.productions[0].choices[1]=new String("ScA");
                g.productions[0].choices[2]=new String("\\");
                g.productions[0].choices[3]=new String("CbD");

                g.productions[1]=new prod();
                g.productions[1].no_choices=3;
                g.productions[1].choices=new String[3];    
                g.productions[1].choices[0]=new String("Se");
                g.productions[1].choices[1]=new String("ABa");
                g.productions[1].choices[2]=new String("B");

                g.productions[2]=new prod();
                g.productions[2].no_choices=2;
                g.productions[2].choices=new String[2];
                g.productions[2].choices[0]=new String("bB");
                g.productions[2].choices[1]=new String("b");

                g.productions[3]=new prod();
                g.productions[3].no_choices=2;
                g.productions[3].choices=new String[2];
                g.productions[3].choices[0]=new String("Sb");
                g.productions[3].choices[1]=new String("b");

                g.productions[4]=new prod();
                g.productions[4].no_choices=2;
                g.productions[4].choices=new String[2];
                g.productions[4].choices[0]=new String("cA");
                g.productions[4].choices[1]=new String("bC");    
        }
		public static void getcfg2(GRAMMAR g)
        {
                char c='a';
                g.count_vertex=5;
                g.vertex=new String[g.count_vertex];
                g.vertex[0]="S";
                g.vertex[1]="A";
                g.vertex[2]="B";
                g.vertex[3]="C";
                g.vertex[4]="D";

                g.count_sigma=5;
                g.sigma=new char[g.count_sigma];
                for(int i=0;i<g.count_sigma;i++)
                {
                        g.sigma[i]=c;
                        c++;
                }
                g.start="S";

                g.productions=new prod[g.count_vertex];

                g.productions[0]=new prod();
                g.productions[0].no_choices=4;
                g.productions[0].choices=new String[4];
                g.productions[0].choices[0]=new String("bC");
                g.productions[0].choices[1]=new String("ScB");
                g.productions[0].choices[2]=new String("AD");
                g.productions[0].choices[3]=new String("BbD");

                g.productions[1]=new prod();
                g.productions[1].no_choices=3;
                g.productions[1].choices=new String[3];    
                g.productions[1].choices[0]=new String("a");
                g.productions[1].choices[1]=new String("Aca");
                g.productions[1].choices[2]=new String("b");

                g.productions[2]=new prod();
                g.productions[2].no_choices=2;
                g.productions[2].choices=new String[2];
                g.productions[2].choices[0]=new String("bB");
                g.productions[2].choices[1]=new String("BB");

                g.productions[3]=new prod();
                g.productions[3].no_choices=2;
                g.productions[3].choices=new String[2];
                g.productions[3].choices[0]=new String("b");
                g.productions[3].choices[1]=new String("a");

                g.productions[4]=new prod();
                g.productions[4].no_choices=2;
                g.productions[4].choices=new String[2];
                g.productions[4].choices[0]=new String("cA");
                g.productions[4].choices[1]=new String("bB");    
        }
        public static void main(String args[])
        {
                boolean result;
                DFA dfa = new DFA();
                NFA nfa = new NFA();
                NEWDFA newdfa;
                RE re=new RE();
                int type=14;
				char c;
                String s=new String();
                try
                {
                        s=args[0];
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                        s="";
                } 
				if(type==0)
				{
						System.out.println(GRAMMAR.isdistinct(s));
				}
                if(type==1)
                {
                      getdfa(dfa);
                      printdfa(dfa);
                      result=dfa.process(s);          //DFA ACCEPTENCE
                      if(result==true)
                            System.out.println("String accepted by DFA");
                      else
                            System.out.println("String not accepted by DFA");  
                }
                if(type==2)
                {
                      getnfa(nfa);
					  printnfa(nfa);
                      result=nfa.process(s);         //NFA ACCEPTANCE
                      if(result==true)
                            System.out.println("String accepted by NFA");
                      else
                            System.out.println("String not accepted by NFA");    
                }
                if(type==3)                              
                {
                        getnfa(nfa);
                        printnfa(nfa);
                        newdfa=nfa.nfatodfa();                  //NFA-DFA CONVERSION,MINIMIZATION
                        printnewdfa(newdfa);
                        printdfa(newdfa.normalize());
                        dfa=newdfa.normalize();
                }
                if(type==4)
                {
                        re = new RE();           //DFA TO REGULAR EXPRRESSION
                        getdfa(dfa);
						printdfa(dfa);
						dfa=dfa.minimize();
						printdfa(dfa);
                        s=re.fatore(dfa);
                        System.out.println(s);
                }
                if(type==5)
                {
                        DFA dfa1=new DFA();
                        //try
                        {
                                nfa=re.retofa(s);                       //REGULAR EXPRESSION TO DFA/NFA
                                printnfa(nfa);
                                newdfa=nfa.nfatodfa();
                                printnewdfa(newdfa);
                                dfa1=newdfa.normalize();     
                                dfa=dfa1.minimize();
                                printdfa(dfa);
                                System.out.println("\n"+re.fatore(dfa));
                        }
                        //catch(Exception e)
                        {
                               // System.out.println("\nInvalid Regular Expression");
                        }
                }
                if(type==6)
                {
                        try
                        { 
                                nfa=re.retofa(s);                       //GRAMMER CORRESPONDING TO DFA/NFA
                                newdfa=nfa.nfatodfa();
                                DFA dfa1=newdfa.normalize();
                                dfa=dfa1.minimize();
                                printdfa(dfa);
                                GRAMMAR g=new GRAMMAR(dfa);
                                printgrammar(g);
                        }
                        catch(Exception e)
                        {
                                System.out.println("Null String entered");
                        }
                }
                if(type==7)        //NFA CORRESPONDING TO GIVEN GRAMMAR
                {
                        GRAMMAR g=new GRAMMAR();
                        getrightgrammar(g);
                        printgrammar(g);

                      /*  GRAMMAR g1=g.removenull();
                        printgrammar(g1);
                        GRAMMAR g2=g1.removeuseless();
                        printgrammar(g2);
                        */

                        nfa=g.makenfa();
                     //   printnfa(nfa);
                        newdfa=nfa.nfatodfa();
                      //  printnewdfa(newdfa);
                        dfa=newdfa.normalize();
                      //  printdfa(dfa);
                        dfa=dfa.minimize();
                        printdfa(dfa);;
                }
                if(type==8)                        //PDA processing
                {
                        int which=3;
                        ID root,current;
                        PDA pda=new PDA();
                        if(which==1)
                                getpda1(pda);
                        if(which==2)
                                getpda2(pda);
						if(which==3)
                                getpda3(pda);
                        printpda(pda);
                        root=pda.process(s);         
                        if(root==null)
                                System.out.println("String not accepted by PDA");  
                        else
                        {
                                System.out.println("String is accepted by PDA\n");  
                                current=root;
                                while(current!=null)
                                {
                                        System.out.println("(q"+current.state+","+current.input+","+current.stack+")");
                                        current=current.child;
                                }
                        }
                }
                if(type==9)                         //MOORE MACHINE
                {
                        Moore mr=new Moore();
                        String st;
                        getMoore(mr);
                        printMoore(mr);
                        System.out.println("\n-------------------------\n");
                        Mealy ml=mr.MrtoMl(mr);
                        printMealy(ml);
                        st=mr.process1(s);
                        System.out.println("\nOutput String of Moore is : "+st);
                        st=ml.process2(s);
                        System.out.println("\nOutput String of Mealy is : "+st);

                }
                if(type==10)                       //MEALEY MACHINE
                {
                        Mealy ml=new Mealy();
                        getMealy(ml);
                        printMealy(ml);
                        Moore mr=ml.mltomr(ml);
                        s=ml.process2(s);
                        System.out.println("\nOutput String is : "+s);
            	}
                if(type==11)
                {                               //CFG 
                        GRAMMAR g=new GRAMMAR();
                        getcfg(g);
                        printgrammar(g);
                        GRAMMAR g1=g.removenull();
                        printgrammar(g1);
                        GRAMMAR g2=g1.removeunit();
                        printgrammar(g2);
                        GRAMMAR g3=g2.removeuseless();
                        printgrammar(g3);
                        sentencialform root=g3.parse(s);         
                        if(root==null)
                                System.out.println("String is not derived by this grammar");  
                        else
                        {
                                System.out.println("String is derived by this grammar\n");  
                                sentencialform current=root;
                                while(current!=null)
                                {
                                        System.out.print(current.s+" --> ");
                                        current=current.child;
                                }
								System.out.println();
                        }
                }
				if(type==12)
				{
						ID current;
						GRAMMAR g=new GRAMMAR();
                        getcfg(g);
                        printgrammar(g);
                        GRAMMAR g1=g.removenull();
                        printgrammar(g1);
                        GRAMMAR g2=g1.removeunit();
                        printgrammar(g2);
                        GRAMMAR g3=g2.removeuseless();
                        printgrammar(g3);
						GRAMMAR ck=new GRAMMAR();
					/*	ck=ck.tochomskey(g3);
						printgrammar(ck); */
						PDA pda=new PDA(g3);
						printpda(pda);
						ID root=pda.process(s);
						if(root==null)
                                System.out.println("String not accepted by PDA");  
                        else
                        {
                                System.out.println("String is accepted by PDA\n");  
                                current=root;
                                while(current!=null)
                                {
                                        System.out.println("(q"+current.state+","+current.input+","+current.stack+")");
                                        current=current.child;
                                }
                        }
				}
                if(type==13)
                {
                        GTG g=new GTG(3);
                        char sigma[]={'a','b','c','\\'};
                        int count_sigma=4;
                        g.graph=new String[g.count_states][g.count_states];

                        g.graph[0][0]=new String("a+b+c");
                        g.graph[0][1]=new String("\\");
                        g.graph[0][2]=new String("c");

                        g.graph[1][0]=new String("c+b");
                        g.graph[1][1]=new String("a");
                        g.graph[1][2]=new String("a");

                        g.graph[2][0]=new String("b");
                        g.graph[2][1]=new String("a+b+c");
                        g.graph[2][2]=new String("\\");

                        for(int i=0;i<g.count_states;i++)
                        {
                                System.out.print(" \tq"+i);
                        }
                        System.out.println("\n");
                        for(int i=0;i<g.count_states;i++)
                        {
                                System.out.print("q"+i);
                                for(int j=0;j<g.count_states;j++)
                                {
                                        System.out.print("\t"+g.graph[i][j]);
                                }
                                System.out.println();
                        }
                        nfa=g.tonfa(sigma,count_sigma);
                        printnfa(nfa);
                }
				if(type==14)
				{
						GRAMMAR ck=new GRAMMAR();
						int which=1;
                         PDA pda=new PDA();
                        if(which==1)
                                getpda1(pda);
                        if(which==2)
                                getpda2(pda);
						if(which==3)
                                getpda3(pda);
						printpda(pda);
                        ck=pda.tocfg();
						printgrammar(ck);
						ck=ck.simplify();
						//getcfg(ck);
						printgrammar(ck);
						ck=ck.removenull();
						printgrammar(ck);
						ck=ck.removeunit();
						printgrammar(ck);
						ck=ck.removeuseless();
						printgrammar(ck);
				}
        }
}
