package org.springblade.common.webservice.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class IpFilter extends AbstractPhaseInterceptor<Message> {

	//@Value("${wsipfilter.ipls}")
	private List<String> ipList;

	//@Value("${wsipfilter.enable}")
	private boolean isFilter;

	public IpFilter() {
		super(Phase.RECEIVE);//定义在哪个阶段进行拦截
	}


	@Override
	public void handleMessage(Message message) throws Fault {
		//String ruleIp = "192.168.1.1";//自定义IP规则，我这里写死了
		if (isFilter) {
			HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
			String ipAddress = "";
			boolean flag = false;
			if (null != request) {
				ipAddress = getUsrIPAddr(request);//获取客户端的IP地址
				System.out.println("获取到客户端的IP地址是:" + ipAddress);
				for (String s : ipList) {
					if (s.equals(ipAddress)) {
						flag = true;
						break;
					}
				}
				//flag = ruleIp.equals(ipAddress);
			}
			if (!flag) {
				System.out.println("IP address " + ipAddress + " is not allowed");
				throw new Fault(new IllegalAccessException("IP address " + ipAddress + " is not allowed"));
			}
			System.out.println("IP authentication passed");
		}
	}

	/**
	 * 获取客户端ip地址
	 *
	 * @param request
	 * @return
	 */
	public String getUsrIPAddr(HttpServletRequest request) {
		String ip = "";
		//1.首先考虑有反向代理的情况，如果有代理，通过“x-forwarded-for”获取真实ip地址
		ip = request.getHeader("x-forwarded-for");
		//2.如果squid.conf的配制文件forwarded_for项默认是off，则：X-Forwarded-For：unknown。考虑用Proxy-Client-IP或WL-Proxy-Client-IP获取
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		//3.最后考虑没有代理的情况，直接用request.getRemoteAddr()获取ip
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		//4.如果通过多级反向代理，则可能产生多个ip，其中第一个非unknown的IP为客户端真实IP（IP按照','分割）
		if (ip != null && ip.split(",").length > 1) {
			ip = (ip.split(","))[0];
		}
		//5.如果是服务器本地访问，需要根据网卡获取本机真实ip
		if ("127.0.0.1".equals(ip)) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				System.out.println(e.getMessage());//获取服务器(本地)ip信息失败
				return "";
			}
		}
//        6.校验ip的合法性，不合法返回""
		if (!isValidIp(ip)) {
			return "The ip is invalid.";
		} else {
			return ip;
		}
	}

	/**
	 * 判断是否为合法IP地址
	 *
	 * @param ipAddress
	 * @return
	 */
	private boolean isValidIp(String ipAddress) {
		boolean retVal = false;
		try {
			if (ipAddress != null && !"".equals(ipAddress)) {
				Pattern pattern = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");
				retVal = pattern.matcher(ipAddress).matches();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retVal;
	}

}
