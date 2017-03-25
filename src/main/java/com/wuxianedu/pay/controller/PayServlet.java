package com.wuxianedu.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxianedu.util.PaymentUtil;

public class PayServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1. 准备13个参数和签名
		String p0_Cmd = "Buy";
		String p1_MerId = PaymentUtil.p1_MerId;
		String p2_Order = req.getParameter("oid");
		String p3_Amt = req.getParameter("money");
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		//FIXME 等下改
		String p8_Url = "http://localhost:8080/pay/paybackservlet";
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = req.getParameter("yh");//支付渠道
		String pr_NeedResponse = "1";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, PaymentUtil.keyValue);
		
		String url = "https://www.yeepay.com/app-merchant-proxy/node";
		StringBuilder sb = new StringBuilder();
		sb.append("?p0_Cmd=").append(p0_Cmd);
		sb.append("&p1_MerId=").append(p1_MerId);
		sb.append("&p2_Order=").append(p2_Order);
		sb.append("&p3_Amt=").append(p3_Amt);
		sb.append("&p4_Cur=").append(p4_Cur);
		sb.append("&p5_Pid=").append(p5_Pid);
		sb.append("&p6_Pcat=").append(p6_Pcat);
		sb.append("&p7_Pdesc=").append(p7_Pdesc);
		sb.append("&p8_Url=").append(p8_Url);
		sb.append("&p9_SAF=").append(p9_SAF);
		sb.append("&pa_MP=").append(pa_MP);
		sb.append("&pd_FrpId=").append(pd_FrpId);
		sb.append("&pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&hmac=").append(hmac);
		
		String requestUrl = url + sb;
		//易宝支付的请求地址 + 参数串?abc=123&asd=sdaf
		//2. 重定向到易宝支付的地址, 并带上这13个参数和签名
		resp.sendRedirect(requestUrl);
	}
}
