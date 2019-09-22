package com.shuitu.jdbc.test;

import com.shuitu.demo.entity.Coder;
import com.shuitu.jdbc.framework.EntityOperation;
import com.shuitu.jdbc.framework.QueryRule;
import com.shuitu.jdbc.framework.QueryRuleSqlBulider;

/**
* @author 全恒
*/
public class SqlTest {

	public static void main(String[] args) {
		try {
			EntityOperation<Coder> entityOperation = new EntityOperation<Coder>(Coder.class, "id");
			System.out.println(entityOperation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
