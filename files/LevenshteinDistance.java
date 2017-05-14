package com.EditDistance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rzx on 2017/5/4.
 */
public class LevenshteinDistance {

    public static int computDistance(char [] str1 ,int str1len,char [] str2 , int str2len){
        int cost;
        if(str1len == 0)
            return str2len;
        if(str2len == 0)
            return str1len;
        if(str1[str1len-1]==str2[str2len-1]) {
            cost = 0;
        }else{
            cost = 1;
        }
        return Math.min(computDistance(str1,str1len-1,str2,str2len)+1,Math.min(computDistance(str1,str1len,str2,str2len-1)+1,computDistance(str1,str1len-1,str2,str2len-1)+cost));

    }
    public static int computDistanct(char [] str1,char [] str2){
        int rowlen = str1.length + 1 ;
        int columnlen = str2.length + 1 ;
        int [][] d = new int [rowlen][columnlen];
        for(int i = 0; i < rowlen; i++){
            for(int j = 0; j < columnlen; j++){
                d[i][j] = 0;
            }
        }

        for(int l = 1; l < rowlen ; l++){
            d[l][0] = l;
        }
        for(int m = 1; m < columnlen ; m++){
            d[0][m] = m;
        }
        int substitutionCost;
        for (int j = 1;j < columnlen;j++){
            for (int i = 1;i< rowlen;i++){
                if(str1[i-1] == str2[j-1]) {
                    substitutionCost = 0;
                }else {
                    substitutionCost = 1;
                }
                d[i][j] = Math.min(d[i-1][j] + 1,Math.min(d[i][j-1] + 1,d[i-1][j-1] + substitutionCost));
//                System.out.print(d[i][j] + "\t");
            }
//            System.out.println();
        }
        return d[rowlen-1][columnlen-1];
    }

    public static int compute(String s,String t){
        if(s.equals(t)){
            return 0;
        }
        if(s.length()==0) {
            return t.length();
        }
        if(t.length()==0){
            return s.length();
        }
        int [] v0 = new int[t.length()+1];
        int [] v1 = new int[t.length()+1];
        int cost;
        for(int i = 0 ;i <v0.length;i++)
            v0[i] = i;

        for(int i = 0 ;i <s.length();i++){
            v1[0] = i + 1;
            for(int j = 0;j<t.length();j++){

                cost = s.charAt(i)==t.charAt(j)?0:1;

                v1[j+1] = Math.min(Math.min(v1[j] + 1,v0[j + 1] + 1),v0[j] + cost);
            }
            for (int j = 0; j < v0.length; j++)
                v0[j] = v1[j];
        }
          return v1[t.length()];
    }


    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        //set the point key to 5
        int key = 2;
        int acuu = 0;
        //get whitelist
        Set<String> set = new HashSet<String>();
        Set<String> set1 = new HashSet<String>();
        BufferedReader whitedomin = null;
        BufferedReader brblack = null;
        BufferedReader brDns = null;
        try {
            whitedomin = new BufferedReader(new FileReader("whitedomin.txt"));
            brblack = new BufferedReader(new FileReader("black.txt"));
            brDns = new BufferedReader(new FileReader("dns2"));
            String line = null;
            while((line = brblack.readLine())!=null){
                set.add(line);
            }
            line = null;
            while((line = whitedomin.readLine())!=null){
                set.add(line);
            }

            line = null;

            String tempDns = "";
            while((line = brDns.readLine())!=null){
                int flag = 1;
               for(String ends : set){
                    if(line.endsWith("."+ends)||line.equals(ends)){
                        flag = 0;
                        break;
                    }
                }
                if(flag==0){
                   continue;
                }
                String tline = line.replaceAll("www\\.","");
//                System.out.println(line);

                int tempM = key;
                for (String dns : set){
                  String templine =  pre(tline,pre(dns));
                  if(templine==null){
                      continue;
                  }
                   int m = compute(dns,templine);
                   if (tempM > m){
                       tempM = m ;
                       tempDns = dns;
                   }
                }
                if(tempM<key&&tempM>0){
//                    System.out.printf("i guess this dns %s is a fake dns %s for cheating! %n ",line,tempDns);
                    System.out.println(line);
                    acuu++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            brDns.close();
        }

        System.out.println(System.currentTimeMillis()-start);
        System.out.println(acuu);
        //get dns and check the dns
//
//
//
//        String str1 = "www.baidu.com.cn";
//        String str2 = "www.baidu.com.cn";
//        char [] a1 = str1.toCharArray();
//        char [] a2 = str2.toCharArray();
//        int pressure = 1;
//
////        for (int i = 0 ;i <pressure;i++){
////            int k = computDistance(a1,a1.length,a2,a2.length);
////
////            System.out.println(k);
////        }
////        System.out.println(System.currentTimeMillis()-start);
//        int j = 0;
//        for (int i = 0 ;i <pressure;i++){
//            j = computDistanct(a1,a2);
////            System.out.println(j);
//        }
//        System.out.printf("result %d ;cost %dms %n",j,System.currentTimeMillis()-start);
//        start = System.currentTimeMillis();
//        int m = 0;
//        for (int i = 0 ;i <pressure;i++){
//            m = compute(str1,str2);
//            System.out.println(m);
//        }
//        System.out.printf("result %d ;cost %dms %n",m,(System.currentTimeMillis()-start));
    }
    public static  int pre(String str){
        return str.split("\\.").length;
    }
    public static  String pre(String str,int len){
        String [] temp = str.split("\\.");
        StringBuffer sb = new StringBuffer();
        if (temp.length<len){
            return null;
        }else{
            for (int i = temp.length-len;i <temp.length; i++){
                sb.append(temp[i]+".");
            }
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }
}
