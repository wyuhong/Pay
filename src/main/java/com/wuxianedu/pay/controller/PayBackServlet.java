package com.wuxianedu.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayBackServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1. 接受易宝的请求参数
		//http://localhost:8080/pay/paybackServelt?r6_Order=12342&money=1234
		String r6_Order = req.getParameter("r6_Order");//订单编号
		String r3_Amt = req.getParameter("r3_Amt");//支付金额
		System.out.println("订单编号:"+r6_Order);
		System.out.println("支付金额:"+r3_Amt);
		resp.getWriter().print("success");
		//2. 更新订单状态, 根据订单编号去update
		synchronized (this) {
			//如果订单状态是已支付, 那么直接跳过
			//select * from t_order where oid= #{r6_Order}, status
			
			//如果是未支付, 那么处理: 发货, 更新状态->已支付
			//2. 更新订单状态, 根据订单编号去update
			//3. 发货(实物+虚拟)
		}
	}
}
