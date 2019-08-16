package com.cqzx.comm.util.sealUtil;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.stream.FileImageOutputStream;

import com.cqzx.comm.util.sealUtil.configuration.SealCircle;
import com.cqzx.comm.util.sealUtil.configuration.SealConfiguration;
import com.cqzx.comm.util.sealUtil.configuration.SealFont;


public class Sealfun {
	public String image(String companyname,String FOOT_TXT) throws Exception{
		 /**
         * 印章配置文件
         */
		SealConfiguration configuration = new SealConfiguration();

        /**
         * 主文字
         */
        SealFont mainFont = new SealFont();
        mainFont.setBold(true);
        mainFont.setFontFamily("楷体");
        mainFont.setMarginSize(8);
        /**************************************************/
        mainFont.setFontText(companyname);
        mainFont.setFontSize(28);
        mainFont.setFontSpace(28.0);
        /**************************************************/
        //mainFont.setFontText("ZHITUWANG CO.LTDECIDDO SH  NANNINGSHI");
        //mainFont.setFontSize(20);
        //mainFont.setFontSpace(15.0);
        /**************************************************/
        /*mainFont.setFontText("欢乐无敌制图网淘宝店专用章");
        mainFont.setFontSize(25);
        mainFont.setFontSpace(12.0);*/

        /**
         * 副文字
         */
        SealFont viceFont = new SealFont();
        viceFont.setBold(true);
        viceFont.setFontFamily("宋体");
        viceFont.setMarginSize(4);
        /**************************************************/
        viceFont.setFontText(FOOT_TXT);
        viceFont.setFontSize(10);
        viceFont.setFontSpace(9.6);
        /**************************************************/
        /*viceFont.setFontText("正版认证");
        viceFont.setFontSize(22);
        viceFont.setFontSpace(12.0);*/

        /**
         * 中心文字
         */
        SealFont centerFont = new SealFont();
        centerFont.setBold(true);
        centerFont.setFontFamily("宋体");
        /**************************************************/
        centerFont.setFontText("★");
        centerFont.setFontSize(80);
        /**************************************************/
        //centerFont.setFontText("淘宝欢乐\n制图网淘宝\n专用章");
        //centerFont.setFontSize(20);
        /**************************************************/
        //centerFont.setFontText("123456789012345");
        //centerFont.setFontSize(20);
        /**************************************************/
        /*centerFont.setFontText("发货专用");
        centerFont.setFontSize(25);

        /**
         * 抬头文字
         */
        SealFont titleFont = new SealFont();
        titleFont.setBold(true);
        titleFont.setFontFamily("宋体");
        titleFont.setFontSize(17);
        /**************************************************/
/*	        titleFont.setFontText("发货专用");
        titleFont.setMarginSize(68);
        titleFont.setFontSpace(10.0);*/
        /**************************************************/
        /*titleFont.setFontText("正版认证");
        titleFont.setMarginSize(68);
        titleFont.setMarginSize(27);*/

        /**
         * 添加主文字
         */
        configuration.setMainFont(mainFont);
        /**
         * 添加副文字
         */
        configuration.setViceFont(viceFont);
        /**
         * 添加中心文字
         */
        configuration.setCenterFont(centerFont);
        /**
         * 添加抬头文字
         */
        //configuration.setTitleFont(titleFont);

        /**
         * 图片大小
         */
        configuration.setImageSize(240);
        /**
         * 背景颜色
         */
        configuration.setBackgroudColor(Color.RED);
        /**
         * 边线粗细、半径
         */
        configuration.setBorderCircle(new SealCircle(3, 112, 112));
        //configuration.setBorderCircle(new SealCircle(3, 140, 100));
        /**
         * 内边线粗细、半径
         */
       // configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
        //configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
        /**
         * 内环线粗细、半径
         */
        //configuration.setInnerCircle(new SealCircle(2, 105, 105));
        //configuration.setInnerCircle(new SealCircle(2, 85, 45));

        //1.生成公章
        try {
        	String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
			path=path.replace('/', '\\'); // 将/换成\
			path=path.replace("file:", ""); //去掉file:
			path=path.replace("classes\\", ""); //去掉class\
			path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
			String os = System.getProperty("os.name");  
			if(os.toLowerCase().startsWith("win")){  
				//System.out.println(os + " can't gunzip");  
			}else{
				path = "/"+path;
			}
			String filename = UUID.randomUUID().toString().replaceAll("-", "")+".png";
            SealUtil.buildAndStoreSeal(configuration, path+filename);
            return path+filename;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        //2.生成私章
        /*SealFont font = new SealFont();
        font.setFontSize(120).setBold(true).setFontText("诸葛孔明");
        SealUtil.buildAndStorePersonSeal(300, 16, font, "印", "C:\\Users\\localhost01\\Desktop\\私章.png");*/
	}
	
	@SuppressWarnings("unused")
	public String image_person(String name) throws Exception{
       
       String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
		path=path.replace('/', '\\'); // 将/换成\
		path=path.replace("file:", ""); //去掉file:
		path=path.replace("classes\\", ""); //去掉class\
		path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
		String os = System.getProperty("os.name");  
		if(os.toLowerCase().startsWith("win")){  
			//System.out.println(os + " can't gunzip");  
		}else{
			path = "/"+path;
		}
		String filename = UUID.randomUUID().toString().replaceAll("-", "")+".png";
		
       //2.生成私章
       SealFont font = new SealFont();
       font.setFontSize(120).setBold(true).setFontText(name).setFontFamily("宋体");
       SealUtil.buildAndStorePersonSeal(300, 16, font, "", path+filename);
       return path+filename;
	}
	
	/**
	 * 生成个人印章，返回字节数组
	 * 两字
	 * 		字号： 40  图片尺寸：120   线条尺寸：10  buildPersonSeal方法中的fixH = 9
	 * 	含“印”  字号： 40  图片尺寸：120   线条尺寸：10  buildPersonSeal方法中的fixH = 9
	 * 三字：
	 * 		字号： 30  图片尺寸：120   线条尺寸：10  buildPersonSeal方法中的fixH = 9
	 * 	含“印”  字号： 40  图片尺寸：120   线条尺寸：10  buildPersonSeal方法中的fixH = 9
	 * 
	 * 四字
	 * 		字号： 40  图片尺寸：120   线条尺寸：10  buildPersonSeal方法中的fixH = 9
	 * @param personName 名称
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] imagePersonSeal(String personName,Integer fontSize) throws Exception{
		 SealFont font = new SealFont();
	     font.setFontSize(fontSize).setBold(true).setFontText(personName).setFontFamily("宋体");
	     byte[] sealBytes = SealUtil.buildBytePersonSeal(120, 10, font, "");
	     return sealBytes;
	}
	
	/**
	 * 生成企业印章  返回字节数组
	 * @param companyname
	 * @param FOOT_TXT
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] imageCompanySeal(String companyname,String FOOT_TXT) throws Exception{
		 /**
        * 印章配置文件
        */
		SealConfiguration configuration = new SealConfiguration();

       /**
        * 主文字
        */
       SealFont mainFont = new SealFont();
       mainFont.setBold(true);
       mainFont.setFontFamily("楷体");
       mainFont.setMarginSize(8);
       /**************************************************/
       mainFont.setFontText(companyname);
       mainFont.setFontSize(28);
       mainFont.setFontSpace(28.0);

       /**
        * 副文字
        */
       SealFont viceFont = new SealFont();
       viceFont.setBold(true);
       viceFont.setFontFamily("宋体");
       viceFont.setMarginSize(10);
       /**************************************************/
       viceFont.setFontText(FOOT_TXT);
       viceFont.setFontSize(18);
       viceFont.setFontSpace(18.0);

       /**
        * 中心文字
        */
       SealFont centerFont = new SealFont();
       centerFont.setBold(true);
       centerFont.setFontFamily("宋体");
       /**************************************************/
       centerFont.setFontText("★");
       centerFont.setFontSize(80);

       /**
        * 抬头文字
        */
       SealFont titleFont = new SealFont();
       titleFont.setFontText("南岸");
       titleFont.setBold(true);
       titleFont.setFontFamily("宋体");
       titleFont.setFontSize(17);

       /**
        * 添加主文字
        */
       configuration.setMainFont(mainFont);
       /**
        * 添加副文字
        */
       configuration.setViceFont(viceFont);
       /**
        * 添加中心文字
        */
       configuration.setCenterFont(centerFont);
       /**
        * 添加抬头文字
        */
//       configuration.setTitleFont(titleFont);

       /**
        * 图片大小
        */
       configuration.setImageSize(240);
       /**
        * 背景颜色
        */
       configuration.setBackgroudColor(Color.RED);
       /**
        * 边线粗细、半径
        */
       configuration.setBorderCircle(new SealCircle(3, 112, 112));
       //configuration.setBorderCircle(new SealCircle(3, 140, 100));
       /**
        * 内边线粗细、半径
        */
      // configuration.setBorderInnerCircle(new SealCircle(1, 135, 135));
       //configuration.setBorderInnerCircle(new SealCircle(1, 135, 95));
       /**
        * 内环线粗细、半径
        */
       //configuration.setInnerCircle(new SealCircle(2, 105, 105));
       //configuration.setInnerCircle(new SealCircle(2, 85, 45));

       //1.生成公章
       byte[] b = null;
       try {
           b = SealUtil.buildByteAndStoreSeal(configuration);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return b;
	}
	
	
	
	public static void main(String[] args) {
		Sealfun sealfun = new Sealfun();
		try {
			byte[] bb = sealfun.imagePersonSeal("重庆",40);
			FileImageOutputStream imageOutput1 = new FileImageOutputStream(new File("f:\\印章测试\\私章2个字.jpeg"));
			imageOutput1.write(bb, 0, bb.length);
			System.out.println("执行完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
