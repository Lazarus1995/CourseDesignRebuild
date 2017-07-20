package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;


public class EncodeFilter implements Filter{

	private String requestEncode="utf-8";
	private String responseEncode="utf-8";



	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

		requestEncode=filterConfig.getServletContext().getInitParameter("requestencode");
		responseEncode=filterConfig.getServletContext().getInitParameter("responseencode");
	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;

		httpServletResponse.setContentType("text/html;charset="+responseEncode);

		httpServletRequest.setCharacterEncoding(requestEncode);

		chain.doFilter(new MyRequest(httpServletRequest), httpServletResponse);
	}


	public void destroy() {
		// TODO Auto-generated method stub

	}


	class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request=null;

		private boolean flag=true;

		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request=request;
		}

		@Override
		public String getParameter(String name) {
			String values[] =getParameterMap().get(name);
			return values==null?null:values[0];
		}
		@Override
		public Map<String, String[]> getParameterMap() {

			try {

				//���get��������
				if("GET".equals(request.getMethod())&&!"POST".equals(request.getMethod())){
					Map<String,String[]> paramMap=request.getParameterMap();
					if(flag){
						for(Map.Entry<String, String[]> entry :paramMap.entrySet()){
							String values[]= entry.getValue();
							for(int i=0;i<values.length;i++){
								values[i]=new String(values[i].getBytes("ISO8859-1"),requestEncode);
								//System.out.println("����������");
							}
						}
						flag=false;
					}
					return paramMap;

				}
				//����������ύ��ʽ
				else{
					return request.getParameterMap();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		@Override
		public String[] getParameterValues(String name) {
			String values[] =getParameterMap().get(name);
			return values==null?null:values;
		}
		
	}
}
