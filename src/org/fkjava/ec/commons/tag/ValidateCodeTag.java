package org.fkjava.ec.commons.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ValidateCodeTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		// 获取JSP里面的out内置对象
		try {
			JspWriter out = super.pageContext.getOut();
			// 获取ContextPath
			String contextPath = super.pageContext.getServletContext().getContextPath();
			out.println("<div class=\"form-group\">");
			out.println("	<label for=\"inputValidateCode\" class=\"col-sm-3 control-label\">图片验证码</label>");
			out.println("	<div class=\"col-sm-9\">");
			out.println("		<div class=\"input-group\">");
			out.println("			<input type=\"text\" ");
			out.println("				class=\"form-control\" ");
			out.println("				id=\"inputValidateCode\" ");
			out.println("				name=\"imageValidateCode\"");
			out.println("				placeholder=\"请输入右边图片中的文字\"/>");
			out.println("			<div class=\"input-group-addon\">");
			out.println("				<img class=\"validate-code-image\" src=\"" + contextPath
					+ "/commons/validateCode.action\"");
			out.println("					onclick=\"regetImage()\"/>");
			out.println("				<script type=\"text/javascript\">");
			out.println("					var regetImage = function(){");
			out.println("						var url = '" + contextPath
					+ "/commons/validateCode.action?_=' + Math.random();");
			out.println("						$('img.validate-code-image').attr('src', url);");
			out.println("					};");
			out.println("				</script>");
			out.println("			</div>");
			out.println("		</div>");
			out.println("	</div>");
			out.println("</div>");

			// 忽略标签的内容体
			return TagSupport.SKIP_BODY;
		} catch (IOException e) {
			throw new JspException("标签库出现问题：" + e.getLocalizedMessage(), e);
		}
	}
}
