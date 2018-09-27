package org.fkjava.ec.commons.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ValidateCodeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}

	public InputStream getRandomCode() throws IOException {
		// 基础班的时候，实现了生成随机字符验证码，并且字符包括大小写字母、数字，而且不重复
		// 1.生成随机字符
		String randomCode = this.generateRandomCode();

		// 2.把随机验证码放Session
		// 之所以加上类名，是为了避免以后跟其他的业务有冲突
		// getName()得到类的全名
		// getSimpleName() 得到class文件的前缀
		String key = ValidateCodeAction.class.getSimpleName() + ".randomCode";
		ActionContext.getContext().getSession().put(key, randomCode);

		// 3.画图
		// RGB : 红、绿、蓝
		// 3.1.创建一个画布
		BufferedImage image = new BufferedImage(100, 34, BufferedImage.TYPE_INT_RGB);

		// 3.2.创建画笔
		Graphics2D g2d = image.createGraphics();

		// 3.3.设置字体大小、颜色
		Font font = new Font(Font.DIALOG, Font.BOLD, 30);
		// Font font = new Font("宋体", Font.BOLD, 25);
		g2d.setFont(font);

		// 全是0是黑色，白色是255
		Color c = new Color(255, 255, 255);
		g2d.setColor(c);

		// 3.4.把整个画布涂白色，默认是黑色的背景
		// Rectangle 表示矩形
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

		// 3.5.输出黑色的文字
		g2d.setColor(Color.BLACK);

		char[] cs = randomCode.toCharArray();
		// g2d.drawChars(cs, 0, cs.length, 5, image.getHeight() - 5);

		// 3.6.扭曲字符、调整每个字符的间距，甚至还可以设置每个字符的颜色都不同
		for (int i = 0; i < cs.length; i++) {
			int x = 5 + (i * 15);
			int y = image.getHeight() - 8;

			// 旋转画笔的方向
			double theta = random.nextDouble();
			theta = theta % 0.3;
			theta = theta - 0.15;
			g2d.rotate(theta);

			g2d.drawChars(cs, i, 1, x, y);

			g2d.rotate(-theta);
		}

		// 3.7.增加一些干扰的【点】
		for (int i = 0; i < 200; i++) {
			// 增加200个点随机画在图片上
			// 画线的时候，需要起始和结束位置，如果起始和结束相同，就是个点
			int x = random.nextInt(image.getWidth());
			int y = random.nextInt(image.getHeight());
			g2d.drawLine(x, y, x, y);
		}

		// 4.把图片转换为InputStream
		// ByteArrayOutputStream把数据写入一个byte[]
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, "png", out);
		// 获取ByteArrayOutputStream里面的字节数组里面的内容
		byte[] imageData = out.toByteArray();
		// 从字节数组读取数据
		InputStream in = new ByteArrayInputStream(imageData);

		return in;
	}

	// 大部分情况下，其实不会使用static修饰
	// 这里为什么使用static修饰呢？因为Action不是单例的，每个请求都会创建一个Action实例
	// 使用static修饰的时候，这些成员变量只会初始化一次，可以被重用
	private static Random random = new Random();
	private static char[] allChars = new char[62];
	static {
		int i = 0;
		for (char c = 'A'; c <= 'Z'; c++, i++) {
			// 把大写字母放入数组里面
			allChars[i] = c;

			// 小写字母
			allChars[i + 26] = (char) (c + 32);

			// 数字从48开始
			if (i < 10) {
				allChars[i + 52] = (char) (48 + i);
			}
		}
	}

	private String generateRandomCode() {
		// 1.创建一个char[]，长度为4、或者6，表示随机字符的数量
		char[] arr = new char[4];
		// 2.包含大小写字母、数字，所以需要准备一个包含所需要字符的大数组
//		char[] allChars = new char[62];
//		int i = 0;
//		for (char c = 'A'; c <= 'Z'; c++, i++) {
//			// 把大写字母放入数组里面
//			allChars[i] = c;
//
//			// 小写字母
//			allChars[i + 26] = (char) (c + 32);
//
//			// 数字从48开始
//			if (i < 10) {
//				allChars[i + 52] = (char) (48 + i);
//			}
//		}
		// System.out.println(allChars);

		// 3.随机从所有字符数组里面获取字符即可！
//		Random random = new Random();
		for (int j = 0; j < arr.length; j++) {
			// 生成0~62，包含0，不包括62
			int x = random.nextInt(allChars.length);
			char c = allChars[x];
			arr[j] = c;
		}
		// 把字符数组转换为String返回
		return new String(arr);
	}

	public static void main(String[] args) {
		int x = '0';
		System.out.println(x);

		ValidateCodeAction a = new ValidateCodeAction();
		String str = a.generateRandomCode();
		System.out.println(str);
	}
}
