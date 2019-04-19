import java.io.*; 
import java.util.*; 
//only extra subject marks manipulation added by Ishaan on 18/3 8:30 AM
public class finalstep
{
    public static void main() throws Exception 
    { 
        String str="";String str1="";String str2="";String str3="";
        int k=0;int c=0;int m=0;int j1=0;
        File re = new File("result.txt"); 
        Scanner sc = new Scanner(re); 
        FileWriter fw=new FileWriter("output.txt"); 
        while (sc.hasNextLine()) 
        {
            str=sc.nextLine();  
            for (int i = 0; i < str.length(); i++) 
            {
                if(str.charAt(i)=='/')
                {
                    k=k+1;
                }
            }   
        } 
        sc = new Scanner(re);
        String s[]=new String[k/2];
        String s1[]=new String[k/2];
        while (sc.hasNextLine()) 
        {
            str=sc.nextLine();  
            for (int i = 0; i < str.length(); i++) 
            {
                
                if(str.charAt(i)=='/')
                {
                    if(c%2==0)
                    {
                        s[m]=str.substring(i+1,i+6);
                        str1=sc.nextLine();int j=0;j1=0;
                        while(true)
                        {
                            if((int)str1.charAt(j)==9&&(int)str1.charAt(j)!=34)
                            {
                                str2=str1.substring(0,j);
                                break;
                            }
                            if((int)str1.charAt(j)==34)
                            {
                                str2=str1.substring(1,str1.length());
                                str1=sc.nextLine();
                                while(true)
                                {
                                    if((int)str1.charAt(j1)==34)
                                    {
                                        str3=str1.substring(0,j1);
                                        break;
                                    }
                                    j1++;
                                }
                                str2=str2+" "+str3;
                                break;
                            }
                            j++;
                        
                    }
                        s1[m]=str2;
                        m++;
                    
                }
                c++;
            }   
        }

    }   
        int esm[][]=new int[k/2][4];
        for(int i=0;i<k/2;i++)
        {
            for(int j=0;j<4;j++)
             esm[i][j]=0;
        }
    int subcode=0;
    sc = new Scanner(re);
    int esc[]={0,0,0,0};
    int ar[][]=new int[k/2][8];int b;int a=0;
    while (sc.hasNextLine()) 
        {
            str=sc.nextLine();  
            b=0;
            for (int i = 0; i < str.length(); i++) 
            {
                 if(str.charAt(i)=='('&&str.charAt(i+1)=='1')
                {
                  subcode=Character.getNumericValue(str.charAt(i-1));
                  //System.out.println(subcode +" ");
                  if(subcode==1)esc[0]++;
                  else if(subcode==2)esc[1]++;
                  else if(subcode==3)esc[2]++;
                  else esc[3]++;
                }
                if(str.charAt(i)=='|'&&(((int)(str.charAt(i-1))>=65 &&((int)str.charAt(i-1)<=90))||(int)str.charAt(i-1)==43))
                {
                    if(((int)(str.charAt(i-1))>=65 &&((int)str.charAt(i-1)<=90)))
                    {
                        ar[a][b]=74-(int)str.charAt(i-1);
                    }
                    else
                    {
                        ar[a][b]=10;
                    }
                    if(b==5)
                    esm[a][subcode-1]=ar[a][5];
                    b++;
                }
      
              
            }
         
            if(b==8)
            {
                a++;
            }
        }
       //Code to print extra subject marks
       /* for(int i=0;i<k/2;i++)
        {
            for(int j=0;j<4;j++)
             {
                 if(esm[i][j]!=0)
                 System.out.print(esm[i][j]+" ");
                }
            System.out.println();
                
        }*/
        double eav[][]=new double[2][4];
        int av;
        double gpai[]=new double[k/2];
         for(int i=0;i<k/2;i++)
        {
            gpai[i]=(4*ar[i][0])+(1.5*ar[i][1])+(4*ar[i][2])+(1.5*ar[i][3])+(4*ar[i][4])+(4*ar[i][6])+(2*ar[i][7]);
            
            if(esm[i][0]!=0) gpai[i]+=esm[i][0]/10;
            else if(esm[i][1]!=0) gpai[i]+=esm[i][1];
            else if(esm[i][2]!=0) gpai[i]+=esm[i][2];
            else if(esm[i][3]!=0) gpai[i]+=esm[i][3];
            gpai[i]/=22;
            int temp=(int)(gpai[i]*100.0);
            gpai[i]=(double)temp/100.0;
            
        }
        //now convert grades to marks for extra subject
        for(int i=0;i<k/2;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(esm[i][j]!=0)
                    esm[i][j]=((esm[i][j]-1)*10)+5;
            }
        }
        //calculate extra subject average
        for(int i=0;i<4;i++)
        {
            av=0;
            for(int j=0;j<k/2;j++)
            {
                av+=esm[j][i];
            }
            av/=esc[i];
            eav[0][i]=av;
            
        }
        //calculating standard deviation of extra subject
        double sd;
        for(int i=0;i<4;i++)
        {
            av=0;
            for(int j=0;j<k/2;j++)
            {
                if(esm[j][i]!=0)
                av+=Math.pow((esm[j][i]-eav[0][i]),2);
            }
            av/=(esc[i])-1;
            sd=Math.sqrt(av);
            eav[1][i]=sd;
        }
        //calculating initial gpa
        double ar1[][]=new double[2][8];double y=0;
        
        //converting grades to marks
          for(int i=0;i<k/2;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(j!=1)
                {
                ar[i][j]=((ar[i][j]-1)*10)+5;
                if(ar[i][j]==35)
                ar[i][j]=40;
                }
            }
        }
        //caculating average of subjects
        for(int i=0;i<8;i++)
        {
            y=0;
            for(int j=0;j<k/2;j++)
            {
                y+=ar[j][i];
            }
            y/=(k/2);
            ar1[0][i]=y;
        }
        //calculate sd of subjects
      
        for(int i=0;i<8;i++)
        {
            y=0;
            for(int j=0;j<k/2;j++)
            {
                y+=Math.pow((ar[j][i]-ar1[0][i]),2);
            }
            y/=(k/2)-1;
            sd=Math.sqrt(y);
            ar1[1][i]=sd;
        }
        for(int i=0;i<2;i++)
        {
            //print average of all subjects
            for(int j=0;j<8;j++)
            {
              //  System.out.print(ar1[i][j]+"\t");
            }
             //System.out.println();}
   
         
          for(i=0;i<k/2;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(j!=5&&j!=1)//adjustment j!=1 is added so that chemistry lab marks are absolute
                   {
                    double marks=ar[i][j];
                    double avg=ar1[0][j];
                    double sde=ar1[1][j];
                    if(marks>=(avg+sde*1.5))
                        ar[i][j]=10;
                    else if(marks<(avg+sde*1.5)&&marks>=(avg+sde*0.5))
                        ar[i][j]=9;
                    else if(marks<(avg+sde*0.5)&&marks>=avg)
                        ar[i][j]=8;
                    else if(marks<avg&&marks>=(avg-sde*0.5))
                        ar[i][j]=7;
                    else if(marks<(avg-sde*0.5)&&marks>=(avg-sde*1.5))
                        ar[i][j]=6;
                    else if(marks<(avg-sde*1.5)&&marks>=(avg-sde*2))
                        ar[i][j]=5;
                    else
                        ar[i][j]=4;
                    }
               if(j==5)
                {
                    //extra subject marks manipulation according to standard deviation
                    int k1;
                    for(k1=0;k1<4;k1++)
                    {
                        if(esm[i][k1]!=0)break;
                    }
                    //k1++;
                    double marks=esm[i][k1];
                    double avg=eav[0][k1];
                    double sde=eav[1][k1];
                    if(marks>=(avg+sde*1.5))
                        esm[i][k1]=10;
                    else if(marks<(avg+sde*1.5)&&marks>=(avg+sde*0.5))
                        esm[i][k1]=9;
                    else if(marks<(avg+sde*0.5)&&marks>=avg)
                        esm[i][k1]=8;
                    else if(marks<avg&&marks>=(avg-sde*0.5))
                        esm[i][k1]=7;
                    else if(marks<(avg-sde*0.5)&&marks>=(avg-sde*1.5))
                        esm[i][k1]=6;
                    else if(marks<(avg-sde*1.5)&&marks>=(avg-sde*2))
                        esm[i][k1]=5;
                    else
                        esm[i][k1]=4;
                    
                }
            }
            
        }
        double gpa[]=new double[k/2];
        for(i=0;i<k/2;i++)
        {
            gpa[i]=(4*ar[i][0])+(1.5*ar[i][1])+(4*ar[i][2])+(1.5*ar[i][3])+(4*ar[i][4])+(4*ar[i][6])+(2*ar[i][7]);
            
            if(esm[i][0]!=0) gpa[i]+=esm[i][0];
            if(esm[i][1]!=0) gpa[i]+=esm[i][1];
            if(esm[i][2]!=0) gpa[i]+=esm[i][2];
            if(esm[i][3]!=0) gpa[i]+=esm[i][3];
            gpa[i]/=22;
            int temp=(int)(gpa[i]*100.0);
            gpa[i]=(double)temp/100.0;
            
        }
        String ss="";
        fw.write("Roll No."+"\t"+"Name"+"\t"+"\t"+"GPA");
        fw.write('\n');
        for(i=0;i<k/2;i++)
            {
                ss="BTECH/"+s[i]+"/18"+'\t'+s1[i]+'\t'+'\t';
                for ( int j = 0; j < ss.length(); j++) 
            {
                fw.write(ss.charAt(j));
            }
          String gpi="";
           String gp="";
           gp=""+gpa[i];
           gpi=""+gpai[i];
           
                for(int j=0;j<gpi.length();j++)
                {
                   fw.write(gpi.charAt(j)); 
                 
                }
                fw.write('\t');
                 for(int j=0;j<gp.length();j++)
                {
                   fw.write(gp.charAt(j)); 
                 
                }
             /*String es=""+esm[i][0]+" "+esm[i][1]+" "+esm[i][2]+" "+esm[i][3];
             for(int j=0;j<es.length();j++)
             fw.write(es.charAt(j));*/
              
            fw.write('\n');
            
            }
             fw.close();
}
}
}
