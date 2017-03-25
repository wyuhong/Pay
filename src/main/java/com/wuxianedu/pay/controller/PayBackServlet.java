package com.wuxianedu.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuxianedu.util.PaymentUtil;

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
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String ru_Trxtime = req.getParameter("ru_Trxtime");
		String ro_BankOrderId = req.getParameter("ro_BankOrderId");
		String rb_BankId = req.getParameter("rb_BankId");
		String rp_PayDate = req.getParameter("rp_PayDate");
		String rq_CardNo = req.getParameter("rq_CardNo");
		String rq_SourceFee = req.getParameter("rq_SourceFee");
		String rq_TargetFee = req.getParameter("rq_TargetFee");
		String hmac = req.getParameter("hmac");
		
		//校验签名
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, PaymentUtil.keyValue);
		System.out.println("签名校验结果:"+isValid);
		if(!isValid){//校验是错误的时候, 直接放弃处理
			return ;
		}
		
		System.out.println("订单编号:"+r6_Order);
		System.out.println("支付金额:"+r3_Amt);
		resp.getWriter().print("success");
		synchronized (this) {
			//如果订单状态是已支付, 那么直接跳过
			//select * from t_order where oid= #{r6_Order}, status
			
			//如果是未支付, 那么处理: 发货, 更新状态->已支付
			//2. 更新订单状态, 根据订单编号去update
			//3. 发货(实物+虚拟)
		}
	}
}

