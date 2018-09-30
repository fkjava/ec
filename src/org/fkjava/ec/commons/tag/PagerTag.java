package org.fkjava.ec.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.fkjava.ec.commerce.vo.IndexPage;

public class PagerTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int doStartTag() throws JspException {
		// 获取JSP里面的out内置对象
		try {
			JspWriter out = super.pageContext.getOut();
			// 获取ContextPath
//			String contextPath = super.pageContext.getServletContext().getContextPath();

			if (this.url.contains("?")) {
				// url本身包含了参数，使用&连接多个参数
				this.url = this.url + "&number=";
			} else {
				// 使用问号连接URL和参数
				this.url = this.url + "?number=";
			}

			// 从页面中查找page对象
			// 相当于${ page }
			// IndexPage page = (IndexPage) super.pageContext.findAttribute("page");
			// 相当于${ requestScope.page }
			IndexPage page = (IndexPage) super.pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
			int number = page.getNumber();
			out.println("<div class=\"row\" style=\"text-align: center;\">");
			out.println("	<nav aria-label=\"Page navigation\">");
			out.println("	    <ul class=\"pagination\">");
			out.println("	        <li>");
			// 上一页确保最少只能到达第一页，0
			out.println(
					"	            <a href=\"" + this.url + (number == 0 ? 0 : number - 1) + "\" aria-label=\"上一页\">");
			out.println("	                <span aria-hidden=\"true\">&laquo;</span>");
			out.println("	            </a>");
			out.println("	        </li>");

			// 假设当前是是第3页，number为2，减去2得到开始为0
			// 假设当前是第5页，number为4，减去2得到开始2
			// 假设当前为第1页，number0，减去2得到-2
			int start = page.getNumber() - 2;
			if( start < 0 ) {
				start = 0;
			}
			//int end = page.getTotalPages() - 1;
			// 要5个按钮
			int end = start + 4;
			if( end > page.getTotalPages() - 1) {
				// 计算超过多少个按钮
				int diff = end - (page.getTotalPages() - 1);
				
				// 不要超出最大页码
				end = page.getTotalPages() - 1;
				// 开始的序号往左边挪
				start = start - diff;
			}
			if( start < 0 ) {
				start = 0;
			}
			for (int i = start; i <= end; i++) {
				//out.println("	        <li><a href=\"" + this.url + i + "\">" + (i + 1) + "</a></li>");
				out.print("<li");
				if(i == page.getNumber()) {
					// 当前页
					out.print(" class='active'");
				}
				out.println("><a href=\"" + this.url + i + "\">" + (i + 1) + "</a></li>");
			}

			out.println("	        <li>");
			// 下一页确保最多只能到达最后一页，总页数-1
			out.println("	            <a href=\"" + this.url
					+ (number >= page.getTotalPages() - 1 ? page.getTotalPages() - 1 : number + 1)
					+ "\" aria-label=\"下一页\">");
			out.println("	                <span aria-hidden=\"true\">&raquo;</span>");
			out.println("	            </a>");
			out.println("	        </li>");
			out.println("	    </ul>");
			out.println("	</nav>");
			out.println("</div>");

			// 忽略标签的内容体
			return TagSupport.SKIP_BODY;
		} catch (IOException e) {
			throw new JspException("标签库出现问题：" + e.getLocalizedMessage(), e);
		}
	}
}
