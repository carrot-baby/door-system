package com.example.shell.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

@Service
public class ShellService {



    String[] start=new String[]{"sh","/root/hjh/shell-test/read/door.sh","start","","door-basic-parameter9001-1.0-SNAPSHOT.jar"};
    String[] stop=new String[]{"sh","/root/hjh/shell-test/read/door.sh","stop",""};

    String[] test=new String[]{"sh","/root/hjh/shell-test/write/door.sh","stop","9001"};

    Random random=new Random();
    int port=0;
    public String test(){
        try{
                Runtime runtime = Runtime.getRuntime();
                Process pro = runtime.exec(test);
                System.out.println("执行启动逻辑");
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuffer strbr = new StringBuffer();
            String line;
            while ((line = br.readLine())!= null)
            {
                strbr.append(line).append("\n");
            }

            String result = strbr.toString();
            System.out.println(result);

        }
        catch (IOException ec)
        {
            ec.printStackTrace();
        }
        return "test....";
    }
    public String start(){
        try{
            port=random.nextInt(65535);
            start[3]= port+"";
            Runtime runtime = Runtime.getRuntime();
            Process pro = runtime.exec(start);
            System.out.println("执行启动逻辑");
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuffer strbr = new StringBuffer();
            String line;
            while ((line = br.readLine())!= null)
            {
                strbr.append(line).append("\n");
            }

            String result = strbr.toString();
            System.out.println(result);

        }
        catch (IOException ec)
        {
            ec.printStackTrace();
        }
        return "start....";
    }
    public String stop(){
        try{



            stop[3]= port+"";
            Runtime runtime = Runtime.getRuntime();
            Process pro = runtime.exec(stop);
            System.out.println("执行终止逻辑");
            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            StringBuffer strbr = new StringBuffer();
            String line;
            while ((line = br.readLine())!= null)
            {
                strbr.append(line).append("\n");
            }

            String result = strbr.toString();
            System.out.println(result);

        }
        catch (IOException ec)
        {
            ec.printStackTrace();
        }
        return "stop....";
    }

//    public boolean updateShell(){
//        //写入文件
////方法2 在单独的进程中执行指定命令和变量。
////第一个变量是sh命令，第二个变量是需要执行的脚本路径，从第三个变量开始是我们要传到脚本里的参数。
//        try{
//            if(!flag){
//                flag=true;
//                Runtime runtime = Runtime.getRuntime();
//                Process pro = runtime.exec(readStart);
//                System.out.println("执行启动逻辑");
//            }
//            Runtime runtime = Runtime.getRuntime();
//            Process pro = runtime.exec(readStop);
//            System.out.println("执行停止逻辑");
//            int status = pro.waitFor();
//            if (status != 0)
//            {
//              return false;
//            }
//            BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//            StringBuffer strbr = new StringBuffer();
//            String line;
//            while ((line = br.readLine())!= null)
//            {
//                strbr.append(line).append("\n");
//            }
//
//            String result = strbr.toString();
//            System.out.println(result);
//
//        }
//        catch (IOException ec)
//        {
//            ec.printStackTrace();
//        }
//        catch (InterruptedException ex){
//            ex.printStackTrace();
//
//        }
//return true;
//    }
}
