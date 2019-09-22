package com.shuitu.jdbc.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.shuitu.demo.entity.Coder;
import com.shuitu.jdbc.demo.dao.CoderDao;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberDaoTest {
	
	@Autowired
	CoderDao coderDao;
	
	//本框架做了两件事：
	//1、自动生成SQL语句
	//2、自动ORM映射
	
	
	//Hibernate优点：
	//1、API丰富，可以实现无SQL操作（HQL），为了兼容所有数据库（都会先解释为HQL）
	//再由HQL翻译成SQL(当然，有支持直接执行SQL的API，为了考虑用户需求复杂性)
	//对所有数据库方言都支持得非常不错
	//2、ORM全自动化
	
	
	//MyBatis优点：
	//1、轻量级，性能好
	//2、SQL和Java代码分离（SqlMap,把每一条SQL语句起一名字，作为Map的key保存）
	//get("selectByName")
	
	
	//本框架的目的：
	//1、性能要好，是啥就是啥，不经过二次处理（不对SQL语句进行二次包装）
	//2、单表操作实现NoSQL(只要用JDBC，SQL是不能省，只不过这拼接SQL的过程不要用户自己写了，
	//	由QueryRule来自动生成SQL语句
	//3、ORM零配置实现自动化（利用反射机制，把字段和属性对应上，然后，自动实例化返回结果）
	
	
	//原则：约定优于配置（保证代码健壮性）
	//DAO原则：一个Dao只操作一张表
	
	
	//约定：
	//1、做修改和删除的是根据主键来操作的
	//2、尽量使用单表操作，如果实在要多表操作，可以先把数据查出来放到内存，然后在内存中进行计算
	//3、支持读写分离
	//4、支持分库分表
	//5、ORM支持的类型原则上只认Java八大基本数据类型  + String（为了降低复杂度）
	
	@Test
	public void testSelectCoderByName(){
		try {
			List<Coder> coders = coderDao.selectByName("shuitu");
			System.out.println(JSON.toJSON(coders));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Ignore
	public void testSelectAll(){
		try {
			System.out.println("-------" + JSON.toJSONString(coderDao.selectAll()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testInsertOne(){
		try {
			Coder data = new Coder();
			data.setName("d1c0");
			boolean r = coderDao.insterOne(data);
			if(r){
				System.out.println(data.getId());
			}else{
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testUpdate(){
		try {
			Coder data = new Coder();
			data.setId("BC000000001");
			data.setName("于菲");
			boolean r = coderDao.updataOne(data);
			System.out.println(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Ignore
	public void testDelete(){
		try {
			Coder data = new Coder();
			data.setId("BC000000001");
			boolean r = coderDao.deleteOne(data);
			System.out.println(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
