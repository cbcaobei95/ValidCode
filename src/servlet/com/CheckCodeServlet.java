package servlet.com;

//�˶� ��֤������ɴ���ο���csdn
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.ForEach;


@WebServlet(description = "checkcode", urlPatterns = { "/CheckCode" })
public class CheckCodeServlet extends HttpServlet {
	/**
	*
	*/

	private static final long serialVersionUID = 1L;

	private int width = 60;

	private int height = 20;

	private int codeCount = 4;

	/**
	 * xx
	 */
	private int xx = 0;

	private int fontHeight;

	/**
	 * codeY
	 */
	private int codeY;

	char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b','c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p',
			'q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G',
			'H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public void init() throws ServletException {
		String strWidth = this.getInitParameter("width");
		String strHeight = this.getInitParameter("height");
		String strCodeCount = this.getInitParameter("codeCount");
		try {
			if (strWidth != null && strWidth.length() != 0) {
				width = Integer.parseInt(strWidth);
			}
			if (strHeight != null && strHeight.length() != 0) {
				height = Integer.parseInt(strHeight);
			}
			if (strCodeCount != null && strCodeCount.length() != 0) {
				codeCount = Integer.parseInt(strCodeCount);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		xx = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;

	}

	/**
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws java.io.IOException
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D gd = buffImg.createGraphics();
		Random random = new Random();
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);

		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		gd.setFont(font);

		gd.setColor(Color.BLACK);
		gd.drawRect(0, 0, width - 1, height - 1);

		gd.setColor(Color.BLACK);
		for (int i = 0; i < 1; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}

		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(62)]);
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			gd.setColor(new Color(red, green, blue));
			gd.drawString(strRand, (i + 1) * xx, codeY);
			randomCode.append(strRand);
		}
		HttpSession session = req.getSession();
		session.setAttribute("validateCode", randomCode.toString());

		String path = "F:/JavaWeb_workspace/ValidCode/WebContent/resource/CheckCode";
		delAllFile(path);

		//		System.out.println(new File("F:/workspace/XSXK/WebContent").list().length);

		File file = new File(path + "/" + new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date()) + ".jpeg");
		if (file.exists())
			resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		resp.setContentType("image/jpeg");

		ServletOutputStream sos = resp.getOutputStream();

		ImageIO.write(buffImg, "jpeg", sos);
		ImageIO.write(buffImg, "jpeg", file);
		sos.close();
	}

	private static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);
				delFolder(path + "/" + tempList[i]);
				flag = true;
			}
		}
		return flag;
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath);
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static Color getRandomColor() {
		Random ran = new Random();
		Color color = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
		return color;
	}
}
