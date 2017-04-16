package com.czm;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mac on 17/4/15.
 */
public class RegexText {
    @Test
    public void testMatch(){
        String qq="736158361";
        String regex="[1-9]\\d{4,14}";
        System.out.println(qq.matches(regex));
        //不能含有6和8
        String regex2="[0-9&&[^68]]+";
        System.out.println("交集:"+qq.matches(regex2));
        //可以含有字母 aw
        String qq2="32aw7458";
        String regex3="[0-9[aw]]+";
        System.out.println("并集:"+qq2.matches(regex3));
    }
    @Test
    public void testSplit(){
        String str="z  s     sdm";
        String regex=" +";
        split(str,regex);
        String path="c://pro/game.exe";
        String regex2="/+";
        split(path,regex2);
        //以注释开始切
        String temp="aa/**ssdd*/dddss";
        String regex3="/\\*.+\\*/";
        split(temp,regex3);
        //叠词切割
        split("mkkkddxsmccd","(\\w)\\1+");
    }
    @Test
    public void testReplace(){
        //数字替换＃
        replace("dd78238dskaj00087hjjk","\\d","#");
        //叠词替换成＃
        replace("mkkkddxsmccd","(\\w)\\1+","#");
        //叠词替换成一个
        replace("mkkkddxsmccd","(\\w)\\1+","$1");
    }
    @Test
    public void testGet(){
        //取出4个字母的
        String s="ming tian ye shi fang jia";
        String regex="\\b\\w{4}\\b";
        Pattern pattern = Pattern.compile(regex);//获取正则表达式对象
        Matcher matcher = pattern.matcher(s);//获取匹配器对象，String的replace split match方法都是这个类实现
//        matcher.matches();指针已经往前走了需要重置
//        matcher.reset();
        while (matcher.find()){//find是指针一个个匹配 matches是全部匹配  lookingAt从头匹配
            sysout(matcher.group());
        }
        //判断是否为ip
        String ip="101.201.140.78:8080/admin";
        String domain="https://163b235v331n445baidu.com";
        String ipregex="(http://|https://)?(\\d{1,3}\\.){3}\\d{1,3}.*";
        sysout("ip:"+ip.matches(ipregex)+" domain:"+domain.matches(ipregex));
        //获取端口
        String regex2=":(\\d{1,5})";
        Matcher matcher1 = Pattern.compile(regex2).matcher(ip);
        if(matcher1.find()) {
            sysout(matcher1.group(1));
        }else{
            if(ip.contains("https")){
                sysout("8443");
            }else{
                sysout("80");
            }
        }
    }
    public String getOneHtml(final String htmlurl) throws IOException
    {
        URL url;
        String temp;
        final StringBuffer sb = new StringBuffer();
        try
        {
            url = new URL(htmlurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String contentType = conn.getContentType();
            String encode="gbk";
            if(contentType.contains("UTF")||contentType.contains("utf")){
                encode="utf-8";
            }
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encode));// 读取网页全部内容
            while ((temp = in.readLine()) != null)
            {
                sb.append(temp);
            }
            in.close();
        }
        catch (final MalformedURLException me)
        {
            System.out.println("你输入的URL格式有问题！请仔细输入");
            me.getMessage();
            throw me;
        }
        catch (final IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        return sb.toString();
    }
    //htmlunit网络爬虫
    @Test
    public void testcase(){
//        String oneHtml = null;
//        try {
//            oneHtml = getOneHtml("http://www.tjwsj.gov.cn/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sysout(oneHtml);

        String str;
        //使用FireFox读取网页
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
        //htmlunit 对css和javascript的支持不好，所以请关闭之
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        HtmlPage page = null;
        try {
            //http://www.tjwsj.gov.cn
            page = webClient.getPage("https://www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        str = page.getTitleText();
        System.out.println(str);
        //关闭webclient
        webClient.closeAllWindows();
//        String str;
//        //创建一个webclient
//        WebClient webClient = new WebClient();
//        //htmlunit 对css和javascript的支持不好，所以请关闭之
//        webClient.getOptions().setJavaScriptEnabled(false);
//        webClient.getOptions().setCssEnabled(false);
//        //获取页面
//        HtmlPage page = null;
//        try {
//            page = webClient.getPage("http://www.tjwsj.gov.cn/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //获取页面的TITLE
//        str = page.getTitleText();
//        System.out.println(str);
//        //获取页面的XML代码
//        str = page.asXml();
//        System.out.println(str);
//        //获取页面的文本
//        str = page.asText();
//        System.out.println(str);
//        //关闭webclient
//        webClient.close();
    }
    @Test
    public void testwebtitle(){
        //获取网页标题
        try {
            URL url=new URL("http://www.tjwsj.gov.cn/");
            InputStreamReader in = new InputStreamReader(url.openStream(), "iso8859-1");
            BufferedReader reader=new BufferedReader(in);
            String line=null;
            String titleregex="<title>(.*)</title>";
            Pattern titlepattern = Pattern.compile(titleregex);
            while((line=reader.readLine())!=null){
                Matcher titlematcher = titlepattern.matcher(line);
                if(titlematcher.find()){
                    String title = titlematcher.group(1);
                    boolean messyCode = isMessyCode(title);
                    sysout(messyCode +"");
                    if(messyCode){
                        sysout(toChinese(title));
                    }else {
                        sysout(title);
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0 ;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++;
            }
        }
        float result = count / chLength ;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }
    }
    public static String toChinese(String msg){
        if(isMessyCode(msg)){
            try {
                String gbkstr = new String(msg.getBytes("iso8859-1"), "gbk");
                if(isMessyCode(gbkstr)){
                    return new String(msg.getBytes("iso8859-1"), "utf-8");
                }
                return gbkstr;
            } catch (Exception e) {
            }
        }
        return msg ;
    }
    @Test
    public void regextext0(){
        String s="aa...a..b..cc...cd...ddd..e";//改为abcde
        String regex="\\.+";//去点
        String s1 = s.replaceAll(regex, "");
        sysout(s1);
        //去重
        sysout(s1.replaceAll("(\\w)\\1+","$1"));
    }
    @Test
    public void regextext1(){
        //ip地址分段排序
        String ip="192.168.3.1;10.2.4.8;2.3.56.3";
        //先补两零,再保留三位
        String s00 = ip.replaceAll("\\d+", "00$0");
        sysout(s00);
        String sss = s00.replaceAll("0+(\\d{3})", "$1");
        sysout(sss);
        String[] split = sss.split(";");
//        Arrays.sort(split);
        //法2
        TreeSet<String> treeSet=new TreeSet<String>();
        for(String s:split){
            treeSet.add(s);
        }
        for(String m:treeSet){
            sysout(m.replaceAll("0+(\\d+)","$1"));
        }
    }
    @Test
    public void regextext2(){
        //邮箱匹配
        String mail="ccc@163.com.cn.cg";
        String regex="[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+";
        sysout(mail.matches(regex)+"");
    }
    public void sysout(String s){
        System.out.println(s);
    }
    public void split(String s,String regex){
        String[] split = s.split(regex);
        for(String a:split){
            sysout(a);
        }
    }
    public void replace(String source,String regex,String des){
       sysout(source.replaceAll(regex,des));
    }
}
